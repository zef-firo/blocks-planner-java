package com.zeffiro.blocks;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import com.zeffiro.exceptions.IllegalBlockException;

public class TableStatus {

    private HashMap<String, Block> arrangement;

    public TableStatus() {
        this.arrangement = new HashMap<String, Block>();
    }

    public TableStatus(HashMap<String, Block> arr) {

        HashMap<String, Block> clonedArr = new HashMap<String, Block>();

        for (String lbl: arr.keySet()) {

            if (clonedArr.containsKey(lbl) || !arr.get(lbl).isFree()) {
                continue;
            }

            Block cloned = new Block(lbl);
            clonedArr.put(cloned.getLabel(), cloned);

            Block toCloneUnder = arr.get(lbl).getUnder();
            Block over = cloned;

            while (toCloneUnder != null) {

                Block clonedUnder = new Block(toCloneUnder.getLabel());
                clonedUnder.setOver(over);
                clonedArr.put(clonedUnder.getLabel(), clonedUnder);

                over = clonedUnder;
                toCloneUnder = toCloneUnder.getUnder();

            }

        }

        this.arrangement = clonedArr;
    }

    /**
     * Adds a block to the status
     * @param b the block to add
     * @throws IllegalBlockException if table condition are violated
     */
    public void addBlock(Block b) throws IllegalBlockException {

        //check if block underneath has me on top
        if (b.getUnder() != null && !b.getUnder().getOver().equals(b)) {
            throw new IllegalBlockException("Underneath block mistmatch.");
        }

        //check if block over has me underneath
        if (b.getOver() != null && !b.getOver().getUnder().equals(b)) {
            throw new IllegalBlockException("Over block mistmatch.");
        }

        //check if block is present yet
        if (this.arrangement.containsKey(b.getLabel())) {
            throw new IllegalBlockException("Block is present yet on table.");
        }

        this.arrangement.put(b.getLabel(), b);

    }

    /**
     * Add more blocks at once
     * @param arr an HashMap containing the blocks
     * @throws IllegalBlockException if table condition are violated 
     */
    public void addBlocks(HashMap<String, Block> arr) throws IllegalBlockException {

        for (String key: arr.keySet()) {
            this.addBlock(arr.get(key));
        }

    }

    /**
     * Get the current arrangement
     * @return the HashMap containing all the blocks
     */
    public HashMap<String, Block> getArrangement() {
        return this.arrangement;
    }

    @Override
    public String toString() {

        HashMap<String, Block> printed = new HashMap<>();
        StringBuilder toReturn = new StringBuilder();

        for (String key: this.getArrangement().keySet()) {

            if (printed.containsKey(key)) {
                continue;
            }

            //check for blocks on table
            if (this.arrangement.get(key).getUnder() == null) {
                toReturn.append(this.arrangement.get(key).getLabel() + " | ");
                printed.put(key, this.arrangement.get(key));

                Block over = this.arrangement.get(key).getOver();
                while (over != null) {
                    toReturn.append(over.getLabel() + " | ");
                    printed.put(over.getLabel(), over);
                    over = over.getOver();
                }

                toReturn.append("\r\n");

            }

        }

        return toReturn.toString();

    }

    public Integer diff(TableStatus other) {

        Integer diff = 0;
        for (String lbl : this.getArrangement().keySet()) {

            Block thisB = this.getArrangement().get(lbl);

            //check for block existance in other object
            Block otherB = other.getArrangement().get(lbl);
            if (otherB == null) {
                diff++;
                continue;
            }

            //check if under and over is the same
            if ( otherB.getOver() != null && thisB.getOver() != null && otherB.getOver().equals(thisB.getOver()) ) {
                diff++;
            }
            if ( otherB.getUnder() != null && thisB.getUnder() != null && otherB.getUnder().equals(thisB.getUnder()) ) {
                diff++;
            }

        }

        TableStatus otherCopy = new TableStatus(other.getArrangement());
        otherCopy.getArrangement().keySet().removeAll(this.getArrangement().keySet());

        return diff + otherCopy.getArrangement().size();

    }

    public static TableStatus randomGen(int size) throws IllegalBlockException {

        TableStatus toRet = new TableStatus();
        HashMap<String, Block> freeBlocks = new HashMap<>();

        for (int i = 0; i < size; i++) {
            //generate a new block
            Block b = new Block(String.valueOf(i));
            //random choose if stay on table or on another block
            if (ThreadLocalRandom.current().nextInt(0, 2) > 0 && freeBlocks.size() > 0) {
                //position on block
                //randomly choose a block
                int lblPos = ThreadLocalRandom.current().nextInt(0, freeBlocks.size());
                String lblVal = (String)freeBlocks.keySet().toArray()[lblPos];
                Block t = freeBlocks.get(lblVal);
                t.setOver(b);
                //remove t from freeblocks
                freeBlocks.remove(lblVal);
            }

            freeBlocks.put(b.getLabel(), b);
            toRet.addBlock(b);

        }
        
        return toRet;

    }

    @Override
    public boolean equals(Object other) {
        return this.toString().equals(other.toString());
    }

    @Override
    public int hashCode() {
        String mapping = this.toString();
        int hash = 7;
        for (int i = 0; i < mapping.length(); i++) {
            hash = hash * 31 + mapping.charAt(i);
        }
        return hash;
    }


}
package com.zeffiro.blocks;

import java.util.HashMap;
import com.zeffiro.exceptions.*;

public class TableStatus {

    private HashMap<String, Block> arrangement;

    public TableStatus() {
        this.arrangement = new HashMap<String, Block>();
    }

    public void addBlock(Block b) throws IllegalBlockException {

        //check if block underneath has me on top
        if(b.getUnder()!=null && !b.getUnder().getOver().equals(b)) {
            throw new IllegalBlockException("Underneath block mistmatch.");
        }

        //check if block over has me underneath
        if(b.getOver()!=null && !b.getOver().getUnder().equals(b)) {
            throw new IllegalBlockException("Over block mistmatch.");
        }

        //check if block is present yet
        if(this.arrangement.containsKey(b.getLabel())) {
            throw new IllegalBlockException("Block is present yet on table.");
        }

        this.arrangement.put(b.getLabel(), b);

    }

    public void addBlocks(HashMap<String, Block> arr) throws IllegalBlockException {

        for(String key: arr.keySet()) {
            this.addBlock(arr.get(key));
        }

    }

}
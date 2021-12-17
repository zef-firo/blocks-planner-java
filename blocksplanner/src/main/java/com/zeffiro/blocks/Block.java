package com.zeffiro.blocks;

public class Block {

    private Block under;
    private Block over;
    private String label;

    public Block(String label) {
        this.setOver(null);
        this.setUnder(null);
        this.label = label;
    }

    /**
     * Sets the block over this one
     * @param over the block to put over
     */
    public void setOver(Block over) {
        this.over = over;
        if (this.over != null) {
            this.over.setUnder(this);
        }
    }

    /**
     * Sets the block under this one
     * @param under the block under
     */
    public void setUnder(Block under) {
        this.under = under;
    }

    /**
     * Get if this block is on table
     * @return wether the block is on table or not
     */
    public boolean isOnTable() {
        return this.under == null;
    }

    /**
     * Get if block has no block over itself
     * @return wether the block is clean or not
     */
    public boolean isFree() {
        return this.over == null;
    }

    /**
     * Get the block over
     * @return the block over itself
     */
    public Block getOver() {
        return this.over;
    }

    /**
     * Get the block under
     * @return the block under itself
     */
    public Block getUnder() {
        return this.under;
    }

    /**
     * Get the name of the block
     * @return the string containing the block label
     */
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean equals(Object other) {
        Block otherB = (Block)other;
        return this.label == otherB.getLabel();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < this.label.length(); i++) {
            hash = hash * 31 + this.label.charAt(i);
        }
        return hash;
    }

    @Override
    public String toString() {
        return this.label;
    }

}
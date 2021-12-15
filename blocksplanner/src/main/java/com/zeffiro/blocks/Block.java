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

    public void setOver(Block over) {
        this.over = over;
        if (this.over != null) {
            this.over.setUnder(this);
        }
    }

    private void setUnder(Block under) {
        this.under = under;
    }

    public boolean isOnTable() {
        return this.under == null;
    }

    public boolean isFree() {
        return this.over == null;
    }

    public Block getOver() {
        return this.over;
    }

    public Block getUnder() {
        return this.under;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean equals(Object other) {
        Block otherB = (Block)other;
        return this.label == otherB.getLabel();
    }

}
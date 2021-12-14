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
        over.setUnder(this);
        this.over = over;
    }

    public void setUnder(Block under) {
        under.setOver(this);
        this.under = under;
    }

    public boolean isOnTable() {
        return this.under==null;
    }

    public boolean isFree() {
        return this.over==null;
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
package com.zeffiro.operations;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;

public class MoveOn extends Operation {
    
    public static String name = "Move on block";

    private Block operatingBlockTo;

    public MoveOn(TableStatus status, Block b, Block to) {
        super(status, b);
        this.operatingBlockTo = to;
    }

    public static boolean canDo(Block b, Block t) {
        return Operation.canDo(b) && Operation.canDo(t) && b.isFree() && !b.isOnTable() && t.isFree();
    }

    public TableStatus performOperation() throws CannotDoOperationException {
        
        TableStatus pStatus = super.performOperation();
        TableStatus nStatus = new TableStatus(pStatus.getArrangement());

        //move block to another one
        Block from = nStatus.getArrangement().get(this.operatingBlock.getLabel());
        Block to = nStatus.getArrangement().get(this.operatingBlockTo.getLabel());

        from.getUnder().setOver(null);
        to.setOver(from);

        return nStatus;

    }

    @Override
    public String toString() {
        return "Move " + this.operatingBlock.getLabel() + " on " + this.operatingBlockTo.getLabel();
    }

}

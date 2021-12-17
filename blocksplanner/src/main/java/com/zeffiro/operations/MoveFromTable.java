package com.zeffiro.operations;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;

public class MoveFromTable extends Operation {
    
    public static String name = "Move from table";

    private Block operatingBlockTo;

    public MoveFromTable(TableStatus status, Block b, Block to) {
        super(status, b);
        this.operatingBlockTo = to;
    }

    public static boolean canDo(Block b, Block t) {
        return Operation.canDo(b) && Operation.canDo(t) && b.isFree() && b.isOnTable() && t.isFree();
    }

    public TableStatus performOperation() throws CannotDoOperationException {
        
        TableStatus pStatus = super.performOperation();
        TableStatus nStatus = new TableStatus(pStatus.getArrangement());

        //move block from the table
        Block from = nStatus.getArrangement().get(this.operatingBlock.getLabel());
        Block to = nStatus.getArrangement().get(this.operatingBlockTo.getLabel());

        to.setOver(from);

        return nStatus;

    }

    @Override
    public String toString() {
        return  "Move " + this.operatingBlock.getLabel() + " from table to " + this.operatingBlockTo.getLabel();
    }

}

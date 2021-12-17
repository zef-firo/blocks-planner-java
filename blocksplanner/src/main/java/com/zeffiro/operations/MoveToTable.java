package com.zeffiro.operations;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;

public class MoveToTable extends Operation {
    
    public static String name = "Move to table";

    public MoveToTable(TableStatus status, Block b) {
        super(status, b);
    }

    public static boolean canDo(Block b) {
        return Operation.canDo(b) && b.isFree() && !b.isOnTable();
    }

    public TableStatus performOperation() throws CannotDoOperationException {
        
        TableStatus pStatus = super.performOperation();
        TableStatus nStatus = new TableStatus(pStatus.getArrangement());

        //move block to table
        Block b = nStatus.getArrangement().get(this.operatingBlock.getLabel());

        b.getUnder().setOver(null);
        b.setUnder(null);

        return nStatus;

    }

    @Override
    public String toString() {
        return "Move " + this.operatingBlock.getLabel() + " to the table.";
    }

}

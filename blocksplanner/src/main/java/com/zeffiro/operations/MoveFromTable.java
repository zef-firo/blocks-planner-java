package com.zeffiro.operations;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;

public class MoveFromTable extends Operation {
    
    public MoveFromTable() {
        super("Move from table");
    }

    public boolean canDo(Block b) {
        return super.canDo(b) && b.isFree() && b.isOnTable();
    }

    public TableStatus performOperation(TableStatus status, String labelFrom, String labelTo) throws CannotDoOperationException {
        
        TableStatus pStatus = super.performOperation(status, labelFrom);

        if (!pStatus.getArrangement().get(labelTo).isFree()) {
            throw new CannotDoOperationException("Destination block is not free! Trying to '" + this + "' on block " + labelFrom + " to " + labelTo);
        }

        TableStatus nStatus = new TableStatus(pStatus.getArrangement());

        //move block from the table
        Block from = nStatus.getArrangement().get(labelFrom);
        Block to = nStatus.getArrangement().get(labelTo);

        to.setOver(from);

        return nStatus;

    }

}

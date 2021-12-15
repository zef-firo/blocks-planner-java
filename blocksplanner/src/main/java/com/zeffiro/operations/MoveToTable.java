package com.zeffiro.operations;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;

public class MoveToTable extends Operation {
    
    public MoveToTable() {
        super("Move to table");
    }

    public boolean canDo(Block b) {
        return super.canDo(b) && b.isFree();
    }

    public TableStatus performOperation(TableStatus status, String label) throws CannotDoOperationException {
        
        TableStatus pStatus = super.performOperation(status, label);
        TableStatus nStatus = new TableStatus(pStatus.getArrangement());

        //move block to table
        Block b = nStatus.getArrangement().get(label);

        b.getUnder().setOver(null);
        b.setUnder(null);

        return nStatus;

    }


}

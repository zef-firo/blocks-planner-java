package com.zeffiro.operations;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;

public class Operation {
    
    public String name;

    public Operation(String name) {
        this.name = name;
    }

    /**
     * Check if this operation is compatible with the block status.
     * @param b the block
     * @return wether the operation can be done or not
     */
    public boolean canDo(Block b) {
        return b != null;
    }

    /**
     * Produce a new status after the application of this operation
     * @param status the previous status
     * @param label the block to operate on
     * @return the new status produced
     * @throws CannotDoOperationException if this operation cannot be performed on the block
     */
    public TableStatus performOperation(TableStatus status, String label) throws CannotDoOperationException {

        Block b = status.getArrangement().get(label);
        if (b == null || !this.canDo(b)) {
            throw new CannotDoOperationException("Cannot to the operation '" + this + "' to block " + label);
        }

        return status;

    }

    @Override
    public String toString() {
        return this.name;
    }

}

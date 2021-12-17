package com.zeffiro.operations;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;

public class Operation {
    
    public static String name = "Operation";

    protected TableStatus operatingStatus;
    protected Block operatingBlock;

    protected Operation(TableStatus status, Block b) {
        this.operatingStatus = status;
        this.operatingBlock = b;
    }

    /**
     * Check if this operation is compatible with the block status.
     * @param b the block
     * @return wether the operation can be done or not
     */
    public static boolean canDo(Block b) {
        return b != null;
    }

    /**
     * Produce a new status after the application of this operation
     * @param status the previous status
     * @param label the block to operate on
     * @return the new status produced
     * @throws CannotDoOperationException if this operation cannot be performed on the block
     */
    public TableStatus performOperation() throws CannotDoOperationException {

        if (this.operatingBlock == null || !Operation.canDo(this.operatingBlock)) {
            throw new CannotDoOperationException("Cannot to the operation '" + this + "' to block " + this.operatingBlock.getLabel());
        }

        return this.operatingStatus;

    }

    @Override
    public String toString() {
        return Operation.name + " performed on " + this.operatingBlock.getLabel();
    }

}

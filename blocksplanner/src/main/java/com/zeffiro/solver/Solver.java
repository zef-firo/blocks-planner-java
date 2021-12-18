package com.zeffiro.solver;

import java.util.HashMap;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.IllegalBlockException;
import com.zeffiro.operations.MoveFromTable;
import com.zeffiro.operations.MoveOn;
import com.zeffiro.operations.MoveToTable;
import com.zeffiro.operations.Operation;

public class Solver {

    protected TableStatus initialStatus;
    protected TableStatus finalStatus;
    private boolean doPrint;

    public Solver() {
        this(true);
    }

    public Solver(boolean doPrint) {
        this.doPrint = doPrint;
        this.populateInitial();
        this.populateFinal();
    }

    protected boolean solve() {
        
        if (this.doPrint()) {
            System.out.println("Initial status is:\r\n\r\n" + this.getInitial());
            System.out.println("\r\nFinal status is is:\r\n\r\n" + this.getFinal());
        }

        return true;
        
    }

    private void populateInitial() {

        Block a = new Block("A");
        Block b = new Block("B");
        Block c = new Block("C");
        Block d = new Block("D");

        a.setOver(b);
        b.setOver(c);

        this.initialStatus = new TableStatus();

        try {
            this.initialStatus.addBlock(a);
            this.initialStatus.addBlock(b);
            this.initialStatus.addBlock(c);
            this.initialStatus.addBlock(d);
        }
        catch (IllegalBlockException ex) {
            System.out.println("Impossibile popolare lo stato iniziale: " + ex.getMessage());
        }

    }

    private void populateFinal() {

        Block a = new Block("A");
        Block b = new Block("B");
        Block c = new Block("C");
        Block d = new Block("D");

        d.setOver(c);
        c.setOver(b);

        this.finalStatus = new TableStatus();

        try {
            this.finalStatus.addBlock(a);
            this.finalStatus.addBlock(b);
            this.finalStatus.addBlock(c);
            this.finalStatus.addBlock(d);
        }
        catch (IllegalBlockException ex) {
            System.out.println("Impossibile popolare lo stato finale: " + ex.getMessage());
        }

    }

    protected HashMap<String, Operation> getPossibleOperations(TableStatus status, Block f, Block t) {

        HashMap<String, Operation> possible = new HashMap<String, Operation>();

        if (MoveFromTable.canDo(f, t)) {
            MoveFromTable nMFT = new MoveFromTable(status, f, t);
            possible.put(nMFT.toString(), nMFT);
        }
        if (MoveOn.canDo(f, t)) {
            MoveOn nMO = new MoveOn(status, f, t);
            possible.put(nMO.toString(), nMO);
        }
        if (MoveToTable.canDo(f)) {
            MoveToTable nMTT = new MoveToTable(status, f);
            possible.put(nMTT.toString(), nMTT);
        }
        
        return possible;

    }

    public TableStatus getInitial() {
        return this.initialStatus;
    }

    public TableStatus getFinal() {
        return this.finalStatus;
    }

    public boolean doPrint() {
        return this.doPrint;
    }
    
}

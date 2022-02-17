package com.zeffiro.solver;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.operations.MoveFromTable;
import com.zeffiro.operations.MoveOn;
import com.zeffiro.operations.MoveToTable;
import com.zeffiro.operations.Operation;

public class Solver {

    public static int MAX_EXECUTION_SECONDS = 10;
    protected TableStatus initialStatus;
    protected TableStatus finalStatus;
    private boolean doPrint;
    protected Instant start;
    protected Instant end;

    public Solver(TableStatus initial, TableStatus goal) {
        this(initial, goal, true);
    }

    public Solver(TableStatus initial, TableStatus goal, boolean doPrint) {
        this.initialStatus = initial;
        this.finalStatus = goal;
        this.doPrint = doPrint;
        this.start = Instant.now();
    }

    public boolean solve() {
        
        if (this.timeIsOut()) {
            return false;
        }

        if (this.doPrint()) {
            System.out.println("Initial status is:\r\n\r\n" + this.getInitial());
            System.out.println("\r\nFinal status is is:\r\n\r\n" + this.getFinal());
        }
        this.end = Instant.now();

        return this.getInitial().equals(this.getFinal());
        
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

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public boolean timeIsOut() {
        this.end = Instant.now();
        return this.getDuration().getSeconds() >= Solver.MAX_EXECUTION_SECONDS;
    }
    
}

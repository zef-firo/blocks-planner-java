package com.zeffiro.solver;

import java.time.Instant;

import com.zeffiro.blocks.TableStatus;

public class IterativeDeepening extends Solver {

    private DepthFirstSolver solver;
    public static int MAX_DEPTH = 1000;

    public IterativeDeepening(TableStatus initial, TableStatus goal) {
        this(initial, goal, true);
    }

    public IterativeDeepening(TableStatus initial, TableStatus goal, boolean doPrint) {
        super(initial, goal, doPrint);
        this.solver = new DepthFirstSolver(initial, goal, doPrint);
    }

    public boolean solve() {

        for (int depth = 0; depth < IterativeDeepening.MAX_DEPTH; depth++) {
            if (this.timeIsOut()) {
                break;
            }    
            if (this.doPrint()) {
                System.out.println("\r\n -- \r\nTRYING WITH DEPTH: " + depth + "\r\n");
            }
            if (this.solver.solve(depth)) {
                this.end = Instant.now();
                return true;
            }
        }
        
        this.end = Instant.now();
        return false;

    }
    
}

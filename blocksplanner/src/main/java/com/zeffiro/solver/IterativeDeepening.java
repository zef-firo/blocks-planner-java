package com.zeffiro.solver;

import com.zeffiro.blocks.TableStatus;

public class IterativeDeepening {

    private DepthFirstSolver solver;

    public IterativeDeepening(TableStatus initial, TableStatus goal) {
        this(initial, goal, true);
    }

    public IterativeDeepening(TableStatus initial, TableStatus goal, boolean doPrint) {
        this.solver = new DepthFirstSolver(initial, goal, doPrint);
    }

    public boolean solve() {

        for (int depth = 0; depth < (this.solver.initialStatus.getArrangement().size() ^ 2); depth++) {
            System.out.println("\r\n -- \r\nTRYING WITH DEPTH: " + depth + "\r\n");
            if (this.solver.solve(depth)) {
                return true;
            }
        }

        return false;

    }
    
}

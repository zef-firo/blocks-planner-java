package com.zeffiro.solver;

public class IterativeDeepening {

    private DepthFirstSolver solver;

    public IterativeDeepening() {
        this.solver = new DepthFirstSolver();
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

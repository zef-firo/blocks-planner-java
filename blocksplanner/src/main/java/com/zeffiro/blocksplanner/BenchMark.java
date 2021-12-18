package com.zeffiro.blocksplanner;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.IllegalBlockException;
import com.zeffiro.solver.AStarSolver;
import com.zeffiro.solver.BreadthFirstSolver;
import com.zeffiro.solver.DepthFirstSolver;
import com.zeffiro.solver.IterativeDeepening;
import com.zeffiro.solver.Solver;

public class BenchMark {

    private Integer limit;
    private Integer step;
    private HashMap<String, HashMap<String, String>> table;

    public BenchMark(int limit, int step) {
        this.limit = limit;
        this.step = step;
        this.table = new HashMap<String, HashMap<String, String>>();
    }
    
    public void perform() {

        int i = this.step;
        while (i <= this.limit) {

            try {
                TableStatus init = TableStatus.randomGen(i);
                TableStatus goal = TableStatus.randomGen(i);

                HashMap<String, Solver> solvers = this.initSolvers(init, goal);
                for (String name : solvers.keySet()) {
                    this.singleBench(name, solvers.get(name), String.valueOf(i));
                }

            } catch (IllegalBlockException e) {
                System.out.println("ERROR: Cannot auto generate statuses.");
            }

            i += this.step;

        }

    }

    private HashMap<String, Solver> initSolvers(TableStatus i, TableStatus g) {

        HashMap<String, Solver> toRet = new HashMap<>();

        toRet.put("DepthFirstSolver", new DepthFirstSolver(i, g, false));
        toRet.put("BreadthFirstSolver", new BreadthFirstSolver(i, g, false));
        toRet.put("AStarSolver", new AStarSolver(i, g, false));
        toRet.put("IterativeDeepening", new IterativeDeepening(i, g, false));

        return toRet;

    }

    public void singleBench(String name, Solver solver, String step) {

        System.out.println("Doing " + name + " at step " + step);

        Instant start = Instant.now();
        boolean solved = solver.solve();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        if (!this.table.containsKey(name)) {
            this.table.put(name, new HashMap<String, String>());
        }

        this.table.get(name).put(step, solved ? timeElapsed.toString() : "OUT");

    }

    public void printTable() {

        //head
        StringBuilder head = new StringBuilder();
        head.append("Solver | ");
        
        int i = this.step;
        while (i <= this.limit) {
            head.append(String.valueOf(i) + " | ");
            i += this.step;
        }

        System.out.println(head.toString() + "\r\n");

        //body
        for (String solver : this.table.keySet()) {

            StringBuilder row = new StringBuilder();
            row.append(solver + " | ");

            for (String step : this.table.get(solver).keySet()) {
                row.append(this.table.get(solver).get(step) + " | ");
            }

            System.out.println(row.toString() + "\r\n");

        }

    }

}

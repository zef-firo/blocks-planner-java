package com.zeffiro.solver;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;
import com.zeffiro.operations.Operation;
import com.zeffiro.tree.WeightedNode;

public class AStarSolver extends Solver {

    private WeightedNode solverTree;
    private WeightedNode solutionLeaf;
    private PriorityQueue<WeightedNode> toExplore;

    public AStarSolver(TableStatus initial, TableStatus goal) {
        super(initial, goal);
        this.toExplore = new PriorityQueue<WeightedNode>();
    }

    public AStarSolver(TableStatus initial, TableStatus goal, boolean doPrint) {
        super(initial, goal, doPrint);
        this.toExplore = new PriorityQueue<WeightedNode>();
    }

    public boolean solve() {

        if (super.solve()) {
            if (this.doPrint()) {
                System.out.println("The initial and final statuses are identical! Nothing to do.");
            }
            return true;
        }
        
        this.solutionLeaf = null;

        //set tree root
        this.solverTree = new WeightedNode(this.initialStatus, 0);
        this.toExplore.add(this.solverTree);

        WeightedNode head = this.toExplore.poll();
        while (this.solutionLeaf == null && head != null) {
            if (this.timeIsOut()) {
                break;
            }    
            this.performSolution(head);
            head = this.toExplore.poll();
        }

        this.end = Instant.now();

        //print solution
        if (this.solutionLeaf == null) {
            if (this.doPrint()) {
                System.out.println("No solution found for the arrangment.");
            }
            return false;
        }
        else {
            if (this.doPrint()) {
                System.out.println("The solution is:\r\n");
                this.printSolution(this.solutionLeaf);
            }
            return true;
        }

    }

    private void printSolution(WeightedNode n) {

        if (n.getParent() != null) {
            this.printSolution((WeightedNode)n.getParent());
        }

        if (n.getPerformedOperation() != null) {
            System.out.println(n.getPerformedOperation());
        }

    }

    private Integer calcAStar(TableStatus st, WeightedNode n) {

        return n.getPathLength() + st.diff(this.finalStatus);

    }

    private void performSolution(WeightedNode n) {

        Collection<Operation> toDo = this.findWhatToDo(n.getCurrentStatus());

        //expand all child WeightedNodes
        for (Operation op : toDo) {
            try {

                TableStatus newStatus = op.performOperation();
                WeightedNode child = new WeightedNode(newStatus, n, op, this.calcAStar(newStatus, n));
                this.toExplore.add(child);

                if (child.getCurrentStatus().equals(this.finalStatus)) {
                    //found the leaf!
                    this.solutionLeaf = child;
                    return;
                }

            }
            catch (CannotDoOperationException ex) {
                System.out.println("\r\nTried to do an impossible operation : " + op);
            }
        }

    }

    /**
     * Finds possible operations to do on a status.
     * @param st The status to look at
     * @return A list of operation to perform
     */
    private Collection<Operation> findWhatToDo(TableStatus st) {

        HashMap<String, Operation> toDo = new HashMap<String, Operation>();

        //scan all WeightedNodes till we find something to do
        for (String nLabel : st.getArrangement().keySet()) {

            //fix our "from" block
            Block f = st.getArrangement().get(nLabel);

            //find our m8 block
            for (String tLabel : st.getArrangement().keySet()) {

                if (nLabel == tLabel) {
                    continue;
                }

                Block t = st.getArrangement().get(tLabel);
                HashMap<String, Operation> toAdd = this.getPossibleOperations(st, f, t);
                
                for (String opS : toAdd.keySet()) {
                    if (!toDo.containsKey(opS)) {
                        toDo.put( opS, toAdd.get(opS) );
                    }
                }

            }

        }

        return toDo.values();

    }   
    
}

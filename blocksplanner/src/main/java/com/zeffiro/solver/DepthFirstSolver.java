package com.zeffiro.solver;

import java.util.Collection;
import java.util.HashMap;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;
import com.zeffiro.operations.Operation;
import com.zeffiro.tree.Node;

public class DepthFirstSolver extends Solver {

    private Node solverTree;
    private Node solutionLeaf;

    private Integer limit;

    public DepthFirstSolver() {
        this(-1);
    }

    public DepthFirstSolver(Integer limit) {
        super();
        this.limit = limit;
    }

    public void solve() {
        super.solve();

        this.solutionLeaf = null;

        //set tree root
        this.solverTree = new Node(this.initialStatus);

        //solve
        this.performSolution(this.solverTree);

        //print solution
        if (this.solutionLeaf == null) {
            System.out.println("No solution found for the arrangment.");
        }
        else {
            System.out.println("The solution is:\r\n");
            this.printSolution(this.solutionLeaf);
        }

    }

    private void printSolution(Node n) {

        if (n.getParent() != null) {
            this.printSolution(n.getParent());
        }

        if (n.getPerformedOperation() != null) {
            System.out.println(n.getPerformedOperation());
        }

    }

    private boolean statusVisitedYet(Node n, TableStatus st) {

        while (n != null && n.getPerformedOperation() != null) {
            if (n.getCurrentStatus().equals(st)) {
                return true;
            }
            n = n.getParent();
        }

        return false;

    }

    private void performSolution(Node n) {

        //check if path is over limit
        if (this.limit > 0 && n.getPathLength() >= this.limit) {
            return;
        }

        Collection<Operation> toDo = this.findWhatToDo(n.getCurrentStatus());

        //apply operations
        for (Operation op : toDo) {
            try {

                TableStatus newStatus = op.performOperation();

                //avoid sussman's anomaly!
                if (this.statusVisitedYet(n, newStatus)) {
                    continue;
                }

                Node child = new Node(newStatus, n, op);

                if (child.getCurrentStatus().equals(this.finalStatus)) {
                    //found the leaf!
                    this.solutionLeaf = child;
                    return;
                }

                this.performSolution(child);
                if (this.solutionLeaf != null) {
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

        //scan all nodes till we find something to do
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

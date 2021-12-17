package com.zeffiro.solver;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;
import com.zeffiro.operations.Operation;
import com.zeffiro.tree.Node;

public class BreadthFirstSolver extends Solver {

    private Node solverTree;
    private Node solutionLeaf;
    private Queue<Node> toExplore;

    public BreadthFirstSolver() {
        super();
        this.toExplore = new LinkedList<Node>();
    }

    public boolean solve() {
        super.solve();

        this.solutionLeaf = null;

        //set tree root
        this.solverTree = new Node(this.initialStatus);
        this.toExplore.add(this.solverTree);

        Node head = this.toExplore.poll();
        while (this.solutionLeaf == null && head != null) {
            this.performSolution(head);
            head = this.toExplore.poll();
        }

        //print solution
        if (this.solutionLeaf == null) {
            System.out.println("No solution found for the arrangment.");
            return false;
        }
        else {
            System.out.println("The solution is:\r\n");
            this.printSolution(this.solutionLeaf);
            return true;
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

    private void performSolution(Node n) {

        Collection<Operation> toDo = this.findWhatToDo(n.getCurrentStatus());

        //expand all child nodes
        for (Operation op : toDo) {
            try {

                TableStatus newStatus = op.performOperation();
                Node child = new Node(newStatus, n, op);
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

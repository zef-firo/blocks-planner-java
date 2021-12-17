package com.zeffiro.tree;

import java.util.ArrayList;

import com.zeffiro.blocks.TableStatus;
import com.zeffiro.operations.Operation;

public class Node {
    
    private Node parent;
    private ArrayList<Node> children;
    private TableStatus currentStatus;
    private Operation performedOperation;

    public Node(TableStatus status) {
        this(status, null, null);
    }

    public Node(TableStatus status, Node parent, Operation pOperation) {
        this.setCurrentStatus(status);
        this.setParent(parent);
        this.setPerformedOperation(pOperation);
        this.children = new ArrayList<Node>();
    }
    
    public Operation getPerformedOperation() {
        return performedOperation;
    }

    private void setPerformedOperation(Operation performedOperation) {
        this.performedOperation = performedOperation;
    }

    public TableStatus getCurrentStatus() {
        return currentStatus;
    }

    private void setCurrentStatus(TableStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Node getParent() {
        return parent;
    }

    private void setParent(Node parent) {
        this.parent = parent;
        if (parent != null) {
            parent.addSon(this);
        }
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public void addSon(Node node) {
        this.children.add(node);
    }

    public Integer getPathLength() {

        Integer toRet = 0;

        Node n = this;
        while (n.getParent() != null) {
            toRet++;
            n = n.getParent();
        }

        return toRet;

    }

}

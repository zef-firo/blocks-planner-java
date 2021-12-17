package com.zeffiro.tree;

import com.zeffiro.blocks.TableStatus;
import com.zeffiro.operations.Operation;

public class WeightedNode extends Node implements Comparable<WeightedNode> {
    
    private Integer weight;

    public WeightedNode(TableStatus status, int weight) {
        super(status);
        this.weight = weight;
    }

    public WeightedNode(TableStatus status, Node parent, Operation pOperation, int weight) {
        super(status, parent, pOperation);
        this.weight = weight;
    }

    public Integer getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(WeightedNode other) {
        return this.getWeight().compareTo(other.getWeight());
    }

}

package com.zeffiro.blocksplanner;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.IllegalBlockException;

public class Solver {

    private TableStatus initialStatus;
    private TableStatus finalStatus;

    public Solver() {
        this.populateInitial();
        this.populateFinal();
    }

    public void solve() {

        System.out.println(this.getInitial());
        System.out.println(this.getFinal());

    }

    private void populateInitial() {

        Block a = new Block("A");
        Block b = new Block("B");
        Block c = new Block("C");
        Block d = new Block("D");

        a.setOver(b);
        b.setOver(c);

        this.initialStatus = new TableStatus();

        try {
            this.initialStatus.addBlock(a);
            this.initialStatus.addBlock(b);
            this.initialStatus.addBlock(c);
            this.initialStatus.addBlock(d);
        }
        catch (IllegalBlockException ex) {
            System.out.println("Impossibile popolare lo stato iniziale: " + ex.getMessage());
        }

    }

    private void populateFinal() {

        Block a = new Block("A");
        Block b = new Block("B");
        Block c = new Block("C");
        Block d = new Block("D");

        d.setOver(c);
        c.setOver(b);
        b.setOver(a);

        this.finalStatus = new TableStatus();

        try {
            this.finalStatus.addBlock(a);
            this.finalStatus.addBlock(b);
            this.finalStatus.addBlock(c);
            this.finalStatus.addBlock(d);
        }
        catch (IllegalBlockException ex) {
            System.out.println("Impossibile popolare lo stato finale: " + ex.getMessage());
        }

    }

    public TableStatus getInitial() {
        return this.initialStatus;
    }

    public TableStatus getFinal() {
        return this.finalStatus;
    }
    
}

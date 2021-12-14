package com.zeffiro.blocksplanner;

import com.zeffiro.blocks.*;
import com.zeffiro.exceptions.IllegalBlockException;

/**
 * Hello world!
 *
 */
public class App 
{

    public TableStatus initialStatus;
    public TableStatus finalStatus;

    public static void main( String[] args )
    {
        
    }

    public void populateInitial() {

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
        catch(IllegalBlockException ex) {
            System.out.println("Impossibile popolare lo stato iniziale: "+ex.getMessage());
        }

    }

    public void populateFinal() {

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
        catch(IllegalBlockException ex) {
            System.out.println("Impossibile popolare lo stato finale: "+ex.getMessage());
        }

    }

}

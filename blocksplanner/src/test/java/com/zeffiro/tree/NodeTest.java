package com.zeffiro.tree;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.IllegalBlockException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeTest {
    
    private TableStatus st;
    private Block a;
    private Block b;
    private Block c;
    private Block d;

    @BeforeEach
    void setUp() throws IllegalBlockException {
        st = new TableStatus();
        a = new Block("A");
        b = new Block("B");
        c = new Block("C");
        d = new Block("D");

        a.setOver(b);
        b.setOver(c);

        st.addBlock(a);
        st.addBlock(b);
        st.addBlock(c);
        st.addBlock(d);
    }
    
    @Test
    public void testInit()
    {
        Node n = new Node(st);
        assertTrue( st.equals(n.getCurrentStatus()), "Test init status match" );
    }

    @Test
    public void testPath()
    {
        Node n = new Node(st);
        Node n2 = new Node(st, n, null);
        assertTrue( st.equals(n2.getParent().getCurrentStatus()), "Test parent status match" );
    }

    @Test
    public void testPathLength()
    {
        Node n = new Node(st);
        Node n2 = new Node(st, n, null);
        assertTrue( n2.getPathLength() == 1, "Test path length" );
    }

}

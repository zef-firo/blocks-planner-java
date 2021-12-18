package com.zeffiro.blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zeffiro.exceptions.IllegalBlockException;

import org.junit.jupiter.api.Test;

public class TableStatusTest {
    
    @Test
    public void testEmptyStatus()
    {
        TableStatus st = new TableStatus();
        assertTrue( st.getArrangement().isEmpty(), "A new status is empty" );
    }

    @Test
    public void testEquals() throws IllegalBlockException
    {
        TableStatus st = new TableStatus();
        TableStatus st2 = new TableStatus();
        Block a = new Block("A");

        st.addBlock(a);
        st2.addBlock(a);

        assertTrue( st.equals(st2), "Two statuses are equal" );
    }

    @Test
    public void testIllegalBlock() throws IllegalBlockException
    {
        TableStatus st = new TableStatus();
        Block a = new Block("A");

        st.addBlock(a);

        Throwable exception = assertThrows(IllegalBlockException.class, () -> st.addBlock(a));
        assertEquals("Block is present yet on table.", exception.getMessage());
    }

    @Test
    public void testDiff() throws IllegalBlockException
    {
        TableStatus st = new TableStatus();
        TableStatus st2 = new TableStatus();
        Block a = new Block("A");
        Block b = new Block("B");

        st.addBlock(a);
        st.addBlock(b);

        assertTrue(st.diff(st2) == st2.diff(st) && st.diff(st2) == 2 && st.getArrangement().size() == 2, "Diff check");
    }

    @Test
    public void pointerIndependance() throws IllegalBlockException
    {
        TableStatus st = new TableStatus();
        Block a = new Block("A");
        Block b = new Block("B");
        st.addBlock(a);
        st.addBlock(b);

        TableStatus st2 = new TableStatus(st.getArrangement());

        a.setOver(b);

        assertTrue(!st.equals(st2), "Pointer independance");
    }

}

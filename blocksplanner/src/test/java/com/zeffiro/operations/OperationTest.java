package com.zeffiro.operations;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.CannotDoOperationException;
import com.zeffiro.exceptions.IllegalBlockException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationTest {
    
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
    public void testCanDo()
    {
        assertTrue( !Operation.canDo(null), "Cannot perform operation on null" );
    }
    
    @Test
    public void testMFTCanDo()
    {
        assertTrue( MoveFromTable.canDo(d, c), "Can do Move From Table" );
    }

    @Test
    public void testMFT() throws CannotDoOperationException
    {
        MoveFromTable mft = new MoveFromTable(st, d, c);
        TableStatus nst = mft.performOperation();
        Block c2 = nst.getArrangement().get("C");
        Block d2 = nst.getArrangement().get("D");
        assertTrue( c2.getOver().equals(d2) , "Move From Table check" );
    }

    @Test
    public void testMOCanDo()
    {
        assertTrue( MoveOn.canDo(c, d), "Can do Move On" );
    }

    @Test
    public void testMO() throws CannotDoOperationException
    {
        MoveOn mo = new MoveOn(st, c, d);
        TableStatus nst = mo.performOperation();
        Block c2 = nst.getArrangement().get("C");
        Block d2 = nst.getArrangement().get("D");
        assertTrue( d2.getOver().equals(c2) , "Move On check" );
    }

    @Test
    public void testMTTCanDo()
    {
        assertTrue( MoveToTable.canDo(c), "Can do Move To Table" );
    }

    @Test
    public void testMTT() throws CannotDoOperationException
    {
        MoveToTable mft = new MoveToTable(st, c);
        TableStatus nst = mft.performOperation();
        Block c2 = nst.getArrangement().get("C");
        assertTrue( c2.isOnTable() , "Move To Table check" );
    }


}

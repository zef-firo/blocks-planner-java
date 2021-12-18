package com.zeffiro.blocks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class BlockTest 
{
    @Test
    public void testSingleBlock()
    {
        Block b = new Block("A");
        assertTrue( b.getOver() == null && b.isFree() && b.getUnder() == null && b.isOnTable(), "Test if single block is free and on table alone" );
    }

    @Test
    public void testEqualsMyself()
    {
        Block a = new Block("A");
        assertTrue( a.equals(a), "I'm equal to myself" );
    }

    @Test
    public void testUnderOver()
    {
        Block a = new Block("A");
        Block b = new Block("B");
        a.setOver(b);
        assertTrue( b.equals(b.getUnder().getOver()), "I'm equal to the block over the block under me" );
    }

    @Test
    public void testHashCode()
    {
        Block a = new Block("A");
        Block b = new Block("B");
        assertTrue( a.hashCode() != b.hashCode(), "Two blocks have different hash codes" );
    }

}

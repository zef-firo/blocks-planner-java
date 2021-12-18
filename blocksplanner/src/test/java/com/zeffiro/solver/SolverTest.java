package com.zeffiro.solver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SolverTest {
    
    @Test
    public void testSolver()
    {
        Solver solver = new Solver();
        assertTrue(solver.solve() , "Test solver" );
    }

    @Test
    public void testDFSolver()
    {
        DepthFirstSolver solver = new DepthFirstSolver();
        assertTrue(solver.solve() , "Test Deep First solver" );
    }
   
    @Test
    public void testBFSolver()
    {
        BreadthFirstSolver solver = new BreadthFirstSolver();
        assertTrue(solver.solve() , "Test Breadth First solver" );
    }

    @Test
    public void testASSolver()
    {
        AStarSolver solver = new AStarSolver();
        assertTrue(solver.solve() , "Test A star solver" );
    }

    @Test
    public void testIteolver()
    {
        IterativeDeepening solver = new IterativeDeepening();
        assertTrue(solver.solve() , "Test A star solver" );
    }

}

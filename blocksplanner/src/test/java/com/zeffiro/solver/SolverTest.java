package com.zeffiro.solver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zeffiro.blocks.TableStatus;
import com.zeffiro.blocksplanner.App;
import com.zeffiro.exceptions.IllegalBlockException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolverTest {
    
    private TableStatus init;
    private TableStatus goal;

    @BeforeEach
    void setUp() throws IllegalBlockException {
        this.init = App.getTestInitial();
        this.goal = App.getTestFinal();
    }


    @Test
    public void testSolver()
    {
        Solver solver = new Solver(this.init, this.init);
        assertTrue(solver.solve() , "Test solver" );
    }

    @Test
    public void testDFSolver()
    {
        DepthFirstSolver solver = new DepthFirstSolver(this.init, this.goal);
        assertTrue(solver.solve() , "Test Deep First solver" );
    }
   
    @Test
    public void testBFSolver()
    {
        BreadthFirstSolver solver = new BreadthFirstSolver(this.init, this.goal);
        assertTrue(solver.solve() , "Test Breadth First solver" );
    }

    @Test
    public void testASSolver()
    {
        AStarSolver solver = new AStarSolver(this.init, this.goal);
        assertTrue(solver.solve() , "Test A star solver" );
    }

    @Test
    public void testIteolver()
    {
        IterativeDeepening solver = new IterativeDeepening(this.init, this.goal);
        assertTrue(solver.solve() , "Test A star solver" );
    }

}

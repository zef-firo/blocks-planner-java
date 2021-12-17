package com.zeffiro.blocksplanner;

import com.zeffiro.solver.DepthFirstSolver;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        DepthFirstSolver solver = new DepthFirstSolver(2);
        solver.solve();
    }


}

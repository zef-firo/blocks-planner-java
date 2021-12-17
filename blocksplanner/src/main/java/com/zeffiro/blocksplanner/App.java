package com.zeffiro.blocksplanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zeffiro.solver.AStarSolver;
import com.zeffiro.solver.BreadthFirstSolver;
import com.zeffiro.solver.DepthFirstSolver;
import com.zeffiro.solver.IterativeDeepening;

public class App 
{

    public static void main( String[] args )
    {
        App.printWelcome();
        
        String option = "start";
        while (!option.equals("q")) {

            App.printMenu();
            option = App.getInput();
            switch (option) {
                case "1":
                    App.printTitle("DEEP FIRST");
                    DepthFirstSolver deepfirstsolver = new DepthFirstSolver();
                    deepfirstsolver.solve();
                    break;
                case "2":
                    App.printTitle("BREADTH FIRST");
                    BreadthFirstSolver breadthfirstsolver = new BreadthFirstSolver();
                    breadthfirstsolver.solve();
                    break;
                case "3":
                    App.printTitle("A STAR");
                    AStarSolver astarsolver = new AStarSolver();
                    astarsolver.solve();
                    break;
                case "4":
                    App.printTitle("ITERATIVE DEEPENING");
                    IterativeDeepening iterativedeepsolver = new IterativeDeepening();
                    iterativedeepsolver.solve();
                    break;
                default:
                    break;
            }

        }

        App.printGoodbye();

    }

    public static void printTitle(String title) {
        System.out.println("\r\n ∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼ " + title + " ∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼ \r\n\r\n");
    }

    public static void printWelcome() {

        System.out.println("\r\n ∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼ \r\n\r\n");
        System.out.println("Hi! This software is used as an example to try different search approaches in the blocks problem.");

    }

    public static void printGoodbye() {

        System.out.println("\r\n\r\nByyyyyyyyyyyyyyyyyyyyyyyyyye!");
        System.out.println("\r\n ∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼∼ \r\n\r\n");

    }


    public static String getInput() {

        try {
            System.out.println("Please type your preference:");
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            return bufferRead.readLine();
        }
        catch (IOException e)
        {
            System.out.println("\r\nUnable to get user input.\r\n");
            return "";
        }


    }

    public static void printMenu() {
        System.out.println("\r\nChoose one algorithm to execute:\r\n");
        System.out.println("1) Depth first algorithm\r\n");
        System.out.println("2) Breath first algorithm\r\n");
        System.out.println("3) A star algorithm\r\n");
        System.out.println("4) Iterative deepening algorithm\r\n");
        System.out.println("q) exit\r\n");
    }

}

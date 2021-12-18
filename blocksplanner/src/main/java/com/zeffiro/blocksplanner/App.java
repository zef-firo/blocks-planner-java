package com.zeffiro.blocksplanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zeffiro.blocks.Block;
import com.zeffiro.blocks.TableStatus;
import com.zeffiro.exceptions.IllegalBlockException;
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

            try {
                TableStatus init = App.getTestInitial();
                TableStatus goal = App.getTestFinal();

                switch (option) {
                    case "1":
                        App.printTitle("DEEP FIRST");
                        DepthFirstSolver deepfirstsolver = new DepthFirstSolver(init,goal);
                        deepfirstsolver.solve();
                        break;
                    case "2":
                        App.printTitle("BREADTH FIRST");
                        BreadthFirstSolver breadthfirstsolver = new BreadthFirstSolver(init,goal);
                        breadthfirstsolver.solve();
                        break;
                    case "3":
                        App.printTitle("A STAR");
                        AStarSolver astarsolver = new AStarSolver(init,goal);
                        astarsolver.solve();
                        break;
                    case "4":
                        App.printTitle("ITERATIVE DEEPENING");
                        IterativeDeepening iterativedeepsolver = new IterativeDeepening(init,goal);
                        iterativedeepsolver.solve();
                        break;
                    default:
                        break;
                }
                    
            }
            catch (IllegalBlockException ex) {
                System.out.println("ERRORE: Impossibile popolare gli stati di test: " + ex.getMessage());
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

    public static TableStatus getTestInitial() throws IllegalBlockException {

        Block a = new Block("A");
        Block b = new Block("B");
        Block c = new Block("C");
        Block d = new Block("D");

        a.setOver(b);
        b.setOver(c);

        TableStatus st = new TableStatus();

        st.addBlock(a);
        st.addBlock(b);
        st.addBlock(c);
        st.addBlock(d);
        
        return st;

    }

    public static TableStatus getTestFinal() throws IllegalBlockException {

        Block a = new Block("A");
        Block b = new Block("B");
        Block c = new Block("C");
        Block d = new Block("D");

        d.setOver(c);
        c.setOver(b);

        TableStatus st = new TableStatus();

        st.addBlock(a);
        st.addBlock(b);
        st.addBlock(c);
        st.addBlock(d);

        return st;

    }


}

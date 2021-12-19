# Java Blocks Planner

This is a Java cli software that plans block rearrangement to apply on an initial blocks position to get the desired arrangement.

----

## How to run

You can run this software executing the `.jar` file directly or using `docker`.

### Jar file

This project is supported by `maven` so make sure to have it installed as well as java running properly.

To build the project

- enter the `blocksplanner` folder
- run the command `mvn install`
- find the `.jar` file inside `target` subfolder
- run it with `java -jar blocksplanner/target/blocksplanner-1.0-SNAPSHOT.jar`

### Docker

Before we start make sure you have docker installed and running.

Then:

- be sure to be in the same folder of `Dockerfile`
- run `docker build -t blocksplanner .` to build the image
- run `docker run -i blocksplanner` to run the image

---

## How it works

Firtly the software asks you wht to do:

<pre>
1) Just a simple test

2) Benchmark!

q) exit

Please type your preference:
</pre>

**To choose an option you have to type the label before the `)` char. For example to choose "exit" you will type "q"**

### Just a simple test

Executes an algorithm of your choise to a predefined blocks configuration *(verified to be solvable by me)*.

If you don't trust me, this is the initial configuration:

<pre>
A | B | C | 
D | 
</pre>

and this is the goal:

<pre>
A | 
D | C | B |
</pre>

*You have to look at this representation as if the floor is the left corner of the screen.*

Anyway, you can choose to test **4** different algorithms to solve the example:

<pre>
1) Depth first algorithm

2) Breath first algorithm

3) A star algorithm

4) Iterative deepening algorithm
</pre>

### Benchmark

I wanted to try this thing so badly, so I complicated things a bit.

The benchmark mode tries to solve *randomly generated* blocks configurations incresing the number of blocks iteratively.

The `limit` and the `step` used to increase the blocks number are chosen by user.

**BEWARE!** As this problems are randomly generated, they could be nearly impossible to solve. For sure one of the algorithms could throw a `java.lang.OutOfMemoryError` error or things like that. To avoid this, each algorithm is coded to last at most *5 sconds*, over than that the problem is declared unsolvable.

The output of the benchmark operation is a table like *this one*:

<pre>
Solver | 2 | 4 | 6 | 8 | 10 | 

IterativeDeepening | PT0.000103625S | PT0.000348458S | PT0.006790208S | OUT | OUT | 

AStarSolver | PT0.000008875S | PT0.000235625S | PT0.009803833S | OUT | OUT | 

BreadthFirstSolver | PT0.000013458S | PT0.000122708S | PT0.003210541S | OUT | OUT | 

DepthFirstSolver | PT0.00001075S | PT0.011760792S | PT0.534384375S | OUT | OUT |
</pre>

where each column tells the number of blocks involved in the problem, and the value of each row is the **solution time**. **OUT** means that the problem is unsolvable in reasonable times.


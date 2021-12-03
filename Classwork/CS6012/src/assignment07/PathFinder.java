package assignment07;

import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class PathFinder<E> {




//
//    class Graph<E> {
//        Set<Node> vertices;
//// various graph methods
//    }

    static class Node {
        char data;
        boolean visited;
        Node cameFrom;
        ArrayList<Node> neighbors;

        Node(){
            visited = false;
            cameFrom = null;
            neighbors = null;
        }
    }

    public static void solveMaze(String inputFile, String outputFile) throws FileNotFoundException {
        File maze = new File(inputFile);
        Scanner sc = new Scanner(maze);
        Node nodes[][];

        Node startNode = null;
        Node goalNode = null;

        int height = sc.nextInt();
        int width = sc.nextInt();

        nodes = new Node[height][width];


        sc.nextLine();
        for (int i = 0; i < height; i++) {

            String string = sc.nextLine();

            for (int j = 0; j < width; j++) {

                char next = string.charAt(j);


                if (next == 'X') {
                    nodes[i][j] = null;
                } else {
                    nodes[i][j] = new Node();
                    nodes[i][j].data = next;

                    if (next == 'S') {
                        startNode = nodes[i][j];
                    } else if (next == 'G') {
                        goalNode = nodes[i][j];
                    }
                }

            }



            }

        for (int i = 1; i < height-1; i++) {
            for (int j = 1; j < width-1; j++) {

                if(nodes[i][j] != null) {

                    ArrayList<Node> neighborSet = new ArrayList<>();

                    neighborSet.add(nodes[i - 1][j]);
                    neighborSet.add(nodes[i + 1][j]);
                    neighborSet.add(nodes[i][j - 1]);
                    neighborSet.add(nodes[i][j + 1]);

                    nodes[i][j].neighbors = neighborSet;
                }

            }
        }

        File solution = new File(outputFile);
        PrintWriter pw = new PrintWriter(solution);

        breadthFirstSearch(startNode, goalNode);

        pw.print(height + " ");
        pw.print(width);
        pw.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(nodes[i][j] == null){
                    pw.print("X");
                }
                else {
                    pw.print(nodes[i][j].data);
                }


            }
            pw.println();

        }
        pw.flush();
    }

    private static void breadthFirstSearch(Node start, Node goal) {

        Queue<Node> Q = new LinkedList<>();
        Q.add(start);

        while (!Q.isEmpty()) {
            Node current = Q.poll();
            if (current.equals(goal)) {
                System.out.println("SUCCESS");
                Node n = goal;
                while(n.cameFrom != start){
                    n.cameFrom.data = '.';
                    n = n.cameFrom;

                }
                return;
            }
            for (Node n : current.neighbors) {
                if (n != null && !n.visited) {
                    Q.add(n);
                    n.cameFrom = current;


                }


            }

            current.visited = true;


        }

    }

}

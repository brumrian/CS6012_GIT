package assignment07;

import java.io.FileNotFoundException;

public class TestPathFinder {

  public static void main(String[] args) {

    /*

     * The below code assumes you have a file "tinyMaze.txt" in your project folder.
     * If PathFinder.solveMaze is implemented, it will create a file "tinyMazeOutput.txt" in your project folder.
     * 
     * REMEMBER - You have to refresh your project to see the output file in your package explorer. 
     * You are still required to make JUnit tests...just lookin' at text files ain't gonna fly.
     *
     */


    try {
      PathFinder.solveMaze("src/assignment07/mazes/tinyMaze.txt", "src/assignment07/mazes/tinyMazeOutput.txt");
      PathFinder.solveMaze("src/assignment07/mazes/bigMaze.txt", "src/assignment07/mazes/bigMazeOutput.txt");
      PathFinder.solveMaze("src/assignment07/mazes/mediumMaze.txt", "src/assignment07/mazes/mediumMazeOutput.txt");
      PathFinder.solveMaze("src/assignment07/mazes/randomMaze.txt", "src/assignment07/mazes/randomMazeOutput.txt");
      PathFinder.solveMaze("src/assignment07/mazes/straight.txt", "src/assignment07/mazes/straightMazeOutput.txt");
      PathFinder.solveMaze("src/assignment07/mazes/turn.txt", "src/assignment07/mazes/turn.txt");



    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}


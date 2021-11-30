package assignment02;

import assignment03.BinarySearchSet;
import assignment04.SortUtil;
import assignment04.aSortUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.*;

public class ContainsTimingExperiment {

  private static final int ITER_COUNT = 100;

  public static void main(String[] args) {
//     you spin me round baby, right round
    long startTime = System.nanoTime();
    while (System.nanoTime() - startTime < 1_000_000_000)
      ;

    try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) { // open up a file writer so we can write
                                                                                // to file.
      Random random = new Random();
      for (int exp = 8; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
        int size = (int) Math.pow(2, exp); // or ..

        // Do the experiment multiple times, and average out the results
        long totalTime = 0;

        for (int iter = 0; iter < ITER_COUNT; iter++) {
          // SET UP!
//          BinarySearchSet<Integer> set = new BinarySearchSet<>();
//          SortedSet<Integer> set = new TreeSet<>();
//          ArrayList<Integer> set = SortUtil.generateAverageCase(500);
          ArrayList<Integer> set = SortUtil.generateAverageCase(size);
//          ArrayList<Integer> set = SortUtil.generateBestCase(size);
//          ArrayList<Integer> set = SortUtil.generateWorstCase(size);
          Comparator comparator = Comparator.naturalOrder();

//          for (int i = 0; i < size; i++) {
//            set.add(i);
//          }
//          int findElement = random.nextInt(size); // This gets me a random int between 0 and size;
//          set.remove(findElement);
          // TIME IT!
          long start = System.nanoTime();
          aSortUtil.quicksort(set, comparator);
          long stop = System.nanoTime();
          totalTime += stop - start;
        }
        double averageTime = totalTime / (double) ITER_COUNT;
        System.out.println(size + "\t" + averageTime); // print to console
        fw.write(size + "\t" + averageTime + "\n"); // write to file.
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
//    Charter charter = new Charter();
//    charter.createChart("QUICK_RANDOMP.tsv", "chart.png");
  }


}

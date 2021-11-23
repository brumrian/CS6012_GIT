import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class timeTest {

        public static void main(String[] args) {
            int time = 0;
            ArrayList<Long> times = new ArrayList<>();
            SortedSet<String> toTest = new TreeSet<>();
           double size = Math.pow(2, 10);
           for(int i = 0; i < size; i++){
               toTest.add("position" + i);
           }

            while(time < 1000000) {
                double result = 0;

                long startTime = System.nanoTime();

               boolean ans = toTest.contains("positionx");

                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime;

                times.add(timeElapsed);

                time++;
//            System.out.println(result + "\n");
            }

            long sum = 0;
            for(long i: times){
                sum += i;

            }

            long average = sum / times.size();
            System.out.println("The average time is... "  + average + " nanoSeconds");

        }



}

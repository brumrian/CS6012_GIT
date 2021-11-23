package lab02;

public class SquareRoots {

    public static void main(String[] args) {
        int time = 0;

        while(time < 100) {
            double result = 0;

            long startTime = System.nanoTime();

            for (int i = 0; i < 10; i++) {
                 result += Math.sqrt(i);
            }

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;

            System.out.println("it took " + timeElapsed + " nanoSeconds /n");
            time++;
//            System.out.println(result + "\n");
        }


    }

}

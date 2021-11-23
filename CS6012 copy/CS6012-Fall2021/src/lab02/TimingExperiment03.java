package lab02;

public class TimingExperiment03 {

  public static void main(String[] args) {
    boolean hitContinue = false;

    long lastTime = System.nanoTime();
    int advanceCount = 0;
    while (advanceCount < 100) {
      long currentTime = System.nanoTime();
      if (currentTime == lastTime) {
        hitContinue = true;
        continue;
      }// I don't think the continue line is ever executed
      System.out.println("Time advanced " + (currentTime - lastTime) + " nanoseconds.");
      lastTime = currentTime;
      advanceCount++;
    }

    System.out.println(hitContinue);
  }
}

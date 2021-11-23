package lab02;

public class TimingExperiment01 {

  public static void main(String[] args) {
    boolean hitContinue = false;
    long lastTime = System.currentTimeMillis();
    int advanceCount = 0;
    while (advanceCount < 100) {
      long currentTime = System.currentTimeMillis();
      if (currentTime == lastTime) {
        hitContinue = true;
        continue;
      }
      System.out.println("Time advanced " + (currentTime - lastTime) + " milliseconds.");
      lastTime = currentTime;
      advanceCount++;
    }
    System.out.println(hitContinue);
  }
}

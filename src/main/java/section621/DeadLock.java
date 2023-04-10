package section621;

import java.util.Random;

/**
 * @author : iyeong-gyo
 * @package : section621
 * @since : 2023/04/10
 */
public class DeadLock {

  public static void main(String[] args) {
    Intersection intersection = new Intersection();
    Thread trainAThread = new Thread(new TrainA(intersection));
    Thread trainBThread = new Thread(new TrainB(intersection));

    trainAThread.start();
    trainBThread.start();
  }

  public static class TrainA implements Runnable {
    private Intersection intersection;
    private Random random = new Random();

    public TrainA(Intersection intersection) {
      this.intersection = intersection;
    }

    @Override
    public void run() {
      int sleepingTime = random.nextInt(5);
      try {
        Thread.sleep(sleepingTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      intersection.takeRoadA();
    }
  }

  public static class TrainB implements Runnable {
    private Intersection intersection;
    private Random random = new Random();

    public TrainB(Intersection intersection) {
      this.intersection = intersection;
    }

    @Override
    public void run() {
      int sleepingTime = random.nextInt(5);
      try {
        Thread.sleep(sleepingTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      intersection.takeRoadB();
    }
  }

  public static class Intersection {

    private Object roadA = new Object();
    private Object roadB = new Object();

    public void takeRoadA() {
      synchronized (roadA) {
        System.out.println("RoadA is locked by thread " + Thread.currentThread().getName());
        synchronized (roadB) {
          System.out.println("Train is passing through road A");
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }

    public void takeRoadB() {
      synchronized (roadB) {
        System.out.println("RoadB is locked by thread " + Thread.currentThread().getName());
        synchronized (roadA) {
          System.out.println("Train is passing through road B");
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

}

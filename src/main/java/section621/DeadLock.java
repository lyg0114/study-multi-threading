package section621;

import java.util.Objects;

/**
 * @author : iyeong-gyo
 * @package : section621
 * @since : 2023/04/10
 */
public class DeadLock {

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

  }

}

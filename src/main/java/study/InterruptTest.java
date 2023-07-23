package study;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/07/22
 */
public class InterruptTest extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      System.out.println(i + 1 + ". before sleep...");
    }

    try {
      sleep(1000);
    } catch (InterruptedException e) {
      System.out.println("InterruptedException 발생");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    InterruptTest t = new InterruptTest();
    t.start();
    t.interrupt();
    t.join();
    System.out.println("Main thread 종료 ...");
  }

}

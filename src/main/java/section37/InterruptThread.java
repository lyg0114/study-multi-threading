package section37;

/**
 * @author : iyeong-gyo
 * @package : section37
 * @since : 2023/04/08
 */
public class InterruptThread {

  public static void main(String[] args) {
    Thread thread = new Thread(new BlockingTask());
    thread.start();
  }

  public static class BlockingTask implements Runnable {
    @Override
    public void run() {
      try {
        Thread.sleep(5000000);
      } catch (InterruptedException e) {
        System.out.println("Exiting blocking thread");
      }
    }
  }

}

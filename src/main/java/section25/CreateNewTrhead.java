package section25;

/**
 * @author : iyeong-gyo
 * @package : section25
 * @since : 2023/04/07
 */
public class CreateNewTrhead {

  public static void main(String[] args) {
    Thread thread = new ygThread();
    thread.start();
    thread.setName("work-0");
  }

  private static class ygThread extends Thread {
    @Override
    public void run() {
      System.out.println("Hello from " + this.getName());
    }
  }
}

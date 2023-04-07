package section24;

/**
 * @author : iyeong-gyo
 * @package : section24
 * @since : 2023/04/07
 */
public class Main2 {

  public static void main(String[] args) {
    Thread thread1 = new Thread(() -> {
      throw new RuntimeException("Interneal Exception");
    });
    thread1.setName("Misbehaving thread");
    thread1.setUncaughtExceptionHandler((r, e) -> {
      e.printStackTrace();
      System.out.println(e.getMessage());
    });
    thread1.start();
  }
}

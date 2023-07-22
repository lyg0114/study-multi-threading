package study;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/07/22 create sum method whcih is calculate sum from one to hundred performing in
 * five differents threads and print on main method.
 */
public class ThreadJoinTest {

  static int sum = 0;

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> sum(1, 20));
    t1.start(); t1.join();
    Thread t2 = new Thread(() -> sum(21, 40));
    t2.start(); t2.join();
    Thread t3 = new Thread(() -> sum(41, 60));
    t3.start(); t3.join();
    Thread t4 = new Thread(() -> sum(61, 80));
    t4.start(); t4.join();
    Thread t5 = new Thread(() -> sum(81, 100));
    t5.start(); t5.join();

    System.out.println("sum = " + sum);
  }

  public static synchronized void sum(int start, int end) {
    for (int i = start; i < end; i++) {
      sum += i;
    }
  }
}

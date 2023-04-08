package section38.example;

/**
 * @author : iyeong-gyo
 * @package : section38
 * @since : 2023/04/08
 */
public class SingleThreadSum {

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    int sum = 0;
    for (int i = 0; i < 100000000; i++) {
      sum += i;
    }
    long endTime = System.currentTimeMillis();
    System.out.println("실행 시간: " + (endTime - startTime) + "ms");
  }
}
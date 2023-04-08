package section38.example;

/**
 * @author : iyeong-gyo
 * @package : section38.example
 * @since : 2023/04/08
 */
public class MultiThreadSum {

  public static void main(String[] args) throws InterruptedException {
    final int numThreads = 4; // 스레드 개수
    long startTime = System.currentTimeMillis();
    Thread[] threads = new Thread[numThreads];
    int[][] ranges = new int[numThreads][2];
    int range = 100000000 / numThreads;
    for (int i = 0; i < numThreads; i++) {
      ranges[i][0] = i * range;
      ranges[i][1] = (i + 1) * range - 1;
      threads[i] = new Thread(new SumThread(ranges[i][0], ranges[i][1]));
      threads[i].start();
    }
    int sum = 0;
    for (int i = 0; i < numThreads; i++) {
      threads[i].join();
      sum += SumThread.partialSum[i];
    }
    long endTime = System.currentTimeMillis();
    System.out.println("실행 시간: " + (endTime - startTime) + "ms");
    System.out.println("결과: " + sum);
  }

  static class SumThread implements Runnable {

    int start, end;
    static int[] partialSum = new int[4];

    SumThread(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public void run() {
      int sum = 0;
      for (int i = start; i <= end; i++) {
        sum += i;
      }
      partialSum[start / (100000000 / 4)] = sum;
    }
  }
}
package study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/06/26
 */
public class LockExample {

  private static class Counter {

    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {
      lock.lock();
      try {
        count++;
      } finally {
        lock.unlock();
      }
    }

    public int getCount() {
      lock.lock();
      try {
        return count;
      } finally {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Counter counter = new Counter();

    // 스레드 1: count 값을 1000번 증가시킴
    Runnable incrementTask = () -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    };

    // 스레드 2: count 값을 1000번 감소시킴
    Runnable decrementTask = () -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    };

    // 스레드 풀에 작업 제출
    executorService.submit(incrementTask);
    executorService.submit(decrementTask);

    // 모든 작업이 완료될 때까지 대기
    executorService.shutdown();
    while (!executorService.isTerminated()) {
    }

    // 최종 count 값을 출력
    System.out.println("Final Count: " + counter.getCount());
  }
}
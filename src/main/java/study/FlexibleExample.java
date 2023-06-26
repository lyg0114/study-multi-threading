package study;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService; import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/06/26
 */
public class FlexibleExample {

  public static void main(String[] args) {
    // 스레드 풀 생성
    ExecutorService executor = Executors.newFixedThreadPool(4);

    // 작업 리스트 생성
    List<Callable<Integer>> tasks = new ArrayList<>();
    tasks.add(() -> {
      // 작업 내용
      Thread.sleep(2000);
      return 1;
    });
    tasks.add(() -> {
      // 작업 내용
      Thread.sleep(1000);
      return 2;
    });
    tasks.add(() -> {
      // 작업 내용
      Thread.sleep(3000);
      return 3;
    });

    try {
      // 작업 제출 및 완료 대기
      List<Future<Integer>> futures = executor.invokeAll(tasks);

      // 작업 결과 처리
      for (Future<Integer> future : futures) {
        if (future.isDone()) {
          int result = future.get();
          System.out.println("작업 결과: " + result);
        }
      }
    } catch (InterruptedException | ExecutionException e) {
      // 예외 처리
      e.printStackTrace();
    }

    // 스레드 풀 종료
    executor.shutdown();
  }
}

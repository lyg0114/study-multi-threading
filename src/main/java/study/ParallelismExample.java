package study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/06/27
 */
public class ParallelismExample {

  public static void main(String[] args) {
    // 병렬 처리를 위한 ExecutorService 생성
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    // Callable을 구현한 작업들 생성
    CallableTask task1 = new CallableTask(1);
    CallableTask task2 = new CallableTask(2);

    try {
      // 작업들을 동시에 실행하고 Future 객체를 통해 결과를 얻음
      Future<Integer> result1 = executorService.submit(task1);
      Future<Integer> result2 = executorService.submit(task2);

      // 작업의 결과를 얻어 출력
      System.out.println("Task 1 result: " + result1.get());
      System.out.println("Task 2 result: " + result2.get());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // ExecutorService 종료
      executorService.shutdown();
    }
  }
}

// Callable 인터페이스를 구현한 작업 클래스
class CallableTask implements Callable<Integer> {

  private int taskId;

  public CallableTask(int taskId) {
    this.taskId = taskId;
  }

  @Override
  public Integer call() throws Exception {
    // 간단한 작업을 시간 지연과 함께 수행
    Thread.sleep(2000);
    return taskId * 2;
  }
}

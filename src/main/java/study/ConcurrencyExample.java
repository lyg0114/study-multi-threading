package study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/06/27
 */
public class ConcurrencyExample {

  public static void main(String[] args) {
    // 병행성을 위한 ExecutorService 생성
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    // Runnable을 구현한 작업들 생성
    RunnableTask task1 = new RunnableTask(1);
    RunnableTask task2 = new RunnableTask(2);

    // 작업들을 동시에 실행
    executorService.execute(task1);
    executorService.execute(task2);

    // ExecutorService 종료
    executorService.shutdown();
  }
}

// Runnable 인터페이스를 구현한 작업 클래스
class RunnableTask implements Runnable {

  private int taskId;

  public RunnableTask(int taskId) {
    this.taskId = taskId;
  }

  @Override
  public void run() {
    // 간단한 작업을 수행
    System.out.println("Task " + taskId + " started");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Task " + taskId + " completed");
  }
}

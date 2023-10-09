package myown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelNumberList {

  public static void main(String[] args) {
    int totalNumbers = 1000;
    int numThreads = 5;
    int numbersPerThread = totalNumbers / numThreads;

    ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
    List<Future<List<Integer>>> futures = new ArrayList<>();

//    for (int i = 0; i < numThreads; i++) {
//      final int start = i * numbersPerThread + 1;
//      final int end = (i == numThreads - 1) ? totalNumbers : (i + 1) * numbersPerThread;
//      Callable<List<Integer>> task = new NumberListTask(start, end);
//      futures.add(executorService.submit(task));
//    }
//
    futures.add(executorService.submit(new NumberListTask(0, 200)));
    futures.add(executorService.submit(new NumberListTask(201, 400)));

    List<Integer> result = new ArrayList<>();

    for (Future<List<Integer>> future : futures) {
      try {
        result.addAll(future.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    executorService.shutdown();

    // 출력
    for (int number : result) {
      System.out.println("number = " + number);
    }
  }
}

class NumberListTask implements Callable<List<Integer>> {

  private int start;
  private int end;

  public NumberListTask(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public List<Integer> call() {
    List<Integer> numbers = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      numbers.add(i);
    }
    return numbers;
  }
}

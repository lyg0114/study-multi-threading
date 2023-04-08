package section38;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : iyeong-gyo
 * @package : section38
 * @since : 2023/04/08
 */
public class CalculateFactorialByMultiProcessing {

  public static void main(String[] args) {

    long before = System.currentTimeMillis();
    List<Long> inputNumbers
        = Arrays.asList(3435L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);
    List<FactorialThread> threads = new ArrayList<>();
    inputNumbers.forEach(i -> threads.add(new FactorialThread(i)));
    threads.forEach(CalculateFactorialByMultiProcessing::executeThread);

    for (int i = 0; i < inputNumbers.size(); i++) {
      FactorialThread factorialThread = threads.get(i);
      if (factorialThread.isFinished()) {
        System.out.println(
            "Factorial of " + inputNumbers.get(i) + " is "
                + factorialThread.getResult());
      } else {
        System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
      }
    }

    long after = System.currentTimeMillis();
    long elapsedTime = after - before; // 실행 시간 계산
    System.out.println("총 실행 시간: " + elapsedTime + "ms");
    System.out.println("########################################################################################");
    System.out.println("########################################################################################");
    System.out.println("########################################################################################");
    System.out.println("########################################################################################");
    System.out.println("########################################################################################");
  }

  private static void executeThread(FactorialThread i) {
    i.setDaemon(true);
    i.start();
    try {
      i.join(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static class FactorialThread extends Thread {

    private long inputNumber;
    private BigInteger result = BigInteger.ZERO;
    private boolean isFinished = false;

    public FactorialThread(long inputNumber) {
      this.inputNumber = inputNumber;
    }

    @Override
    public void run() {
      this.result = factorial(inputNumber);
      this.isFinished = true;
    }

    private BigInteger factorial(long n) {
      BigInteger tempResult = BigInteger.ONE;
      for (long i = n; i > 0; i--) {
        tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
      }
      return tempResult;
    }

    public BigInteger getResult() {
      return result;
    }

    public boolean isFinished() {
      return isFinished;
    }
  }
}

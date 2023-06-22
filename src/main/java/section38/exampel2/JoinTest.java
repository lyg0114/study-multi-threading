package section38.exampel2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinTest {

  public static void main(String[] args) throws InterruptedException {

    StartMultiThread ut = new StartMultiThread();
    StartSingleThread st = new StartSingleThread();

    long startTime = System.nanoTime();
    ut.start();
    long endTime = System.nanoTime();
    long executionTime = endTime - startTime;
    System.out.println("multi Time: " + executionTime + " nanoseconds");

    long startTime2 = System.nanoTime();
    st.start();
    long endTime2 = System.nanoTime();
    long executionTime2 = endTime2 - startTime2;
    System.out.println("single Time: " + executionTime2 + " nanoseconds");

  }

  public static class StartMultiThread {

    public void start() throws InterruptedException {
      List<Long> inputNumbers = Arrays.asList(100000000L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

      List<FactorialThread> threads = new ArrayList<>();

      for (long inputNumber : inputNumbers) {
        threads.add(new FactorialThread(inputNumber));
      }

      for (Thread thread : threads) {
        thread.setDaemon(true);
        thread.start();
      }

      for (Thread thread : threads) {
        thread.join(2000);
      }

//      for (int i = 0; i < inputNumbers.size(); i++) {
//        FactorialThread factorialThread = threads.get(i);
//        if (factorialThread.isFinished()) {
//          System.out .println( "Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
//        } else {
//          System.out .println("The calculation for " + inputNumbers.get(i) + " is still in progress");
//        }
//      }
    }
  }

  public static class StartSingleThread {

    public void start() throws InterruptedException {
      List<Long> inputNumbers = Arrays.asList(100000000L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);
      List<BigInteger> res = new ArrayList<>();

      for (Long inputNumber : inputNumbers) {
        res.add(factorial(inputNumber));
      }

//      for (BigInteger re : res) {
//        System.out.println("re = " + re);
//      }
    }

    public BigInteger factorial(long n) {
      BigInteger tempResult = BigInteger.ONE;
      for (long i = n; i > 0; i--) {
        tempResult = tempResult.multiply(new BigInteger((Long.toString(i))));
      }
      return tempResult;
    }

  }

  public static class FactorialThread extends Thread {

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

    public BigInteger factorial(long n) {
      BigInteger tempResult = BigInteger.ONE;

      for (long i = n; i > 0; i--) {
        tempResult = tempResult.multiply(new BigInteger((Long.toString(i))));
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

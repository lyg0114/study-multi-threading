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
public class CalculateFactorialBySingleProcessing {

  public static void main(String[] args) {
    long before = System.currentTimeMillis();
    List<Long> inputNumbers
        = Arrays.asList(3435L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);
    List<Factorial> factorials = new ArrayList<>();
    inputNumbers.forEach(i -> factorials.add(new Factorial(i)));
    factorials.forEach(Factorial::factorial);
    long after = System.currentTimeMillis();
    long elapsedTime = after - before; // 실행 시간 계산
    System.out.println("총 실행 시간: " + elapsedTime + "ms");
  }


  private static class Factorial {

    private long inputNumber;
    private BigInteger result = BigInteger.ZERO;
    private boolean isFinished = false;

    public Factorial(long inputNumber) {
      this.inputNumber = inputNumber;
    }

    private BigInteger factorial() {
      BigInteger tempResult = BigInteger.ONE;
      for (long i = inputNumber; i > 0; i--) {
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

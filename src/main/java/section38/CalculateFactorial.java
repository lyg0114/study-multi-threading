package section38;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * @author : iyeong-gyo
 * @package : section38
 * @since : 2023/04/08
 */
public class CalculateFactorial {

  public static void main(String[] args) {
    List<Long> inputNumbers
        = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);
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

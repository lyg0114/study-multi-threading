package section37;

import java.math.BigInteger;

/**
 * @author : iyeong-gyo
 * @package : section37
 * @since : 2023/04/08
 */
public class InterruptThread2 {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(
        new LongComputatonTask(new BigInteger("20000"), new BigInteger("1000000000")));
    thread.setName("big");
    thread.start();
    thread.sleep(3000);
    thread.interrupt();
  }

  private static class LongComputatonTask implements Runnable {

    private BigInteger base;
    private BigInteger power;

    public LongComputatonTask(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override
    public void run() {
      System.out.println(base + "^" + power + " =" + pow(base, power));
    }

    private BigInteger pow(BigInteger base, BigInteger power) {
      BigInteger result = BigInteger.ONE;
      for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("current result : " + result);
          return BigInteger.ZERO;
        }
        result = result.multiply(base);
      }
      return result;
    }
  }
}

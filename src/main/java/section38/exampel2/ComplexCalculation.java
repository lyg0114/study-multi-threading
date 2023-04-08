package section38.exampel2;

import java.math.BigInteger;

/**
 * @author : iyeong-gyo
 * @package : section38.exampel2
 * @since : 2023/04/08
 */
public class ComplexCalculation {

  public static void main(String[] args) throws InterruptedException {
    BigInteger result = calculateResult(new BigInteger("2"), new BigInteger("10"),
        new BigInteger("2"), new BigInteger("10"));
    System.out.println("#######################################################");
    System.out.println(result);
    System.out.println("#######################################################");
  }

  public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2,
      BigInteger power2) throws InterruptedException {
    BigInteger result = null;

    PowerCalculatingThread front = new PowerCalculatingThread(base1, power1);
    PowerCalculatingThread after = new PowerCalculatingThread(base2, power2);

    front.start();
    after.start();
    front.join();
    after.join();
    BigInteger result1 = front.getResult();
    BigInteger result2 = after.getResult();
    return result1.add(result2);
  }

  private static class PowerCalculatingThread extends Thread {

    private BigInteger result = BigInteger.ONE;
    private BigInteger base;
    private BigInteger power;

    public PowerCalculatingThread(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    //Implement the calculation of result = base ^ power
    @Override
    public void run() {
      for (BigInteger i = BigInteger.ZERO; i.compareTo(this.power) != 0; i = i.add(BigInteger.ONE)) {
        result = result.multiply(this.base);
      }
      System.out.println("result = " + result);
    }

    public BigInteger getResult() {
      return result;
    }
  }

}

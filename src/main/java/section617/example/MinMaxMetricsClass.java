package section617.example;

import java.util.Random;

/**
 * @author : iyeong-gyo
 * @package : section617.example
 * @since : 2023/04/10
 */
public class MinMaxMetricsClass {

  public static void main(String[] args) {
    MinMaxMetrics minMaxMetrics = new MinMaxMetrics();
    BusinessLogic businessLogicThread1 = new BusinessLogic(minMaxMetrics);
    BusinessLogic businessLogicThread2 = new BusinessLogic(minMaxMetrics);
    MetricsPrinter printer = new MetricsPrinter(minMaxMetrics);

    businessLogicThread1.start();
    businessLogicThread2.start();
    printer.start();
  }

  private static class MetricsPrinter extends Thread {

    private MinMaxMetrics minMaxMetrics;

    public MetricsPrinter(MinMaxMetrics minMaxMetrics) {
      this.minMaxMetrics = minMaxMetrics;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        long max = minMaxMetrics.getMax();
        long min = minMaxMetrics.getMin();
        System.out.println("min = " + min);
        System.out.println("max = " + max);
        System.out.println("########################################");
      }
    }
  }

  private static class BusinessLogic extends Thread {

    private MinMaxMetrics metrics;
    private Random random = new Random();

    public BusinessLogic(MinMaxMetrics metrics) {
      this.metrics = metrics;
    }

    @Override
    public void run() {
      while (true) {
        metrics.addSample(random.nextInt(100));
      }
    }
  }

  private static class MinMaxMetrics {

    private volatile long minValue;
    private volatile long maxValue;

    public MinMaxMetrics() {
      this.maxValue = Long.MIN_VALUE;
      this.minValue = Long.MAX_VALUE;
    }

    public void addSample(long newSample) {
      synchronized (this) {
        this.minValue = Math.min(newSample, this.minValue);
        this.maxValue = Math.max(newSample, this.maxValue);
      }
    }

    public long getMin() {
      return this.minValue;
    }

    public long getMax() {
      return this.maxValue;
    }
  }
}

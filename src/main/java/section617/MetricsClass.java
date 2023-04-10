package section617;

import java.util.Random;

/**
 * @author : iyeong-gyo
 * @package : section617
 * @since : 2023/04/10
 */
public class MetricsClass {

  public static void main(String[] args) {
    Metrics metrics = new Metrics();
    BusinessLogic businessLogicThread1 = new BusinessLogic(metrics);
    BusinessLogic businessLogicThread2 = new BusinessLogic(metrics);
    MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

    businessLogicThread1.start();
    businessLogicThread2.start();
    metricsPrinter.start();

  }

  private static class MetricsPrinter extends Thread {

    private Metrics metrics;

    public MetricsPrinter(Metrics metrics) {
      this.metrics = metrics;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        double currentAverage = metrics.getAverage();
        System.out.println("currentAverage = " + currentAverage);
      }
    }
  }

  private static class BusinessLogic extends Thread {

    private Metrics metrics;
    private Random random = new Random();

    public BusinessLogic(Metrics metrics) {
      this.metrics = metrics;
    }

    @Override
    public void run() {
      while (true) {
        long start = System.currentTimeMillis();

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        long l = end - start;
        metrics.addSample(l);
      }
    }
  }

  private static class Metrics {

    private long count = 0;
    private volatile double average = 0.0;

    public synchronized void addSample(long sample) {
      double currentSum = average * count;
      count++;
      average = (currentSum + sample) / count;
    }

    public double getAverage() {
      return average;
    }
  }
}

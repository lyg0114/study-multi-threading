package section617.example;

/**
 * @author : iyeong-gyo
 * @package : section617.example
 * @since : 2023/04/10
 */
public class MinMaxMetrics {

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

  /**
   * Returns the smallest sample we've seen so far.
   */
  public long getMin() {
    return this.minValue;
  }

  /**
   * Returns the biggest sample we've seen so far.
   */
  public long getMax() {
    return this.maxValue;
  }
}

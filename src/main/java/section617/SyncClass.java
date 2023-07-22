package section617;

/**
 * @author : iyeong-gyo
 * @package : section617
 * @since : 2023/04/10
 */
public class SyncClass {

  public static void main(String[] args) throws InterruptedException {
    InventoryCounter inventoryCounter = new InventoryCounter();
    IncrementThread incrementThread = new IncrementThread(inventoryCounter);
    DecrementintThread decrementintThread = new DecrementintThread(inventoryCounter);

    incrementThread.start();
    decrementintThread.start();

    incrementThread.join();
    decrementintThread.join();

    System.out.println("####################################");
    System.out.println(inventoryCounter.getItems());
    System.out.println("####################################");
  }

  private static class DecrementintThread extends Thread {

    private InventoryCounter inventoryCounter;

    public DecrementintThread(InventoryCounter inventoryCounter) {
      this.inventoryCounter = inventoryCounter;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10000; i++) {
        inventoryCounter.decrement();
      }
    }
  }

  private static class IncrementThread extends Thread {

    private InventoryCounter inventoryCounter;

    public IncrementThread(InventoryCounter inventoryCounter) {
      this.inventoryCounter = inventoryCounter;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10000; i++) {
        inventoryCounter.increment();
      }
    }
  }

  private static class InventoryCounter {

    private int items = 0;
    Object lock = new Object();

    public synchronized void increment() {
      items++;
    }

    public synchronized void decrement() {
      items--;
    }

    public synchronized int getItems() {
      synchronized (this.lock) {
        return items;
      }
    }
  }
}

package section25;

/**
 * @author : iyeong-gyo
 * @package : section25
 * @since : 2023/04/07
 */
public class VaultExample {

  public static final int MAX_PASSORD = 9999;

  public static void main(String[] args) {

  }

  private static class Vault {

    private int password;

    public Vault(int password) {
      this.password = password;
    }

    public boolean isCorrectPassword(int guess) {
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return this.password == guess;
    }
  }

  private static abstract class HackerThread extends Thread {

    protected Vault vault;

    public HackerThread(Vault vault) {
      this.vault = vault;
      this.setName(this.getClass().getSimpleName());
      this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public synchronized void start() {
      System.out.println("Starting thread " + this.getName());
      super.start();
    }
  }

  private static class AscendingHackerThread extends HackerThread {

    public AscendingHackerThread(Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = 0; guess < MAX_PASSORD; guess++) {
        if (vault.isCorrectPassword(guess)) {
          System.out.println(this.getName() + " guessed the password " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class DecendingHackerThread extends HackerThread {

    public DecendingHackerThread(Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = MAX_PASSORD; guess >= 0; guess--) {
        if (vault.isCorrectPassword(guess)) {
          System.out.println(this.getName() + " guessed the password " + guess);
          System.exit(0);
        }
      }
    }
  }


}

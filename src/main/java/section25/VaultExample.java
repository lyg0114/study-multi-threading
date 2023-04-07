package section25;

/**
 * @author : iyeong-gyo
 * @package : section25
 * @since : 2023/04/07
 */
public class VaultExample {

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

}

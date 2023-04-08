package section37;

import java.io.IOException;

/**
 * @author : iyeong-gyo
 * @package : section37
 * @since : 2023/04/08
 */
public class Question1 {

  public static void main(String[] args) {
    Thread thread = new Thread(new WatingForUserInput('q'));
    thread.setName("InputWatingThread - q");
    thread.start();

    Thread thread2 = new Thread(new WatingForUserInput('i'));
    thread2.setName("InputWatingThread - i");
    thread2.start();
  }


  private static class WatingForUserInput implements Runnable {

    private char targetChr;

    public WatingForUserInput(char targetChr) {
      this.targetChr = targetChr;
    }

    @Override
    public void run() {
      try {
        while (true) {
          char input = (char) System.in.read();
          if (input == targetChr) {
            return;
          }
          System.out.println(Thread.currentThread().getName() + " input = " + input);
        }
      } catch (IOException e) {
        System.out.println("An exception was caught " + e);
      }
    }

  }

}

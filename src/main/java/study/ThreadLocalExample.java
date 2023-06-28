package study;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/06/28
 */
public class ThreadLocalExample {

  // 스레드 로컬 변수를 선언합니다.
  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static void main(String[] args) {
    // 첫 번째 스레드를 생성하고 실행합니다.
    Thread thread1 = new Thread(() -> {
      // 스레드 로컬 변수에 값을 설정합니다.
      threadLocal.set("Hello from Thread 1");

      // 스레드 로컬 변수의 값을 출력합니다.
      System.out.println("Thread 1: " + threadLocal.get());

      // 스레드 로컬 변수의 값을 제거합니다.
      threadLocal.remove();
    });

    // 두 번째 스레드를 생성하고 실행합니다.
    Thread thread2 = new Thread(() -> {
      // 스레드 로컬 변수에 값을 설정합니다.
      threadLocal.set("Hello from Thread 2");

      // 스레드 로컬 변수의 값을 출력합니다.
      System.out.println("Thread 2: " + threadLocal.get());

      // 스레드 로컬 변수의 값을 제거합니다.
      threadLocal.remove();
    });

    // 두 개의 스레드를 시작합니다.
    thread1.start();
    thread2.start();
  }
}

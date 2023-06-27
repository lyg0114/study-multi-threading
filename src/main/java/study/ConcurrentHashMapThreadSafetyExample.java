package study;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/06/27
 */

public class ConcurrentHashMapThreadSafetyExample {

  public static void main(String[] args) throws InterruptedException {
    Map<String, Integer> map = getMap();

    // 여러 스레드가 동시에 맵에 요소를 추가하는 작업 수행
    Runnable task = () -> {
      for (int i = 0; i < 1000; i++) {
        map.put(Thread.currentThread().getName() + "-" + i, i);
      }
    };

    // 여러 스레드 생성 및 작업 제출
    Thread thread1 = new Thread(task);
    Thread thread2 = new Thread(task);
    thread1.start();
    thread2.start();

    // 모든 작업이 완료될 때까지 대기
    thread1.join();
    thread2.join();

    // 맵의 크기 확인
    int size = map.size();
    System.out.println("Map size: " + size);
  }

  public static Map<String, Integer> getMap() {
    return new ConcurrentHashMap<>();
//    return new HashMap<>();
  }
}


package study;

/**
 * @author : iyeong-gyo
 * @package : study
 * @since : 2023/06/28
 */
public class MultiInher {

  public static void main(String[] args) {
    Human human = new Human();
    Fish fish = new Fish();

    human.walk();  // Human walking
    human.swim();  // Human swimming

    fish.swim();   // Fish swimming
  }
}

interface Walkable {

  void walk();
}

interface Swimmable {

  void swim();
}

class Human implements Walkable, Swimmable {

  @Override
  public void walk() {
    System.out.println("Human walking");
  }

  @Override
  public void swim() {
    System.out.println("Human swimming");
  }
}

class Fish implements Swimmable {

  @Override
  public void swim() {
    System.out.println("Fish swimming");
  }
}



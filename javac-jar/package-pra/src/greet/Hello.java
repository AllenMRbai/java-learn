package greet;

public class Hello {
  public void say() {
    System.out.println("hello");
    SuperHello sh = new SuperHello();
    sh.say();
  }
}
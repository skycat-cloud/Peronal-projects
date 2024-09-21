import java.util.Scanner;

public class Ex2_11 {

  static Scanner scanner = new Scanner(System.in);

  public static void displayResults(int x, int n) {
    int leftShifted = x << n;
    int expectedLeftShift = x * (int) Math.pow(2, n);
    
    int rightShifted = x >> n;
    int expectedRightShift = x / (int) Math.pow(2, n);

    System.out.printf("[a] x * (2^%d) = %d\n", n, expectedLeftShift);
    System.out.printf("[b] x / (2^%d) = %d\n", n, expectedRightShift);
    System.out.printf("[c] x << %d = %d\n", n, leftShifted);
    System.out.printf("[d] x >> %d = %d\n", n, rightShifted);

    if (expectedLeftShift == leftShifted) {
      System.out.println("[a]와 [c]의 값이 일치합니다.");
    } else {
      System.out.println("[a]와 [c]의 값이 일치하지 않습니다.");
    }

    if (expectedRightShift == rightShifted) {
      System.out.println("[b]와 [d]의 값이 일치합니다.");
    } else {
      System.out.println("[b]와 [d]의 값이 일치하지 않습니다.");
    }
  }

  public static void main(String[] args) {
    int x, n;
    System.out.println("정수 x를 n비트 시프트합니다.");
    System.out.print("x: ");
    x = scanner.nextInt();
    System.out.print("n: ");
    n = scanner.nextInt();
    
    displayResults(x, n);
  }
}


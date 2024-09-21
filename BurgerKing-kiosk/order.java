import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class order {
  static Scanner scanner = new Scanner(System.in);
  
  public static void orderask() {
    int burgerC;
    
    while (true) {
      System.out.println("");
      System.out.println("");
      System.out.print("주문 진행하시겠습니까?(진행:1, 돌아가기:2) : ");
      
      try {
        burgerC = scanner.nextInt();
        
        switch (burgerC) {
          case 1:
            System.out.println("");
            PaymentManager.payment();
            return;  // 주문 진행 후 메서드를 종료
          case 2:
            BurgerMenu.displayBurgerMenu();
            BurgerMenu.orderBurger();
            return;
          default:
            System.out.println("에러가 발생했습니다. 올바른 숫자를 입력해 주세요.");
            try {
              TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
              e.printStackTrace();  // 예외 발생 시 스택 트레이스를 출력합니다.
            }
            System.out.println("");
            System.out.println("");
            // 다시 입력을 받도록 루프를 계속합니다.
        }
        
      } catch (InputMismatchException e) {
        System.out.println("");
        System.out.println("숫자를 입력해주세요.");
        scanner.next(); // 잘못된 입력 제거
      }
    }
  }
}

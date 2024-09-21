import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainMenuSelect {
  static Scanner scanner = new Scanner(System.in);

  public static void mainmenuselect() {
    while (true) {
      try {
        System.out.print("메뉴 번호를 입력해주세요: ");
        int selectnum1 = scanner.nextInt(); // 사용자가 입력한 메뉴 번호를 받음

        switch (selectnum1) {
          case 1 -> { // 주문하기
            System.out.println("");
            System.out.println("");
            BurgerMenu.displayBurgerMenu(); // 버거 메뉴 출력
            System.out.println("");
            BurgerMenu.orderBurger(); // 버거 주문
            return; // 주문 후 메서드 종료
          }

          case 3 -> { // 관리자 메뉴
            System.out.println("");
            System.out.println("");
            AdminMenu.admin(); // 관리자 메뉴 실행
            return; // 관리자 메뉴 실행 후 메서드 종료
          }

          default -> { // 잘못된 숫자 입력 시
            System.out.println("");
            System.out.println("잘못된 숫자입니다. 다시 입력해 주세요.");
            try{
              TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
            }
            BurgerMenu.mainmenu();
            mainmenuselect();
            return;
          }
        }

      } catch (InputMismatchException e) { // 숫자가 아닌 값을 입력할 때 예외 처리
        scanner.next();
        System.out.println("");
        System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
        
        try{
          TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e2){
          }
          BurgerMenu.mainmenu();
          mainmenuselect();
          return;
      }
    }
  }
}


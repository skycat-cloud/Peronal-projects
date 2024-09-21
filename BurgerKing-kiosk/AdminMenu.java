import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AdminMenu { // 메뉴 추가/삭제
  static Scanner scanner = new Scanner(System.in); // Scanner 객체 생성
  static boolean authPassword = false; // 인증 상태를 저장하는 변수

  public static void admin() {
    // 패스워드 인증이 안 되었을 경우에만 패스워드 입력 요청
    if (!authPassword) {
      int password;
      System.out.print("관리자 페이지 입니다. 패스워드를 입력하세요: (1450) (888 입력시 되돌아갑니다) ");

      try {
        password = scanner.nextInt(); // 패스워드 입력
        switch (password) {
          case 888 -> {
            BurgerMenu.initializeMenu(); // 버거 메뉴판 불러옴
            BurgerMenu.mainmenu(); // 키오스크 메인 메뉴
            MainMenuSelect.mainmenuselect(); // 메인 메뉴 선택자
            return; // 888 입력 시 메인 메뉴로 돌아가기
          }
          case 1450 -> {
            authPassword = true; // 패스워드 인증 완료
            System.out.println("관리자 인증 성공");
          }
          default -> {
            System.out.println("패스워드가 틀립니다.");
            try {
              TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {}
            System.out.println("");
            System.out.println("");
            admin();
          }
        }
      } catch (InputMismatchException e) {
        System.out.println("잘못된 입력입니다. 다시 시도해 주세요.");
        scanner.nextLine(); // 버퍼 비우기
        admin(); // 다시 시도
        return;
      }
    }

    // 패스워드 인증 후 관리자 메뉴를 표시
    int adminNum;
    System.out.println("");
    System.out.println("");
    System.out.println("///////////////////관리자 메뉴///////////////////");
    System.out.println("1. 메뉴 추가 || 2. 메뉴 삭제 || 3. 메인 메뉴로 돌아가기");
    System.out.print("숫자를 입력해 주세요: ");
    try {
      adminNum = scanner.nextInt(); // 관리자 메뉴 선택
    } catch (InputMismatchException e) {
      System.out.println("잘못된 입력입니다. 관리자 메뉴를 다시 로드합니다.");
      scanner.nextLine(); // 버퍼 비우기
      admin(); // 다시 로드
      return;
    }

    switch (adminNum) {
      case 1 -> { // 메뉴 추가
        addMenu();
      }
      case 2 -> { // 메뉴 삭제
        deleteMenu();
      }
      case 3 -> {
        BurgerMenu.mainmenu(); // 키오스크 메인 메뉴로 돌아가기
        MainMenuSelect.mainmenuselect(); // 메인 메뉴 선택자
      }
      default -> {
        System.out.println("잘못된 선택입니다. 관리자 메뉴를 다시 로드합니다.");
        admin(); // 다시 로드
      }
    }
  }

  private static void addMenu() {
    try {
      System.out.print("추가할 버거 이름: ");
      String name = scanner.next(); // 버거 이름 입력

      System.out.print("칼로리: ");
      int calories = scanner.nextInt(); // 칼로리 입력

      System.out.print("가격: ");
      int price = scanner.nextInt(); // 가격 입력

      BurgerMenu.burgerMenu.add(new Burger(name, calories, price));
      System.out.println("메뉴가 추가되었습니다.");

      TimeUnit.SECONDS.sleep(1); // 1초 대기
      admin(); // 메뉴 추가 후 다시 관리자 메뉴로 돌아감
    } catch (InputMismatchException | InterruptedException e) {
      System.out.println("오류 발생. 다시 시도해 주세요.");
      scanner.nextLine(); // 버퍼 비우기
      admin(); // 다시 관리자 메뉴로 돌아감
    }
  }

  private static void deleteMenu() {
    try {
      BurgerMenu.displayBurgerMenu();
      System.out.print("삭제할 메뉴 번호를 입력해주세요: ");
      int removeIndex = scanner.nextInt(); // 삭제할 메뉴 번호 입력

      if (removeIndex > 0 && removeIndex <= BurgerMenu.burgerMenu.size()) {
        BurgerMenu.burgerMenu.remove(removeIndex - 1);
        System.out.println("메뉴가 삭제되었습니다.");
        TimeUnit.SECONDS.sleep(1); // 1초 대기
        admin(); // 메뉴 삭제 후 다시 관리자 메뉴로 돌아감
      } 
      
      else {
        System.out.println("잘못된 메뉴 번호입니다.");
        admin(); // 다시 관리자 메뉴로 돌아감
      }
    } catch (InputMismatchException | InterruptedException e) {
      System.out.println("오류 발생. 다시 시도해 주세요.");
      scanner.nextLine(); // 버퍼 비우기
      admin(); // 다시 관리자 메뉴로 돌아감
    }
  }
}

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BurgerMenu {
  static ArrayList<Burger> burgerMenu = new ArrayList<>();
  static Scanner scanner = new Scanner(System.in);
  static int burgerNum, burgerQ, setornot, setQ;

  public static void mainmenu(){
    System.out.println("");
    System.out.println("@@@@@@@@@@@@ 버거킹 키오스크 입니다 @@@@@@@@@@@@");
    System.out.println("1: 주문하기 || ***** || 3: 관리자 메뉴");
  }

  public static void initializeMenu() {
    burgerMenu.add(new Burger("통새우와퍼", 744, 7900));
    burgerMenu.add(new Burger("콰트로치즈와퍼", 901, 7900));
    burgerMenu.add(new Burger("몬스터와퍼", 1110, 9300));
    burgerMenu.add(new Burger("베이컨치즈와퍼", 862, 8900));
    burgerMenu.add(new Burger("불고기와퍼", 755, 7100));
  }

  public static void askSetMenu() {
    while (true) {
      System.out.println("세트는 감자 튀김과 콜라500ml가 추가됩니다.");
      System.out.print("세트로 하시겠습니까? (+3000원) Yes:1 || No: 2 : ");
      try {
        setornot = scanner.nextInt();
        if (setornot == 1) {
          System.out.printf("주문하신 버거 수량은 %d개 입니다. 주문하실 세트 메뉴의 수량을 입력해 주세요. : ", burgerQ);
          setQ = scanner.nextInt();
          
          if (setQ > burgerQ) {
            System.out.println("주문하신 세트 메뉴 수량이 총 버거의 수량보다 큽니다. 다시 입력해 주세요.");

            try {
              TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
              e.printStackTrace();  
            }

            System.out.println("");
            System.out.println("");
            continue;
          } 
          
          else {
            System.out.printf("세트 메뉴 %d개 선택하셨습니다.%n", setQ);
            break;
          }

        } 
        
        else if (setornot == 2) {
          System.out.println("단품이 선택되었습니다.");
          break;
        } 
        
        else {
          System.out.println("");
          System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
        }
      } 
      
      catch (InputMismatchException e) {
        System.out.println("");
        System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
        scanner.next(); 
      }
    }
  }

  public static void addTotalPrice() {
    int totalPrice = 0;
    Burger burger = burgerMenu.get(burgerNum - 1);
    totalPrice = burger.price * burgerQ;
    if (setornot == 1 && burgerQ == 1) {
      System.out.printf("총 가격은 %d원 입니다.\n", totalPrice + 3000);
    } 
    
    else if (setornot == 2) {
      System.out.printf("총 가격은 %d원 입니다.\n", totalPrice);
    } 
    
    else {
      System.out.printf("총 가격은 %d원 입니다.\n", totalPrice + (3000 * setQ));
    }
  }

  public static void displayBurgerMenu() {
    System.out.println("///////////////////햄버거///////////////////");
    if (burgerMenu.size() <= 0) {
      System.out.println("오류가 발생했습니다. 메뉴가 없습니다. 메인 메뉴로 돌아갑니다.");

      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("");
      System.out.println("");
      BurgerMenu.mainmenu();
      MainMenuSelect.mainmenuselect();
      return;
    }
    for (int i = 0; i < burgerMenu.size(); i++) {
      Burger b = burgerMenu.get(i);
      System.out.printf("%d. %s || %d Kcal || %d 원%n", i + 1, b.name, b.calories, b.price);
    }
  }

  public static void orderBurger() {
    while (true) {
      System.out.println("주문할 햄버거 메뉴 번호를 입력해주세요");
      System.out.print("메인 메뉴로 돌아가시려면 888번을 입력해주세요: ");

      try {
        burgerNum = scanner.nextInt();

        if (burgerNum == 888) {
          System.out.println("");
          System.out.println("");
          BurgerMenu.mainmenu();
          MainMenuSelect.mainmenuselect();
          return;
        } 
        
        else if (burgerNum < 1 || burgerNum > BurgerMenu.burgerMenu.size()) {
          System.out.println("");
          System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
          TimeUnit.SECONDS.sleep(1);
          System.out.println("");
          System.out.println("");
          BurgerMenu.displayBurgerMenu();
          continue;
        } 
        
        else {
          System.out.print("수량을 입력해 주세요: ");
          burgerQ = scanner.nextInt();
          System.out.println("");
          System.out.println("");
          Burger selectedBurger = BurgerMenu.burgerMenu.get(burgerNum - 1);
          System.out.printf("%s %d개 선택하셨습니다.%n", selectedBurger.name, burgerQ);
          askSetMenu();
          order.orderask(); // 주문 방식 선택 (카드, 현금)
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("");
        System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
        scanner.next(); 
        
      } catch (InterruptedException e) {
        e.printStackTrace(); 
      }
    }
  }
}

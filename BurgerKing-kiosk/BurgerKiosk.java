public class BurgerKiosk {
  public static void main(String[] args) {
    BurgerMenu.initializeMenu(); //버거 메뉴판 불러옴
    BurgerMenu.mainmenu(); //키오스크 메인 메뉴
    MainMenuSelect.mainmenuselect(); //메인 메뉴 선택자
  }
}
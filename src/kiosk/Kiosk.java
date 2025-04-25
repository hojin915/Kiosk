package kiosk;

import menucontainer.BurgerMenuContainer;
import menucontainer.ChickenMenuContainer;
import menucontainer.DrinksMenuContainer;

import java.util.*;

public class Kiosk {
    private List<Menu> menus = new ArrayList<>();

    // 메뉴 리스트를 직접 넣어서 생성
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }
    // 직접 메뉴 추가할 때 사용
    public Kiosk() {
    }
    // 메뉴리스트에 메뉴 추가
    // Kiosk 클래스에서만 사용(예정)
    private void getMenus(Menu menu) {
        menus.add(menu);
    }

    public void startOrder() {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        // Menu 추가될 때마다 Container 만들어주고
        // 여기에서 추가
        Menu burgerMenu = new BurgerMenuContainer();
        Menu chickenMenu = new ChickenMenuContainer();
        Menu drinksMenu = new DrinksMenuContainer();
        getMenus(burgerMenu);
        getMenus(chickenMenu);
        getMenus(drinksMenu);
        while (true) {
            // 전체 메뉴 출력 -> 메뉴 선택하면 그 메뉴판 출력으로 변경해야함
            for (Menu menu : menus) {
                menu.printMenu();
            }

            try {
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자 입력받기");
            }
            if (input == 0) break;
            System.out.println("0을 누르면 종료");
        }
    }
}

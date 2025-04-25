package kiosk;

import menucontainer.BurgerMenuContainer;
import menucontainer.ChickenMenuContainer;
import menucontainer.DrinksMenuContainer;

import java.util.*;

public class Kiosk extends Print{
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
    // 작동하면 제일먼저 실행
    // 메뉴이름들 출력
    @Override
     protected void printBody() {
        int menusNumber = 1;
        for (Menu menu : menus) {
            System.out.printf("%-2d. %s\n", menusNumber, menu.getMenuName());
            menusNumber++;
        }
    }

    /* 입력값(선택한 번호, 선택지 범위)
    private int checkBoundary(int input, int boundary){
        if(input >= 1 && input <= boundary) {
            return input;
        } else {
            System.out.println("범위 밖의 숫자 선택");
            return -1;
        }
    }*/

    private void selectMenus(int input){
        int boundary = menus.size();
        menus.get(input - 1).print();
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
            int position = 0;
            print();
            try {
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자 입력받기");
            }
            if (input == 0) continue;
            // if (input == 0) break;
            selectMenus(input);
            // 전체 메뉴 출력 -> 메뉴 선택하면 그 메뉴판 출력으로 변경해야함
        }
    }
}
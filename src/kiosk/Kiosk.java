package kiosk;

import menucontainer.BurgerMenuContainer;
import menucontainer.ChickenMenuContainer;
import menucontainer.DrinksMenuContainer;

import java.util.*;

public class Kiosk extends Print {
    private List<Menu> menus = new ArrayList<>();

    // 순서를 사용하려면 state.ordinal()
    private enum State {
        STEP_END,
        STEP_START,
        STEP_MENUS,
        STEP_MENUITEMS
    }

    public State getState(int input) {
        for (State state : State.values()) {
            if (state.ordinal() == input) {
                return state;
            }
        }
        System.out.println(input + " process is not exist");
        // 처음으로 돌아가기
        return State.STEP_START;
        // 프로그램 끝내기
        //return State.STEP_END;
    }


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
    @Override
    protected void printFooter() {
        System.out.printf("%-2d. %s\n", 0, "종료하기");
    }

    // 스캔 받을 때 숫자가 아니거나, 범위를 넘어가면 -1 리턴, startOrder에서 처리해주기
    private int scan(int boundary) {
        Scanner sc = new Scanner(System.in);
        int select;
        try {
            select = sc.nextInt();
            select = checkBoundary(select, boundary);
        } catch (InputMismatchException e) {
            System.out.println("숫자 입력받기");
            select = -1;
        }
        return select;
    }
    private int checkBoundary(int input, int boundary){
        if(input >= 0 && input <= boundary) {
            return input;
        } else {
            System.out.println("범위 밖의 숫자 선택");
            return -1;
        }
    }

    // 메뉴목록 선택(버거 메뉴, 치킨 메뉴, 음료 메뉴)
    private Menu selectMenu(int input) {
        int boundary = menus.size();
        return menus.get(input - 1);
    }
    // 메뉴목록 출력(버거 메뉴, 치킨 메뉴, 음료 메뉴)
    private void printMenu(int input) {
        int boundary = menus.size();
        menus.get(input - 1).print();
    }

    public void startOrder() {
        State state = State.STEP_START;
        // ordinal: state 관리, select: menu, mennuItem 선택 번호
        int ordinal = 1;
        int select = 0;
        int boundary = 0;
        // Menu 추가될 때마다 Container 만들어주고
        // 여기에서 추가
        Menu burgerMenu = new BurgerMenuContainer();
        Menu chickenMenu = new ChickenMenuContainer();
        Menu drinksMenu = new DrinksMenuContainer();
        getMenus(burgerMenu);
        getMenus(chickenMenu);
        getMenus(drinksMenu);
        Menu selectedMenu = new Menu();
        MenuItem selectedMenuItem = null;
        while (true) {
            switch (getState(ordinal)) {
                case STEP_END -> {
                    System.out.println("종료합니다");
                    return;
                }
                case STEP_START -> {
                    boundary = menus.size();
                    if(select >= 0 && select <= boundary){
                        this.print();
                    }
                    select = scan(boundary);
                    if(select == 0) ordinal -= 1;
                    else if(select > 0){
                        selectedMenu = selectMenu(select);
                        ordinal+= 1;
                    }
                }
                case STEP_MENUS -> {
                    boundary = selectedMenu.getMenu().size();
                    if(select >= 0 && select <= boundary) {
                        this.printMenu(select);
                    }
                    select = scan(boundary);
                    if(select == 0) ordinal -= 1;
                    else if(select > 0) {
                        selectedMenuItem = selectedMenu.getMenu().get(select - 1);
                        ordinal += 1;
                    }
                }
                case STEP_MENUITEMS -> {
                    if(selectedMenuItem != null) {
                        selectedMenuItem.printMenuItem();
                        select = 0;
                        ordinal = 1;
                    } else{
                        System.out.println("MenuItem is null");
                        ordinal = 0;
                    }
                    System.out.println("처음부터 시작하시겠습니까?");
                    System.out.println("0 . 처음으로     1 . 종료");
                    int temp = scan(1);
                    if(temp == 0) ordinal = 1;
                    if(temp == 1) ordinal = 0;
                }
            }
        }
    }
}
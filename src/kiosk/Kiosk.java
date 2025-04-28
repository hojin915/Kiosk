package kiosk;

import menucontainer.BurgerMenuContainer;
import menucontainer.ChickenMenuContainer;
import menucontainer.DrinksMenuContainer;

import java.util.*;

public class Kiosk extends Print {
    private List<Menu> menus = new ArrayList<>();

    // 사용하지는 않지만 일단 생성(menu 리스트 직접 넣어서 생성)
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public Kiosk() {
    }

    // 메뉴리스트에 메뉴 추가
    // Kiosk 클래스에서만 사용(예정)
    private void getMenus(Menu menu) {
        menus.add(menu);
    }

    // Kiosk 클래스는 추상클래스 Print 상속중
    // printBody, printFooter
    // 메뉴들의 목록을 출력
    @Override
    protected void printBody() {
        int menusNumber = 1;
        System.out.println("< 메인 메뉴 >");
        for (Menu menu : menus) {
            System.out.printf("%-2d. %s\n", menusNumber, menu.getMenuName());
            menusNumber++;
        }
    }

    @Override
    protected void printFooter() {
        System.out.printf("%-2d. %s\n", 0, "종료하기");
    }

    // 스캔 받을 때 숫자가 아니거나, 범위를 넘어가면 -1 리턴
    // -1의 분기는 startOrder에서 처리해주기
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

    private int checkBoundary(int input, int boundary) {
        if (input >= 0 && input <= boundary) {
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

    // 메뉴목록중 선택한 메뉴 출력(버거 메뉴, 치킨 메뉴, 음료 메뉴)
    private void printMenu(int input) {
        int boundary = menus.size();
        menus.get(input - 1).print();
    }

    public void startOrder() {
        OrderState state = OrderState.STEP_START;
        int ordinal = 1; // state 관리, case 이동
        int selectMenuNum = 0; // menu 선택
        int selectMenuItemNum = 0; // menuItem 선택
        int boundary = 0; // menu, menuItem 리스트의 사이즈로 scanner 응답 가능 범위
        ShoppingBasket basket = new ShoppingBasket();
        // Menu 추가될 때마다 Container 만들어주고
        // 여기에서 추가
        Menu burgerMenu = new BurgerMenuContainer();
        Menu chickenMenu = new ChickenMenuContainer();
        Menu drinksMenu = new DrinksMenuContainer();
        getMenus(burgerMenu);
        getMenus(chickenMenu);
        getMenus(drinksMenu);
        Menu selectedMenu = new Menu(); // while 내부에서 선언하면 자동으로 초기화
        MenuItem selectedMenuItem = null; // case 안에서 선언하면 다른 case 에서 사용 불가
        while (true) {
            switch (state.getState(ordinal)) {
                case STEP_END -> {
                    System.out.println("종료합니다");
                    return; // startOrder 끝내기
                }
                case STEP_START -> {
                    boundary = menus.size();
                    // scan을 통해 얻은 값이 기준을 만족하지 않으면 -1인 상태이다
                    // 그런 경우에는 메뉴를 다시 보여주지 않고 scan부터 시작
                    if (selectMenuNum >= 0 && selectMenuNum <= boundary) {
                        this.print();
                    }
                    if (!basket.getBasket().isEmpty()) {
                        System.out.println("");
                        System.out.println("< 주문 메뉴 >");
                        System.out.println(boundary + 1 + " . Orders     | Check your basket and order");
                        System.out.println(boundary + 2 + " . Cancel     | Clear your basket");
                    }
                    selectMenuNum = scan(boundary + 2);
                    if (selectMenuNum == 0) ordinal -= 1; // 이전 state로, 여기서는 종료
                    else if (selectMenuNum > 0 && selectMenuNum <= boundary) { // menu 선택하고 다음 state로
                        selectedMenu = selectMenu(selectMenuNum);
                        ordinal += 1;
                    } else if (selectMenuNum > boundary) {
                        ordinal = 4;
                    }
                }
                case STEP_MENUS -> {
                    boundary = selectedMenu.getMenu().size();
                    if (selectMenuNum >= 0 && selectMenuNum <= boundary) {
                        this.printMenu(selectMenuNum);
                    }
                    selectMenuItemNum = scan(boundary);
                    if (selectMenuItemNum == 0) ordinal -= 1; // 이전 state로
                    else if (selectMenuItemNum > 0) { // menuItem 선택하고 다음 state로
                        selectedMenuItem = selectedMenu.getMenu().get(selectMenuItemNum - 1);
                        ordinal += 1;
                    }
                }
                case STEP_MENUITEMS -> {
                    if (selectedMenuItem != null) {
                        System.out.print("선택한메뉴: ");
                        selectedMenuItem.printMenuItem(1);
                        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인     2. 취소");
                        int temp = scan(2);
                        if (temp == 2){
                            ordinal = 2;
                        }
                        if (temp == 1) {
                            basket.addMenuItem(selectedMenuItem);
                            ordinal = 1;
                            System.out.println(selectedMenuItem.getMenuName() + " 이 장바구니에 추가되었습니다.");
                        }
                    } else {
                        System.out.println("MenuItem is null");
                        ordinal = 0;
                    }
                }
                case STEP_BASKET -> {
                    if (selectMenuNum - boundary == 1) {
                        System.out.println("");
                        System.out.println("아래와 같이 주문하시겠습니까?");
                        basket.printBasket(1);
                        System.out.println("< 전체 금액 >");
                        System.out.println("Total: " + MoneyFormat.moneyFormat(basket.getTotalPrice()));
                        System.out.println("1. 주문하기      2. 메뉴판");
                        int temp = scan(2);
                        if(temp == 1){
                            System.out.println("주문이 완료되었습니다. 금액은 " + MoneyFormat.moneyFormat(basket.getTotalPrice()) + " 입니다");
                            basket.clearBasket();
                        }
                        if(temp == 0 ) System.out.println("1, 2 중 하나를 입력해주세요");
                        selectMenuNum = 0;
                        selectMenuItemNum = 0;
                        ordinal = 1;
                    }
                    if (selectMenuNum - boundary == 2) {
                        basket.clearBasket();
                        selectMenuNum = 0;
                        selectMenuItemNum = 0;
                        System.out.println("");
                        ordinal = 1;
                    }
                }
            }
        }
    }
}
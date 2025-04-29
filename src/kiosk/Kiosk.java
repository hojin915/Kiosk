package kiosk;

import format.MoneyFormat;
import format.Print;
import menucontainer.BurgerMenuContainer;
import menucontainer.ChickenMenuContainer;
import menucontainer.DrinksMenuContainer;

import java.util.*;

public class Kiosk extends Print {
    // 1회 주문 최대 주문량 관리
    private final int maxOrderSize = 50;
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
        System.out.println("< 메인 메뉴 >");
        for (Menu menu : menus) {
            System.out.printf("%-2d. %s\n", menu.getMenuId(), menu.getMenuName());
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
            if (select >= 0 && select <= boundary) {
                return select;
            } else {
                System.out.println("범위 밖의 숫자 선택");
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자 입력받기");
            select = -1;
        }
        return select;
    }

    // 메뉴목록 선택(버거 메뉴, 치킨 메뉴, 음료 메뉴)
    private Menu selectMenu(int input) {
        return menus.get(input - 1);
    }

    // 메뉴목록중 선택한 메뉴 출력(버거 메뉴, 치킨 메뉴, 음료 메뉴)
    private void printMenu(int input) {
        menus.stream()
                .filter(menu -> menu.getMenuId() == input)
                .findFirst()
                .ifPresent(Print::print);
    }

    public void startOrder() {
        OrderContext context = new OrderContext();
        OrderState state = OrderState.STEP_START;
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
        context.setMenus(menus);
        context.setBasketClass(new ShoppingBasket());
        while (true) {
            switch (context.getState()) {
                // 프로그램 종료
                case STEP_END -> {
                    System.out.println("종료합니다");
                    return; // startOrder 끝내기
                }
                // 처음 시작하면 보게되는 터미널, 메뉴 카테고리 선택 단계
                case STEP_START -> {
                    new StepStart().handle(context);
                }
                // 상위메뉴 선택 후 해당 메뉴의 MenuItem 목록에서 메뉴 선택 단계
                case STEP_MENUES -> {
                    new StepMenu().handle(context);
                }
                // MenuItem 선택 후 장바구니 추가, 취소 단계
                case STEP_MENUITEMS -> {
                    new StepMenuItems().handle(context);
                }
                // MenuItem에서 장바구니 선택 후 선택한 메뉴 개수 선택 단계
                case STEP_ITEMCOUNTS -> {
                    new StepItemCounts().handle(context);
                }
                // 장바구니 단계, 주문하거나 초기화면으로 이동
                case STEP_BASKET -> {
                    new StepBasket().handle(context);
                }
                case STEP_DISCOUNT -> {
                    new StepDiscount().handle(context);
                }
            }
        }
    }
}
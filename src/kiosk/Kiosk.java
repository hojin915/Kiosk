package kiosk;

import menucontainer.BurgerMenuContainer;
import menucontainer.ChickenMenuContainer;
import menucontainer.DrinksMenuContainer;

import java.util.*;

public class Kiosk {
    private List<Menu> menus = new ArrayList<>();

    // 사용하지는 않지만 일단 생성(menu 리스트 직접 넣어서 생성)
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }
    public Kiosk() {
    }

    // 메뉴리스트에 메뉴 추가
    private void getMenus(Menu menu) {
        menus.add(menu);
    }

    public void startOrder() {
        OrderContext context = new OrderContext();
        // Menu 추가될 때마다 Container 만들어주고
        // 여기에서 추가
        Menu burgerMenu = new BurgerMenuContainer();
        Menu chickenMenu = new ChickenMenuContainer();
        Menu drinksMenu = new DrinksMenuContainer();
        getMenus(burgerMenu);
        getMenus(chickenMenu);
        getMenus(drinksMenu);
        // OrderContext 초기값 세팅
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
                case STEP_START -> new StepStart().handle(context);
                // 상위메뉴 선택 후 해당 메뉴의 MenuItem 목록에서 메뉴 선택 단계
                case STEP_MENUES -> new StepMenu().handle(context);
                // MenuItem 선택 후 장바구니 추가, 취소 단계
                case STEP_MENUITEMS -> new StepMenuItems().handle(context);
                // MenuItem에서 장바구니 선택 후 선택한 메뉴 개수 선택 단계
                case STEP_ITEMCOUNTS -> new StepItemCounts().handle(context);
                // 장바구니 단계, 주문하거나 초기화면으로 이동
                case STEP_BASKET -> new StepBasket().handle(context);
                // 할인율 적용 단계, 시스템 종료
                case STEP_DISCOUNT -> new StepDiscount().handle(context);
                default -> System.out.println("Invalid State");
            }
        }
    }
}
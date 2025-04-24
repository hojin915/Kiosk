package kiosk;

import menucontainer.BurgerMenuContainer;
import menucontainer.ChickenMenuContainer;
import menucontainer.DrinksMenuContainer;

import java.util.*;

public class Kiosk {
    private List<Menu> menus = new ArrayList<>();

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }
    public Kiosk() {}

    private void getMenus(Menu menu) {
        menus.add(menu);
    }

    public void startOrder(){
        // Menu 추가될 때마다 Container만들어주고
        // 여기에서 추가
        Menu burgerMenu = new BurgerMenuContainer();
        Menu chickenMenu = new ChickenMenuContainer();
        Menu drinksMenu = new DrinksMenuContainer();
        getMenus(burgerMenu);
        getMenus(chickenMenu);
        getMenus(drinksMenu);
        for(Menu menu : menus) {
            menu.printMenu();
        }
        System.out.println("0을 누르면 종료");
    }
}

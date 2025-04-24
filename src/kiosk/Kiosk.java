package kiosk;

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
        Menu burgerMenu = new BurgerMenuContainer();
        getMenus(burgerMenu);
        burgerMenu.printMenu();
    }
}

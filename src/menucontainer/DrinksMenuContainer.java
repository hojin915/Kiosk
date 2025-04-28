package menucontainer;

import kiosk.Menu;
import kiosk.MenuItem;

public class DrinksMenuContainer extends Menu {

    public DrinksMenuContainer() {
        super("음료 메뉴");
        addMenuItem(new MenuItem(1, "Coke", 1500, "코X콜라"));
        addMenuItem(new MenuItem(2, "Sprite", 1500, "마X틴듀"));
        addMenuItem(new MenuItem(3, "ZeroCoke", 1500, "펩X라임제로"));
        addMenuItem(new MenuItem(4, "FruitSoda", 2000, "(청포도/복숭아/레몬/망고)"));
        addMenuItem(new MenuItem(5, "FruitJuice", 2500, "(오렌지주스/슈퍼베리워터주스)"));
    }
}
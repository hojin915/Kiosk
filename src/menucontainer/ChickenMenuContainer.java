package menucontainer;

import kiosk.Menu;
import kiosk.MenuItem;

public class ChickenMenuContainer extends Menu {

    public ChickenMenuContainer() {
        super(2, "치킨 메뉴");
        addMenuItem(new MenuItem(getMenuId(), "FriedChicken", 15000, "바삭한 후라이드 치킨"));
        addMenuItem(new MenuItem(getMenuId(), "YangnyeomChicken", 16000, "매콤 달콤한 양념치킨"));
        addMenuItem(new MenuItem(getMenuId(), "Half-Half Chicken", 16000, "고민하는 당신을 위해서"));
    }
}
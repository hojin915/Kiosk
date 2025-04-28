package menucontainer;

import kiosk.Menu;
import kiosk.MenuItem;

public class BurgerMenuContainer extends Menu {
    // 생성자를 통해 즉시 초기화
    public BurgerMenuContainer() {
        super(1, "버거 메뉴"); // 부모 생성자(String menuName)를 사용
        // 부모에서 속성으로 초기화한 리스트를 가지고 있기 때문에
        // 즉시 addMenuItem 으로 메뉴 추가 가능
        this.addMenuItem(new MenuItem(getMenuId(), "ThighBurger", 5500, "다릿살버거"));
        this.addMenuItem(new MenuItem(getMenuId(), "FilletBurger", 6000, "가슴살버거"));
        this.addMenuItem(new MenuItem(getMenuId(), "DeepCheeseBurger", 6000, "녹인 치즈와 치킨"));
        this.addMenuItem(new MenuItem(getMenuId(), "WhiteGarlicBurger", 6500, "햄 양파 마늘 치킨"));
    }
}
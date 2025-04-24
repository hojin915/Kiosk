package kiosk;

public class BurgerMenuContainer extends Menu {

    public BurgerMenuContainer() {
        super("버거 메뉴");
        addMenuItem(new MenuItem("ThighBurger", 5500, "다릿살버거"));
        addMenuItem(new MenuItem("FilletBurger", 6000, "가슴살버거"));
        addMenuItem(new MenuItem("DeepCheeseBurger", 6000, "녹인 치즈와 치킨"));
        addMenuItem(new MenuItem("WhiteGarlicBurger", 6500, "햄 양파 마늘 치킨"));
    }
}

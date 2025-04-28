package kiosk;

import format.Print;

import java.util.*;

public class Menu extends Print {
    private int MenuId;
    private String menuName;
    private List<MenuItem> menuItems = new ArrayList<>();

    // menu 만들 때 필수적으로 메뉴이름까지 정하기
    public Menu(int MenuId, String menuName) {
        this.MenuId = MenuId;
        this.menuName = menuName;
    }
    public Menu() {}
    // 한번에 메뉴리스트를 넣기
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    // GETTER
    protected int getMenuId() {
        return this.MenuId;
    }
    public String getMenuName() {
        return this.menuName;
    }
    public List<MenuItem> getMenu() {
        return this.menuItems;
    }

    // menuItem 하나씩 추가하기
    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    @Override
    public void printBody() {
        int menuItemNum = 1;
        System.out.println("< " + menuName + " >");
        for (MenuItem item : menuItems) {
            // 메뉴번호. 메뉴이름 | 가격 | 설명 형식으로 출력
            System.out.print(menuItemNum + ". ");
            item.printMenuItem(0);
            menuItemNum++;
        }
    }
}

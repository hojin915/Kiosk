package kiosk;

import format.Print;

import java.util.*;

public class Menu {
    private final int MenuId;
    private final String menuName;
    // List 의 값은 변경하지만 새로운 List 에 할당하지 않기 때문에 final List
    private final List<MenuItem> menuItems = new ArrayList<>();

    // menu 만들 때 필수적으로 Id, 이름 정하기
    public Menu(int MenuId, String menuName) {
        this.MenuId = MenuId;
        this.menuName = menuName;
    }

    // GETTER
    public int getMenuId() {
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

    // 메뉴의 List<MenuItem>을 형식에 맞춰서 출력
    public void printMenu() {
        int menuItemNum = 1;
        System.out.println("< " + menuName + " >");
        for (MenuItem item : menuItems) {
            // 메뉴번호. 메뉴이름 | 가격 | 설명 형식으로 출력
            System.out.print(menuItemNum + ". ");
            item.printMenuItem(0);
            menuItemNum++;
        }
        System.out.println("0 . 뒤로가기");
    }
}

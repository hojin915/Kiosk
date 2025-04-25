package kiosk;

import java.util.*;

import static kiosk.TextManager.padRight;

public class Menu extends Print {
    private String menuName;
    private List<MenuItem> menuItems = new ArrayList<>();

    // menu만들 때 필수적으로 메뉴이름까지 정하기
    public Menu(String menuName) {
        this.menuName = menuName;
    }

    // 한번에 메뉴리스트를 넣기
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    // 메뉴이름 리턴받기
    public String getMenuName() {
        return menuName;
    }

    // 메뉴리스트 리턴받기
    public List<MenuItem> getMenu() {
        return menuItems;
    }

    // menuItem 하나씩 추가하기
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    @Override
    public void printBody() {
        int menuNumber = 1;
        int maxMenuLength = 0;
        for (MenuItem item : menuItems) {
            maxMenuLength = Math.max(maxMenuLength, item.getMenuName().length());
        }
        maxMenuLength += 1;
        System.out.println("< " + menuName + " >");
        for (MenuItem item : menuItems) {
            // 메뉴번호. 메뉴이름 | 가격 | 설명 형식으로 출력
            System.out.printf("%-2d. %-" + maxMenuLength + "s | %-6d | %s\n", menuNumber, item.getMenuName(), item.getPrice(), item.getDescription());
            menuNumber++;
        }
    }
}

package kiosk;

import java.util.*;

import static kiosk.TextManager.padRight;

public class Menu {
    private String menuName;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String menuName) {
        this.menuName = menuName;
    }

    public Menu() {
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    public List<MenuItem> getMenu() {
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void printMenu() {
        int menuNumber = 1;
        int maxMenuLength = 0;
        for(MenuItem item : menuItems) {
            maxMenuLength = Math.max(maxMenuLength, item.getMenuName().length());
        }
        maxMenuLength += 1;
        System.out.println("< " + menuName + " >");
        for (MenuItem item : menuItems) {
            System.out.printf("%-2d. %-" + maxMenuLength + "s | %-6d | %s\n", menuNumber, item.getMenuName(), item.getPrice(), item.getDescription());
            menuNumber++;
        }
    }
}

package kiosk;

public class MenuItem {
    private int menuId;
    private String menuName;
    private int price;
    private String description;

    public MenuItem(int menuId, String menuName, int price, String description) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.description = description;
    }

    // GETTER
    public int getMenuId() {
        return menuId;
    }
    public String getMenuName() {
        return menuName;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }

    // SETTER
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public void printMenuItem(){
        int menuNameLength = menuName.length();
        System.out.printf("%-2d. %-" + menuNameLength + "s | %-6d | %s\n", menuId, menuName, price, description);
    }
}

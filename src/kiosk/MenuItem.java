package kiosk;

public class MenuItem {
    private String menuName;
    private int price;
    private String description;

    public MenuItem(String menuName, int price, String description) {
        this.menuName = menuName;
        this.price = price;
        this.description = description;
    }

    // GETTER
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
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDescription(String description){
        this.description = description;
    }
}

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
        // int menuNameLength = menuName.length(); // 가변형 메뉴길이
        int menuNameLength = 20; // 정해진 메뉴길이
        String priceString = "W " + price/1000 + "." + price%1000/100;
        // 품목번호. 메뉴이름 | 가격 | 설명 형식으로 출력
        System.out.printf("%-2d. %-" + menuNameLength + "s | %-6s | %s\n", menuId, menuName, priceString, description);
    }
}

package kiosk;

import format.MoneyFormat;

// 메뉴상품(menus -> menu -> *menuItem*)
public class MenuItem {
    private final int menuNameSort = 20; // 메뉴이름 정렬 간격 관리
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

    // 수량 제외, 설명 포함 출력
    public void printMenuItem(int type){
        int menuNameLength1 = menuName.length(); // 하나만 출력할 때
        String priceString = MoneyFormat.moneyFormat(price);
        // 메뉴이름 | 가격 | 설명 형식으로 출력 (넘버링 각자 추가)
        // 정렬시켜서 출력
        if(type == 0) {
            System.out.printf("%-" + menuNameSort + "s | %-7s | %s\n", menuName, priceString, description);
        }
        // 이름 길이 맞춰서 출력
        if(type == 1) {
            System.out.printf("%-" + menuNameLength1 + "s | %-7s | %s\n", menuName, priceString, description);
        }
    }

    // 수량 포함, 설명 제외 출력
    public void printMenuItemWithQuantity(int quantity){
        int totalPrice = price * quantity;
        String priceString = MoneyFormat.moneyFormat(totalPrice);
        // 메뉴이름 | 가격 | 개수 형식으로 출력 (넘버링 각자 추가)
        System.out.printf("%-" + menuNameSort + "s | %-6s | %d 개\n", menuName, priceString, quantity);
    }
}

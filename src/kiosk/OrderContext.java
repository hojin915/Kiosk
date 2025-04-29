package kiosk;

import java.util.*;

public class OrderContext {
    private List<Menu> menus;   // 메뉴들의 집합
    private Menu selectedMenu;  // 선택된 상위 메뉴
    private MenuItem selectedMenuItem;  // 선택된 메뉴
    private int selectMenuNum = 0;  // 상위메뉴 선택단계에서 선택한 메뉴 숫자
    private int selectMenuItemNum = 0;  // 메뉴 선택단계에서 선택한 메뉴 숫자
    private boolean alreadyPrinted = false; // 출력 관리(입력에 문제가 생겨도 목록을 다시 출력하지 않고 입력만 새로받음)
    private ShoppingBasket basket;  // 장바구니
    private final Scanner sc = new Scanner(System.in);
    private OrderState state = OrderState.STEP_START;   // 주문 단계(초기값 STEP_START)

    // 매개변수와 같이 scan도 모든 단계에서 사용하기 때문에 context에 포함
    public int scan(int boundary) {
        int select;
        try {
            select = sc.nextInt();
            if(select >= 0 && select <= boundary){
                return select;
            } else {
                System.out.println("범위 밖의 숫자 선택");
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자 입력받기");
            select = -1;
        }
        return select;
    }

    // <GETTER>
    public OrderContext getOrderContext(){
        return this;
    }
    public List<Menu> getMenus(){
        return this.menus;
    }
    public Menu getSelectedMenu(){
        return this.selectedMenu;
    }
    public MenuItem getSelectedMenuItem(){
        return this.selectedMenuItem;
    }
    public int getSelectMenuNum(){
        return this.selectMenuNum;
    }
    public int getSelectMenuItemNum(){
        return this.selectMenuItemNum;
    }
    public boolean getAlreadyPrinted(){
        return this.alreadyPrinted;
    }
    public ShoppingBasket getBasketClass(){
        return this.basket;
    }
    public OrderState getState() {
        return this.state;
    }

    // <SETTER>
    public void setMenus(List<Menu> menus){
        this.menus = menus;
    }
    public void setSelectedMenu(Menu selectedMenu){
        this.selectedMenu = selectedMenu;
    }
    public void setSelectedMenuItem(MenuItem selectedMenuItem){
        this.selectedMenuItem = selectedMenuItem;
    }
    public void setSelectMenuNum(int num){
        this.selectMenuNum = num;
    }
    public void setAlreadyPrinted(boolean printed){
        this.alreadyPrinted = printed;
    }
    public void setSelectMenuItemNum(int num){
        this.selectMenuItemNum = num;
    }
    public void setBasketClass(ShoppingBasket basket){
        this.basket = basket;
    }
    public void setState(OrderState state){
        this.state = state;
    }

    public void clearOrderContext(){
        this.selectedMenu = null;
        this.selectedMenuItem = null;
        this.selectMenuNum = 0;
        this.selectMenuItemNum = 0;
    }
}

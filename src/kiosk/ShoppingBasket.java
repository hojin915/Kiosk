package kiosk;

import java.util.*;

public class ShoppingBasket {
    // final 로 선언해도 내용물 변경 가능
    // 새로운 객체로 변경을 막음
    private final Map<MenuItem, Integer> basket = new HashMap<>();
    private int totalPrice;

    // 메뉴와 개수를 함께 입력
    public void addMenuItem(MenuItem menuItem, Integer quantity) {
        basket.merge(menuItem, quantity, Integer::sum);
        totalPrice += menuItem.getPrice() * quantity;
    }

    public void removeMenuItem(MenuItem menuItem){
        totalPrice -= menuItem.getPrice() * basket.get(menuItem);
        basket.remove(menuItem);
    }

    // <GETTER>
    public int getTotalPrice() {
        return totalPrice;
    }
    public Map<MenuItem, Integer> getBasketMap() {
        return basket;
    }

    public void printBasket(int startNumber) {
        if(basket.isEmpty()){
            System.out.println("Basket is empty");
        } else {
            // Map을 entrySet 들의 List로 바꾸고 MenuId에 대해 오름차순으로 정렬한다(버거 -> 치킨 -> 음료)
            List<Map.Entry<MenuItem, Integer>> entryList = new ArrayList<>(basket.entrySet());
            entryList.sort((a, b) -> a.getKey().getMenuId() - b.getKey().getMenuId());
            for(Map.Entry<MenuItem, Integer> entry : entryList){
                System.out.print(startNumber + " . ");
                entry.getKey().printMenuItemWithQuantity(entry.getValue());
                startNumber++;
            }
        }
    }

    public void clearBasket(){
        basket.clear();
        totalPrice = 0;
    }
}

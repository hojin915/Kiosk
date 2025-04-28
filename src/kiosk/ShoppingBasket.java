package kiosk;

import java.util.*;

public class ShoppingBasket {
    private int totalPrice;
    private List<MenuItem> basket = new ArrayList<>();

    public void addMenuItem(MenuItem menuItem) {
        basket.add(menuItem);
        totalPrice += menuItem.getPrice();
    }

    public void removeMenuItem(MenuItem menuItem){
        basket.remove(menuItem);
        totalPrice -= menuItem.getPrice();
    }

    // <GETTER>
    public int getTotalPrice() {
        return totalPrice;
    }
    public List<MenuItem> getBasket() {
        return basket;
    }

    public void printBasket(int startNumber) {
        if(basket.isEmpty()){
            System.out.println("Basket is empty");
        } else {
            for(MenuItem menuItem : basket){
                System.out.print(startNumber + " . ");
                menuItem.printMenuItem(1);
                startNumber++;
            }
        }
    }

    public void clearBasket(){
        basket.clear();
        totalPrice = 0;
    }
}

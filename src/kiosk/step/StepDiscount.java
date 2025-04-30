package kiosk.step;

import format.MoneyFormat;
import kiosk.*;

// STEP_DISCOUNT
public class StepDiscount implements OrderStep {
    @Override
    public void handle(OrderContext context){
        ShoppingBasket basket = context.getBasketClass();
        Discount customer = Discount.ORDINARY_PERSON;

        if(!context.getAlreadyPrinted()) {
            System.out.println("할인 정보를 입력해주세요");
            customer.printDiscount();
        }

        customer = customer.getDiscount(context.scan(Discount.values().length));
        context.setAlreadyPrinted(true);

        if(customer != null){
            int discountedPrice = customer.discountedPrice(basket.getTotalPrice());
            System.out.println("주문이 완료되었습니다. 금액은 " + MoneyFormat.moneyFormat(discountedPrice) + " 입니다");

            basket.clearBasket();
            context.setAlreadyPrinted(false);

            context.setState(OrderState.STEP_END);
        } else {
            System.out.println("번호 중 하나를 입력해주세요");
        }
    }
}

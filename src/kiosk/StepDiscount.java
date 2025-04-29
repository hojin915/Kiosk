package kiosk;

import format.MoneyFormat;

public class StepDiscount implements OrderStep{
    @Override
    public void handle(OrderContext context){
        ShoppingBasket basket = context.getBasketClass();
        Discount customer = Discount.ORDINARY_PERSON;
        if(context.getTempNum() == 0) {
            System.out.println("할인 정보를 입력해주세요");
            customer.printDiscount();
        }
        customer = customer.getDiscount(context.scan(Discount.values().length));
        context.setTempNum(1);
        if(customer != null){
            int discountedPrice = customer.discountedPrice(basket.getTotalPrice());
            System.out.println("주문이 완료되었습니다. 금액은 " + MoneyFormat.moneyFormat(discountedPrice) + " 입니다");
            basket.clearBasket();
            context.setTempNum(0);
            context.setState(OrderState.STEP_END);
        } else {
            System.out.println("번호 중 하나를 입력해주세요");
        }
    }
}

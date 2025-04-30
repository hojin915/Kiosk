package kiosk.step;

import format.MoneyFormat;
import kiosk.OrderContext;
import kiosk.OrderState;
import kiosk.OrderStep;
import kiosk.ShoppingBasket;

// STEP_BASKET
public class StepBasket implements OrderStep {
    @Override
    public void handle(OrderContext context){
        ShoppingBasket basket = context.getBasketClass();
        int boundary = context.getMenus().size();

        if(context.getSelectMenuNum() - boundary == 1){
            if(!context.getAlreadyPrinted()) {
                System.out.println("아래와 같이 주문하시겠습니까?");
                basket.printBasket(1);
                System.out.println("< 전체 금액 >");
                System.out.println("Total: " + MoneyFormat.moneyFormat(basket.getTotalPrice()));
                System.out.println("1. 주문하기      2. 메뉴판");
            }

            int select = context.scan(2);
            context.setAlreadyPrinted(true);

            if(select == 1){
                context.setAlreadyPrinted(false);

                context.setState(context.getState().next());
            } else if(select == 2){
                System.out.println("메뉴로 이동합니다");

                context.setSelectMenuNum(0);
                context.setAlreadyPrinted(false);

                context.setState(OrderState.STEP_START);
            } else if(select == 0) {
                System.out.println("1, 2 중 하나를 입력해주세요");
            }
        }
        if(context.getSelectMenuNum() - boundary == 2){
            basket.clearBasket();
            context.setBasketClass(basket);

            context.setSelectMenuNum(0);
            context.setAlreadyPrinted(false);

            context.setState(OrderState.STEP_START);
        }
    }
}
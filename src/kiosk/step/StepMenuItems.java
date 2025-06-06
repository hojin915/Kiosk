package kiosk.step;

import kiosk.MenuItem;
import kiosk.OrderContext;
import kiosk.OrderState;
import kiosk.OrderStep;

// STEP_MENUITEMS
public class StepMenuItems implements OrderStep {
    @Override
    public void handle(OrderContext context){
        // 이전 단게에서 가져온 menuItem
        MenuItem selectedMenuItem = context.getSelectedMenuItem();
        OrderState state = context.getState();

        if(selectedMenuItem != null){
            if(!context.getAlreadyPrinted()) {
                System.out.print("선택한메뉴: ");
                selectedMenuItem.printMenuItem(1);
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인     2. 취소");
            }

            int select = context.scan(2);
            context.setAlreadyPrinted(true);

            if(select == 2){
                context.setState(state.previous());
                context.setAlreadyPrinted(false);
            } else if(select == 1){
                context.setState(state.next());
                context.setAlreadyPrinted(false);
            } else if(select == 0){
                System.out.println("1, 2 중 하나를 입력해 주세요");
            }
        } else {
            System.out.println("MenuItem is null");
            context.setState(OrderState.STEP_END);
        }
    }
}

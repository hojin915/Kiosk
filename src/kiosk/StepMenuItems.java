package kiosk;

public class StepMenuItems implements OrderStep{
    @Override
    public void handle(OrderContext context){
        MenuItem selectedMenuItem = context.getSelectedMenuItem();
        OrderState state = context.getState();
        if(selectedMenuItem != null){
            if(context.getTempNum() == 0) {
                System.out.print("선택한메뉴: ");
                selectedMenuItem.printMenuItem(1);
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인     2. 취소");
            }
            int select = context.scan(2);
            context.setTempNum(1);
            if(select == 2){
                context.setState(state.previous());
                context.setTempNum(0);
            } else if(select == 1){
                context.setState(state.next());
                context.setTempNum(0);
            } else if(select == 0){
                System.out.println("1, 2 중 하나를 입력해 주세요");
            }
        } else {
            System.out.println("MenuItem is null");
            context.setState(OrderState.STEP_END);
        }
    }
}

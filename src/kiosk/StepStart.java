package kiosk;

public class StepStart implements OrderStep{

    private Menu selectMenu(OrderContext context, int input){
        return context.getMenus().stream()
                .filter(menu -> menu.getMenuId() == input)
                .findFirst()
                .orElse(null);
    }

    private void printMenus(OrderContext context){
        System.out.println("< 메인 메뉴 >");
        for(Menu menu : context.getMenus()){
            System.out.printf("%-2d. %s\n", menu.getMenuId(), menu.getMenuName());
        }
        System.out.printf("%-2d. %s\n", 0, "종료하기");
    }

    @Override
    public void handle(OrderContext context){
        int boundary = context.getMenus().size();
        int select;
        OrderState state = context.getState();
        // 일단 진입하면 출력, 선택한 숫자가 범위 안이면 다시 출력 안함
        if(context.getSelectMenuNum() >= 0 && context.getSelectMenuNum() <= boundary){
            printMenus(context);
        }
        // 장바구니 있으면 추가적으로 출력
        if(!context.getBasketClass().getBasketMap().isEmpty()){
            System.out.println("");
            System.out.println("< 주문 메뉴 >");
            System.out.println(boundary + 1 + " . Orders     | Check your basket and order");
            System.out.println(boundary + 2 + " . Cancel     | Clear your basket");
        }
        if(context.getBasketClass().getBasketMap().isEmpty()){
            select = context.scan(boundary);
        } else{
            select = context.scan(boundary + 2);
        }
        context.setSelectMenuNum(select);
        // 0 -> 종료, 범위 내부 -> 선택한 메뉴로 이동,
        // select != -1 이면서 범위 외부 -> 장바구니 단계로
        if(select == 0){
            context.setState(state.previous());
        } else if (select > 0 && select <= boundary) {
            Menu selectedMenu = selectMenu(context, select);
            context.setSelectedMenu(selectedMenu);
            context.setState(state.next());
        } else if (select > boundary){
            context.setState(OrderState.STEP_BASKET);
        }
    }
}
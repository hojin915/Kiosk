package kiosk;

// STEP_START
public class StepStart implements OrderStep{

    // 메뉴목록 선택(버거 메뉴, 치킨 메뉴, 음료 메뉴)
    private Menu selectMenu(OrderContext context, int input){
        return context.getMenus().stream()  // menus를 stream으로 바꾸고
                .filter(menu -> menu.getMenuId() == input)  // MenuId가 input과 같은 메뉴만 필터링
                .findFirst()  // 위 조건을 만족하는 첫번째 요소 반환
                .orElse(null);  // 없다면 null을 반환
    }

    // 상위메뉴 목록(버거, 치킨, 음료) 출력
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
            System.out.println("< 주문 메뉴 >");
            System.out.println(boundary + 1 + " . Orders     | Check your basket and order");
            System.out.println(boundary + 2 + " . Cancel     | Clear your basket");
        }

        // 장바구니 있으면 input 범위 2 추가
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
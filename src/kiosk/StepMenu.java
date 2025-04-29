package kiosk;

import format.Print;

// STEP_MENUES
public class StepMenu implements OrderStep{

    private void printMenu(OrderContext context, int input) {
        context.getMenus().stream()  // menus를 stream으로 변환
                .filter(menu -> menu.getMenuId() == input)  // menuId가 input과 같은 메뉴 필터링
                .findFirst() // 조건을 만족하는 첫번째 요소
                .ifPresent(Print::print);  // 존재한다면 print(Menu 클래스의 print)
    }

    @Override
    public void handle(OrderContext context){
        int boundary = context.getSelectedMenu().getMenu().size();
        OrderState state = context.getState();
        int selected = context.getSelectMenuNum(); // 이전단계(STEP_START)에서 선택한 메뉴 숫자
        // 일단 진입하면 출력, 선택한 숫자가 범위 안이면 다시 출력 안함
        if(context.getSelectMenuItemNum() >= 0 && context.getSelectMenuItemNum() <= boundary){
            printMenu(context, selected);
        }
        int select = context.scan(boundary);
        context.setSelectMenuItemNum(select);
        if(select == 0) {
            context.setState(state.previous());
        } else if (select > 0){
            MenuItem selectedMenuItem = context.getSelectedMenu().getMenu().get(select - 1);
            context.setSelectedMenuItem(selectedMenuItem);
            context.setState(state.next());
        }
    }
}

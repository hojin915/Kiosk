package kiosk.step;

import kiosk.*;

// STEP_MENUES
public class StepMenu implements OrderStep {

    // 메뉴목록중 선택한 메뉴 출력(버거 메뉴, 치킨 메뉴, 음료 메뉴)
    private void printSelectedMenu(OrderContext context, int input) {
        context.getMenus().stream()  // menus를 stream으로 변환
                .filter(menu -> menu.getMenuId() == input)  // menuId가 input과 같은 메뉴 필터링
                .findFirst() // 조건을 만족하는 첫번째 요소
                .ifPresent(Menu::printMenu);  // 존재한다면 print(Menu 클래스의 printMenu)
    }

    @Override
    public void handle(OrderContext context){
        int boundary = context.getSelectedMenu().getMenu().size();
        OrderState state = context.getState();
        int selected = context.getSelectMenuNum(); // 이전단계(STEP_START)에서 선택한 메뉴 숫자

        // 일단 진입하면 출력, 이 단계에서 선택했던 숫자가 범위 밖이면 다시 출력 안함
        if(context.getSelectMenuItemNum() >= 0 && context.getSelectMenuItemNum() <= boundary){
            printSelectedMenu(context, selected);
        }

        int select = context.scan(boundary);
        context.setSelectMenuItemNum(select);

        // 0 -> 이전으로, 범위 내부 -> menuItem 선택하고 다음 단게로
        // 범위 외부(select = -1) -> 단계 다시 진행
        if(select == 0) {
            context.setState(state.previous());
        } else if (select > 0){
            MenuItem selectedMenuItem = context.getSelectedMenu().getMenu().get(select - 1);
            context.setSelectedMenuItem(selectedMenuItem);
            context.setState(state.next());
        }
    }
}

package kiosk;


// 순서를 사용하려면 state.ordinal()
public enum OrderState {
    STEP_END,
    STEP_START,
    STEP_MENUS,
    STEP_MENUITEMS,
    STEP_BASKET;

    public OrderState getState(int input) {
        for (OrderState state : OrderState.values()) {
            if (state.ordinal() == input) {
                return state;
            }
        }
        System.out.println(input + " process is not exist");
        // 처음으로 돌아가기
        return OrderState.STEP_START;
        // 프로그램 끝내기
        //return State.STEP_END;
    }
}
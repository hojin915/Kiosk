package kiosk;

public enum OrderState {
    STEP_END,
    STEP_START,
    STEP_MENUES,
    STEP_MENUITEMS,
    STEP_ITEMCOUNTS,
    STEP_BASKET,
    STEP_DISCOUNT;

    // 다음 단계, 이전 단계
    public OrderState next() {
        return switch (this) {
            case STEP_END -> STEP_START;
            case STEP_START -> STEP_MENUES;
            case STEP_MENUES -> STEP_MENUITEMS;
            case STEP_MENUITEMS -> STEP_ITEMCOUNTS;
            case STEP_ITEMCOUNTS -> STEP_BASKET;
            case STEP_BASKET -> STEP_DISCOUNT;
            default -> STEP_END;
        };
    }
    public OrderState previous() {
        return switch(this) {
            case STEP_START -> STEP_END;
            case STEP_MENUES -> STEP_START;
            case STEP_MENUITEMS -> STEP_MENUES;
            case STEP_ITEMCOUNTS -> STEP_MENUITEMS;
            case STEP_BASKET -> STEP_ITEMCOUNTS;
            case STEP_DISCOUNT -> STEP_BASKET;
            default -> STEP_END;
        };
    }
}
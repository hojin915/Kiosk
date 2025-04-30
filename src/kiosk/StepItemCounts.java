package kiosk;

// STEP_ITEMCOUNTS
public class StepItemCounts implements OrderStep{
    @Override
    public void handle(OrderContext context) {
        int maxOrderSize = 50;
        MenuItem selectedMenuItem = context.getSelectedMenuItem();
        ShoppingBasket basket = context.getBasketClass();

        if(!context.getAlreadyPrinted()) {
            System.out.println(selectedMenuItem.getMenuName() + " 를 몇개 담으시겠습니까? (최대 " + maxOrderSize + " 개, 취소를 원하시면 0을 입력해주세요)");
        }
        int quantity = context.scan(maxOrderSize);
        context.setAlreadyPrinted(true);

        // 0이 아니고 최대개수 이하 -> 장바구니에 추가, 전체 메뉴와 장바구니 제외한 context 초기화하고 처음으로
        // 0이면 -> 이전단계(STEP_MENUES)로, menuItem 선택하며 생긴 값 초기화
        if(quantity > 0 && quantity <= maxOrderSize){
            basket.addMenuItem(selectedMenuItem, quantity);
            context.setBasketClass(basket);
            System.out.println(selectedMenuItem.getMenuName() + " " + quantity + " 개가 장바구니에 추가되었습니다.");

            context.clearOrderContext();
            context.setAlreadyPrinted(false);

            context.setState(OrderState.STEP_START);
        } else if (quantity == 0){
            context.setSelectMenuItemNum(0);
            context.setSelectedMenuItem(null);
            context.setAlreadyPrinted(false);

            context.setState(OrderState.STEP_MENUES);
        }
    }
}

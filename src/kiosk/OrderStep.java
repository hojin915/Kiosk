package kiosk;

// Step 메서드들에게 handle 구현 강제
public interface OrderStep {
    void handle(OrderContext context);
}

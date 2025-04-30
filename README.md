# Kiosk Project

## 요구사항
콘솔 기반의 키오스크를 객체 지향 설계를 적용해 구현하기
1. 카테고리 선택, 메뉴 선택, 장바구니, 할인 기능 등 포함
2. 메뉴, 순서제어를 클래스로 관리하고 적절하게 캡슐화하기
3. 가독성 높이기 -> Enum을 이용한 상수관리, 스트림 활용

### 기능과 무관한 파일 목록:  
/src/format/Print.java  
/src/kiosk/TextManager.java  

### 실행 클래스: /src/kiosk  
Main.java  
Kiosk.java

### 고객, 순서제어 클래스: /src/kiosk  
Discount.java -> 고객 타입관리(할인) Enum 클래스   
OrderState.java -> 키오스크 순서제어 Enum 클래스
OrderStep.java -> 키오스크 순서제어 인터페이스  
OrderContext.java -> 단계별 실행 클래스간 매개변수

### 단계별 실행 클래스: /src/kiosk/step  
StepStart.java  
StepMenu.java  
StepMenuItems.java  
StepCounts.java  
StepBasket.java  
StepDiscount.java

### 구성요소 클래스: /src/kiosk  
Menu.java -> 메뉴(카테고리)  
MenuItem.java -> 메뉴(음식)  
ShoppingBasket.java -> 장바구니

### 기타 클래스:  
/src/format/MoneyFormat.java -> 금액 표기 방식
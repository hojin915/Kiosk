package kiosk;

public enum Discount {
    ORDINARY_PERSON("일반인", 0),
    STUDENT("학생", 3),
    SOLDIER("군인", 5),
    VETERAN("국가유공자", 10);

    private final String customer;
    private final int discountPercent;

    Discount(String customer, int percent) {
        this.customer = customer;
        this.discountPercent = percent;
    }

    // 숫자(input)를 enum Discount 로 변환
    public Discount getDiscount(int input) {
        return switch (input) {
            case 1 -> ORDINARY_PERSON;
            case 2 -> STUDENT;
            case 3 -> SOLDIER;
            case 4 -> VETERAN;
            default -> null;
        };
    }

    // 할인율 적용 가격 리턴
    public int discountedPrice(int price) {
        return switch (this) {
            case ORDINARY_PERSON -> (price / 100) * (100 - ORDINARY_PERSON.discountPercent);
            case STUDENT -> (price / 100) * (100-STUDENT.discountPercent);
            case SOLDIER -> (price / 100) * (100-SOLDIER.discountPercent);
            case VETERAN -> (price / 100) * (100-VETERAN.discountPercent);
        };
    }

    public void printDiscount(){
        int customerNum = 1;
        for(Discount customer : Discount.values()){
            System.out.printf("%d. %-10s : %d%%\n", customerNum, customer.customer, customer.discountPercent);
            customerNum++;
        }
    }
}

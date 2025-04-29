package format;

// 돈 형식 통일
public class MoneyFormat {

    private MoneyFormat() {}
    // 생성자를 막고
    // static 메서드를 생성해 객체 없이 money의 형식을 사용할 수 있도록 하였다
    public static String moneyFormat(int money){
        return "W " + (money / 1000) + "." + (money % 1000 );
    }
}

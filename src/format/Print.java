package format;

// 단계별로 프린트 할 때 고정적인 텍스트 추가
// 적용할 메서드의 클래스에 상속하고 printBody를 @Override해서 사용한다
public abstract class Print {
    public void print() {
        printBody();
        printFooter();
    }

    protected abstract void printBody();

    protected void printFooter() {
        System.out.println("0 . 뒤로가기");
    }
}
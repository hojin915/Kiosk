package kiosk;

// 단계별로 프린트 할 때 고정적인 텍스트 추가
abstract class Print {
    public void print() {
        printBody();
        printFooter();
    }

    protected abstract void printBody();

    protected void printFooter() {
        System.out.println("0 . 뒤로가기");
    }
}

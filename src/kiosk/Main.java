package kiosk;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Kiosk kiosk = new Kiosk();
        Menu burger = new Menu();
        burger.addMenuItem(new MenuItem("싸이버거", 5500, "다릿살버거"));
        burger.addMenuItem(new MenuItem("휠렛버거", 6000, "가슴살버거"));
        burger.addMenuItem(new MenuItem("딥치즈버거", 6000, "녹인 치즈와 치킨"));
        burger.addMenuItem(new MenuItem("화이트갈릭버거", 6500, "햄 양파 마늘 치킨"));

        while(true){

            kiosk.startOrder();
            int input = 0;
            try{
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자 입력받기");
            }
            if(input == 0) break;

        }
    }
}
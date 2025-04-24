package kiosk;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Kiosk kiosk = new Kiosk();
        Menu burgers = new Menu();


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
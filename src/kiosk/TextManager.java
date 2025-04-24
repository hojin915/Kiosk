package kiosk;

public class TextManager {
    public static int getDisplayWidth(String str){
        int width = 0;
        for(char c : str.toCharArray()){
            if(c >= 0xAC00 && c <= 0xD7A3){
                width += 2;
            } else {
                width += 1;
            }
        }
        return width;
    }

    public static String padRight(String str, int totalWidth){
        int textWidth = getDisplayWidth(str);
        int padding = totalWidth - textWidth;
        StringBuilder sb = new StringBuilder(str);
        for(int i = 0; i < padding; i++){
            sb.append(" ");
        }
        return sb.toString();
    }
}

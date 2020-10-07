package com.example.shop.ultil;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.shop.activity.MainActivity;

public class Util {
    public static int convertStringToInt(String price) {
        String result = "";
        for (int i = 0; i < price.length(); i++) {
            if (Character.isDigit(price.charAt(i))) {
                result = result + price.charAt(i);
            }
        }
        return Integer.valueOf(result);
    }

    public static String convertIntToString(int price) {
        String str = String.valueOf(price);
        String result = "đ";
        int count = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (count == 3) {
                result = "." + result;
                count = 0;
            }
            result = str.charAt(i) + result;
            count++;
        }
        return result;
    }

    public static String setDefaultCourse(String fullCourse) {
        String result = "";
        for (int i = 0; i < fullCourse.length(); i++) {
            boolean isEnd = true;
            if(fullCourse.charAt(i) == '*'){
                for (int j = i + 1; j < fullCourse.length(); j++) {
                    if (fullCourse.charAt(j) == '/') {
                        result = result + fullCourse.substring(i, j) + "\n";
                        isEnd = false;
                        i = j - 1;
                        break;
                    }
                }
                if(isEnd){
                    result = result + fullCourse.substring(i, fullCourse.length() - 3);
                    break;
                }
            }
        }

        return result;

    }

}

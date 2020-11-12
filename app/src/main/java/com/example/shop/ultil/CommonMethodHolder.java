package com.example.shop.ultil;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.shop.activity.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CommonMethodHolder {
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
            if (fullCourse.charAt(i) == '*') {
                for (int j = i + 1; j < fullCourse.length(); j++) {
                    if (fullCourse.charAt(j) == '/') {
                        result = result + fullCourse.substring(i, j) + "\n";
                        isEnd = false;
                        i = j - 1;
                        break;
                    }
                }
                if (isEnd) {
//                    result = result + fullCourse.substring(i, fullCourse.length() - 3);
                    result = result + fullCourse.substring(i);
                    break;
                }
            }
        }
        return result;
    }

    public static String[] seperateAsterisk(String productName) {
        String[] result = productName.split("\\*");
        return result;
    }

    public static String[] seperateSlash(String productName) {
        String[] result = productName.split("/");
        return result;
    }

    public static String[] deleteNextLine(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArr[i].replace("\n", "");
        }
        return strArr;
    }

    public static void saveCart(ArrayList<Product> arrListCart, int cartCount, String cartTotal, boolean isRequireFromEditProduct, Cart cart) {
        cart.setArrListProductInCart(arrListCart);
        cart.setCartCount(cartCount);
        cart.setCartTotal(cartTotal);
        cart.setRequireFromEditProduct(isRequireFromEditProduct);
        Hawk.put("cart", cart);
    }

}

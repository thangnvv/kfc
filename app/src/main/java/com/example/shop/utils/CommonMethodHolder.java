package com.example.shop.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.shop.objects.Cart;
import com.example.shop.objects.Product;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class CommonMethodHolder {
    public static int convertStringToInt(String price) {
        String result = "";
        for (int i = 0; i < price.length(); i++) {
            if (Character.isDigit(price.charAt(i))) {
                result = result + price.charAt(i);
            }
        }
        return Integer.parseInt(result);
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

    public static String[] separateAsterisk(String productName) {
        return productName.split("\\*");
    }

    public static String[] separateSlash(String productName) {
        return productName.split("/");
    }

    public static String[] deleteNextLine(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArr[i].replace("\n", "");
        }
        return strArr;
    }

    public static boolean checkNetworkStatus(Context context) {
        ConnectivityManager conn =  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void saveCart(ArrayList<Product> arrListCart, int cartCount, String cartTotal, boolean isRequireFromEditProduct, Cart cart) {
        cart.setArrListProductInCart(arrListCart);
        cart.setCartCount(cartCount);
        cart.setCartTotal(cartTotal);
        cart.setRequireFromEditProduct(isRequireFromEditProduct);
        Hawk.put("cart", cart);
    }

}

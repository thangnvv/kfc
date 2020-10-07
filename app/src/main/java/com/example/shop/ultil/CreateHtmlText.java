package com.example.shop.ultil;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

public class CreateHtmlText {
    public static Spanned createTextRequired(String str){

        String title = getColoredSpanned(str, "#495057");
        String asterisk = getColoredSpanned("*","#e4002b");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return Html.fromHtml(title+" "+asterisk , Html.FROM_HTML_MODE_LEGACY);
        }else{
            return Html.fromHtml(title+" "+asterisk);
        }
    }

    public static Spanned buildText(String str){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return Html.fromHtml(str , Html.FROM_HTML_MODE_LEGACY);
        }else{
            return Html.fromHtml(str);
        }
    }


    private static String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
}

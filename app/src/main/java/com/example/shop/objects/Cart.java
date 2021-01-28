package com.example.shop.objects;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> arrListProductInCart;
    private int cartCount;
    private String cartTotal;
    private boolean isRequireFromEditProduct;
    private static Cart cart;

    private Cart(){
    }

    public static Cart getInstance(){
        if(cart == null){
            cart = new Cart();
        }
        return cart;
    }

    public ArrayList<Product> getArrListProductInCart() {
        return arrListProductInCart;
    }

    public void setArrListProductInCart(ArrayList<Product> arrListProductInCart) {
        this.arrListProductInCart = arrListProductInCart;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public String getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(String cartTotal) {
        this.cartTotal = cartTotal;
    }

    public boolean isRequireFromEditProduct() {
        return isRequireFromEditProduct;
    }

    public void setRequireFromEditProduct(boolean requireFromEditProduct) {
        isRequireFromEditProduct = requireFromEditProduct;
    }
}

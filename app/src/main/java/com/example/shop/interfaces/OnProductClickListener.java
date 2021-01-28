package com.example.shop.interfaces;

import com.example.shop.objects.Product;

public interface OnProductClickListener {
    void onSettingProduct(Product product);
    void onOrderProduct(Product product);
}

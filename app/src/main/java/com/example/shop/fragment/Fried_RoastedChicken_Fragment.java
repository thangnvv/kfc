package com.example.shop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.shop.R;
import com.example.shop.adapter.ProductAdapter;
import com.example.shop.ultil.BannerImage;
import com.example.shop.ultil.Product;

import java.util.ArrayList;

public class Fried_RoastedChicken_Fragment extends Fragment {

    GridView mGridViewFriedRoastedChicken;
    ArrayList<Product> mListProduct;
    ProductAdapter mProductAdapter;
    ArrayList<BannerImage> FriedChicken1PC, FriedChicken2PC, FriedChicken3PC, FriedChicken6PC,
            FriedChicken9PC, FriedChicken12PC, RoastedChicken1PC, HotWings3PC, HotWings5PC;

    public Fried_RoastedChicken_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fried__roasted_chicken_, container, false);

        mListProduct = new ArrayList<>();

        createBannerListForProduct();
        addProductInfo();

        mProductAdapter = new ProductAdapter(mListProduct, getContext());

        mGridViewFriedRoastedChicken = view.findViewById(R.id.gridViewFriedRoastedChicken);
        mGridViewFriedRoastedChicken.setAdapter(mProductAdapter);


        return view;
    }

    private void addProductInfo() {

        mListProduct.add(new Product(FriedChicken1PC, "Gà Rán (1 Miếng)", "36.000đ", "* 1 Miếng Gà Giòn Cay / 1 Miếng Gà Giòn Không Cay / 1 Miếng Gà Truyền Thống"));
        mListProduct.add(new Product(FriedChicken2PC, "Gà Rán (2 Miếng)", "68.000đ", "* 2 Miếng Gà Giòn Cay / 2 Miếng Gà Giòn Không Cay / 2 Miếng Gà Truyền Thống"));
        mListProduct.add(new Product(FriedChicken3PC, "Gà Rán (3 Miếng)", "99.000đ", "* 3 Miếng Gà Giòn Cay / 3 Miếng Gà Giòn Không Cay / 3 Miếng Gà Truyền Thống"));
        mListProduct.add(new Product(FriedChicken6PC, "Gà Rán (6 Miếng)", "195.000đ", "* 6 Miếng Gà Giòn Cay / 6 Miếng Gà Giòn Không Cay / 6 Miếng Gà Truyền Thống"));
        mListProduct.add(new Product(FriedChicken9PC, "Gà Rán (9 Miếng)", "289.000đ", "* 9 Miếng Gà Giòn Cay / 9 Miếng Gà Giòn Không Cay / 9 Miếng Gà Truyền Thống"));
        mListProduct.add(new Product(FriedChicken12PC, "Gà Rán (12 Miếng)", "379.000đ", "* 12 Miếng Gà Giòn Cay / 12 Miếng Gà Giòn Không Cay / 12 Miếng Gà Truyền Thống"));

        mListProduct.add(new Product(RoastedChicken1PC, "Gà Quay (1 Miếng)", "68.000đ", "* 1 Miếng Gà Quay Giấy Bạc / 1 Miếng Gà Quay Tiêu"));

        mListProduct.add(new Product(HotWings3PC, "Phần Hot Wings 3 Miếng", "49.000đ", "* Phần Hot Wings 3 Miếng"));
        mListProduct.add(new Product(HotWings3PC, "Phần Hot Wings 5 Miếng", "71.000đ", "* Phần Hot Wings 5 Miếng"));

    }

    private void createBannerListForProduct() {
        FriedChicken1PC = new ArrayList<>();
        FriedChicken1PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/7166d1bee7b66d1e90e7899cda0b03be.jpg"));
        FriedChicken1PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/c2547f0cf7b56544a8cec6f0f8dcc2b5.jpg"));
        FriedChicken1PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/6a1a257b973c34b693aa0f6b3ddb4a1c.jpg"));

        FriedChicken2PC = new ArrayList<>();
        FriedChicken2PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/1eb5c19fa53a45152989923c626feebe.jpg"));
        FriedChicken2PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/1079cbc720677fa9adfb9fb208c0c330.jpg"));
        FriedChicken2PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/a1508668a24b6f2e532fde57be4ea581.jpg"));

        FriedChicken3PC = new ArrayList<>();
        FriedChicken3PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/6d19408b40d53bba4601aadd883b97ec.jpg"));

        FriedChicken6PC = new ArrayList<>();
        FriedChicken6PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/68c63acecebbd5752530e3064e5d6cfb.jpg"));

        FriedChicken9PC = new ArrayList<>();
        FriedChicken9PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/a6525886894c89c32ca47d57a9170f93.jpg"));

        FriedChicken12PC = new ArrayList<>();
        FriedChicken12PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/2798a3a4e0d3f66c2a45b51f09f043e8.jpg"));

        RoastedChicken1PC = new ArrayList<>();
        RoastedChicken1PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/3708a6181f25a1a4e25ed6fe86e2f649.jpg"));

        HotWings3PC = new ArrayList<>();
        HotWings3PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/b847fbacc79fb14179b5c1e1604fef53.jpg"));

        HotWings5PC = new ArrayList<>();
        HotWings5PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/b25e3cd3548d8669e2cbc28bcaff8993.jpg"));
    }

}
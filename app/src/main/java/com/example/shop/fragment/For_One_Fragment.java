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


public class For_One_Fragment extends Fragment {

    GridView gridView;
    ArrayList<Product> mListProduct;
    ProductAdapter mProductAdapter;
    ArrayList<BannerImage> ComboChickenA, ComboChickenB, ComboChickenC, ComboChickenD, ComboRiceA, ComboRiceB, ComboRiceC;

    public For_One_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createBannerListForProduct();

        View view = inflater.inflate(R.layout.fragment_combo_one_person_old, container, false);

        mListProduct = new ArrayList<>();

        createBannerListForProduct();
        addProductInfo();

        mProductAdapter = new ProductAdapter(mListProduct, getContext());

        gridView = view.findViewById(R.id.gridViewProduct);
        gridView.setAdapter(mProductAdapter);


        return view;
    }

    private void addProductInfo(){
        mListProduct.add(new Product(ComboChickenA, "COMBO GÀ RÁN A", "79.000đ", "* 2 Miếng Gà Giòn Cay/ 2 Miếng Gà Giòn Không Cay/ 2 Miếng Gà Truyền Thống\n" +
                "* 1 Pepsi Lon" ));

        mListProduct.add(new Product(ComboChickenB, "COMBO GÀ RÁN B", "79.000đ", "* 1 Phần Hot Wings 3 Miếng\n"+ "* 1 Khoai Tây Chiên (Lớn)\n" + "* 1 Pepsi Lon"));

        mListProduct.add(new Product(ComboChickenC, "COMBO GÀ RÁN C", "85.000đ", "* 1 Miếng Gà Giòn Cay / 1 Miếng Gà Giòn Không Cay / 1 Miếng Gà Truyền Thống\n" +
                "* 1 Burger Tôm \n* 1 Pepsi Lon "));

        mListProduct.add(new Product(ComboChickenD, "COMBO GÀ RÁN D", "89.000đ", "* 1 Miếng Gà Giòn Cay \n" + "* 1 Burger Zinger\n* 1 Pepsi Lon "));

        mListProduct.add(new Product(ComboRiceA, "COMBO CƠM A", "69.000đ", "* 1 Cơm Gà Giòn Cay / 1 Cơm Gà Giòn Không Cay / 1 Cơm Gà Truyền Thống / 1 Cơm Phi-lê Gà Quay Flava / 1 Cơm Phi-lê Gà Quay Tiêu / 1 Cơm Phi-lê Gà Giòn / 1 Cơm Gà Xào Xốt Nhật / 1 Cơm Gà Xiên Que \n" + "* 1 Súp Gà\n* 1 Pepsi Lon "));
        mListProduct.add(new Product(ComboRiceB, "COMBO CƠM B", "89.000đ", "* 1 Cơm Gà Giòn Cay / 1 Cơm Gà Giòn Không Cay / 1 Cơm Gà Truyền Thống / 1 Cơm Phi-lê Gà Quay Flava / 1 Cơm Phi-lê Gà Quay Tiêu / 1 Cơm Phi-lê Gà Giòn / 1 Cơm Gà Xào Xốt Nhật / 1 Cơm Gà Xiên Que \n" + "* 1 Miếng Gà Giòn Cay / 1 Miếng Gà Truyền Thống / 1 Miếng Gà Giòn Không Cay\n* 1 Pepsi Lon "));
        mListProduct.add(new Product(ComboRiceC, "COMBO CƠM C", "95.000đ", "* 1 Cơm Gà Giòn Cay / 1 Cơm Gà Giòn Không Cay / 1 Cơm Gà Truyền Thống / 1 Cơm Phi-lê Gà Quay Flava / 1 Cơm Phi-lê Gà Quay Tiêu / 1 Cơm Phi-lê Gà Giòn / 1 Cơm Gà Xào Xốt Nhật / 1 Cơm Gà Xiên Que \n" + "* 1 Burger Gà Quay Flava / 1 Burger Zinger\n* 1 Pepsi Lon "));
    }

    private void createBannerListForProduct(){

        ComboChickenA = new ArrayList<>();
        ComboChickenA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/b09860e31866521c22705711916cc402.jpg"));
        ComboChickenA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/7155a5793d2f7cf90213c58138540e72.png"));
        ComboChickenA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/6ccc9d71f2af52e912a76a8b8dd65628.png"));

        ComboChickenB = new ArrayList<>();
        ComboChickenB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/7d36d8d380315c169ba830b0b5b4c26d.jpg"));
        ComboChickenB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/3b81e6d07ba142dd136f43ebd5ef4f64.png"));

        ComboChickenC = new ArrayList<>();
        ComboChickenC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/c94db21b4ac5ab7333300f3f7e78671e.jpg"));
        ComboChickenC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/98668917f791eca645950c19f3d7f3b8.png"));
        ComboChickenC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/1c12c83b68723b7afe4afc234ed4c161.png"));

        ComboChickenD = new ArrayList<>();
        ComboChickenD.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/f7e98746a52f1e24dbbe663be0ade3e0.jpg"));
        ComboChickenD.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/aa343d5f8d4759579b48b4d4661144d9.png"));
        ComboChickenD.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/d87752957670d8afb59b13b9ac12f911.png"));

        ComboRiceA = new ArrayList<>();
        ComboRiceA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/a8ef8a8b23927a56bbfd9a9884416c9b.jpg"));
        ComboRiceA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/c8340b988f7450260acc613b62f72e6a.png"));
        ComboRiceA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/0b838be50c421a982287770a380d0fdf.png"));

        ComboRiceB = new ArrayList<>();
        ComboRiceB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/c72217be125e9ff11818a7cdce0b0a06.jpg"));
        ComboRiceB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/5fe03d4ae24d7e69ba4fcf267f586b97.png"));
        ComboRiceB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/f06a44fb6cc123ad304062f613f66901.png"));

        ComboRiceC = new ArrayList<>();
        ComboRiceC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/e72b0f625bc005513f52779da65c37df.jpg"));
        ComboRiceC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/ade963857b83fc5246036dced9282ddd.png"));
        ComboRiceC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/948be97cbd084a138bd223db33dee102.png"));


    }
}
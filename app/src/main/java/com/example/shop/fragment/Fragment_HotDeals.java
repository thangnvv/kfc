package com.example.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.shop.R;
import com.example.shop.adapter.ProductAdapter;
import com.example.shop.ultil.BannerImage;
import com.example.shop.ultil.Product;

import java.util.ArrayList;

public class Fragment_HotDeals extends Fragment {

    GridView gridViewHotDeals;
    ArrayList<Product> mListProduct;
    ProductAdapter mProductAdapter;
    ArrayList<BannerImage> CheesePumpkinBar2PC, CheesePumpkinBar3PC, CheesePumpkinBar5PC, ComboCheesePumkinBarHDA,
            ComboCheesePumkinBarHDB, ComboCheesePumkinBarHDC, SteakWithRice, SteakWithFries, ZingerSteakHDA, ZingerSteakHDB ;

    public Fragment_HotDeals() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hot_deals, container, false);

        mListProduct = new ArrayList<>();

        createBannerListForProduct();
        addProductInfo();

        mProductAdapter = new ProductAdapter(mListProduct, getContext());

        gridViewHotDeals = view.findViewById(R.id.gridViewHotDeals);
        gridViewHotDeals.setAdapter(mProductAdapter);

        return view;
    }

    private void addProductInfo() {

        mListProduct.add(new Product(CheesePumpkinBar2PC, "THANH BÍ PHÔ-MAI (2 THANH)", "26.000đ", "* 2 Thanh Bí Phô-mai"));
        mListProduct.add(new Product(CheesePumpkinBar3PC, "THANH BÍ PHÔ-MAI (3 THANH)", "32.000đ", "* 3 Thanh Bí Phô-mai"));
        mListProduct.add(new Product(CheesePumpkinBar5PC, "THANH BÍ PHÔ-MAI (5 THANH)", "52.000đ", "* 5 Thanh Bí Phô-mai"));

        mListProduct.add(new Product(ComboCheesePumkinBarHDA, "COMBO THANH BÍ PHÔ-MAI HDA", "94.000đ",
                "* 2 Miếng Gà Giòn Cay / 2 Miếng Gà Truyền thống / 2 Miếng Gà Truyền thống\n" + "* 2 Thanh Bí Phô-mai\n" + "* 1 Pepsi Lon"));

        mListProduct.add(new Product(ComboCheesePumkinBarHDB, "COMBO THANH BÍ PHÔ-MAI HDB", "74.000đ",
                "* 1 Burger Gà Quay Flava / 1 Burger Zinger / 1 Burger Tôm\n" + "* 2 Thanh Bí Phô-mai\n" + "* 1 Pepsi Lon"));

        mListProduct.add(new Product(ComboCheesePumkinBarHDC, "COMBO THANH BÍ PHÔ-MAI HDC", "189.000đ",
                "* 4 Miếng Gà Giòn Cay / 4 Miếng Gà Truyền thống / 4 Miếng Gà Giòn Không Cay\n" + "* 4 Thanh Bí Phô-mai\n" + "* 2 Pepsi Lon"));

        mListProduct.add(new Product(SteakWithRice, "GÀ BÍT-TẾT VỚI CƠM", "39.000đ",
                "* 1 Phần Gà Bít-tết với Cơm"));

        mListProduct.add(new Product(SteakWithFries, "GÀ BÍT-TẾT VỚI KHOAI TÂY CHIÊN", "39.000đ",
                "* 1 Phần Gà Bít-tết với Khoai Tây Chiên"));

        mListProduct.add(new Product(ZingerSteakHDA, "COMBO GÀ BÍT-TẾT HDA", "81.000đ",
                "* 1 Phần Gà Bít-tết với Khoai Tây Chiên\n" + "* 1 Miếng Gà Giòn Cay / 1 Miếng Gà Giòn Không Cay / 1 Miếng Gà Truyền Thống\n"+
                        "* 1 Pepsi Lon"));

        mListProduct.add(new Product(ZingerSteakHDB, "COMBO GÀ BÍT-TẾT HDB", "81.000đ",
                "* 1 Phần Gà Bít-tết với Cơm\n" + "* 1 Miếng Gà Giòn Cay / 1 Miếng Gà Giòn Không Cay / 1 Miếng Gà Truyền Thống\n"+
                        "* 1 Pepsi Lon"));
    }

    private void createBannerListForProduct() {

        CheesePumpkinBar2PC = new ArrayList<>();
        CheesePumpkinBar2PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/8bfdbebe2912884ccb4211daa91d0608.png"));

        CheesePumpkinBar3PC = new ArrayList<>();
        CheesePumpkinBar3PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/54000757396ab87ccdb384710bc65255.png"));

        CheesePumpkinBar5PC = new ArrayList<>();
        CheesePumpkinBar5PC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/d052f02f7225c00c442b0b277cca85cc.png"));

        ComboCheesePumkinBarHDA = new ArrayList<>();
        ComboCheesePumkinBarHDA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/b2a6fcd7f1c2d9ddc62fc75763a5b3fc.png"));

        ComboCheesePumkinBarHDB = new ArrayList<>();
        ComboCheesePumkinBarHDB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/49dc70e0ff5f86628da0a6cdc0c52b0e.png"));

        ComboCheesePumkinBarHDC = new ArrayList<>();
        ComboCheesePumkinBarHDC .add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/33c9d291071e54032b0ee886fcdefa32.png"));

        SteakWithRice = new ArrayList<>();
        SteakWithRice.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/3eb4ca5007d1a17081f42c3054dd4aca.png"));

        SteakWithFries = new ArrayList<>();
        SteakWithFries.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/9041eefa68563d44aeb4a731e41dcbca.png"));

        ZingerSteakHDA = new ArrayList<>();
        ZingerSteakHDA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/e817a62cc3149106253b3da3f7a3f213.png"));

        ZingerSteakHDB = new ArrayList<>();
        ZingerSteakHDB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/74cf3d0e2cc7b0fc8f9d9134ad140d5b.png"));
    }

}
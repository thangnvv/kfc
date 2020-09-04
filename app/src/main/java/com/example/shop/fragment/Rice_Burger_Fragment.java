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
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;

public class Rice_Burger_Fragment extends Fragment {

    GridView mGridViewRiceBurger;
    ArrayList<Product> mListProduct;
    ProductAdapter mProductAdapter;
    ArrayList<BannerImage> ComboTemika1, ComboTemikaA, ComboTemikaB, ComboTemikaC, ComboTemikaD, TraditionalChickenRice1Pc,
             SpicyChickenRice1Pc, NoneSpicyChickenRice1Pc, BlackRicePepper1Pc, FlavaRoastFillerRice1Pc,
             ToridonRice1Pc, TeriyakiRice1Pc, ChickenSkewerRice1Pc, ShrimpBurger1Pc, FlavaRoastBurger1Pc, ZingerBurger1Pc;

    public Rice_Burger_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rice_burger, container, false);
        mListProduct = new ArrayList<>();
        createBannerListForProduct();
        addProductInfo();
        mProductAdapter = new ProductAdapter(mListProduct, getContext());
        mGridViewRiceBurger = view.findViewById(R.id.gridViewRiceBurger);
        mGridViewRiceBurger.setAdapter(mProductAdapter);
        return view;
    }

    private void addProductInfo() {
        mListProduct.add(new Product(ComboTemika1, "COMBO TEMAKI 1", "34.000đ", "* 1 Phần Temaki"));
        mListProduct.add(new Product(ComboTemikaA, "COMBO TEMAKI A", "61.000đ", "* 2 Phần Temaki\n"+
                "* 1 Miếng Gà Giòn Cay / 1 Miếng Gà Giòn Không Cay / 1 Miếng Gà Truyền Thống\n" + "* 1 Pepsi Lon"));
        mListProduct.add(new Product(ComboTemikaB, "COMBO TEMAKI B", "83.000đ", "* 2 Phần Temaki\n"+
                "* 1 Miếng Gà Giòn Cay / 1 Miếng Gà Giòn Không Cay / 1 Miếng Gà Truyền Thống\n" + "* 1 Pepsi Lon"));
        mListProduct.add(new Product(ComboTemikaC, "COMBO TEMAKI C", "86.000đ", "* 2 Phần Temaki\n"+
                "* 1 Khoai Tây Chiên (Vừa)\n" + "* 1 Pepsi Lon"));
        mListProduct.add(new Product(ComboTemikaD, "COMBO TEMAKI D", "199.000đ", "* 3 Phần Temaki\n"+
                "* 3 Miếng Gà Giòn Cay / 3 Miếng Gà Giòn Không Cay / 3 Miếng Gà Truyền Thống\n" + "* 2 Pepsi Lon"));

        mListProduct.add(new Product(TraditionalChickenRice1Pc,"Cơm Gà Truyền Thống (1 Phần)" ,"41.000đ", "* Cơm Gà Truyền Thống (1 Phần)"));

        mListProduct.add(new Product(SpicyChickenRice1Pc, "Cơm Gà Giòn Cay (1 Phần)" ,"41.000đ", "* Cơm Gà Giòn Cay (1 Phần)"));
        mListProduct.add(new Product(NoneSpicyChickenRice1Pc, "Cơm Gà Giòn Không Cay (1 Phần)" ,"41.000đ", "* Cơm Gà Giòn Không Cay (1 Phần)"));
        mListProduct.add(new Product(BlackRicePepper1Pc, "Cơm Phi Lê Gà Quay Tiêu (1 Phần)", "41.000đ", "* Cơm Phi Lê Gà Quay Tiêu (1 Phần)"));
        mListProduct.add(new Product(FlavaRoastFillerRice1Pc, "Cơm Phi Lê Gà Quay Flava (1 Phần)", "41.000đ", "* Cơm Phi Lê Gà Quay Flava (1 Phần)"));
        mListProduct.add(new Product(ToridonRice1Pc, "Cơm Gà Xào Sốt Nhật (1 Phần)", "41.000đ", "* Cơm Gà Xào Xốt Nhật (1 Phần)"));
        mListProduct.add(new Product(TeriyakiRice1Pc, "Cơm Phi Lê Gà Giòn (1 Phần)", "41.000đ", "* Cơm Phi Lê Gà Giòn (1 Phần)"));
        mListProduct.add(new Product(ChickenSkewerRice1Pc, "Cơm Gà Xiên Que (1 Phần)", "41.000đ", "* Cơm Gà Xiên Que (1 Phần)"));

        mListProduct.add(new Product(ShrimpBurger1Pc, "Burger Tôm (1 Phần)", "42.000đ", "* Burger Tôm" ));
        mListProduct.add(new Product(FlavaRoastBurger1Pc, "Burger Gà Quay Flava (1 Phần)", "47.000đ", "* Burger Gà Quay Flava (1 Phần)" ));
        mListProduct.add(new Product(ZingerBurger1Pc, "Burger Zinger (1 Phần)", "51.000đ", "* Burger Zinger (1 Phần)" ));

    }

    private void createBannerListForProduct() {

        ComboTemika1 = new ArrayList<>();
        ComboTemika1.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/b0c34313c168e8d4f41422641e6ec3e0.png"));

        ComboTemikaA = new ArrayList<>();
        ComboTemikaA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/31359042b457a389b7547e65ac0f2e2a.png"));

        ComboTemikaB = new ArrayList<>();
        ComboTemikaB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/68be6c26d95f216305a87059fb145307.png"));

        ComboTemikaC = new ArrayList<>();
        ComboTemikaC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/43193c418c6602c042a8a504b0024156.png"));

        ComboTemikaD = new ArrayList<>();
        ComboTemikaD.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/8fc8b1431dfe0c79ad5b27f32813b04d.png"));

        TraditionalChickenRice1Pc = new ArrayList<>();
        TraditionalChickenRice1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/9886fd4a2c72a01d10b5f4713d00fe73.jpg"));

        SpicyChickenRice1Pc = new ArrayList<>();
        SpicyChickenRice1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/130dba524bed0e612d77979bfd579ed4.jpg"));

        NoneSpicyChickenRice1Pc = new ArrayList<>();
        NoneSpicyChickenRice1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/0f0b7d8bef924fd0d531bfac6b244cf3.jpg"));

        BlackRicePepper1Pc = new ArrayList<>();
        BlackRicePepper1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/3ddaeb667c13c83a5bcb3d03d6bad726.jpg"));

        FlavaRoastFillerRice1Pc = new ArrayList<>();
        FlavaRoastFillerRice1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/5c51e7c7d3df87b54d54d7080992b80f.jpg"));

        ToridonRice1Pc = new ArrayList<>();
        ToridonRice1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/ffb2cd0df18ed9c1af7a883e65868f68.jpg"));

        TeriyakiRice1Pc = new ArrayList<>();
        TeriyakiRice1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/876327fff18e80fc6b1ddd0826ba65bf.jpg"));

        ChickenSkewerRice1Pc = new ArrayList<>();
        ChickenSkewerRice1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/7b96a5d9bae5684f3bed8491fc20e467.jpg"));

        ShrimpBurger1Pc = new ArrayList<>();
        ShrimpBurger1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/c21e391e9447babbc5ec76a902b68d88.jpg"));

        FlavaRoastBurger1Pc = new ArrayList<>();
        FlavaRoastBurger1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/c12072579cf5226b75cd90bf36d6ab90.jpg"));

        ZingerBurger1Pc = new ArrayList<>();
        ZingerBurger1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/9cff128ec12ceed884d13a48b2aecc79.jpg"));

    }

}
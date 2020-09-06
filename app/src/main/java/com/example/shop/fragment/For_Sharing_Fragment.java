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

public class For_Sharing_Fragment extends Fragment {

    GridView gridViewComboGroup;
    ArrayList<Product> mListProduct;
    ProductAdapter mProductAdapter;
    ArrayList<BannerImage> ComboGroupA, ComboGroupB, ComboGroupC, ComboGroupD, ComboGroupE, ComboGroupF, ComboFamilyA, ComboFamilyB;

    public For_Sharing_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_combo_group_old, container, false);

        mListProduct = new ArrayList<>();

        createBannerListForProduct();
        addProductInfo();

        mProductAdapter = new ProductAdapter(mListProduct, getContext());

        gridViewComboGroup = view.findViewById(R.id.gridViewComboGroup);
        gridViewComboGroup.setAdapter(mProductAdapter);

        return view;
    }

    private void addProductInfo() {
        mListProduct.add(new Product(ComboGroupA , "COMBO NHÓM A", "129.000đ", "* 2 Miếng Gà Giòn Cay / 2 Miếng Gà Giòn Không Cay / 2 Miếng Gà Truyền thống\n"+
                "* 1 Burger Tôm\n" + "* 2 Pepsi Lon"));
        mListProduct.add(new Product(ComboGroupB , "COMBO NHÓM B", "149.000đ", "* 3 Miếng Gà Giòn Cay / 3 Miếng Gà Giòn Không Cay / 3 Miếng Gà Truyền Thống\n"+
                "* 1 Khoai Tây Chiên (Lớn)\n" + "* 2 Pepsi Lon"));
        mListProduct.add(new Product(ComboGroupC , "COMBO NHÓM C", "185.000đ", "* 4 Miếng Gà Giòn Cay / 4 Miếng Gà Giòn Không Cay / 4 Miếng Gà Truyền thống\n"+
                "* 1 Khoai Tây Chiên (Lớn)\n" + "* 2 Pepsi Lon"));
        mListProduct.add(new Product(ComboGroupD , "COMBO NHÓM D", "185.000đ", "* 2 Miếng Gà Giòn Cay / 2 Miếng Gà Giòn Không Cay / 2 Miếng Gà Truyền thống\n"+
                "* 1 Miếng Gà Quay Giấy Bạc / 1 Miếng Gà Quay Tiêu\n" + "* 1 Khoai Tây Chiên (Lớn)\n" + "* 2 Pepsi Lon"));
        mListProduct.add(new Product(ComboGroupE , "COMBO NHÓM E", "199.000đ", "* 3 Miếng Gà Giòn Cay / 3 Miếng Gà Giòn Không Cay / 3 Miếng Gà Truyền Thống\n"+
                "* 1 Burger Gà Quay Flava / 1 Burger Zinger\n" + "* 1 Khoai Tây Chiên (Lớn)\n" + "* 2 Pepsi Lon"));
        mListProduct.add(new Product(ComboGroupF , "COMBO NHÓM F", "205.000đ", "* 3 Miếng Gà Giòn Cay / 3 Miếng Gà Giòn Không Cay / 3 Miếng Gà Truyền Thống\n"+
                "* 1 Popcorn (Lớn)\n" + "* 1 Khoai Tây Chiên (Lớn)\n" + "* 2 Pepsi Lon"));

        mListProduct.add(new Product(ComboFamilyA , "COMBO GIA ĐÌNH A", "359.000đ", "* 8 Miếng Gà Giòn Cay / 8 Miếng Gà Giòn Không Cay / 8 Miếng Gà Truyền Thống\n"+
               "* 2 Khoai Tây Chiên (Lớn)\n" + "* 4 Pepsi Lon"));

        mListProduct.add(new Product(ComboFamilyB , "COMBO GIA ĐÌNH B", "359.000đ", "* 5 Miếng Gà Giòn Cay / 5 Miếng Gà Giòn Không Cay / 5 Miếng Gà Truyền Thống\n"+
                "2 Burger Gà Quay Flava / 2 Burger Zinger\n"  +"* 2 Khoai Tây Chiên (Lớn)\n" + "* 3 Pepsi Lon"));
    }

    private void createBannerListForProduct() {

        ComboGroupA = new ArrayList<>();
        ComboGroupA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/daed2efdd381a1659f2e2105d61ab6d7.jpg"));
        ComboGroupA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/b19dd9fa871ddd4e6560dbd1cbc790f4.png"));
        ComboGroupA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/4adb863ffb8e0e61f07fff978147762e.png"));

        ComboGroupB = new ArrayList<>();
        ComboGroupB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/e4df1abdfdc755e27586dadc05a4bda6.jpg"));
        ComboGroupB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/d8df24e78e27b77b66d9b8a4ab68ccc4.png"));
        ComboGroupB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/a379f8e673c0564a501dcf916a4ad98b.png"));

        ComboGroupC = new ArrayList<>();
        ComboGroupC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/3c217e6db5fdc6483b5e02b5a2618cec.jpg"));
        ComboGroupC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/a952d7e8e60dda7ebab6d135d8579a9f.png"));
        ComboGroupC.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/3e8f6f58aec880a3dfd0fa9824d46d1b.png"));

        ComboGroupD = new ArrayList<>();
        ComboGroupD.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/f2253b127baf48f164fba5c05237974e.jpg"));
        ComboGroupD.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/e6f1ee325cae57209d081a0bbf40c85c.png"));
        ComboGroupD.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/573fbe23cd7b147443e23e62232f4fe0.png"));

        ComboGroupE = new ArrayList<>();
        ComboGroupE.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/80aa245a5f1a882b945796d00fa04873.jpg"));
        ComboGroupE.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/36a039a046e1bd7963737bbe07915d45.png"));
        ComboGroupE.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/7a991a5665e8ac2b2852e47be20540b4.png"));

        ComboGroupF = new ArrayList<>();
        ComboGroupF.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/237bb345b19ba88d50aa91016e033deb.jpg"));
        ComboGroupF.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/c05115ba8e4dc570b686b52dd78e91ef.png"));
        ComboGroupF.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/195f1c5aaeba3d2668a8bac989cca59d.png"));

        ComboFamilyA = new ArrayList<>();
        ComboFamilyA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/4759577e8f6efbde53097ec706db26de.jpg"));
        ComboFamilyA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/2ac62eeb78b1482fcf4af0d4eb24f07d.png"));
        ComboFamilyA.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/57a12acf3f1d3e3a0267dc18d85b7f22.png"));

        ComboFamilyB = new ArrayList<>();
        ComboFamilyB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/0646342ebaf0a9a188573c63b867fea6.jpg"));
        ComboFamilyB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/1f70b3a0a80f8b18503025a541cc14a2.png"));
        ComboFamilyB.add(new BannerImage("https://kfcvietnam.com.vn/uploads/combo/bb1eafba47bb009b6b269fdc2b796dfa.png"));
    }
}
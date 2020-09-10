package com.example.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.shop.R;
import com.example.shop.adapter.ProductALaCarteAdapter;
import com.example.shop.adapter.ProductAdapter;
import com.example.shop.ultil.BannerImage;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.ProductALaCarte;

import java.util.ArrayList;

public class Desserts_And_Drinks_Fragment extends Fragment {

    GridView mGridViewDessertsAndDrinks;
    ArrayList<ProductALaCarte> mListProduct;
    ProductALaCarteAdapter mProductAdapter;

    public Desserts_And_Drinks_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snacks, container, false);
        mListProduct = new ArrayList<>();
        addProductInfo();
        mProductAdapter = new ProductALaCarteAdapter(mListProduct, getContext());
        mGridViewDessertsAndDrinks = view.findViewById(R.id.gridViewSnacks);
        mGridViewDessertsAndDrinks.setAdapter(mProductAdapter);
        return view;
    }

    private void addProductInfo() {
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/90e669f4a4213f0ca59cae3df3edb37f.png",
                "Mochi Trà Xanh (1 Cái)", "17.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/5e116447e65bd1ff5c76370e054fdd50.png",
                "Mochi Trà Xanh (3 Cái)", "42.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/01f8354111f50d5824af05bc02c7b452.png",
                "Mochi Socola (1 Cái)", "17.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/7e185577c24c4cd65119df670e0a6047.png",
                "Mochi Socola (3 Cái)", "42.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/83d3d0737b42e9066328c815692a2ea8.jpg",
                "Bánh Trứng (1 cái)", "17.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/be95ff8508fa5aacb78baba4a6b644c1.jpg",
                "Bánh Trứng (4 Cái)", "50.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/8f9a4cb3b943dd9d363fe889f1a977f2.png",
                "Pepsi Vị Chanh Không Calo Lon", "17.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/d9e2a3a3bd13fcf569f714339220ea7b.png",
                "Pepsi Lon", "17.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/31e08694578dae58aa5d0bbfc4d66b38.png",
                "7Up Lon", "17.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/ec86fc55906339789edbd992856951f8.jpg",
                "Hộp Milo", "19.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/4dd31878f442ea6c57c9e6264efa84b2.jpg",
                "Aquafina", "15.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/f7bad00dc06e32ac5eea439cfb61110b.png",
                "Twister Lon", "17.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/f7afb1144d4f249aaf3129f8a2b4f697.jpg",
                "Trà đào", "24.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/164d3fb94d979df7980d0ff105e13805.png",
                "Trà Sữa Bạc Hà", "19.000đ", 1));
    }


}
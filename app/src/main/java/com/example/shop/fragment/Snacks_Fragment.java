package com.example.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.shop.R;
import com.example.shop.adapter.ProductALaCarteAdapter;
import com.example.shop.adapter.ProductAdapter;
import com.example.shop.interfaces.OnALaCarteProductClickListener;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.ultil.BannerImage;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.ProductALaCarte;

import java.util.ArrayList;

public class Snacks_Fragment extends Fragment implements OnALaCarteProductClickListener {

    RecyclerView recyclerViewProductSnacks;
    ArrayList<ProductALaCarte> mListProduct;
    ProductALaCarteAdapter mProductAdapter;
    OnALaCarteProductClickListener onALaCarteProductClickListener;
    Context context;

    public Snacks_Fragment() {
        // Required empty public constructor
    }

    public Snacks_Fragment(Context context){
        this.context = context;
    }

    public void setOnALaCarteProductClickListener(OnALaCarteProductClickListener onALaCarteProductClickListener){
        this.onALaCarteProductClickListener = onALaCarteProductClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_snacks, container, false);
        mListProduct = new ArrayList<>();
        addProductInfo();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mProductAdapter = new ProductALaCarteAdapter(mListProduct, getContext());
        recyclerViewProductSnacks = view.findViewById(R.id.recyclerViewSnacks);
        recyclerViewProductSnacks.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewProductSnacks.setAdapter(mProductAdapter);
        mProductAdapter.setOnALaCarteProductClickListener(this);

        return view;
    }

    private void addProductInfo() {
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/cd5012f29bd76d8805ddfb640c21c23c.jpg",
                "Popcorn (Vừa)","37.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/8736783e7ab937ee6b59ab7346e200ca.jpg",
                "Popcorn (Lớn)","57.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/e3f046e3043dae1b45ce325f4ba68cec.jpg",
                "Phô mai viên (4 viên)","29.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/8d6a82cd5cad07813d853985275fbede.jpg",
                "Phô mai viên (6 viên)","39.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/7e3a9a03ff13da14ba9ee73bc0a4511f.jpg",
                "Mashies nhân Gravy (3 viên)","19.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/71f34bde66f239d1af6ffadd137c59e6.jpg",
                "Mashies nhân Gravy (5 viên)","29.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/1cd1820236113bc739d49aac82f5c16e.jpg",
                "Mashies nhân Rau Củ (3 viên)","25.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/654b474f6d239540fd535147212a9b12.jpg",
                "Mashies nhân Rau Củ (5 viên)","35.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/63cb9709f28aaba108da830645919952.jpg",
                "Cá Thanh (3 Thanh)","41.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/993d32a7ab814af33860a97b78f78459.jpg",
                "Xà Lách KFC","16.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/f278dfdb191c3bdccbc1d01d9874ea8d.jpg",
                "Xà Lách Gà KFC","20.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/a820f32c8875fe1c784f3d352fead5fa.jpg",
                "Khoai Tây Chiên (Vừa)","14.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/ceaee98fe27abcf5c1230f0fdc2d29af.jpg",
                "Khoai Tây Chiên (Lớn)","27.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/b4431f0780fa184caf27f7902a890bad.jpg",
                "Khoai Tây Chiên (Đại)","37.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/81ecda181b4ef5da7f9ac148f285e759.jpg",
                "Bắp cải trộn (Vừa)","12.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/ef4d1427f0b85019c847b3853cbe6295.jpg",
                "Bắp cải trộn (Lớn)","22.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/3d08287366dad49a0e2ac0d90f9d7ff3.jpg",
                "Bắp Cải Trộn (Đại)","32.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/da78b7e7961db72ce9f6b892acce589a.jpg",
                "Khoai tây nghiền (Vừa)","12.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/6b63f8851c3fb35a9ba4348a6f88a2e1.jpg",
                "Khoai tây nghiền (Lớn)","22.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/15ad4925cbe7d17cc866c806078e15ba.jpg",
                "Khoai tây nghiền (Đại)","32.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/be88d03a8d38c4d420e08877256ee0be.jpg",
                "Cơm Trắng","10.000đ", 1));
        mListProduct.add(new ProductALaCarte("https://kfcvietnam.com.vn/uploads/product/424f9e1de8a97ee7b24dc1dff53fcffc.jpg",
                "Súp Gà","12.000đ", 1));
    }


    @Override
    public void onALaCarteOrderClickListener(String foodPrice) {
        onALaCarteProductClickListener.onALaCarteOrderClickListener(foodPrice);
    }
}
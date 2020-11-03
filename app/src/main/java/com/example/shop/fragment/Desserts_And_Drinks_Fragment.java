package com.example.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.shop.R;
import com.example.shop.adapter.ProductALaCarteAdapter;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.ultil.BannerImage;
import com.example.shop.ultil.Product;

import java.util.ArrayList;

public class Desserts_And_Drinks_Fragment extends Fragment implements OnProductClickListener {

    RecyclerView recyclerViewViewProductDessertsAndDrinks;
    ArrayList<Product> mListProduct;
    ProductALaCarteAdapter mProductAdapter;
    OnProductClickListener onProductClickListener;
    Context context;
    ArrayList<BannerImage> MatchaMochi1Pc, MatchaMochi3Pcs, ChocolateMochi1Pc, ChocolateMochi3Pcs, Tart1Pc, Tart4Pcs,
                            PepsiLimeFlavour, PepsiCola, SevenUp ,Milo, Water, Twister, PeachTea, MintMilkTea;
    public Desserts_And_Drinks_Fragment() {
        // Required empty public constructor
    }

    public Desserts_And_Drinks_Fragment(Context context) {
        this.context = context;
    }

    public void setProductClickListener(OnProductClickListener onProductClickListener){
        this.onProductClickListener = onProductClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desserts_and_drinks, container, false);
        mListProduct = new ArrayList<>();
        createBannerListForProduct();
        addProductInfo();
        mProductAdapter = new ProductALaCarteAdapter(mListProduct, getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewViewProductDessertsAndDrinks = view.findViewById(R.id.recyclerViewDessertsAndDrinks);
        recyclerViewViewProductDessertsAndDrinks.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewViewProductDessertsAndDrinks.setAdapter(mProductAdapter);
        mProductAdapter.setOnProductClickListener(this);

        return view;
    }

    private void addProductInfo() {
        mListProduct.add(new Product(MatchaMochi1Pc, "Mochi Trà Xanh (1 Cái)", "17.000đ", "",1));
        mListProduct.add(new Product(MatchaMochi3Pcs, "Mochi Trà Xanh (3 Cái)", "42.000đ", "",1));
        mListProduct.add(new Product(ChocolateMochi1Pc, "Mochi Socola (1 Cái)", "17.000đ", "",1));
        mListProduct.add(new Product(ChocolateMochi3Pcs, "Mochi Socola (3 Cái)", "42.000đ", "",1));
        mListProduct.add(new Product(Tart1Pc, "Bánh Trứng (1 cái)", "17.000đ", "",1));
        mListProduct.add(new Product(Tart4Pcs, "Bánh Trứng (4 Cái)", "50.000đ", "",1));
        mListProduct.add(new Product(PepsiLimeFlavour, "Pepsi Vị Chanh Không Calo Lon", "17.000đ", "",1));
        mListProduct.add(new Product(PepsiCola, "Pepsi Lon", "17.000đ", "",1));
        mListProduct.add(new Product(SevenUp, "7Up Lon", "17.000đ", "",1));
        mListProduct.add(new Product(Milo, "Hộp Milo", "19.000đ","", 1));
        mListProduct.add(new Product(Water, "Aquafina", "15.000đ", "",1));
        mListProduct.add(new Product(Twister, "Twister Lon", "17.000đ", "",1));
        mListProduct.add(new Product(PeachTea, "Trà đào", "24.000đ", "",1));
        mListProduct.add(new Product(MintMilkTea, "Trà Sữa Bạc Hà", "19.000đ", "",1));
    }

    private void createBannerListForProduct() {

        MatchaMochi1Pc = new ArrayList<>();
        MatchaMochi1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/90e669f4a4213f0ca59cae3df3edb37f.png"));

        MatchaMochi3Pcs = new ArrayList<>();
        MatchaMochi3Pcs.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/5e116447e65bd1ff5c76370e054fdd50.png"));

        ChocolateMochi1Pc = new ArrayList<>();
        ChocolateMochi1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/01f8354111f50d5824af05bc02c7b452.png"));

        ChocolateMochi3Pcs = new ArrayList<>();
        ChocolateMochi3Pcs.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/7e185577c24c4cd65119df670e0a6047.png"));

        Tart1Pc = new ArrayList<>();
        Tart1Pc.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/83d3d0737b42e9066328c815692a2ea8.jpg"));

        Tart4Pcs = new ArrayList<>();
        Tart4Pcs.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/be95ff8508fa5aacb78baba4a6b644c1.jpg"));

        PepsiLimeFlavour = new ArrayList<>();
        PepsiLimeFlavour.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/8f9a4cb3b943dd9d363fe889f1a977f2.png"));

        PepsiCola = new ArrayList<>();
        PepsiCola.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/d9e2a3a3bd13fcf569f714339220ea7b.png"));

        SevenUp = new ArrayList<>();
        SevenUp.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/31e08694578dae58aa5d0bbfc4d66b38.png"));

        Milo = new ArrayList<>();
        Milo.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/ec86fc55906339789edbd992856951f8.jpg"));

        Water = new ArrayList<>();
        Water.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/4dd31878f442ea6c57c9e6264efa84b2.jpg"));

        Twister = new ArrayList<>();
        Twister.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/f7bad00dc06e32ac5eea439cfb61110b.png"));

        PeachTea = new ArrayList<>();
        PeachTea.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/f7afb1144d4f249aaf3129f8a2b4f697.jpg"));

        MintMilkTea = new ArrayList<>();
        MintMilkTea.add(new BannerImage("https://kfcvietnam.com.vn/uploads/product/164d3fb94d979df7980d0ff105e13805.png"));

    }

    @Override
    public void onSettingProduct(Product product) {

    }

    @Override
    public void onOrderProduct(Product product) {
        onProductClickListener.onOrderProduct(product);
    }

}
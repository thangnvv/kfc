package com.example.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.shop.R;
import com.example.shop.adapter.ProductALaCarteAdapter;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.ultil.BannerImage;
import com.example.shop.ultil.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Snacks_Fragment extends Fragment implements OnProductClickListener{

    RecyclerView recyclerViewProductSnacks;
    ArrayList<Product> mListProduct;
    ProductALaCarteAdapter mProductAdapter;
    OnProductClickListener onProductClickListener;
    Context context;

    public Snacks_Fragment() {
        // Required empty public constructor
    }

    public Snacks_Fragment(Context context){
        this.context = context;
    }

    public void setProductClickListener(OnProductClickListener onProductClickListener){
        this.onProductClickListener = onProductClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListProduct = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("product/snacks");

        myRef.orderByChild("position").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                mListProduct.add(snapshot.getValue(Product.class));
                if(mProductAdapter != null){
                    mProductAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_snacks, container, false);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mProductAdapter = new ProductALaCarteAdapter(mListProduct, context);
        recyclerViewProductSnacks = view.findViewById(R.id.recyclerViewSnacks);
        recyclerViewProductSnacks.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewProductSnacks.setAdapter(mProductAdapter);
        mProductAdapter.setOnProductClickListener(this);


        return view;
    }

    @Override
    public void onSettingProduct(Product product) {

    }

    @Override
    public void onOrderProduct(Product product) {
        onProductClickListener.onOrderProduct(product);
    }

}
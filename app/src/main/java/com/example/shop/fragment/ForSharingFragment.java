package com.example.shop.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.ProductAdapter;
import com.example.shop.interfaces.OnFragmentScrollListener;
import com.example.shop.interfaces.OnProductClickListener;
import com.example.shop.objects.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class ForSharingFragment extends Fragment implements OnProductClickListener{

    RecyclerView recyclerViewProductGroup;
    ArrayList<Product> mListProduct;
    ProductAdapter mProductAdapter;
    OnProductClickListener onProductClickListener;
    OnFragmentScrollListener onFragmentScrollListener;
    Context context;

    public ForSharingFragment() {
        // Required empty public constructor
    }
    public ForSharingFragment(Context context) {
        this.context = context;
    }

    public void setProductClickListener(OnProductClickListener onProductClickListener){
        this.onProductClickListener = onProductClickListener;
    }

    public void setOnFragmentScrollListener(OnFragmentScrollListener onFragmentScrollListener){
        this.onFragmentScrollListener = onFragmentScrollListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListProduct = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("product/for_sharing_combo");

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

        View view = inflater.inflate(R.layout.fragment_combo_group, container, false);
        mProductAdapter = new ProductAdapter(mListProduct, getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewProductGroup = view.findViewById(R.id.recyclerViewComboGroup);
        recyclerViewProductGroup.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewProductGroup.setAdapter(mProductAdapter);
        mProductAdapter.setOnProductClickListener(this);
        recyclerViewProductGroup.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                onFragmentScrollListener.onScroll();
            }
        });
        return view;
    }

    @Override
    public void onSettingProduct(Product product) {
        Hawk.put("productSetting", product);
        onProductClickListener.onSettingProduct(product);
    }

    @Override
    public void onOrderProduct(Product product) {
        onProductClickListener.onOrderProduct(product);
    }
}
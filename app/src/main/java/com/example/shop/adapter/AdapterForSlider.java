package com.example.shop.adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.ultil.BannerImage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterForSlider extends SliderViewAdapter<AdapterForSlider.SliderAdapterVH> {

    private Context context;
    private ArrayList<String> mSliderItems;

    public AdapterForSlider(Context context, ArrayList<String> mSliderItems) {
        this.context        = context;
        this.mSliderItems   = mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(final SliderAdapterVH viewHolder, final int position) {


        Glide.with(viewHolder.itemView)
                .load(mSliderItems.get(position))
                .fitCenter()
                .into(viewHolder.imageBannerMain);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageBannerMain;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageBannerMain = itemView.findViewById(R.id.imageBanner);
            this.itemView = itemView;
        }
    }

}
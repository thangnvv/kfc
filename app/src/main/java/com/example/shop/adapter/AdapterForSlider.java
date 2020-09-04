package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.ultil.BannerImage;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class AdapterForSlider extends
        SliderViewAdapter<AdapterForSlider.SliderAdapterVH> {

    private Context context;
    private List<BannerImage> mSliderItems;

    public AdapterForSlider(Context context, List<BannerImage> mSliderItems) {
        this.context        = context;
        this.mSliderItems   = mSliderItems;
    }



    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        BannerImage sliderItem = mSliderItems.get(position);

        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImageUrl())
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
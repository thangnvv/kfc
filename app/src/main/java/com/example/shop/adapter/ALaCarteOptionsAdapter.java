package com.example.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;

public class ALaCarteOptionsAdapter extends RecyclerView.Adapter<ALaCarteOptionsAdapter.ViewHolder>{
    Context context;
    int currentChoosenPosition = 0;
    String chosenName;
    String[] aLaCarteLine;
    OnChooseListener onChooseListener;

    public void setOnChooseListener(OnChooseListener onChooseListener){
        this.onChooseListener = onChooseListener;
    }


    public ALaCarteOptionsAdapter(Context context, String chosenName, String[] aLaCarteLine) {
        this.context = context;
        this.chosenName = chosenName;
        this.aLaCarteLine = aLaCarteLine;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.alacarte_line, parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textViewALaCarteLine.setText(aLaCarteLine[position]);

        Log.d("DDD", "In Alacarte options adapter: chosen name: " + chosenName.trim() + "; current position name: " + aLaCarteLine[position].trim());

        if(aLaCarteLine[position].trim().equals(chosenName.trim())){
            holder.linearLayoutCheckChoose.setVisibility(View.VISIBLE);
            currentChoosenPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return aLaCarteLine.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewALaCarteLine;
        private LinearLayout linearLayoutCheckChoose;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewALaCarteLine = itemView.findViewById(R.id.textViewALaCarteLine);
            linearLayoutCheckChoose = itemView.findViewById(R.id.linearLayoutCheckChoose);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayoutCheckChoose.setVisibility(View.VISIBLE);
                    onChooseListener.unCheckFoodName(aLaCarteLine[getAdapterPosition()]);
                }
            });
        }
    }

    public interface OnChooseListener{
        void unCheckFoodName(String foodName);
    }
}

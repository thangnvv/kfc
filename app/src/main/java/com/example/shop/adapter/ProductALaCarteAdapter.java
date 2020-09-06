package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.ultil.Product;
import com.example.shop.ultil.ProductALaCarte;

import java.io.InputStream;
import java.util.ArrayList;

public class ProductALaCarteAdapter extends BaseAdapter {

    private ArrayList<ProductALaCarte> mProductList;
    private Context context;
    TextView mTextViewFoodName, mTextViewFoodPrice, mTextViewPortion;
    ImageView mImageViewBanner;
    Button mButtonOrder;
    ImageButton mImageButtonPlus, mImageButtonMinus;

    public ProductALaCarteAdapter(ArrayList<ProductALaCarte> mProductList, Context context) {
        this.mProductList = mProductList;
        this.context = context;
    }

    public ArrayList<ProductALaCarte> getmProductList() {
        return mProductList;
    }

    public void setmProductList(ArrayList<ProductALaCarte> mProductList) {
        this.mProductList = mProductList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProductALaCarte productALaCarte = mProductList.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_product_alacarte, null);
        initView(view);


        mTextViewFoodName.setText(productALaCarte.getFoodName());
        mTextViewFoodPrice.setText(productALaCarte.getFoodPrice());
        mTextViewPortion.setText(productALaCarte.getPortion() + "");
        new DownloadImageTask((ImageView) view.findViewById(R.id.imgViewBanner))
                .execute(productALaCarte.getUrlImageBanner());

        return view;
    }

    private void initView(View view){
        mTextViewFoodName  = view.findViewById(R.id.txtViewProductName);
        mTextViewFoodPrice = view.findViewById(R.id.txtViewProductPrice);
        mTextViewPortion   = view.findViewById(R.id.txtViewPortion);
        mImageButtonMinus  = view.findViewById(R.id.imgButtonMinus);
        mImageButtonPlus   = view.findViewById(R.id.imgButtonPlus);
        mButtonOrder       = view.findViewById(R.id.btnOrderProduct);

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

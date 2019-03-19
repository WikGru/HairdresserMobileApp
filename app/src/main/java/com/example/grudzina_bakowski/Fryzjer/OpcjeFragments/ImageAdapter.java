package com.example.grudzina_bakowski.Fryzjer.OpcjeFragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.grudzina_bakowski.Fryzjer.R;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    Integer[] images={R.raw.pic_1,R.raw.pic_2,R.raw.pic_3,R.raw.pic_4,R.raw.pic_5,R.raw.pic_6,R.raw.pic_7};
    // trzeba dorobić żeby dynamicznie dodawało , nie miałem czasu xd
    public ImageAdapter(Context context2){
        context=context2;
    }
    @Override
    public int getCount() {
        return images.length;
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
        ImageView imageView =new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
       imageView.setLayoutParams(new GridView.LayoutParams(700,700));
        return imageView;
    }
}

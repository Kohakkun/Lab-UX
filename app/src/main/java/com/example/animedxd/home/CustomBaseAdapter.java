package com.example.animedxd.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animedxd.R;

public class CustomBaseAdapter extends BaseAdapter {
    Context ctx;
    String mangaList[];
    int mangaImage[];
    String mangaSynopsis[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, String[] mangaList, int[] mangaImage, String[] mangaSynopsis){
        this.ctx = ctx;
        this.mangaList = mangaList;
        this.mangaImage = mangaImage;
        this.mangaSynopsis = mangaSynopsis;
        inflater = LayoutInflater.from(ctx);

    }
    @Override
    public int getCount() {
        return mangaList.length;
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
        convertView = inflater.inflate(R.layout.activity_custom_list_home, null);
        TextView judul = convertView.findViewById(R.id.judul);
        TextView synopsis = convertView.findViewById(R.id.synopsis);
        ImageView cover = convertView.findViewById(R.id.gambarCover);
        judul.setText(mangaList[position]);
        synopsis.setText(mangaSynopsis[position]);
        cover.setImageResource(mangaImage[position]);

        return convertView;
    }
}

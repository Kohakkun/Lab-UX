package com.example.animedxd.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animedxd.R;
import com.example.animedxd.home.CustomBaseAdapter;

public class CustomBaseAdapterList extends BaseAdapter {

    Context ctx;
    String angka[];
    int animeCover[];
    String judulAnime[];
    String genreAnime[];
    String synopsisAnime[];
    LayoutInflater inflater;

    public CustomBaseAdapterList(Context ctx, String[] angka, int[] animeCover, String[] judulAnime, String[] genreAnime, String[] synopsisAnime){
        this.ctx = ctx;
        this.angka = angka;
        this.animeCover = animeCover;
        this.judulAnime = judulAnime;
        this.genreAnime = genreAnime;
        this.synopsisAnime = synopsisAnime;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return judulAnime.length;
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

        convertView = inflater.inflate(R.layout.activity_custom_list_list, null);
        TextView angka2 = convertView.findViewById(R.id.angka);
        ImageView animeCover2 = convertView.findViewById(R.id.gambarCoverList);
        TextView judulList2 = convertView.findViewById(R.id.judulList);
        TextView genreList2 = convertView.findViewById(R.id.genreList);
        TextView synopsisList2 = convertView.findViewById(R.id.synopsisList);

        angka2.setText(angka[position]);
        animeCover2.setImageResource(animeCover[position]);
        judulList2.setText(judulAnime[position]);
        genreList2.setText(genreAnime[position]);
        synopsisList2.setText(synopsisAnime[position]);

        return convertView;
    }
}

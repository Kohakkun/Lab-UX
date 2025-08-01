package com.example.animedxd.list;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

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
    private final Typeface customFont1;
    private final Typeface customFont2;
    private final Typeface customFont3;


    public CustomBaseAdapterList(Context ctx, String[] angka, int[] animeCover, String[] judulAnime, String[] genreAnime, String[] synopsisAnime){
        this.ctx = ctx;
        this.angka = angka;
        this.animeCover = animeCover;
        this.judulAnime = judulAnime;
        this.genreAnime = genreAnime;
        this.synopsisAnime = synopsisAnime;
        inflater = LayoutInflater.from(ctx);
        this.customFont1 = ResourcesCompat.getFont(ctx, R.font.montserrat_bold);
        this.customFont2 = ResourcesCompat.getFont(ctx, R.font.montserrat_semibold);
        this.customFont3 = ResourcesCompat.getFont(ctx, R.font.montserrat_regular);
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
        judulList2.setTypeface(customFont1);
        genreList2.setText(genreAnime[position]);
        genreList2.setTypeface(customFont2);
        synopsisList2.setText(synopsisAnime[position]);
        synopsisList2.setTypeface(customFont3);



        return convertView;
    }
}

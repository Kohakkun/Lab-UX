package com.example.animedxd.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.animedxd.R;
import com.example.animedxd.databinding.FragmentHomeNewsBinding;

import java.util.ArrayList;


public class HomeNewsFragment extends Fragment {

    FragmentHomeNewsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeNewsBinding.inflate(getLayoutInflater());

        ImageSlider imageSlider = binding.imageSlider;
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.animenews1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.animenews2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.animenews3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.animenews4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.animenews5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.animenews6, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        binding.tabManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mangaFrag = new HomeMangaFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, mangaFrag).commit();
            }
        });

        return binding.getRoot();
    }

}
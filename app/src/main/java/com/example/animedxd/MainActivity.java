package com.example.animedxd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.animedxd.databinding.ActivityMainBinding;
import com.example.animedxd.home.HomeNewsFragment;
import com.example.animedxd.list.ListFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        replaceFragment(new HomeNewsFragment());
        binding.bottomNav.setBackground(null);

        binding.bottomNav.setOnItemSelectedListener(item -> {
           if(item.getItemId() == R.id.homeLogo){
               replaceFragment(new HomeNewsFragment());
           }
           else if(item.getItemId() == R.id.listLogo){
               replaceFragment(new ListFragment());
           }

            return true;
        });

        //        binding.toolBar.setOnClickListener(item ->{
//            if(item.getId() == R.id.logOut){
//
//            }
//        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
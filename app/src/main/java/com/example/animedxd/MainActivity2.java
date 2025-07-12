package com.example.animedxd;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.animedxd.databinding.ActivityMain2Binding;
import com.example.animedxd.databinding.ActivityMainBinding;
import com.example.animedxd.detail.AttackOnTitanDetailFragment;
import com.example.animedxd.detail.BlackCloverDetailFragment;
import com.example.animedxd.detail.BlueLockDetailFragment;
import com.example.animedxd.detail.BorutoDetailFragment;
import com.example.animedxd.detail.NoragamiDetailFragment;
import com.example.animedxd.detail.ReZeroDetailFragment;
import com.example.animedxd.detail.SoloLevelingDetailFragment;
import com.example.animedxd.detail.SpyXFamilyDetailFragment;
import com.example.animedxd.detail.TokyoRevengersDetailFragment;
import com.example.animedxd.detail.YourLieInAprilDetailFragment;
import com.example.animedxd.home.HomeNewsFragment;
import com.example.animedxd.list.ListFragment;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String fragmentName = getIntent().getStringExtra("fragment_name");
        Fragment fragment = null;

        binding.back.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            intent.putExtra("fragment_to_open", "ListFragment");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // Optional, biar MainActivity2 ditutup
        });

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

        switch (fragmentName) {
            case "SoloLeveling":
                fragment = new SoloLevelingDetailFragment();
                break;
            case "BlackClover":
                fragment = new BlackCloverDetailFragment();
                break;
            case "Boruto":
                fragment = new BorutoDetailFragment();
                break;
            case "BlueLock":
                fragment = new BlueLockDetailFragment();
                break;
            case "AttackOnTitan":
                fragment = new AttackOnTitanDetailFragment();
                break;
            case "SpyXFamily":
                fragment = new SpyXFamilyDetailFragment();
                break;
            case "YourLieInApril":
                fragment = new YourLieInAprilDetailFragment();
                break;
            case "TokyoRevengers":
                fragment = new TokyoRevengersDetailFragment();
                break;
            case "ReZero":
                fragment = new ReZeroDetailFragment();
                break;
            case "Noragami":
                fragment = new NoragamiDetailFragment();
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout2, fragment)
                    .commit();
        }

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}
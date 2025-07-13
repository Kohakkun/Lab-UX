// File: app/src/main/java/com/example/animedxd/MainActivity2.java
package com.example.animedxd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.animedxd.databinding.ActivityMain2Binding;
import com.example.animedxd.detail.DetailFragment;
import com.example.animedxd.model.Anime;
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

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.back.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            intent.putExtra("fragment_to_open", "ListFragment");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        binding.bottomNav.setBackground(null);

        binding.bottomNav.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.homeLogo){
                replaceMainContentFragment(new HomeNewsFragment());
                return true;
            }
            else if(item.getItemId() == R.id.listLogo){
                replaceMainContentFragment(new ListFragment());
                return true;
            }
            return false;
        });

        Anime anime = (Anime) getIntent().getSerializableExtra("anime_data");

        if (anime != null) {
            DetailFragment detailFragment = DetailFragment.newInstance(anime);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout2, detailFragment)
                    .commit();

            binding.bottomNav.setVisibility(View.GONE);
            if (binding.frameLayout != null) {
                binding.frameLayout.setVisibility(View.GONE);
            }
            if (binding.frameLayout2 != null) {
                binding.frameLayout2.setVisibility(View.VISIBLE);
            }

        } else {
            if (binding.frameLayout != null) {
                binding.frameLayout.setVisibility(View.VISIBLE);
                replaceMainContentFragment(new HomeNewsFragment());
            }
            if (binding.frameLayout2 != null) {
                binding.frameLayout2.setVisibility(View.GONE);
            }
            binding.bottomNav.setVisibility(View.VISIBLE);
        }
    }

    private void replaceMainContentFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
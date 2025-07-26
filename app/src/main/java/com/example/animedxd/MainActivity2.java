package com.example.animedxd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

// TAMBAHKAN IMPORT INI
import com.example.animedxd.about.AboutFragment;
import com.example.animedxd.databinding.ActivityMain2Binding;
import com.example.animedxd.detail.DetailFragment;
import com.example.animedxd.login.LoginActivity;
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

        setSupportActionBar(binding.toolBar);

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

        // CUKUP SATU BLOK LISTENER INI SAJA
        binding.bottomNav.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.homeLogo){
                replaceMainContentFragment(new HomeNewsFragment());
                return true;
            }
            else if(item.getItemId() == R.id.listLogo){
                replaceMainContentFragment(new ListFragment());
                return true;
            }
            else if(item.getItemId() == R.id.aboutLogo){
                replaceMainContentFragment(new AboutFragment());
                return true;
            }
            return false;
        });

        setSupportActionBar(binding.toolBar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logOut) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Letakkan method ini di dalam kelas Activity Anda (misal, di bawah onCreate)
    private void logout() {
        // 1. Hapus data dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AnimeDXDPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Menghapus semua data sesi
        editor.apply();

        // 2. Buat Intent untuk kembali ke LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);

        // 3. Set flags untuk membersihkan semua activity sebelumnya
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // 4. Jalankan Intent dan tutup Activity saat ini
        startActivity(intent);
        finish();
    }

    private void replaceMainContentFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
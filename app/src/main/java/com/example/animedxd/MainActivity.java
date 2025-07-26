package com.example.animedxd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.animedxd.about.AboutFragment;
import com.example.animedxd.databinding.ActivityMainBinding;
import com.example.animedxd.home.HomeNewsFragment;
import com.example.animedxd.list.ListFragment;
import com.example.animedxd.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolBar);

        if (savedInstanceState == null) {
            String fragmentName = getIntent().getStringExtra("fragment_to_open");
            if ("ListFragment".equals(fragmentName)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new ListFragment())
                        .commit();
                // TAMBAHKAN BARIS INI untuk update tampilan navbar
                binding.bottomNav.setSelectedItemId(R.id.listLogo);
            } else {
                // Default behavior
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new HomeNewsFragment())
                        .commit();
                // Secara default, navbar akan memilih item pertama (Home)
            }
        }

        setSupportActionBar(binding.toolBar);

        binding.bottomNav.setBackground(null);

        binding.bottomNav.setOnItemSelectedListener(item -> {
           if(item.getItemId() == R.id.homeLogo){
               replaceFragment(new HomeNewsFragment());
           }
           else if(item.getItemId() == R.id.listLogo){
               replaceFragment(new ListFragment());
           }
           else if(item.getItemId() == R.id.aboutLogo){
               replaceFragment(new AboutFragment());
               return true;
           }
            return true;
        });

        //        binding.toolBar.setOnClickListener(item ->{
//            if(item.getId() == R.id.logOut){
//
//            }
//        });
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

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
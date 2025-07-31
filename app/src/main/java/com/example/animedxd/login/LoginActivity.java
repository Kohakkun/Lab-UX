package com.example.animedxd.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.animedxd.MainActivity;
import com.example.animedxd.MainActivity2;
import com.example.animedxd.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    // 1. Deklarasi komponen UI yang akan digunakan
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Menghubungkan ke file XML Anda

        // 2. Inisialisasi komponen UI dengan findViewById
        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        signInButton = findViewById(R.id.sign_in_button);

        // 3. Mengatur listener saat tombol di-klik
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil method untuk validasi dan login
                validateAndLogin();
            }
        });
    }

    private void validateAndLogin() {
        // Ambil input dari pengguna dan hapus spasi di awal/akhir
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // 4. Proses Validasi sesuai soal
        if (TextUtils.isEmpty(username)) {
            // Tampilkan error jika username kosong
            usernameEditText.setError("Username must be filled in");
            usernameEditText.requestFocus();
            return; // Hentikan proses
        }

        if (username.length() < 5 || username.length() > 10) {
            // Tampilkan error jika panjang username tidak sesuai
            usernameEditText.setError("Length of username must be 5 - 10 characters");
            usernameEditText.requestFocus();
            return; // Hentikan proses
        }

        if (TextUtils.isEmpty(password)) {
            // Tampilkan error jika password kosong
            passwordEditText.setError("Password must be filled in");
            passwordEditText.requestFocus();
            return; // Hentikan proses
        }

        // ---- Simulasi Login Sukses ----
        // Jika semua validasi berhasil:

        // Sesuai soal: "Store the username to the global variable that will be used later."
        // Kita bisa gunakan Intent untuk mengirim data username ke Activity berikutnya.
        // MainActivity2 adalah halaman Home berdasarkan soal.

        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

// 1. Simpan username ke SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AnimeDXDPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LOGGED_IN_USERNAME", username);
        editor.putBoolean("IS_LOGGED_IN", true); // Simpan status login
        editor.apply(); // Gunakan apply() untuk menyimpan secara asynchronous

// 2. Pindah ke halaman utama
        Intent intentToHome = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intentToHome);
        finish(); // Tetap panggil finish()
    }
}
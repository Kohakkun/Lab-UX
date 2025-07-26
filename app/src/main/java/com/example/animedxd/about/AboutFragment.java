package com.example.animedxd.about;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.animedxd.R;
import com.example.animedxd.databinding.FragmentAboutBinding;
import com.example.animedxd.login.LoginActivity;

import static android.content.Context.MODE_PRIVATE;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // PERBAIKAN 3: Menambahkan pengecekan null untuk keamanan
        if (getActivity() != null) {
            // Ambil username dari SharedPreferences
            SharedPreferences prefs = getActivity().getSharedPreferences("AnimeDXDPrefs", MODE_PRIVATE);
            String username = prefs.getString("LOGGED_IN_USERNAME", "Guest");

            // Set pesan sambutan
            binding.textGreetingAbout.setText("Welcome, " + username);
        }

    }

    private void showPopupMenu(View view) {
        if (getActivity() == null) return;

        PopupMenu popup = new PopupMenu(getActivity(), view);
        // Ganti R.menu.popup_menu menjadi R.menu.header
        popup.getMenuInflater().inflate(R.menu.header, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            // Ganti R.id.action_logout menjadi R.id.logOut
            if (item.getItemId() == R.id.logOut) {
                logoutUser();
                return true;
            }
            return false;
        });
        popup.show();
    }

    private void logoutUser() {
        if (getActivity() == null) return;

        SharedPreferences prefs = getActivity().getSharedPreferences("AnimeDXDPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
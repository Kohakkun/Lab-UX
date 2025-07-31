package com.example.animedxd.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animedxd.R;
import com.example.animedxd.databinding.FragmentHomeMangaBinding;

public class HomeMangaFragment extends Fragment {

    FragmentHomeMangaBinding binding;

    String mangaList[] = {"Jujutsu Kaisen", "Naruto", "Sakamoto Days", "One Piece", "Fullmetal Alchemist", "Chainsaw Man", "Fairy Tail"};
    int mangaImage[] = {R.drawable.mangacoer4, R.drawable.mangacover2, R.drawable.mangacover1, R.drawable.mangacover3, R.drawable.mangacover8, R.drawable.mangacover9, R.drawable.mangacover10};

    String mangaSynopsis[] = {"Jujutsu Kaisen follows Yuji Itadori as he battles deadly curses after swallowing a powerful cursed object. He trains as a sorcerer while uncovering dark secrets and facing dangerous foes.",
            "Naruto follows a young ninja, Naruto Uzumaki, who seeks recognition and dreams of becoming the strongest ninja, the Hokage. Along the way, he faces powerful enemies and uncovers the truth about his past.",
            "Sakamoto Days follows a retired hitman turned convenience store owner who’s forced back into action to protect his peaceful life. Despite his dad-bod appearance, he’s still a legendary killer with unbeatable skills.",
            "One Piece follows Monkey D. Luffy, a spirited pirate with a rubber body, on his quest to find the legendary treasure and become the Pirate King. Along the way, he builds a loyal crew and faces powerful foes across the vast seas.",
            "Fullmetal Alchemist follows two brothers, Edward and Alphonse Elric, who use alchemy in a dangerous quest to restore their bodies after a tragic accident. Their journey uncovers dark secrets about their world and the cost of forbidden knowledge.",
            "Chainsaw Man centers on Denji, a devil hunter who merges with his chainsaw devil pet to survive, gaining terrifying powers. Caught between devils and devil hunters, he battles for a better life in a brutal, chaotic world.",
            "Fairy Tail follows Lucy Heartfilia and Natsu Dragneel as they take on thrilling adventures with their magical guild. Together with their friends, they fight dark forces and protect the bonds they hold dear."};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeMangaBinding.inflate(getLayoutInflater());

        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getActivity().getApplicationContext(), mangaList, mangaImage, mangaSynopsis);
        binding.listView.setAdapter(customBaseAdapter);

        binding.tabNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newsFrag = new HomeNewsFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, newsFrag).commit();
            }
        });

        if (getActivity() != null) {
            // Ambil username dari SharedPreferences
            SharedPreferences prefs = getActivity().getSharedPreferences("AnimeDXDPrefs", MODE_PRIVATE);
            String username = prefs.getString("LOGGED_IN_USERNAME", "Guest");

            // Set pesan sambutan
            binding.welcome.setText("Welcome, " + username);
        }

        return binding.getRoot();
    }


}
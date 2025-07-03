package com.example.animedxd.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.animedxd.R;
import com.example.animedxd.databinding.FragmentListBinding;
import com.example.animedxd.detail.DetailFragment;

public class ListFragment extends Fragment {

    FragmentListBinding binding;

    String[] angka = {"1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9.", "10."};
    int[] animeCover = {R.drawable.animecover1, R.drawable.animecover2, R.drawable.animecover3, R.drawable.animecover4, R.drawable.animecover5, R.drawable.animecover6, R.drawable.animecover7, R.drawable.animecover8, R.drawable.animecover9, R.drawable.animecover10};
    String[] judulAnime = {"Solo Levelling", "Black Clover", "Boruto", "Blue Lock", "Attack On Titan", "Spy x Family", "Your Lie in April", "Tokyo Revengers", "Re:Zero", "Noragami"};
    String[] genreAnime = {"Genre : Action, Regressor, Fantasy", "Genre : Action, Magic, Adventure", "Genre : Action, Martial Arts, Shounen", "Genre : Sports, Psychological, Drama", "Genre : Action, Dark Fantasy, Military", "Genre : Action, Comedy, Slice of Life", "Genre : Drama, Romance, Music", "Genre : Action, Supernatural, Sci-Fi (Time Travel)", "Genre :  Isekai, Dark Fantasy, Drama", "Genre : Action, Supernatural, Urban Fantasy"};
    String[] synopsisAnime = {" Solo Leveling follows Jinwoo Sung, a weak hunter who gains the power to level up infinitely after a mysterious dungeon incident. He rises from the weakest to the most powerful, facing monsters, secrets, and gods.",
    "Black Clover follows Asta, a boy born without magic in a world where magic is everything. With sheer determination and a rare anti-magic power, he aims to become the Wizard King.",
    "Boruto follows Naruto’s son, Boruto Uzumaki, as he tries to forge his own ninja path beyond his father’s shadow. New threats emerge, pushing him and his friends to grow stronger than ever.",
    "Blue Lock centers on a nationwide project to create Japan’s best striker through intense, cutthroat competition. Yoichi Isagi fights to rise to the top and redefine what it means to be the best.",
    "Attack on Titan follows Eren Yeager as he battles towering man-eating titans after his world is shattered. As secrets unravel, he questions freedom, enemies, and his own humanity.",
    " Spy x Family follows a spy who must build a fake family for a mission—unaware his wife is an assassin and his adopted daughter is a telepath. Together, they juggle secret lives while pretending to be the perfect family.",
    "Your Lie in April tells the story of a piano prodigy who rediscovers music and emotion through a free-spirited violinist. Their bond transforms his world, even as a hidden truth changes everything.",
    "Tokyo Revengers follows Takemichi, who travels back in time to save his middle school girlfriend from a tragic future. He dives into gang conflicts, rewriting fate one fight at a time.",
    "Re:Zero centers on Subaru, who’s transported to a fantasy world and gains the ability to reset time upon death. Every choice brings pain, growth, and the struggle to protect those he loves.",
    "Noragami follows Yato, a minor god who dreams of having millions of worshippers but works odd jobs for 5 yen. Alongside a human girl and a spirit weapon, he battles phantoms and his mysterious past."};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(getLayoutInflater());

        CustomBaseAdapterList customBaseAdapterList = new CustomBaseAdapterList(getActivity().getApplicationContext(), angka, animeCover, judulAnime, genreAnime, synopsisAnime);
        binding.listViewList.setAdapter(customBaseAdapterList);

        binding.listViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Custom", "item click" + position);
                Fragment newFragment = new DetailFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, newFragment);
                transaction.commit();
            }
        });


        return binding.getRoot();
    }
}
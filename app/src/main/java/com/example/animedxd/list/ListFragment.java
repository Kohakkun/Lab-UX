// File: app/src/main/java/com/example/animedxd/list/ListFragment.java
package com.example.animedxd.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;

import com.example.animedxd.MainActivity2;
import com.example.animedxd.R;
import com.example.animedxd.databinding.FragmentListBinding;
import com.example.animedxd.model.Anime;
import com.example.animedxd.model.Anime.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment responsible for displaying a list of anime.
 * When an anime item is clicked, it passes the selected Anime object
 * to MainActivity2 to display its details in the DetailFragment.
 */
public class ListFragment extends Fragment {

    FragmentListBinding binding;

    String[] angka = {"1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9.", "10."};
    int[] animeCover = {R.drawable.animecover1, R.drawable.animecover2, R.drawable.animecover3, R.drawable.animecover4, R.drawable.animecover5, R.drawable.animecover6, R.drawable.animecover7, R.drawable.animecover8, R.drawable.animecover9, R.drawable.animecover10};
    String[] judulAnime = {"Solo Leveling", "Black Clover", "Boruto", "Blue Lock", "Attack On Titan", "Spy x Family", "Your Lie in April", "Tokyo Revengers", "Re:Zero", "Noragami"};
    String[] genreAnime = {"Action, Regressor, Fantasy", "Action, Magic, Adventure", "Action, Martial Arts, Shounen", "Sports, Psychological, Drama", "Action, Dark Fantasy, Military", "Action, Comedy, Slice of Life", "Drama, Romance, Music", "Action, Supernatural, Sci-Fi (Time Travel)", "Isekai, Dark Fantasy, Drama", "Action, Supernatural, Urban Fantasy"};

    // This is the SHORT synopsis array for the list view
    String[] shortSynopsisAnime = {
            "Solo Leveling follows Jinwoo Sung, a weak hunter who gains the power to level up infinitely after a mysterious dungeon incident. He rises from the weakest to the most powerful, facing monsters, secrets, and gods.",
            "Black Clover follows Asta, a boy born without magic in a world where magic is everything. With sheer determination and a rare anti-magic power, he aims to become the Wizard King.",
            "Boruto follows Naruto’s son, Boruto Uzumaki, as he tries to forge his own ninja path beyond his father’s shadow. New threats emerge, pushing him and his friends to grow stronger than ever.",
            "Blue Lock centers on a nationwide project to create Japan’s best striker through intense, cutthroat competition. Yoichi Isagi fights to rise to the top and redefine what it means to be the best.",
            "Attack on Titan follows Eren Yeager as he battles towering man-eating titans after his world is shattered. As secrets unravel, he questions freedom, enemies, and his own humanity.",
            "Spy x Family follows a spy who must build a fake family for a mission—unaware his wife is an assassin and his adopted daughter is a telepath. Together, they juggle secret lives while pretending to be the perfect family.",
            "Your Lie in April tells the story of a piano prodigy who rediscovers music and emotion through a free-spirited violinist. Their bond transforms his world, even as a hidden truth changes everything.",
            "Tokyo Revengers follows Takemichi, who travels back in time to save his middle school girlfriend from a tragic future. He dives into gang conflicts, rewriting fate one fight at a time.",
            "Re:Zero centers on Subaru, who’s transported to a fantasy world and gains the ability to reset time upon death. Every choice brings pain, growth, and the struggle to protect those he loves.",
            "Noragami follows Yato, a minor god who dreams of having millions of worshippers but works odd jobs for 5 yen. Alongside a human girl and a spirit weapon, he battles phantoms and his mysterious past."
    };

    // These are the FULL synopses for the detail page
    String fullSynopsisSoloLeveling = "Known as the Weakest Hunter of All Mankind, Sung Jinwoo's contribution to raids amounts to trying not to get killed. Unfortunately, between his mother's hospital bills, his sister's tuition, and his own lack of job prospects, he has no choice but to continue to put his life on the line. So when an opportunity for a bigger payout presents itself, he takes it...only to come face-to-face with a being whose power outstrips anything he's ever seen! With the party leader missing an arm and the only healer a quivering mess, can Jinwoo some-how find a way for them to escape?";
    String fullSynopsisBlackClover = "Asta and Yuno were abandoned at the same church on the same day. Raised together as children, they came to know of the \"Wizard King\"—a title given to the strongest mage in the kingdom—and promised that they would compete against each other for the position of the next Wizard King. However, as they grew up, the stark differences between them became apparent. Yuno was a prodigy with immense magical power, while Asta possessed no magic at all. Despite this, Asta refused to give up on his dream and trained relentlessly, eventually gaining a mysterious five-leaf clover grimoire that granted him anti-magic abilities. Now, both Asta and Yuno embark on their respective journeys to become the Wizard King, facing challenges, making allies, and battling powerful foes in a world where magic is everything.";
    String fullSynopsisBoruto = "The life of the shinobi is beginning to change. Boruto Uzumaki, son of Seventh Hokage Naruto Uzumaki, has enrolled in the Ninja Academy to learn the ways of the ninja. Now, as a series of mysterious events unfolds, Boruto’s story is about to begin!";
    String fullSynopsisBlueLock = "After a disastrous defeat at the 2018 World Cup, Japan's team struggles to regroup. But what's missing? An absolute Ace Striker, who can guide them to the win. The Football Association is hell-bent on creating a striker who hungers for goals and thirsts for victory, and who can be the decisive instrument in turning around a losing match...and to do so, they've gathered 300 of Japan's best and brightest youth players. Who will emerge to lead the team?";
    String fullSynopsisAttackOnTitan = "Centuries ago, mankind was slaughtered to near extinction by monstrous humanoid creatures called titans, forcing humans to hide in fear behind enormous concentric walls. What makes these giants truly terrifying is that their taste for human flesh is not born out of hunger but what appears to be out of pleasure. To ensure their survival, the remnants of humanity began living within defensive barriers, resulting in one hundred years without a single titan encounter. However, that calm is soon shattered when a colossal titan manages to breach the supposedly impregnable outer wall, reigniting the fight for survival against the man-eating abominations.";
    String fullSynopsisSpyXFamily = "For the agent known as 'Twilight,' no order is too tall if it is for the sake of peace. Operating as Westalis' master spy, Twilight works tirelessly to prevent extremists from sparking a war with neighboring country Ostania. For his latest mission, he must investigate Ostanian politician Donovan Desmond by infiltrating his son's school: the prestigious Eden Academy. Thus, the agent faces the most difficult task of his career: get married, have a child, and play family.";
    String fullSynopsisYourLieInApril = "Music accompanies the path of the human metronome, the prodigious pianist Kousei Arima. But after the passing of his mother, Saki Arima, Kousei falls into a downward spiral, rendering him unable to hear the sound of his own piano. Two years later, Kousei still avoids the piano, leaving behind his admirers and rivals, and lives a colorless life alongside his friends Tsubaki Sawabe and Ryouta Watari. However, everything changes when he meets a beautiful violinist, Kaori Miyazono, who stirs up his world and sets him on a journey to face music again.";
    String fullSynopsisTokyoRevengers = "Takemichi Hanagaki's life is at an all-time low. Just when he thought it couldn't get worse, he finds out that Hinata Tachibana, his ex-girlfriend, was murdered by the Tokyo Manji Gang. The next day, a brush with death brings him back 12 years in time to his middle school days. Takemichi, now a crybaby hero, decides to infiltrate the Tokyo Manji Gang to rewrite the future and save Hinata from her tragic fate.";
    String fullSynopsisReZero = "When Subaru Natsuki leaves the convenience store, the last thing he expects is to be wrenched from his everyday life and dropped into a fantasy world. Things aren't looking good for the bewildered teenager; however, not long after his arrival, he is attacked by some thugs. Armed with only a bag of groceries and a now-useless cell phone, he is quickly beaten to a pulp. Fortunately, a mysterious beauty named Satella, in hot pursuit after the one who stole her insignia, happens upon Subaru and saves him. But unbeknownst to them, a much darker force stalks the pair from the shadows, and just minutes after locating the insignia, Subaru and Satella are brutally murdered. However, Subaru immediately reawakens to a familiar scene—confronted by the same group of thugs, meeting Satella all over again—the mystery deepens as history repeats itself.";
    String fullSynopsisNoragami = "In times of need, if you look in the right place, you just may see a strange telephone number scrawled in red. If you call this number, you will hear a young man introduce himself as the Yato God. Yato is a minor deity and a self-proclaimed 'Delivery God,' who dreams of having millions of worshippers. Without a single shrine dedicated to his name, however, his goals are far from being realized. He spends his days doing odd jobs for five yen apiece, until his weapon partner deserts him. Just as things seem to be looking grim, he meets Hiyori Iki, a middle school girl who saves him from a car accident, taking the hit for him. Remarkably, she survives, but the event has caused her soul to become loose and hence able to leave her body. Hiyori demands that Yato return her to normal, but upon learning that he needs a new partner to do so, reluctantly agrees to help him find one. And with Hiyori's help, Yato's luck may finally be turning around.";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(getLayoutInflater());

        // Pass the SHORT synopsis array to the CustomBaseAdapterList for display in the list view
        CustomBaseAdapterList customBaseAdapterList = new CustomBaseAdapterList(getActivity().getApplicationContext(), angka, animeCover, judulAnime, genreAnime, shortSynopsisAnime);
        binding.listViewList.setAdapter(customBaseAdapterList);

        binding.listViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Anime selectedAnime = null;
                List<Review> reviews = new ArrayList<>();
                int currentUserProfileImageResId = R.drawable.review_profile;

                // Populate the Anime object with FULL synopsis and initial reviews
                switch (position) {
                    case 0: // Solo Leveling
                        reviews.add(new Review("HunterFan_01", "The animation is just insane! A-1 Pictures really did justice to the source material. Every fight scene is a masterpiece. Arise!", currentUserProfileImageResId));
                        reviews.add(new Review("WebtoonReader", "As someone who read the webtoon, I had high expectations, and they were MET. The pacing is great and the voice acting for Jinwoo is perfect.", currentUserProfileImageResId));
                        reviews.add(new Review("ActionJunkie", "Finally, an anime that's pure hype from start to finish. No boring filler, just awesome leveling up and epic battles. Can't wait for season 2!", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Solo Leveling", R.drawable.animecover1, "TV", "12", "Shounen",
                                "Winter 2024", "A-1 Pictures", "Action, Regressor, Fantasy", "Web novel",
                                "R - 17+ (violence & profanity)", "8.75", fullSynopsisSoloLeveling, 4.5f, reviews
                        );
                        break;
                    case 1: // Black Clover
                        reviews.add(new Review("Xaoc", "I'm the biggest 'Black Clover' fan out there, and I can honestly say this anime is everything! The action is insane, the magic system is super creative, and Asta's determination gives me chills every time. No matter how many times I watch it, it never gets old 'Black Clover' will always have a special place in my heart!", currentUserProfileImageResId));
                        reviews.add(new Review("Cmi_reviews", "Black Clover is an absolute blast! The growth of Asta and Yuno is so satisfying to watch. The humor, action, and emotional moments are perfectly balanced.", currentUserProfileImageResId));
                        reviews.add(new Review("D3C_Official", "A must-watch for shonen lovers! The fight choreography is top-tier, and the development of the supporting cast is genuinely compelling. Don't skip this one!", currentUserProfileImageResId));
                        reviews.add(new Review("SlurpyGeek", "From zero magic to hero! Asta's journey is incredibly motivating. Plus, the magic spells are visually stunning and unique.", currentUserProfileImageResId));
                        reviews.add(new Review("ByakuyaChan", "I initially hesitated, but Black Clover quickly became one of my favorites. The world-building is solid, and the characters are endearing. You won't regret it!", currentUserProfileImageResId));
                        reviews.add(new Review("DarkExodus_", "The themes of perseverance and friendship are beautifully portrayed. Every episode leaves you wanting more. A true masterpiece of modern shonen anime.", currentUserProfileImageResId));
                        reviews.add(new Review("Kohakkun_Pro", "This anime has it all: epic battles, hilarious moments, and genuinely heartwarming scenes. The voice acting is superb, especially Asta's!", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Black Clover", R.drawable.animecover2, "TV", "170", "Shounen",
                                "Fall 2017", "Pierrot", "Action, Magic, Adventure", "Manga",
                                "PG 13", "8.14", fullSynopsisBlackClover, 4.0f, reviews
                        );
                        break;
                    case 2: // Boruto
                        reviews.add(new Review("NextGenFan", "It's cool to see the old characters as adults and how their kids are growing up. Kawaki's arc is the best part!", currentUserProfileImageResId));
                        reviews.add(new Review("NarutoForever", "The manga content is great, but you have to be patient through the anime-canon stuff. When it adapts the manga, it's peak.", currentUserProfileImageResId));
                        reviews.add(new Review("SaradaUchiha", "I love Team 7, especially Sarada! She has so much potential. The new Otsutsuki villains are interesting too.", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Boruto: Naruto Next Generations", R.drawable.animecover3, "TV", "293", "Shounen",
                                "Spring 2017", "Pierrot", "Action, Martial Arts, Shounen", "Manga",
                                "PG-13", "5.79", fullSynopsisBoruto, 2.8f, reviews
                        );
                        break;
                    case 3: // Blue Lock
                        reviews.add(new Review("Egoist_Striker", "This isn't your typical sports anime. It's a battle royale on a soccer field and I'm here for it! Isagi is a genius.", currentUserProfileImageResId));
                        reviews.add(new Review("SoccerFreak", "The concept of 'ego' in sports is so fascinating. Every character is unique and has their own weapon. The animation is top-notch.", currentUserProfileImageResId));
                        reviews.add(new Review("BachiraBestBoi", "I've never been so hyped during a sports anime. The intensity is unreal. Can't wait for the next season!", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Blue Lock", R.drawable.animecover4, "TV", "24", "Shounen",
                                "Fall 2022", "8bit", "Sports, Psychological, Drama", "Manga",
                                "PG-13", "8.35", fullSynopsisBlueLock, 4.2f, reviews
                        );
                        break;
                    case 4: // Attack on Titan
                        reviews.add(new Review("SurveyCorpsScout", "This anime is a masterpiece of storytelling. The plot twists are insane and the characters are so well-written. Sasageyo!", currentUserProfileImageResId));
                        reviews.add(new Review("LeviAckermanFan", "The animation, especially the ODM gear sequences, is breathtaking. MAPPA did an incredible job with the final season.", currentUserProfileImageResId));
                        reviews.add(new Review("TitanTheorist", "More than just action, it's a deep story about freedom, war, and humanity. It will make you question everything. 10/10.", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Attack On Titan", R.drawable.animecover5, "TV", "94", "Shounen",
                                "Spring 2013", "Wit Studio, MAPPA", "Action, Dark Fantasy, Military", "Manga",
                                "R - 17+", "9.05", fullSynopsisAttackOnTitan, 4.5f, reviews
                        );
                        break;
                    case 5: // Spy x Family
                        reviews.add(new Review("AnyaPeanuts", "Waku Waku! This show is so wholesome and funny. Anya is the best character of all time. Heh.", currentUserProfileImageResId));
                        reviews.add(new Review("LoidIsCool", "The perfect blend of spy action, comedy, and heartwarming family moments. The animation is beautiful and stylish.", currentUserProfileImageResId));
                        reviews.add(new Review("YorTheThornPrincess", "I love the dynamic between Loid, Yor, and Anya. They might be a fake family, but their bond feels so real. Elegant!", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Spy x Family", R.drawable.animecover6, "TV", "37", "Shounen",
                                "Spring 2022", "Wit Studio, CloverWorks", "Action, Comedy, Slice of Life", "Manga",
                                "PG-13", "8.97", fullSynopsisSpyXFamily, 4.5f, reviews
                        );
                        break;
                    case 6: // Your Lie in April
                        reviews.add(new Review("PianistInTears", "This anime is a beautiful tragedy. The music is phenomenal and the story will break your heart in the best way possible.", currentUserProfileImageResId));
                        reviews.add(new Review("KaoriMySunshine", "The vibrant colors and animation are stunning, perfectly contrasting the emotional story. Kaori brought color to Kousei's world and mine.", currentUserProfileImageResId));
                        reviews.add(new Review("ClassicalFan", "As a classical music lover, this was a treat. The performances are so emotional and powerful. Prepare to cry. A lot.", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Your Lie in April", R.drawable.animecover7, "TV", "22", "Shounen",
                                "Fall 2014", "A-1 Pictures", "Drama, Romance, Music", "Manga",
                                "PG-13", "8.81", fullSynopsisYourLieInApril, 4.4f, reviews
                        );
                        break;
                    case 7: // Tokyo Revengers
                        reviews.add(new Review("CrybabyHero", "Takemichi is a different kind of protagonist, and I love him for it. His determination to save everyone is inspiring.", currentUserProfileImageResId));
                        reviews.add(new Review("Toman_Forever", "Mikey and Draken are some of the coolest characters in anime. The whole delinquent gang theme is awesome.", currentUserProfileImageResId));
                        reviews.add(new Review("TimeLeaper", "The time travel plot is so compelling! Every episode leaves you on the edge of your seat. The stakes are always high.", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Tokyo Revengers", R.drawable.animecover8, "TV", "37", "Shounen",
                                "Spring 2021", "Lidenfilms", "Action, Supernatural, Sci-Fi (Time Travel)", "Manga",
                                "R - 17+", "8.09", fullSynopsisTokyoRevengers, 4.0f, reviews
                        );
                        break;
                    case 8: // Re:Zero
                        reviews.add(new Review("ILoveEmilia", "A masterpiece of the isekai genre. Subaru's suffering and growth is one of the most compelling character arcs I've ever seen.", currentUserProfileImageResId));
                        reviews.add(new Review("ReturnByDeath", "This is not your typical power fantasy isekai. It's a psychological thriller that will keep you guessing. The world-building is incredible.", currentUserProfileImageResId));
                        reviews.add(new Review("RemIsBestGirl", "The emotional highs and lows are intense. The supporting cast is fantastic, especially Rem. Who's Rem?", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Re:Zero - Starting Life in Another World", R.drawable.animecover9, "TV", "50", "Seinen",
                                "Spring 2016", "White Fox", "Isekai, Dark Fantasy, Drama", "Light novel",
                                "R - 17+", "8.33", fullSynopsisReZero, 4.2f, reviews
                        );
                        break;
                    case 9: // Noragami
                        reviews.add(new Review("DeliveryGodYato", "Yato is hilarious and surprisingly deep. The mix of comedy and serious emotional moments is perfect. Studio Bones did an amazing job!", currentUserProfileImageResId));
                        reviews.add(new Review("YukineIsMySon", "The relationship between Yato, Hiyori, and Yukine is the heart of the show. Yukine's character development is fantastic.", currentUserProfileImageResId));
                        reviews.add(new Review("ShinkiMaster", "Awesome action scenes and a really cool magic system based on Japanese mythology. The soundtrack is a banger too. Season 3 when?!", currentUserProfileImageResId));
                        selectedAnime = new Anime(
                                "Noragami", R.drawable.animecover10, "TV", "25", "Shounen",
                                "Winter 2014", "Bones", "Action, Supernatural, Urban Fantasy", "Manga",
                                "PG-13", "8.19", fullSynopsisNoragami, 4.1f, reviews
                        );
                        break;
                }

                if (selectedAnime != null) {
                    Intent intent = new Intent(getActivity(), MainActivity2.class);
                    intent.putExtra("anime_data", selectedAnime);
                    startActivity(intent);
                }
            }
        });

        return binding.getRoot();
    }
}

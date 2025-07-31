package com.example.animedxd.detail;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.animedxd.R;
import com.example.animedxd.model.Anime;
import com.example.animedxd.model.Anime.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * A single, generic Fragment for displaying anime details.
 * It receives an Anime object via arguments and populates its views dynamically.
 */
public class DetailFragment extends Fragment {

    private static final String ARG_ANIME = "anime_object";

    private ImageView animeImage;
    private TextView animeTitle, animeType, animeEpisodes, animeDemographic,
            animePremiered, animeStudio, animeGenre, animeSource, animeRating,
            animeScore, animeSynopsis;
    private RatingBar ratingBar;
    private LinearLayout reviewContainer;
    private Button btnWriteReview;
    private TextView btnReadMoreSynopsis;

    private String fullSynopsisText;

    private Anime currentAnime;
    private List<Review> reviewsList = new ArrayList<>();
    private String currentUserUsername = "Xaoc";
    private int currentUserProfileImageResId = R.drawable.review_profile;

    /**
     * Factory method to create a new instance of DetailFragment with a specific Anime object.
     * This is the recommended way to pass arguments to fragments.
     *
     * @param anime The Anime object containing the details to display.
     * @return A new instance of DetailFragment.
     */
    public static DetailFragment newInstance(Anime anime) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ANIME, anime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentAnime = (Anime) getArguments().getSerializable(ARG_ANIME);
            if (currentAnime != null) {
                reviewsList.addAll(currentAnime.getInitialReviews());
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        initializeViews(view);

        if (currentAnime != null) {
            setAnimeDetails(currentAnime);
            setupSynopsis(currentAnime.getFullSynopsis());
            displayReviews(); // Display initial reviews
        }

        btnWriteReview.setOnClickListener(v -> showWriteReviewDialog());

        return view;
    }

    /**
     * Initializes all the TextViews, ImageView, RatingBar, and LinearLayout
     * from the inflated view.
     * @param view The root view of the fragment's layout.
     */
    private void initializeViews(View view) {
        animeImage = view.findViewById(R.id.animeImage);
        animeTitle = view.findViewById(R.id.animeTitle);
        animeType = view.findViewById(R.id.animeType);
        animeEpisodes = view.findViewById(R.id.animeEpisodes);
        animeDemographic = view.findViewById(R.id.animeDemographic);
        animePremiered = view.findViewById(R.id.animePremiered);
        animeStudio = view.findViewById(R.id.animeStudio);
        animeGenre = view.findViewById(R.id.animeGenre);
        animeSource = view.findViewById(R.id.animeSource);
        animeRating = view.findViewById(R.id.animeRating);
        animeScore = view.findViewById(R.id.animeScore);
        animeSynopsis = view.findViewById(R.id.animeSynopsis);
        ratingBar = view.findViewById(R.id.ratingBar);
        reviewContainer = view.findViewById(R.id.reviewContainer);
        btnWriteReview = view.findViewById(R.id.btnWriteReview);
        btnReadMoreSynopsis = view.findViewById(R.id.btnReadMoreSynopsis);
    }

    /**
     * Sets the detailed information of the anime to the respective UI elements.
     * @param anime The Anime object containing the data.
     */
    private void setAnimeDetails(Anime anime) {
        animeTitle.setText(anime.getTitle());
        animeImage.setImageResource(anime.getCoverResId());
        animeType.setText("Type: " + anime.getType());
        animeEpisodes.setText("Episodes: " + anime.getEpisodes());
        animeDemographic.setText("Demographic: " + anime.getDemographic());
        animePremiered.setText("Premiered: " + anime.getPremiered());
        animeStudio.setText("Studio: " + anime.getStudio());
        animeGenre.setText("Genre: " + anime.getGenre());
        animeSource.setText("Source: " + anime.getSource());
        animeRating.setText("Rating: " + anime.getRating());
        animeScore.setText(anime.getScore());
        ratingBar.setRating(anime.getRatingBarScore());
    }

    /**
     * Sets up the synopsis text view with "Read More/Show Less" functionality.
     * @param synopsis The full synopsis text for the anime.
     */
    private void setupSynopsis(String synopsis) {
        fullSynopsisText = synopsis;
        animeSynopsis.setText(fullSynopsisText);

        animeSynopsis.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                animeSynopsis.getViewTreeObserver().removeOnPreDrawListener(this);
                Layout layout = animeSynopsis.getLayout();
                if (layout != null && (layout.getEllipsisCount(layout.getLineCount() - 1) > 0 || animeSynopsis.getLineCount() > animeSynopsis.getMaxLines())) {
                    btnReadMoreSynopsis.setVisibility(View.VISIBLE);
                } else {
                    btnReadMoreSynopsis.setVisibility(View.GONE);
                }
                return true;
            }
        });

        btnReadMoreSynopsis.setOnClickListener(v -> {
            if (btnReadMoreSynopsis.getText().equals("Read More")) {
                animeSynopsis.setMaxLines(Integer.MAX_VALUE);
                animeSynopsis.setEllipsize(null);
                btnReadMoreSynopsis.setText("Show Less");
            } else {
                animeSynopsis.setMaxLines(3);
                animeSynopsis.setEllipsize(android.text.TextUtils.TruncateAt.END);
                btnReadMoreSynopsis.setText("Read More");
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            animeSynopsis.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    /**
     * Clears existing reviews and re-adds all reviews from the reviewsList to the UI.
     */
    private void displayReviews() {
        reviewContainer.removeAllViews();
        for (int i = 0; i < reviewsList.size(); i++) {
            addReviewCard(reviewsList.get(i), i == reviewsList.size() - 1);
        }
    }

    /**
     * Displays a dialog for the user to write and submit a new review.
     */
    private void showWriteReviewDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_write_review, null);

        ImageView dialogUserProfileImage = dialogView.findViewById(R.id.dialogUserProfileImage);
        TextView dialogUsername = dialogView.findViewById(R.id.dialogUsername);
        EditText etReviewInput = dialogView.findViewById(R.id.etReviewInput);
        Button btnPostReview = dialogView.findViewById(R.id.btnPostReview);

        dialogUserProfileImage.setImageResource(currentUserProfileImageResId);
        dialogUsername.setText(currentUserUsername);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnPostReview.setOnClickListener(v -> {
            String reviewText = etReviewInput.getText().toString().trim();

            if (reviewText.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a review.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Review submitted successfully!", Toast.LENGTH_SHORT).show();

                Review newReview = new Review(currentUserUsername, reviewText, currentUserProfileImageResId);
                reviewsList.add(0, newReview);


                dialog.dismiss();
            }
        });
    }

    /**
     * Adds a single review card to the reviewContainer LinearLayout.
     * @param review The Review object to display.
     * @param isLastItem A boolean indicating if this is the last review in the list,
     * used to conditionally add a divider.
     */
    private void addReviewCard(Review review, boolean isLastItem) {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View reviewCard = inflater.inflate(R.layout.fragment_item_review, reviewContainer, false);

        ImageView imgUserProfile = reviewCard.findViewById(R.id.imgUserProfile);
        TextView tvUsername = reviewCard.findViewById(R.id.tvUsername);
        TextView tvReviewContent = reviewCard.findViewById(R.id.tvReviewContent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            tvReviewContent.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }

        if (review.getUserProfileImageResId() != 0) {
            imgUserProfile.setImageResource(review.getUserProfileImageResId());
        } else {
            imgUserProfile.setImageResource(R.drawable.review_profile);
        }
        tvUsername.setText(review.getUsername());
        tvReviewContent.setText(review.getReviewContent());

        reviewContainer.addView(reviewCard);

        if (!isLastItem) {
            View divider = new View(requireContext());
            LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) getResources().getDisplayMetrics().density * 1
            );
            dividerParams.setMargins(0, (int) getResources().getDisplayMetrics().density * 8, 0, (int) getResources().getDisplayMetrics().density * 8);
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(Color.parseColor("#444444"));
            reviewContainer.addView(divider);
        }
    }
}

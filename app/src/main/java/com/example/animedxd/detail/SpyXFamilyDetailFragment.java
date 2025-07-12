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

import java.util.ArrayList;
import java.util.List;

public class SpyXFamilyDetailFragment extends Fragment {

    private ImageView animeImage;
    private TextView animeTitle, animeType, animeEpisodes, animeDemographic,
            animePremiered, animeStudio, animeGenre, animeSource, animeRating,
            animeScore, animeSynopsis;
    private RatingBar ratingBar;
    private LinearLayout reviewContainer;
    private Button btnWriteReview;
    private TextView btnReadMoreSynopsis;
    private String fullSynopsisText;

    private List<Review> reviewsList = new ArrayList<>();
    private String currentUserUsername = "Xaoc";
    private int currentUserProfileImageResId = R.drawable.review_profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spy_x_family_fragment_detail, container, false);

        initializeViews(view);
        setAnimeDetails();
        setupSynopsis();
        addInitialReviews();
        displayReviews();
        btnWriteReview.setOnClickListener(v -> showWriteReviewDialog());

        return view;
    }

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

    private void setAnimeDetails() {
        animeTitle.setText("Spy x Family");
        animeImage.setImageResource(R.drawable.animecover6);
        animeType.setText("Type: TV");
        animeEpisodes.setText("Episodes: 37");
        animeDemographic.setText("Demographic: Shounen");
        animePremiered.setText("Premiered: Spring 2022");
        animeStudio.setText("Studio: Wit Studio, CloverWorks");
        animeGenre.setText("Genre: Action, Comedy, Slice of Life");
        animeSource.setText("Source: Manga");
        animeRating.setText("Rating: PG-13");
        animeScore.setText("8.97");
        ratingBar.setRating(4.5f);
    }

    private void setupSynopsis() {
        fullSynopsisText = "For the agent known as 'Twilight,' no order is too tall if it is for the sake of peace. Operating as Westalis' master spy, Twilight works tirelessly to prevent extremists from sparking a war with neighboring country Ostania. For his latest mission, he must investigate Ostanian politician Donovan Desmond by infiltrating his son's school: the prestigious Eden Academy. Thus, the agent faces the most difficult task of his career: get married, have a child, and play family.";
        animeSynopsis.setText(fullSynopsisText);

        animeSynopsis.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                animeSynopsis.getViewTreeObserver().removeOnPreDrawListener(this);
                if (animeSynopsis.getLayout() != null && (animeSynopsis.getLayout().getEllipsisCount(animeSynopsis.getLineCount() - 1) > 0 || animeSynopsis.getLineCount() > animeSynopsis.getMaxLines())) {
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
    }

    private void addInitialReviews() {
        reviewsList.add(new Review("AnyaPeanuts", "Waku Waku! This show is so wholesome and funny. Anya is the best character of all time. Heh.", R.drawable.review_profile));
        reviewsList.add(new Review("LoidIsCool", "The perfect blend of spy action, comedy, and heartwarming family moments. The animation is beautiful and stylish.", R.drawable.review_profile));
        reviewsList.add(new Review("YorTheThornPrincess", "I love the dynamic between Loid, Yor, and Anya. They might be a fake family, but their bond feels so real. Elegant!", R.drawable.review_profile));
    }

    private void displayReviews() {
        reviewContainer.removeAllViews();
        for (int i = 0; i < reviewsList.size(); i++) {
            addReviewCard(reviewsList.get(i), i == reviewsList.size() - 1);
        }
    }

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
                Toast.makeText(requireContext(), "Review submitted!", Toast.LENGTH_SHORT).show();
                reviewsList.add(0, new Review(currentUserUsername, reviewText, currentUserProfileImageResId));
                displayReviews();
                dialog.dismiss();
            }
        });
    }

    private void addReviewCard(Review review, boolean isLastItem) {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View reviewCard = inflater.inflate(R.layout.fragment_item_review, reviewContainer, false);

        ImageView imgUserProfile = reviewCard.findViewById(R.id.imgUserProfile);
        TextView tvUsername = reviewCard.findViewById(R.id.tvUsername);
        TextView tvReviewContent = reviewCard.findViewById(R.id.tvReviewContent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            tvReviewContent.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }

        imgUserProfile.setImageResource(review.getUserProfileImageResId());
        tvUsername.setText(review.getUsername());
        tvReviewContent.setText(review.getReviewContent());

        reviewContainer.addView(reviewCard);

        if (!isLastItem) {
            View divider = new View(requireContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
            params.setMargins(0, 24, 0, 24);
            divider.setLayoutParams(params);
            divider.setBackgroundColor(Color.parseColor("#444444"));
            reviewContainer.addView(divider);
        }
    }

    private static class Review {
        String username;
        String reviewContent;
        int userProfileImageResId;

        Review(String username, String reviewContent, int userProfileImageResId) {
            this.username = username;
            this.reviewContent = reviewContent;
            this.userProfileImageResId = userProfileImageResId;
        }

        public String getUsername() { return username; }
        public String getReviewContent() { return reviewContent; }
        public int getUserProfileImageResId() { return userProfileImageResId; }
    }
}

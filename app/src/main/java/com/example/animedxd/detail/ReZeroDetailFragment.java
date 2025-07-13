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

public class ReZeroDetailFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.re_zero_fragment_detail, container, false);

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
        animeTitle.setText("Re:Zero - Starting Life in Another World");
        animeImage.setImageResource(R.drawable.animecover9);
        animeType.setText("Type: TV");
        animeEpisodes.setText("Episodes: 50");
        animeDemographic.setText("Demographic: Seinen");
        animePremiered.setText("Premiered: Spring 2016");
        animeStudio.setText("Studio: White Fox");
        animeGenre.setText("Genre: Isekai, Dark Fantasy, Drama");
        animeSource.setText("Source: Light novel");
        animeRating.setText("Rating: R - 17+");
        animeScore.setText("8.33");
        ratingBar.setRating(4.2f);
    }

    private void setupSynopsis() {
        fullSynopsisText = "When Subaru Natsuki leaves the convenience store, the last thing he expects is to be wrenched from his everyday life and dropped into a fantasy world. Things aren't looking good for the bewildered teenager; however, not long after his arrival, he is attacked by some thugs. Armed with only a bag of groceries and a now-useless cell phone, he is quickly beaten to a pulp. Fortunately, a mysterious beauty named Satella, in hot pursuit after the one who stole her insignia, happens upon Subaru and saves him. But unbeknownst to them, a much darker force stalks the pair from the shadows, and just minutes after locating the insignia, Subaru and Satella are brutally murdered. However, Subaru immediately reawakens to a familiar scene—confronted by the same group of thugs, meeting Satella all over again—the mystery deepens as history repeats itself.";
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
        reviewsList.add(new Review("ILoveEmilia", "A masterpiece of the isekai genre. Subaru's suffering and growth is one of the most compelling character arcs I've ever seen.", R.drawable.review_profile));
        reviewsList.add(new Review("ReturnByDeath", "This is not your typical power fantasy isekai. It's a psychological thriller that will keep you guessing. The world-building is incredible.", R.drawable.review_profile));
        reviewsList.add(new Review("RemIsBestGirl", "The emotional highs and lows are intense. The supporting cast is fantastic, especially Rem. Who's Rem?", R.drawable.review_profile));
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

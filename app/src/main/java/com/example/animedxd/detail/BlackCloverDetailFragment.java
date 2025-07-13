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

// CHANGE 1: Renamed the class
public class BlackCloverDetailFragment extends Fragment {

    // ... (Your existing view declarations)
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

    // Define current user's info for the dialog (replace with actual user data)
    private String currentUserUsername = "Xaoc"; // Your user's username
    private int currentUserProfileImageResId = R.drawable.review_profile; // Your user's profile image drawable

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // CHANGE 2: Updated the layout file reference
        View view = inflater.inflate(R.layout.black_clover_fragment_detail, container, false);

        // ... (Your existing view initializations)
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


        // ... (Your existing data setting for anime details)
        animeTitle.setText("Black Clover");
        animeImage.setImageResource(R.drawable.animecover2);
        animeType.setText("Type: TV");
        animeEpisodes.setText("Episodes: 170");
        animeDemographic.setText("Demographic: Shounen");
        animePremiered.setText("Premiered: Fall 2017");
        animeStudio.setText("Studio: Pierrot");
        animeGenre.setText("Genre: Action, Magic, Adventure");
        animeSource.setText("Source: Manga");
        animeRating.setText("Rating: PG 13");
        animeScore.setText("8.14");
        ratingBar.setRating(4.0f);


        // ... (Your existing Synopsis and "Read More" Logic)
        fullSynopsisText = "Asta and Yuno were abandoned at the same church on the same day. Raised together as children, they came to know of the \"Wizard King\"—a title given to the strongest mage in the kingdom—and promised that they would compete against each other for the position of the next Wizard King. However, as they grew up, the stark differences between them became apparent. Yuno was a prodigy with immense magical power, while Asta possessed no magic at all. Despite this, Asta refused to give up on his dream and trained relentlessly, eventually gaining a mysterious five-leaf clover grimoire that granted him anti-magic abilities. Now, both Asta and Yuno embark on their respective journeys to become the Wizard King, facing challenges, making allies, and battling powerful foes in a world where magic is everything.";
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

        btnReadMoreSynopsis.setOnClickListener(new View.OnClickListener() {
            boolean isExpanded = false;
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    animeSynopsis.setMaxLines(3);
                    animeSynopsis.setEllipsize(android.text.TextUtils.TruncateAt.END);
                    btnReadMoreSynopsis.setText("Read More");
                } else {
                    animeSynopsis.setMaxLines(Integer.MAX_VALUE);
                    animeSynopsis.setEllipsize(null);
                    btnReadMoreSynopsis.setText("Show Less");
                }
                isExpanded = !isExpanded;
            }
        });

        // --- Add initial sample reviews ---
        reviewsList.add(new Review(
                "Xaoc",
                "I'm the biggest 'Black Clover' fan out there, and I can honestly say this anime is everything! The action is insane, the magic system is super creative, and Asta's determination gives me chills every time. No matter how many times I watch it, it never gets old 'Black Clover' will always have a special place in my heart!",
                R.drawable.review_profile
        ));
        reviewsList.add(new Review(
                "Cmi_reviews",
                "Black Clover is an absolute blast! The growth of Asta and Yuno is so satisfying to watch. The humor, action, and emotional moments are perfectly balanced.",
                R.drawable.review_profile
        ));
        reviewsList.add(new Review(
                "D3C_Official",
                "A must-watch for shonen lovers! The fight choreography is top-tier, and the development of the supporting cast is genuinely compelling. Don't skip this one!",
                R.drawable.review_profile
        ));
        reviewsList.add(new Review(
                "SlurpyGeek",
                "From zero magic to hero! Asta's journey is incredibly motivating. Plus, the magic spells are visually stunning and unique.",
                R.drawable.review_profile
        ));
        reviewsList.add(new Review(
                "ByakuyaChan",
                "I initially hesitated, but Black Clover quickly became one of my favorites. The world-building is solid, and the characters are endearing. You won't regret it!",
                R.drawable.review_profile
        ));
        reviewsList.add(new Review(
                "DarkExodus_",
                "The themes of perseverance and friendship are beautifully portrayed. Every episode leaves you wanting more. A true masterpiece of modern shonen anime.",
                R.drawable.review_profile
        ));
        reviewsList.add(new Review(
                "Kohakkun_Pro",
                "This anime has it all: epic battles, hilarious moments, and genuinely heartwarming scenes. The voice acting is superb, especially Asta's!",
                R.drawable.review_profile
        ));

        displayReviews();

        // --- Pop-up Logic ---
        btnWriteReview.setOnClickListener(v -> {
            showWriteReviewDialog();
        });

        return view;
    }

    private void displayReviews() {
        reviewContainer.removeAllViews();
        // Loop with index to determine if it's the last item
        for (int i = 0; i < reviewsList.size(); i++) {
            boolean isLastItem = (i == reviewsList.size() - 1);
            addReviewCard(reviewsList.get(i), isLastItem);
        }
    }

    // Method to show the review writing dialog
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

        btnPostReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reviewText = etReviewInput.getText().toString().trim();

                if (reviewText.isEmpty()) {
                    Toast.makeText(requireContext(), "Please enter a review.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Review submitted successfully!", Toast.LENGTH_SHORT).show();

                    Review newReview = new Review(currentUserUsername, reviewText, currentUserProfileImageResId);
                    reviewsList.add(0, newReview); // Add new review to the top

                    displayReviews(); // Re-display all reviews

                    dialog.dismiss();
                }
            }
        });
    }

    // MODIFIED: Now takes a boolean 'isLastItem'
    private void addReviewCard(Review review, boolean isLastItem) {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View reviewCard = inflater.inflate(R.layout.fragment_item_review, reviewContainer, false); // Still item_review

        ImageView imgUserProfile = reviewCard.findViewById(R.id.imgUserProfile);
        TextView tvUsername = reviewCard.findViewById(R.id.tvUsername);
        TextView tvReviewContent = reviewCard.findViewById(R.id.tvReviewContent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                tvReviewContent.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            } else {
                tvReviewContent.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            }
        }

        if (review.getUserProfileImageResId() != 0) {
            imgUserProfile.setImageResource(review.getUserProfileImageResId());
        } else {
            imgUserProfile.setImageResource(R.drawable.review_profile);
        }
        tvUsername.setText(review.getUsername());
        tvReviewContent.setText(review.getReviewContent());

        reviewContainer.addView(reviewCard);

        // CONDITIONAL DIVIDER ADDITION
        if (!isLastItem) { // Only add divider if it's NOT the last item
            View divider = new View(requireContext());
            LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) getResources().getDisplayMetrics().density * 1 // 1dp height
            );
            dividerParams.setMargins(0, (int) getResources().getDisplayMetrics().density * 8, 0, (int) getResources().getDisplayMetrics().density * 8);
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(Color.parseColor("#444444"));
            reviewContainer.addView(divider);
        }
    }

    private static class Review {
        private String username;
        private String reviewContent;
        private int userProfileImageResId;

        public Review(String username, String reviewContent, int userProfileImageResId) {
            this.username = username;
            this.reviewContent = reviewContent;
            this.userProfileImageResId = userProfileImageResId;
        }

        public String getUsername() {
            return username;
        }

        public String getReviewContent() {
            return reviewContent;
        }

        public int getUserProfileImageResId() {
            return userProfileImageResId;
        }
    }
}
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
    private ImageView gradientOverlay; // Added for the gradient effect

    private String fullSynopsisText;

    private Anime currentAnime;
    private List<Review> reviewsList = new ArrayList<>();
    private String currentUserUsername = "Xaoc"; // Default username, consider fetching from SharedPreferences
    private int currentUserProfileImageResId = R.drawable.review_profile; // Default profile image

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
                // Initialize reviewsList with initial reviews from the Anime object
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
        gradientOverlay = view.findViewById(R.id.gradientOverlay); // Initialize the new ImageView
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

        // This listener ensures that the "Read More" button and gradient only appear
        // if the text actually overflows.
        animeSynopsis.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                // Remove the listener to prevent multiple calls
                animeSynopsis.getViewTreeObserver().removeOnPreDrawListener(this);

                Layout layout = animeSynopsis.getLayout();
                if (layout != null && (layout.getEllipsisCount(layout.getLineCount() - 1) > 0 || animeSynopsis.getLineCount() > animeSynopsis.getMaxLines())) {
                    btnReadMoreSynopsis.setVisibility(View.VISIBLE);
                    gradientOverlay.setVisibility(View.VISIBLE); // Show gradient if text is truncated
                } else {
                    btnReadMoreSynopsis.setVisibility(View.GONE);
                    gradientOverlay.setVisibility(View.GONE); // Hide gradient if text fits
                }
                return true;
            }
        });

        btnReadMoreSynopsis.setOnClickListener(v -> {
            if (btnReadMoreSynopsis.getText().equals("Read More")) {
                animeSynopsis.setMaxLines(Integer.MAX_VALUE); // Show full text
                animeSynopsis.setEllipsize(null); // Remove ellipsis
                btnReadMoreSynopsis.setText("Show Less");
                gradientOverlay.setVisibility(View.GONE); // Hide gradient when full text is shown
            } else {
                animeSynopsis.setMaxLines(3); // Collapse to 3 lines
                animeSynopsis.setEllipsize(android.text.TextUtils.TruncateAt.END); // Add ellipsis
                btnReadMoreSynopsis.setText("Read More");
                gradientOverlay.setVisibility(View.VISIBLE); // Show gradient when text is truncated
            }
        });

        // Enable text justification for Android Q and above for better text flow
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
            // Pass true for the last item to avoid adding a divider after it
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
        LinearLayout reviewContentLayout = dialogView.findViewById(R.id.review_content_layout);
        TextView dialogErrorMessage = dialogView.findViewById(R.id.dialog_error_message);

        // Set current user's profile image and username in the dialog
        dialogUserProfileImage.setImageResource(currentUserProfileImageResId);
        dialogUsername.setText(currentUserUsername);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnPostReview.setOnClickListener(v -> {
            String reviewText = etReviewInput.getText().toString().trim();

            if (reviewText.isEmpty()) {
                // Hide all main components
                reviewContentLayout.setVisibility(View.GONE);
                // Set text and color for error message
                dialogErrorMessage.setText("Please enter a review.");
                dialogErrorMessage.setTextColor(Color.parseColor("#FFFFFF"));
                // Show the error message
                dialogErrorMessage.setVisibility(View.VISIBLE);
                // After 2000ms, hide the error message and show back the main components
                dialogView.postDelayed(() -> {
                    dialogErrorMessage.setVisibility(View.GONE);
                    reviewContentLayout.setVisibility(View.VISIBLE);
                }, 2000);
            } else {
                // Hide main components and display a success message
                reviewContentLayout.setVisibility(View.GONE);
                dialogErrorMessage.setText("Review submitted successfully.");
                dialogErrorMessage.setTextColor(Color.parseColor("#FFFFFF")); // Green color for success
                dialogErrorMessage.setVisibility(View.VISIBLE);

                // Create a new review object with current user's data
                Review newReview = new Review(currentUserUsername, reviewText, currentUserProfileImageResId);
                reviewsList.add(0, newReview); // Add new review to the top of the list

                // Delay the dismissal of the dialog
                dialogView.postDelayed(() -> {
                    dialog.dismiss(); // Close the dialog after a short delay
                    // Re-display all reviews to reflect the new addition
                    displayReviews();
                }, 2000);
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

        // Apply text justification for Android Q and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            tvReviewContent.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }

        // Set review details
        if (review.getUserProfileImageResId() != 0) {
            imgUserProfile.setImageResource(review.getUserProfileImageResId());
        } else {
            // Fallback to a default profile image if none is specified
            imgUserProfile.setImageResource(R.drawable.review_profile);
        }
        tvUsername.setText(review.getUsername());
        tvReviewContent.setText(review.getReviewContent());

        reviewContainer.addView(reviewCard);

        // Add a divider after each review, except for the very last one
        if (!isLastItem) {
            View divider = new View(requireContext());
            LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) getResources().getDisplayMetrics().density * 1 // 1dp height
            );
            // Add margin for spacing between review cards
            dividerParams.setMargins(0, (int) getResources().getDisplayMetrics().density * 8, 0, (int) getResources().getDisplayMetrics().density * 8);
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(Color.parseColor("#444444")); // Dark grey divider color
            reviewContainer.addView(divider);
        }
    }
}
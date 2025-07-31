package com.example.animedxd.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects; // Added for equals() and hashCode()

/**
 * A data class to represent an Anime entry, including its details,
 * a short synopsis for lists, a full synopsis for details, and initial reviews.
 * Implements Serializable to allow passing between Activities/Fragments via Bundles.
 */
public class Anime implements Serializable {
    private String title;
    private int imageResId; // Renamed from imageResId to coverResId for clarity (optional)
    private String type;
    private String episodes;
    private String demographic;
    private String premiered;
    private String studio;
    private String genre;
    private String source;
    private String rating;
    private String score;
    private String shortSynopsis; // New field for short synopsis
    private String fullSynopsis;   // Renamed or kept as synopsis for full synopsis
    private float ratingBarScore;
    private List<Review> initialReviews;

    /**
     * Constructor for the Anime data model.
     *
     * @param title The title of the anime.
     * @param coverResId The drawable resource ID for the anime's cover image.
     * @param type The type of the anime (e.g., "TV", "Movie").
     * @param episodes The number of episodes.
     * @param demographic The target demographic (e.g., "Shounen", "Seinen").
     * @param premiered The season and year it premiered (e.g., "Winter 2024").
     * @param studio The animation studio.
     * @param genre The genre(s) of the anime.
     * @param source The original source material (e.g., "Manga", "Web novel").
     * @param rating The age rating (e.g., "R - 17+", "PG-13").
     * @param score The numerical score as a string (e.g., "8.75").
     * @param shortSynopsis A brief synopsis for display in lists.
     * @param fullSynopsis The comprehensive synopsis text for detail views.
     * @param ratingBarScore The float value for the RatingBar.
     * @param initialReviews A list of sample reviews for the anime.
     */
    public Anime(String title, int coverResId, String type, String episodes, String demographic,
                 String premiered, String studio, String genre, String source, String rating,
                 String score, String shortSynopsis, String fullSynopsis, // Updated constructor parameters
                 float ratingBarScore, List<Review> initialReviews) {
        this.title = title;
        this.imageResId = coverResId; // Assign to imageResId
        this.type = type;
        this.episodes = episodes;
        this.demographic = demographic;
        this.premiered = premiered;
        this.studio = studio;
        this.genre = genre;
        this.source = source;
        this.rating = rating;
        this.score = score;
        this.shortSynopsis = shortSynopsis;
        this.fullSynopsis = fullSynopsis;
        this.ratingBarScore = ratingBarScore;
        this.initialReviews = initialReviews;
    }

    // --- Getters ---
    public String getTitle() {
        return title;
    }

    // Renamed for consistency with constructor param, kept original method name to avoid refactor in other files
    public int getCoverResId() {
        return imageResId;
    }

    public String getType() {
        return type;
    }

    public String getEpisodes() {
        return episodes;
    }

    public String getDemographic() {
        return demographic;
    }

    public String getPremiered() {
        return premiered;
    }

    public String getStudio() {
        return studio;
    }

    public String getGenre() {
        return genre;
    }

    public String getSource() {
        return source;
    }

    public String getRating() {
        return rating;
    }

    public String getScore() {
        return score;
    }

    public String getShortSynopsis() {
        return shortSynopsis;
    }

    public String getFullSynopsis() {
        return fullSynopsis;
    }

    public float getRatingBarScore() {
        return ratingBarScore;
    }

    public List<Review> getInitialReviews() {
        return initialReviews;
    }

    // --- Added Setters (Optional, but useful if data needs to be mutable) ---
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoverResId(int coverResId) {
        this.imageResId = coverResId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public void setDemographic(String demographic) {
        this.demographic = demographic;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public void setFullSynopsis(String fullSynopsis) {
        this.fullSynopsis = fullSynopsis;
    }

    public void setRatingBarScore(float ratingBarScore) {
        this.ratingBarScore = ratingBarScore;
    }

    public void setInitialReviews(List<Review> initialReviews) {
        this.initialReviews = initialReviews;
    }

    // --- Utility Methods ---

    /**
     * Provides a convenient string representation of the Anime object, useful for debugging.
     */
    @Override
    public String toString() {
        return "Anime{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", episodes='" + episodes + '\'' +
                ", genre='" + genre + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    /**
     * Implements equals to compare Anime objects based on their unique attributes (e.g., title).
     * This is important if you store Anime objects in Sets or need to check for duplicates.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anime anime = (Anime) o;
        return Objects.equals(title, anime.title) &&
                Objects.equals(premiered, anime.premiered) && // Consider premiere date to differentiate remakes
                Objects.equals(studio, anime.studio); // Consider studio for uniqueness
    }

    /**
     * Implements hashCode to be consistent with equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, premiered, studio);
    }

    /**
     * Nested static class to represent a single review.
     * Also implements Serializable for consistency.
     */
    public static class Review implements Serializable {
        private String username;
        private String reviewContent;
        private int userProfileImageResId;

        /**
         * Constructor for a Review.
         *
         * @param username The username of the reviewer.
         * @param reviewContent The content of the review.
         * @param userProfileImageResId The drawable resource ID for the reviewer's profile image.
         */
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

        // --- Added Setters for Review (Optional) ---
        public void setUsername(String username) {
            this.username = username;
        }

        public void setReviewContent(String reviewContent) {
            this.reviewContent = reviewContent;
        }

        public void setUserProfileImageResId(int userProfileImageResId) {
            this.userProfileImageResId = userProfileImageResId;
        }

        // --- Utility Methods for Review ---
        @Override
        public String toString() {
            return "Review{" +
                    "username='" + username + '\'' +
                    ", reviewContent='" + reviewContent.substring(0, Math.min(reviewContent.length(), 30)) + "...'" + // Truncate for brevity
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Review review = (Review) o;
            return userProfileImageResId == review.userProfileImageResId &&
                    Objects.equals(username, review.username) &&
                    Objects.equals(reviewContent, review.reviewContent);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, reviewContent, userProfileImageResId);
        }
    }
}
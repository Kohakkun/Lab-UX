package com.example.animedxd.model;

import java.io.Serializable;
import java.util.List;

/**
 * A data class to represent an Anime entry, including its details and initial reviews.
 * Implements Serializable to allow passing between Activities/Fragments via Bundles.
 */
public class Anime implements Serializable {
    private String title;
    private int imageResId;
    private String type;
    private String episodes;
    private String demographic;
    private String premiered;
    private String studio;
    private String genre;
    private String source;
    private String rating;
    private String score;
    private String synopsis;
    private float ratingBarScore;
    private List<Review> initialReviews;

    /**
     * Constructor for the Anime data model.
     *
     * @param title The title of the anime.
     * @param imageResId The drawable resource ID for the anime's cover image.
     * @param type The type of the anime (e.g., "TV", "Movie").
     * @param episodes The number of episodes.
     * @param demographic The target demographic (e.g., "Shounen", "Seinen").
     * @param premiered The season and year it premiered (e.g., "Winter 2024").
     * @param studio The animation studio.
     * @param genre The genre(s) of the anime.
     * @param source The original source material (e.g., "Manga", "Web novel").
     * @param rating The age rating (e.g., "R - 17+", "PG-13").
     * @param score The numerical score as a string (e.g., "8.75").
     * @param synopsis The full synopsis text.
     * @param ratingBarScore The float value for the RatingBar.
     * @param initialReviews A list of sample reviews for the anime.
     */
    public Anime(String title, int imageResId, String type, String episodes, String demographic,
                 String premiered, String studio, String genre, String source, String rating,
                 String score, String synopsis, float ratingBarScore, List<Review> initialReviews) {
        this.title = title;
        this.imageResId = imageResId;
        this.type = type;
        this.episodes = episodes;
        this.demographic = demographic;
        this.premiered = premiered;
        this.studio = studio;
        this.genre = genre;
        this.source = source;
        this.rating = rating;
        this.score = score;
        this.synopsis = synopsis;
        this.ratingBarScore = ratingBarScore;
        this.initialReviews = initialReviews;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
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

    public String getSynopsis() {
        return synopsis;
    }

    public float getRatingBarScore() {
        return ratingBarScore;
    }

    public List<Review> getInitialReviews() {
        return initialReviews;
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
    }
}

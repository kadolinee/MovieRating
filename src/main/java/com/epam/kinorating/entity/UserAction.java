package com.epam.kinorating.entity;

import java.io.Serializable;
import java.sql.Date;

public class UserAction implements Serializable{
    private int id;
    private int userId;
    private int movieId;
    private int rating;
    private String review;
    private Date dateValuation;
    private Date dateReview;

    public UserAction() {}

    public UserAction(int rating, Date dateValuation, String review, Date dateReview) {
        this.rating = rating;
        this.dateValuation = dateValuation;
        this.review = review;
        this.dateReview = dateReview;
    }

    public UserAction(int userId, int movieId, int rating, Date dateValuation) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.dateValuation = dateValuation;
    }

    public UserAction(int userId, int movieId, String review, Date dateReview) {
        this.userId = userId;
        this.movieId = movieId;
        this.review = review;
        this.dateReview = dateReview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDateValuation() {
        return dateValuation;
    }

    public void setDateValuation(Date dateValuation) {
        this.dateValuation = dateValuation;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

}

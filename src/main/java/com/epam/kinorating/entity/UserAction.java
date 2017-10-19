package com.epam.kinorating.entity;

import java.io.Serializable;
import java.sql.Date;

public class UserAction implements Serializable{
    private int ID;
    private int userID;
    private int movieID;
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

    public UserAction(int userID, int movieID, int rating, Date dateValuation) {
        this.userID = userID;
        this.movieID = movieID;
        this.rating = rating;
        this.dateValuation = dateValuation;
    }

    public UserAction(int userID, int movieID, String review, Date dateReview) {
        this.userID = userID;
        this.movieID = movieID;
        this.review = review;
        this.dateReview = dateReview;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
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

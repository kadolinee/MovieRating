package com.epam.kinorating.entity;

import java.io.Serializable;

public class Movie implements Serializable{
    private int id;
    private String name_ru;
    private String name_en;
    private String genre_ru;
    private String genre_en;
    private int year;
    private String title_ru;
    private String title_en;
    private String country_ru;
    private String country_en;
    private int duration;
    private String cast_ru;
    private String cast_en;
    private String awards_ru;
    private String awards_en;
    private String image;
    private boolean tvSerial;

    public Movie() {}

    public Movie(String name_ru, String name_en, String genre_ru, String genre_en, int year, String title_ru,
                 String title_en, String country_ru, String country_en, int duration, String cast_ru, String cast_en,
                 String awards_ru, String awards_en, boolean tvSerial, String image) {
        this.name_ru = name_ru;
        this.name_en = name_en;
        this.genre_ru = genre_ru;
        this.genre_en = genre_en;
        this.year = year;
        this.title_ru = title_ru;
        this.title_en = title_en;
        this.country_ru = country_ru;
        this.country_en = country_en;
        this.duration = duration;
        this.cast_ru = cast_ru;
        this.cast_en = cast_en;
        this.awards_ru = awards_ru;
        this.awards_en = awards_en;
        this.image = image;
        this.tvSerial = tvSerial;
    }

    public Movie(int id, String name_ru, String name_en, String genre_ru, String genre_en, int year,
                 String country_ru, String country_en) {
        this.id = id;
        this.name_ru = name_ru;
        this.name_en = name_en;
        this.genre_ru = genre_ru;
        this.genre_en = genre_en;
        this.year = year;
        this.country_ru = country_ru;
        this.country_en = country_en;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getGenre_ru() {
        return genre_ru;
    }

    public void setGenre_ru(String genre_ru) {
        this.genre_ru = genre_ru;
    }

    public String getGenre_en() {
        return genre_en;
    }

    public void setGenre_en(String genre_en) {
        this.genre_en = genre_en;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle_ru() {
        return title_ru;
    }

    public void setTitle_ru(String title_ru) {
        this.title_ru = title_ru;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getCountry_ru() {
        return country_ru;
    }

    public void setCountry_ru(String country_ru) {
        this.country_ru = country_ru;
    }

    public String getCountry_en() {
        return country_en;
    }

    public void setCountry_en(String country_en) {
        this.country_en = country_en;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCast_ru() {
        return cast_ru;
    }

    public void setCast_ru(String cast_ru) {
        this.cast_ru = cast_ru;
    }

    public String getCast_en() {
        return cast_en;
    }

    public void setCast_en(String cast_en) {
        this.cast_en = cast_en;
    }

    public String getAwards_ru() {
        return awards_ru;
    }

    public void setAwards_ru(String awards_ru) {
        this.awards_ru = awards_ru;
    }

    public String getAwards_en() {
        return awards_en;
    }

    public void setAwards_en(String awards_en) {
        this.awards_en = awards_en;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isTvSerial() {
        return tvSerial;
    }

    public void setTvSerial(boolean tvSerial) {
        this.tvSerial = tvSerial;
    }

}
package com.epam.kinorating.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "movie")
public class Movie implements Serializable{
    @Id
    private int id;

    private String nameRu;
    private String nameEn;
    private String genreRu;
    private String genreEn;
    private int year;
    private String titleRu;
    private String titleEn;
    private String countryRu;
    private String countryEn;
    private int duration;
    private String castRu;
    private String castEn;
    private String awardsRu;
    private String awardsEn;
    private String image;
    private boolean tvSerial;

    public Movie() {}

    public Movie(String nameRu, String nameEn, String genreRu, String genreEn, int year, String titleRu,
                 String titleEn, String countryRu, String countryEn, int duration, String castRu, String castEn,
                 String awardsRu, String awardsEn, boolean tvSerial, String image) {
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.genreRu = genreRu;
        this.genreEn = genreEn;
        this.year = year;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.countryRu = countryRu;
        this.countryEn = countryEn;
        this.duration = duration;
        this.castRu = castRu;
        this.castEn = castEn;
        this.awardsRu = awardsRu;
        this.awardsEn = awardsEn;
        this.image = image;
        this.tvSerial = tvSerial;
    }


    public Movie(int id, String nameRu, String nameEn, String genreRu, String genreEn, String countryRu, String countryEn) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.genreRu = genreRu;
        this.genreEn = genreEn;
        this.countryRu = countryRu;
        this.countryEn = countryEn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getGenreRu() {
        return genreRu;
    }

    public void setGenreRu(String genreRu) {
        this.genreRu = genreRu;
    }

    public String getGenreEn() {
        return genreEn;
    }

    public void setGenreEn(String genreEn) {
        this.genreEn = genreEn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getCountryRu() {
        return countryRu;
    }

    public void setCountryRu(String countryRu) {
        this.countryRu = countryRu;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCastRu() {
        return castRu;
    }

    public void setCastRu(String castRu) {
        this.castRu = castRu;
    }

    public String getCastEn() {
        return castEn;
    }

    public void setCastEn(String castEn) {
        this.castEn = castEn;
    }

    public String getAwardsRu() {
        return awardsRu;
    }

    public void setAwardsRu(String awardsRu) {
        this.awardsRu = awardsRu;
    }

    public String getAwardsEn() {
        return awardsEn;
    }

    public void setAwardsEn(String awardsEn) {
        this.awardsEn = awardsEn;
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
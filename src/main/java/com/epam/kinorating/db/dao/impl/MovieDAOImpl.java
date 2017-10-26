package com.epam.kinorating.db.dao.impl;

import com.epam.kinorating.config.Fields;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.db.dao.AbstractDAO;
import com.epam.kinorating.db.dao.MovieDAO;
import com.epam.kinorating.db.manager.DBManager;
import com.epam.kinorating.entity.Movie;
import com.epam.kinorating.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl extends AbstractDAO implements MovieDAO {
    private static final Logger log = Logger.getLogger(MovieDAOImpl.class);

    public MovieDAOImpl() {
        this.manager = DBManager.getInstance();
    }

    private static final String SQL_CREATE_MOVIE = "INSERT INTO movie(name_ru, name_en, genre_ru, genre_en, year, title_ru, title_en, country_ru, country_en, duration, cast_ru, cast_en, awards_ru, awards_en, tv_serial, image)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_READ_MOVIE = "SELECT * FROM movie WHERE id=?";
    private static final String SQL_READ_MOVIES_FOR_PAGE = "SELECT id, name_ru, name_en, title_ru, title_en, image FROM movie ORDER BY id DESC LIMIT ?, 3";
    private static final String SQL_READ_MOVIES_BY_NAME = "SELECT id, name_ru, name_en, title_ru, title_en, image FROM movie WHERE UPPER (name_ru) = UPPER (?) or UPPER (name_en) = UPPER (?)";


    @Override
    public boolean create(Movie movie) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_CREATE_MOVIE);

            stmt.setString(1, movie.getNameRu());
            stmt.setString(2, movie.getNameEn());
            stmt.setString(3, movie.getGenreRu());
            stmt.setString(4, movie.getGenreEn());
            stmt.setInt(5, movie.getYear());
            stmt.setString(6, movie.getTitleRu());
            stmt.setString(7, movie.getTitleEn());
            stmt.setString(8, movie.getCountryRu());
            stmt.setString(9, movie.getCountryEn());
            stmt.setInt(10, movie.getDuration());
            stmt.setString(11, movie.getCastRu());
            stmt.setString(12, movie.getCastEn());
            stmt.setString(13, movie.getAwardsRu());
            stmt.setString(14, movie.getAwardsEn());
            stmt.setBoolean(15, movie.isTvSerial());
            stmt.setString(16, movie.getImage());

            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(Messages.MOVIE_ADD_ERROR, e);
            throw new DAOException(Messages.MOVIE_ADD_ERROR, e);
        } finally {
            close(stmt);
            close(con);
        }
        return true;
    }

    @Override
        public Movie read(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Movie movie = null;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_READ_MOVIE);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            if (rs.next()) {
                String nameRu = rs.getString(Fields.MOVIE_NAME_RU);
                String nameEn = rs.getString(Fields.MOVIE_NAME_EN);
                String genreRu = rs.getString(Fields.MOVIE_GENRE_RU);
                String genreEn = rs.getString(Fields.MOVIE_GENRE_EN);
                int year = rs.getInt(Fields.MOVIE_YEAR);
                String titleRu = rs.getString(Fields.MOVIE_TITLE_RU);
                String titleEn = rs.getString(Fields.MOVIE_TITLE_EN);
                String countryRu = rs.getString(Fields.MOVIE_COUNTRY_RU);
                String countryEn = rs.getString(Fields.MOVIE_COUNTRY_EN);
                int duration = rs.getInt(Fields.MOVIE_DURATION);
                String castRu = rs.getString(Fields.MOVIE_CAST_RU);
                String castEn = rs.getString(Fields.MOVIE_CAST_EN);
                String awardsRu = rs.getString(Fields.MOVIE_AWARDS_RU);
                String awardsEn = rs.getString(Fields.MOVIE_AWARDS_EN);
                String image = rs.getString(Fields.MOVIE_IMAGE);
                boolean tvSerial = rs.getBoolean(Fields.MOVIE_TVSERIAL);

                movie = new Movie(nameRu, nameEn, genreRu, genreEn, year, titleRu, titleEn, countryRu,
                        countryEn, duration, castRu, castEn, awardsRu, awardsEn, tvSerial, image);
            }
        } catch (SQLException e) {
            log.error(Messages.MOVIE_READ_BY_ID_ERROR, e);
            throw new DAOException(Messages.MOVIE_READ_BY_ID_ERROR, e);
        } finally {
            close(rs);
            close(stmt);
            close(con);
        }
        return movie;
    }

    @Override
    public List<Movie> read(int page, String name) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            con = manager.getConnection();
            if (name == null) {
                stmt = con.prepareStatement(SQL_READ_MOVIES_FOR_PAGE);
                stmt.setInt(1, page);
            }
            else {
                stmt = con.prepareStatement(SQL_READ_MOVIES_BY_NAME);
                stmt.setString(1, name);
                stmt.setString(2, name);
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(Fields.MOVIE_ID);
                String nameRu = rs.getString(Fields.MOVIE_NAME_RU);
                String nameEn = rs.getString(Fields.MOVIE_NAME_EN);
                String titleRu = rs.getString(Fields.MOVIE_TITLE_RU);
                String titleEn = rs.getString(Fields.MOVIE_TITLE_EN);
                String image = rs.getString(Fields.MOVIE_IMAGE);
                Movie movie = new Movie();
                movie.setId(id);
                movie.setNameRu(nameRu);
                movie.setNameEn(nameEn);
                movie.setTitleRu(titleRu);
                movie.setTitleEn(titleEn);
                movie.setImage(image);

                movieList.add(movie);
            }
        } catch (SQLException e) {
            log.error(Messages.MOVIE_READ_MOVIES_BY_NAME, e);
            throw new DAOException(Messages.MOVIE_READ_MOVIES_BY_NAME, e);
        } finally {
            close(rs);
            close(stmt);
            close(con);
        }
        return movieList;
    }

}

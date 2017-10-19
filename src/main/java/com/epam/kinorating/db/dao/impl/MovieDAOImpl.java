package com.epam.kinorating.db.dao.impl;

import com.epam.kinorating.config.Fields;
import com.epam.kinorating.config.Messages;
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

public class MovieDAOImpl extends AbstractDAOImpl implements MovieDAO {
    private static final Logger log = Logger.getLogger(MovieDAOImpl.class);

    private DBManager manager = DBManager.getInstance();

    private static final String SQL_CREATE_MOVIE = "INSERT INTO movie(name_ru, name_en, genre_ru, genre_en, year, title_ru, title_en, country_ru, country_en, duration, cast_ru, cast_en, awards_ru, awards_en, tv_serial, image)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_READ_MOVIE = "SELECT * FROM movie WHERE id=?";
    private static final String SQL_READ_MOVIES_FOR_PAGE = "SELECT id, name_ru, name_en, title_ru, title_en, image FROM movie ORDER BY id DESC LIMIT ?, 3";
    private static final String SQL_READ_MOVIES_BY_NAME = "SELECT id, name_ru, name_en, title_ru, title_en, image FROM movie WHERE UPPER (name_ru) = UPPER (?) or UPPER (name_en) = UPPER (?)";


    @Override
    public boolean create(Movie movie) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_CREATE_MOVIE);

            stmt.setString(1, movie.getName_ru());
            stmt.setString(2, movie.getName_en());
            stmt.setString(3, movie.getGenre_ru());
            stmt.setString(4, movie.getGenre_en());
            stmt.setInt(5, movie.getYear());
            stmt.setString(6, movie.getTitle_ru());
            stmt.setString(7, movie.getTitle_en());
            stmt.setString(8, movie.getCountry_ru());
            stmt.setString(9, movie.getCountry_en());
            stmt.setInt(10, movie.getDuration());
            stmt.setString(11, movie.getCast_ru());
            stmt.setString(12, movie.getCast_en());
            stmt.setString(13, movie.getAwards_ru());
            stmt.setString(14, movie.getAwards_en());
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
        public Movie read(int id) throws DAOException {
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
                String name_ru = rs.getString(Fields.MOVIE_NAME_RU);
                String name_en = rs.getString(Fields.MOVIE_NAME_EN);
                String genre_ru = rs.getString(Fields.MOVIE_GENRE_RU);
                String genre_en = rs.getString(Fields.MOVIE_GENRE_EN);
                int year = rs.getInt(Fields.MOVIE_YEAR);
                String title_ru = rs.getString(Fields.MOVIE_TITLE_RU);
                String title_en = rs.getString(Fields.MOVIE_TITLE_EN);
                String country_ru = rs.getString(Fields.MOVIE_COUNTRY_RU);
                String country_en = rs.getString(Fields.MOVIE_COUNTRY_EN);
                int duration = rs.getInt(Fields.MOVIE_DURATION);
                String cast_ru = rs.getString(Fields.MOVIE_CAST_RU);
                String cast_en = rs.getString(Fields.MOVIE_CAST_EN);
                String awards_ru = rs.getString(Fields.MOVIE_AWARDS_RU);
                String awards_en = rs.getString(Fields.MOVIE_AWARDS_EN);
                String image = rs.getString(Fields.MOVIE_IMAGE);
                boolean tvSerial = rs.getBoolean(Fields.MOVIE_TVSERIAL);

                movie = new Movie(name_ru, name_en, genre_ru, genre_en, year, title_ru, title_en, country_ru,
                        country_en, duration, cast_ru, cast_en, awards_ru, awards_en, tvSerial, image);
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
    public ArrayList<Movie> readByPage(int page) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_READ_MOVIES_FOR_PAGE);

            stmt.setInt(1, page);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(Fields.MOVIE_ID);
                String name_ru = rs.getString(Fields.MOVIE_NAME_RU);
                String name_en = rs.getString(Fields.MOVIE_NAME_EN);
                String title_ru = rs.getString(Fields.MOVIE_TITLE_RU);
                String title_en = rs.getString(Fields.MOVIE_TITLE_EN);
                String image = rs.getString(Fields.MOVIE_IMAGE);
                Movie movie = new Movie();
                movie.setId(id);
                movie.setName_ru(name_ru);
                movie.setName_en(name_en);
                movie.setTitle_ru(title_ru);
                movie.setTitle_en(title_en);
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

    @Override
    public ArrayList<Movie> read(String name) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_READ_MOVIES_BY_NAME);

            stmt.setString(1, name);
            stmt.setString(2, name);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(Fields.MOVIE_ID);
                String name_ru = rs.getString(Fields.MOVIE_NAME_RU);
                String name_en = rs.getString(Fields.MOVIE_NAME_EN);
                String title_ru = rs.getString(Fields.MOVIE_TITLE_RU);
                String title_en = rs.getString(Fields.MOVIE_TITLE_EN);
                String image = rs.getString(Fields.MOVIE_IMAGE);
                Movie movie = new Movie();
                movie.setId(id);
                movie.setName_ru(name_ru);
                movie.setName_en(name_en);
                movie.setTitle_ru(title_ru);
                movie.setTitle_en(title_en);
                movie.setImage(image);

                movieList.add(movie);
            }
        } catch (SQLException e) {
            log.error(Messages.MOVIE_READ_ALL_MOVIES, e);
            throw new DAOException(Messages.MOVIE_READ_ALL_MOVIES, e);
        } finally {
            close(stmt);
            close(rs);
            close(con);
        }
        return movieList;
    }

}

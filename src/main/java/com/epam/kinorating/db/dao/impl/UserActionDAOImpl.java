package com.epam.kinorating.db.dao.impl;

import com.epam.kinorating.config.Fields;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.db.dao.AbstractDAO;
import com.epam.kinorating.db.dao.UserActionDAO;
import com.epam.kinorating.db.manager.DBManager;
import com.epam.kinorating.entity.UserAction;
import com.epam.kinorating.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserActionDAOImpl extends AbstractDAO implements UserActionDAO {
    private static final Logger log = Logger.getLogger(UserActionDAOImpl.class);

    public UserActionDAOImpl() {
        this.manager = DBManager.getInstance();
    }

    private static final String SQL_INSERT_USER_ACTION = "INSERT INTO user_action(user_id, movie_id, rating, review, date_valuation, date_create_review) VALUES (?,?,?,?,?,?)";
    private static final String SQL_READ_ALL_USER_ACTION = "SELECT rating, review, date_valuation, date_create_review FROM user_action WHERE movie_id=?";
    private static final String SQL_IS_RATED = "SELECT EXISTS (SELECT * FROM user_action WHERE user_id=? AND movie_id=? AND rating != 0)";


    public boolean create(UserAction userAction) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_INSERT_USER_ACTION);

            stmt.setInt(1, userAction.getUserId());
            stmt.setInt(2, userAction.getMovieId());
            stmt.setInt(3, userAction.getRating());
            stmt.setString(4, userAction.getReview());
            stmt.setDate(5, userAction.getDateValuation());
            stmt.setDate(6, userAction.getDateReview());

            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(Messages.USER_ACTION_ADD_ERROR, e);
            throw new DAOException(Messages.USER_ACTION_ADD_ERROR, e);
        } finally {
            close(stmt);
            close(con);
        }
        return true;
    }

    @Override
    public List<UserAction> readAll(int movieId) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<UserAction> userActionList = new ArrayList<>();
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_READ_ALL_USER_ACTION);

            stmt.setInt(1, movieId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int rating = rs.getInt(Fields.USER_ACTION_RATING);
                String review = rs.getString(Fields.USER_ACTION_REVIEW);
                Date dateValuation = rs.getDate(Fields.USER_ACTION_DATE_VALUATION);
                Date dateCreateReview = rs.getDate(Fields.USER_ACTION_DATE_CREATE_REVIEW);

                UserAction userAction = new UserAction(rating, dateValuation, review, dateCreateReview);

                userActionList.add(userAction);
            }
        } catch (SQLException e) {
            log.error(Messages.USER_ACTION_READ_ALL_ERROR, e);
            throw new DAOException(Messages.USER_ACTION_READ_ALL_ERROR, e);
        } finally {
            close(stmt);
            close(rs);
            close(con);
        }
        return userActionList;
    }


    @Override
    public int isRated(int userId, int movieId) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int checkRating = 0;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_IS_RATED);

            stmt.setInt(1, userId);
            stmt.setInt(2, movieId);

            rs = stmt.executeQuery();
            if (rs.next()) {
                checkRating = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error(Messages.USER_ACTION_IS_RATED, e);
            throw new DAOException(Messages.USER_ACTION_IS_RATED, e);
        } finally {
            close(stmt);
            close(rs);
            close(con);
        }
        return checkRating;
    }

}

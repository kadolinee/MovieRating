package com.epam.kinorating.service.impl;

import com.epam.kinorating.db.dao.UserActionDAO;
import com.epam.kinorating.db.dao.impl.AbstractDAOImpl;
import com.epam.kinorating.db.manager.DBManager;
import com.epam.kinorating.entity.UserAction;
import com.epam.kinorating.exception.DAOException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.UserActionService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserActionServiceImpl extends AbstractDAOImpl implements UserActionService{
    private static final Logger log = Logger.getLogger(UserActionServiceImpl.class);

    private UserActionDAO userActionDAO;

    public UserActionServiceImpl(UserActionDAO userActionDAO) {
        this.userActionDAO = userActionDAO;
    }

    @Override
    public boolean add(UserAction userAction) throws ServiceException {
        try {
            userActionDAO.create(userAction);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during adding user action", e);
            throw new ServiceException("Invalid service operation occurred during adding user action", e);
        }
        return true;
    }

    @Override
    public Map<String, Object> getMovieDetails(int movieId) {
        Map<String, Object> valueMap = new HashMap<>();
        try {
            ArrayList<UserAction> userActions = userActionDAO.readAll(movieId);

            int sum = 0;
            int ratingsNumber = 0;
            ArrayList<UserAction> reviews = new ArrayList<>();
            for (UserAction userAction : userActions) {
                if (userAction.getRating() != 0) {
                    sum = sum + userAction.getRating();
                    ratingsNumber++;
                }
                if (userAction.getReview() != null) {
                    reviews.add(userAction);
                }
            }
            double movieRating = (double) sum / ratingsNumber;

            valueMap.put("ratingsNumber", ratingsNumber);
            valueMap.put("movieRating", movieRating);
            valueMap.put("reviews", reviews);

            return valueMap;
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during getting movie details", e);
            throw new ServiceException("Invalid service operation occurred during getting movie details", e);
        }
    }

    @Override
    public boolean isRated(int userId, int movieId) {
        try {
            int valueCheck = userActionDAO.isRated(userId, movieId);

            return valueCheck != 0;
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during check user action", e);
            throw new ServiceException("Invalid service operation occurred during check user action", e);
        }
    }

}

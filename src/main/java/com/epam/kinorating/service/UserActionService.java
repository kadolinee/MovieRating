package com.epam.kinorating.service;

import com.epam.kinorating.db.dao.UserActionDAO;
import com.epam.kinorating.db.dao.impl.UserActionDAOImpl;
import com.epam.kinorating.entity.UserAction;
import com.epam.kinorating.exception.DAOException;
import com.epam.kinorating.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserActionService {
    private static final Logger log = Logger.getLogger(UserActionService.class);

    private static UserActionDAO userActionDAO = new UserActionDAOImpl();

    private UserActionService() {}

    public static boolean add(UserAction userAction) {
        try {
            userActionDAO.create(userAction);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during adding user action", e);
            throw new ServiceException("Invalid service operation occurred during adding user action", e);
        }
        return true;
    }

    public static Map<String, Object> getMovieDetails(int movieId) {
        Map<String, Object> valueMap = new HashMap<>();
        try {
            List<UserAction> userActions = userActionDAO.readAll(movieId);

            int sum = 0;
            int ratingsNumber = 0;
            double movieRating = 0;
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
            if (ratingsNumber != 0) {
                movieRating = (double) sum / ratingsNumber;
            }
            valueMap.put("ratingsNumber", ratingsNumber);
            valueMap.put("movieRating", movieRating);
            valueMap.put("reviews", reviews);

        } catch (DAOException e) {
            log.error("Invalid service operation occurred during getting movie details", e);
            throw new ServiceException("Invalid service operation occurred during getting movie details", e);
        }

        return valueMap;
    }

    public static boolean isRated(int userId, int movieId) {
        try {
            int valueCheck = userActionDAO.isRated(userId, movieId);

            return valueCheck != 0;
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during check user action", e);
            throw new ServiceException("Invalid service operation occurred during check user action", e);
        }
    }

}

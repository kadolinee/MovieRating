package com.epam.kinorating.service;

import com.epam.kinorating.db.dao.UserDAO;
import com.epam.kinorating.db.dao.impl.UserDAOImpl;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.encryptor.Encryptor;
import com.epam.kinorating.exception.DAOException;
import com.epam.kinorating.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {
    private static final Logger log = Logger.getLogger(UserService.class);

    private static UserDAO userDAO = new UserDAOImpl();

    private UserService() {}

    public static User find(String name, String password) {
        try {
            String salt = userDAO.read(name);
            if (salt == null) {
                return null;
            } else {
                String encryptPassword = Encryptor.getSHA512SecurePassword(password, Encryptor.convertStringToArray(salt));

                return userDAO.read(name, encryptPassword);
            }

        } catch (DAOException e) {
            log.error("Invalid service operation occurred during finding user", e);
            throw new ServiceException("Invalid service operation occurred during finding user", e);
        }
    }

    public static int add(User user) {
        try {
            byte[] saltArray = Encryptor.getSalt();
            String securePassword = Encryptor.getSHA512SecurePassword(user.getPassword(), saltArray);

            user.setSalt(Encryptor.convertArrayToString(saltArray));
            user.setPassword(securePassword);

            return userDAO.create(user);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during adding user", e);
            throw new ServiceException("Invalid service operation occurred during adding user", e);
        }
    }

    public static boolean update(User user) {
        try {
            return userDAO.update(user);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during updating user", e);
            throw new ServiceException("Invalid service operation occurred during updating user", e);
        }
    }

    public static double calc(int ratingsNumber, double movieRating, int ratingSetByUser) {
        double ratingChange = 0;

        if (ratingsNumber > 2) {
            switch ((int) Math.abs(ratingSetByUser - Math.round(movieRating))) {
                case 0:
                    ratingChange = 1;
                    break;
                case 1:
                    ratingChange = 0.5;
                    break;
                case 2:
                    ratingChange = 0.25;
                    break;
                case 3:
                    ratingChange = -0.25;
                    break;
                case 4:
                    ratingChange = -0.5;
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    ratingChange = -1;
                    break;
            }
        }
        return ratingChange;
    }

    public static List<User> findAll() {
        try {
            return userDAO.readAll();
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during finding all users", e);
            throw new ServiceException("Invalid service operation occurred during finding all users", e);
        }
    }

    public static boolean isFree(String name, String mail) {
        try {
            int valueCheck = userDAO.check(name, mail);

            return valueCheck == 0;
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during check user", e);
            throw new ServiceException("Invalid service operation occurred during check user", e);
        }
    }
}

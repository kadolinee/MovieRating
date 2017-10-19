package com.epam.kinorating.service.impl;

import com.epam.kinorating.db.dao.UserDAO;
import com.epam.kinorating.db.dao.impl.AbstractDAOImpl;
import com.epam.kinorating.db.manager.DBManager;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.encryptor.Encryptor;
import com.epam.kinorating.exception.DAOException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.UserService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

public class UserServiceImpl extends AbstractDAOImpl implements UserService{
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User find(String name, String password) throws ServiceException {
        try {
            String salt = userDAO.read(name);
            if (salt == null) {
                return null;
            } else {
                String encryptPassword = Encryptor.get_SHA_512_SecurePassword(password, Encryptor.convertStringToArray(salt));

                return userDAO.read(name, encryptPassword);
            }

        } catch (DAOException e) {
            log.error("Invalid service operation occurred during finding user", e);
            throw new ServiceException("Invalid service operation occurred during finding user", e);
        }
    }

    @Override
    public int add(User user) throws ServiceException {
        try {
            byte[] saltArray = Encryptor.getSalt();
            String securePassword = Encryptor.get_SHA_512_SecurePassword(user.getPassword(), saltArray);

            user.setSalt(Encryptor.convertArrayToString(saltArray));
            user.setPassword(securePassword);

            return userDAO.create(user);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during adding user", e);
            throw new ServiceException("Invalid service operation occurred during adding user", e);
        }
    }

    @Override
    public boolean update(User user) throws ServiceException {
        try {
            return userDAO.update(user);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during updating user", e);
            throw new ServiceException("Invalid service operation occurred during updating user", e);
        }
    }

    @Override
    public double calc(int ratingsNumber, double movieRating, int ratingSetByUser) {
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

    @Override
    public ArrayList<User> findAll() throws ServiceException {
        try {
            return userDAO.readAll();
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during finding all users", e);
            throw new ServiceException("Invalid service operation occurred during finding all users", e);
        }
    }

    @Override
    public boolean isFreeMail(String mail) throws ServiceException {
        try {
            int valueCheck = userDAO.checkMail(mail);

            return valueCheck == 0;
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during check user mail", e);
            throw new ServiceException("Invalid service operation occurred during check user mail", e);
        }
    }

    @Override
    public boolean isFreeName(String name) throws ServiceException {
        try {
            int valueCheck = userDAO.checkName(name);

            return valueCheck == 0;
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during check user name", e);
            throw new ServiceException("Invalid service operation occurred during check user name", e);
        }
    }
}

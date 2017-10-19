package com.epam.kinorating.db.dao.impl;

import com.epam.kinorating.config.Fields;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.db.dao.UserDAO;
import com.epam.kinorating.db.manager.DBManager;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOImpl extends AbstractDAOImpl implements UserDAO {
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);

    private DBManager manager = DBManager.getInstance();

    private static final String SQL_INSERT_USER = "INSERT INTO user(name, mail, password, date_create, salt) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_USER = "UPDATE user SET rating=?, banned=?  WHERE id=?";
    private static final String SQL_READ_ALL_USERS = "SELECT id, name, rating, mail, date_create FROM user";
    private static final String SQL_READ_USER_BY_NAME_AND_PWD = "SELECT id, name, password, mail, rating, date_create, banned, role_id FROM user WHERE name = ? AND password = ?";
    private static final String SQL_READ_USER_SALT_BY_NAME = "SELECT salt FROM user WHERE name=?";
    private static final String SQL_CHECK_NAME = "SELECT EXISTS (SELECT * FROM user WHERE name =?)";
    private static final String SQL_CHECK_MAIL = "SELECT EXISTS (SELECT * FROM user WHERE mail =?)";

    @Override
    public int create(User user) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int id = 0;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getMail());
            stmt.setString(3, user.getPassword());
            stmt.setDate(4, user.getDateCreate());
            stmt.setString(5, user.getSalt());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error(Messages.USER_CREATE_ERROR, e);
            throw new DAOException(Messages.USER_CREATE_ERROR, e);
        } finally {
            close(stmt);
            close(rs);
            close(con);
        }
        return id;
    }

    @Override
    public User read(String name, String password) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        User user = null;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_READ_USER_BY_NAME_AND_PWD);

            stmt.setString(1, name);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(Fields.USER_ID);
                String mail = rs.getString(Fields.USER_MAIL);
                Date dateCreate = rs.getDate(Fields.USER_DATE_CREATE);
                double rating = rs.getDouble(Fields.USER_RATING);
                boolean banned = rs.getBoolean(Fields.USER_BANNED);
                int roleId = rs.getInt(Fields.USER_ROLE_ID);

                user = new User(id, name, rating, mail, dateCreate);
                user.setPassword(password);
                user.setBanned(banned);
                user.setRoleId(roleId);
            }
        } catch (SQLException e) {
            log.error(Messages.USER_READ_BY_NAME_AND_PWD_ERROR, e);
            throw new DAOException(Messages.USER_READ_BY_NAME_AND_PWD_ERROR, e);
        } finally {
            close(stmt);
            close(rs);
            close(con);
        }
        return user;
    }

    @Override
    public String read(String name) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String salt = null;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_READ_USER_SALT_BY_NAME);
            stmt.setString(1, name);

            rs = stmt.executeQuery();

            if (rs.next()) {
                salt = rs.getString(Fields.USER_SALT);
            }
        } catch (SQLException e) {
            log.error(Messages.USER_SALT_READ_BY_NAME_ERROR, e);
            throw new DAOException(Messages.USER_SALT_READ_BY_NAME_ERROR, e);
        } finally {
            close(stmt);
            close(rs);
            close(con);
        }
        return salt;
    }

    @Override
    public int checkName(String name) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int checkName = 0;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_CHECK_NAME);

            stmt.setString(1, name);

            rs = stmt.executeQuery();
            if (rs.next()) {
                checkName = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error(Messages.USER_CHECK_NAME_ERROR, e);
            throw new DAOException(Messages.USER_CHECK_NAME_ERROR, e);
        } finally {
            close(stmt);
            close(rs);
            close(con);
        }
        return checkName;
    }

    @Override
    public int checkMail(String mail) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int checkMail = 0;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_CHECK_MAIL);

            stmt.setString(1, mail);

            rs = stmt.executeQuery();
            if (rs.next()) {
                checkMail = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error(Messages.USER_CHECK_MAIL_ERROR, e);
            throw new DAOException(Messages.USER_CHECK_MAIL_ERROR, e);
        } finally {
            close(stmt);
            close(rs);
            close(con);
        }
        return checkMail;
    }

    @Override
    public boolean update(User user) throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE_USER);

            stmt.setDouble(1, user.getRating());
            stmt.setBoolean(2, user.isBanned());
            stmt.setInt(3, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(Messages.USER_UPDATE_ERROR, e);
            throw new DAOException(Messages.USER_UPDATE_ERROR, e);
        } finally {
            close(stmt);
            close(con);
        }
        return true;
    }

    @Override
    public ArrayList<User> readAll() throws DAOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<User> userList = new ArrayList<User>();
        try {
            con = manager.getConnection();
            stmt = con.prepareStatement(SQL_READ_ALL_USERS);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(Fields.USER_ID);
                String name = rs.getString(Fields.USER_NAME);
                double rating = rs.getDouble(Fields.USER_RATING);
                String mail = rs.getString(Fields.USER_MAIL);
                Date dateCreate = rs.getDate(Fields.USER_DATE_CREATE);

                User user = new User(id, name, rating, mail, dateCreate);

                userList.add(user);
            }
        } catch (SQLException e) {
            log.error(Messages.USER_READ_ALL_ERROR, e);
            throw new DAOException(Messages.USER_READ_ALL_ERROR, e);
        } finally {
            close(rs);
            close(stmt);
            close(con);
        }
        return userList;
    }
}
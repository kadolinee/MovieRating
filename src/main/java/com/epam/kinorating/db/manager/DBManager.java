package com.epam.kinorating.db.manager;

import com.epam.kinorating.exception.DBException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBManager {
    private static final Logger log = Logger.getLogger(DBManager.class);
    private static DBManager instance;
    private static Lock lock = new ReentrantLock();
    private DataSource ds;

    public static DBManager getInstance() throws DBException {
        try {
            lock.lock();
            if (instance == null) {
                instance = new DBManager();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc/movieRating");
            log.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            log.error("Cannot obtain data source", ex);
            throw new DBException("Cannot obtain data source", ex);
        }
    }

    public Connection getConnection() {
        Connection con;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            log.error("Error cannot obtain connection", ex);
            throw new DBException("Error cannot obtain connection", ex);
        }
        return con;
    }
}

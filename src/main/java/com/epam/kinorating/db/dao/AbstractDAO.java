package com.epam.kinorating.db.dao;

import com.epam.kinorating.db.manager.DBManager;
import com.epam.kinorating.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;

public abstract class AbstractDAO {
    private static final Logger log = Logger.getLogger(AbstractDAO.class);

    protected DBManager manager;

    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("Exception occurred during closing result set", e);
                throw new DAOException("Exception occurred during closing result set", e);
            }
        }
    }

    protected void close(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.error("Exception occurred during closing statement", e);
                throw new DAOException("Exception occurred during closing statement", e);
            }
        }
    }

    protected void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Exception occurred during closing connection", e);
                throw new DAOException("Exception occurred during closing connection", e);
            }
        }
    }
}

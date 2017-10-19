package com.epam.kinorating.db.dao.impl;

import com.epam.kinorating.db.dao.AbstractDAO;
import com.epam.kinorating.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;

public abstract class AbstractDAOImpl implements AbstractDAO {
    private static final Logger log = Logger.getLogger(AbstractDAOImpl.class);

    @Override
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("Exception occurred during closing result set", e);
                throw new DAOException("Exception occurred during closing result set", e);
            }
        }
    }

    @Override
    public void close(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.error("Exception occurred during closing statement", e);
                throw new DAOException("Exception occurred during closing statement", e);
            }
        }
    }

    @Override
    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Exception occurred during closing statement", e);
                throw new DAOException("Exception occurred during closing statement", e);
            }
        }
    }
}

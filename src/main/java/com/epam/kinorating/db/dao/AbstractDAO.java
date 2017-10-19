package com.epam.kinorating.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface AbstractDAO {
    void close(ResultSet rs);
    void close(PreparedStatement stmt);
    void close(Connection conn);
}

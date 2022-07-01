package com.example.tadi.JDBC;

import com.example.tadi.JDBC.JDBCModel;

import java.sql.Connection;

public class JDBCController {
    JDBCModel JdbcModel = new JDBCModel();

    public Connection ConnnectionData() {
        return JdbcModel.getConnectionSQLServer();
    }
}

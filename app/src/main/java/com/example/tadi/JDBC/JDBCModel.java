package com.example.tadi.JDBC;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

@SuppressLint("NewApi")
public class JDBCModel {
    public Connection getConnectionSQLServer() {
        Connection connection = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            String connectionString = "jdbc:jtds:sqlserver://192.168.1.14:1433;databasename=Tadi;user=sach;password=Sach1";

            connection = DriverManager.getConnection(connectionString);

            Log.e("ThongBao", "Ket noi thanh cong");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

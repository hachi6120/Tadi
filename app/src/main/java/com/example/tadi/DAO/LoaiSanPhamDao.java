package com.example.tadi.DAO;

import android.content.Context;
import android.widget.Toast;

import com.example.tadi.JDBC.JDBCController;
import com.example.tadi.Model.LoaiSP;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoaiSanPhamDao {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public LoaiSanPhamDao() {
        connection = jdbcController.ConnnectionData();
    }

    public void Insert(LoaiSP objUser) throws Exception {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT LoaiSanPham(maLoai,tenLoai) VALUES (?,?)");
            preparedStatement.setString(2,objUser.getTenLoai());
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

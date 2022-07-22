package com.example.tadi.DAO;

import android.util.Log;

import com.example.tadi.JDBC.JDBCController;
import com.example.tadi.Model.LoaiSP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamDao {
    private Connection connection;

    public LoaiSanPhamDao() {
        JDBCController db = new JDBCController();
        connection = db.ConnnectionData();
    }

    public void Insert(LoaiSP objUser) throws Exception {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT LoaiSanPham(tenLoai) VALUES (?)");
            preparedStatement.setString(1,objUser.getTenLoai());
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Updata(LoaiSP objUser) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE LoaiSanPham SET tenLoai=?  WHERE maLoai=?");
                preparedStatement.setString(1,objUser.getTenLoai());
                preparedStatement.setInt(2,objUser.getMaLoai());
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int Delete(String id) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM LoaiSanPham WHERE maLoai=?");
                preparedStatement.setString(1,id);
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }


    public List<LoaiSP> getAll(){
        List<LoaiSP> listCat = new ArrayList<LoaiSP>();
        try {
            if (this.connection != null) {
                String sqlQuery = "SELECT * FROM LoaiSanPham ";
                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    LoaiSP objCat = new LoaiSP();
                    objCat.setMaLoai(resultSet.getInt("maLoai")); // truyền tên cột dữ liệu
                    objCat.setTenLoai(resultSet.getString("tenLoai")); // tên cột dữ liệu là name
                    listCat.add(objCat);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }
        return  listCat;
    }

}

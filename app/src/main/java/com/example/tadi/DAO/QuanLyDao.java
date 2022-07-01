package com.example.tadi.DAO;

import android.util.Log;

import com.example.tadi.JDBC.JDBCController;
import com.example.tadi.Model.QuanLy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDao {
    private Connection connection;

    public QuanLyDao() {
        JDBCController db = new JDBCController();
        connection = db.ConnnectionData();
    }

    public void Insert(QuanLy objUser) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT NhanVien(maNhanVien,tenNhanVien,sdt,cccd,maKhau) VALUES (?,?,?,?,?)");
                preparedStatement.setString(1,objUser.getMaQL());
                preparedStatement.setString(2,objUser.getTenQL());
                preparedStatement.setString(3,objUser.getSdtQL());
                preparedStatement.setString(4,objUser.getCCCD());
                preparedStatement.setString(5,objUser.getMatKhauQL());
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void Updata(QuanLy objUser) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE NhanVien SET tenNhanVien=? , sdt=? , cccd=? , maKhau=?  WHERE maNhanVien=?");
                preparedStatement.setString(1,objUser.getTenQL());
                preparedStatement.setString(2,objUser.getSdtQL());
                preparedStatement.setString(3,objUser.getCCCD());
                preparedStatement.setString(4,objUser.getMatKhauQL());
                preparedStatement.setString(5,objUser.getMaQL());
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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM NhanVien WHERE maNhanVien=?");
                preparedStatement.setString(1,id);
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }


    public List<QuanLy> getAll(){
        List<QuanLy> listCat = new ArrayList<QuanLy>();
        try {
            if (this.connection != null) {
                String sqlQuery = "SELECT * FROM NhanVien ";
                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    QuanLy objCat = new QuanLy();
                    objCat.setMaQL(resultSet.getString("maNhanVien")); // truyền tên cột dữ liệu
                    objCat.setTenQL(resultSet.getString("tenNhanVien")); // tên cột dữ liệu là name
                    objCat.setSdtQL(resultSet.getString("sdt"));
                    objCat.setCCCD(resultSet.getString("cccd"));
                    objCat.setMatKhauQL(resultSet.getString("maKhau"));
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

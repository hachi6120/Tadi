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
                Statement statement = this.connection.createStatement(); // kh???i t???o c???u tr??c truy v???n
                ResultSet resultSet = statement.executeQuery(sqlQuery); // th???c thi c??u l???nh truy v???n
                while (resultSet.next()) { // ?????c d??? li???u g??n v??o ?????i t?????ng v?? ????a v??o list
                    LoaiSP objCat = new LoaiSP();
                    objCat.setMaLoai(resultSet.getInt("maLoai")); // truy???n t??n c???t d??? li???u
                    objCat.setTenLoai(resultSet.getString("tenLoai")); // t??n c???t d??? li???u l?? name
                    listCat.add(objCat);
                }
            } // n???u k???t n???i kh??c null th?? m???i select v?? th??m d??? li???u v??o, n???u kh??ng th?? tr??? v??? ds r???ng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: C?? l???i truy v???n d??? li???u " );
            e.printStackTrace();
        }
        return  listCat;
    }

}

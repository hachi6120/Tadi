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
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT NhanVien(maNhanVien,tenNhanVien,sdt,cccd,maKhau,avatarNhanVien) VALUES (?,?,?,?,?,?)");
                preparedStatement.setString(1,objUser.getMaQL());
                preparedStatement.setString(2,objUser.getTenQL());
                preparedStatement.setString(3,objUser.getSdtQL());
                preparedStatement.setString(4,objUser.getCCCD());
                preparedStatement.setString(5,objUser.getMatKhauQL());
                preparedStatement.setBytes(6,objUser.getAvatarNhanVien());
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
                Statement statement = this.connection.createStatement(); // kh???i t???o c???u tr??c truy v???n
                ResultSet resultSet = statement.executeQuery(sqlQuery); // th???c thi c??u l???nh truy v???n
                while (resultSet.next()) { // ?????c d??? li???u g??n v??o ?????i t?????ng v?? ????a v??o list
                    QuanLy objCat = new QuanLy();
                    objCat.setMaQL(resultSet.getString("maNhanVien")); // truy???n t??n c???t d??? li???u
                    objCat.setTenQL(resultSet.getString("tenNhanVien")); // t??n c???t d??? li???u l?? name
                    objCat.setSdtQL(resultSet.getString("sdt"));
                    objCat.setCCCD(resultSet.getString("cccd"));
                    objCat.setMatKhauQL(resultSet.getString("maKhau"));
                    objCat.setAvatarNhanVien(resultSet.getBytes("avatarNhanVien"));
                    listCat.add(objCat);
                }
            } // n???u k???t n???i kh??c null th?? m???i select v?? th??m d??? li???u v??o, n???u kh??ng th?? tr??? v??? ds r???ng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: C?? l???i truy v???n d??? li???u " );
            e.printStackTrace();
        }
        return  listCat;
    }

    public int checkLogin(String id, String password){
        List<QuanLy> list = new ArrayList<>();
        try {
            if (this.connection != null) {
                String sqlQuery = "SELECT * FROM NhanVien WHERE maNhanVien='"+id+"' AND maKhau='"+password+"'";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    QuanLy objCat = new QuanLy();
                    objCat.setMaQL(resultSet.getString("maNhanVien"));
                    objCat.setMatKhauQL(resultSet.getString("maKhau"));
                    list.add(objCat);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.size()==0){
            return -1;
        }
        return 1;
    }

}

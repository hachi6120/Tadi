package com.example.tadi.DAO;

import android.util.Log;

import com.example.tadi.JDBC.JDBCController;
import com.example.tadi.Model.DonHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DonHangDao {
    private Connection connection;

    public DonHangDao() {
        JDBCController db = new JDBCController();
        connection = db.ConnnectionData();
    }

    public void Insert(DonHang objUser) throws Exception {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT DonHang(maSanPham,maNguoiDung,maNhanVien,soLuongMua,sizeDatHang,thoiGian,trangThai) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,objUser.getMaSP());
            preparedStatement.setString(2,objUser.getMaND());
            preparedStatement.setString(3,objUser.getMaQL());
            preparedStatement.setInt(4,objUser.getSoLuongMua());
            preparedStatement.setString(5,objUser.getSizeDathang());
            preparedStatement.setString(6,objUser.getThoiGian());
            preparedStatement.setInt(7,objUser.getTrangThai());
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Updata(DonHang objUser) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DonHang SET maNhanVien=? , trangThai=?  WHERE maDonHang=?");
                preparedStatement.setString(1,objUser.getMaQL());
                preparedStatement.setInt(2,objUser.getTrangThai());
                preparedStatement.setInt(3,objUser.getMaDon());
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


    public List<DonHang> getAll(){
        List<DonHang> listCat = new ArrayList<DonHang>();
        try {
            if (this.connection != null) {
                String sqlQuery = "SELECT * FROM DonHang ";
                Statement statement = this.connection.createStatement(); // kh???i t???o c???u tr??c truy v???n
                ResultSet resultSet = statement.executeQuery(sqlQuery); // th???c thi c??u l???nh truy v???n
                while (resultSet.next()) { // ?????c d??? li???u g??n v??o ?????i t?????ng v?? ????a v??o list
                    DonHang objCat = new DonHang();
                    objCat.setMaDon(resultSet.getInt("maDonHang")); // truy???n t??n c???t d??? li???u
                    objCat.setMaSP(resultSet.getInt("maSanPham")); // t??n c???t d??? li???u l?? name
                    objCat.setMaND(resultSet.getString("maNguoiDung"));
                    objCat.setMaQL(resultSet.getString("maNhanVien"));
                    objCat.setSoLuongMua(resultSet.getInt("soLuongMua"));
                    objCat.setSizeDathang(resultSet.getString("sizeDatHang"));
                    objCat.setThoiGian(resultSet.getString("thoiGian"));
                    objCat.setTrangThai(resultSet.getInt("trangThai"));
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

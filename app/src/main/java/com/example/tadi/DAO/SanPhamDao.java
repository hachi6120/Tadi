package com.example.tadi.DAO;

import android.util.Log;

import com.example.tadi.JDBC.JDBCController;
import com.example.tadi.Model.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {
    private Connection connection;

    public SanPhamDao() {
        JDBCController db = new JDBCController();
        connection = db.ConnnectionData();
    }

    public void Insert(SanPham objUser) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT SanPham(maLoai" +
                        ",soLuongSizeS" +
                        ",soLuongSizeM" +
                        ",soLuongSizeL" +
                        ",tenSanPham" +
                        ",giaNhap" +
                        ",giaBan" +
                        ",soLuong" +
                        ",nhaSX" +
                        ",chatLieu" +
                        ",mauSac" +
                        ",moTa" +
                        ",hinhAnh) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setInt(1,objUser.getMaLoai());
                preparedStatement.setInt(2,objUser.getSoLuongSizeS());
                preparedStatement.setInt(3,objUser.getSoLuongSizeM());
                preparedStatement.setInt(4,objUser.getSoLuongSizeL());
                preparedStatement.setString(5,objUser.getTenSP());
                preparedStatement.setInt(6,objUser.getGiaNhap());
                preparedStatement.setInt(7,objUser.getGiaBan());
                preparedStatement.setInt(8,objUser.getSoLuong());
                preparedStatement.setString(9,objUser.getNhaSX());
                preparedStatement.setString(10,objUser.getChatLieu());
                preparedStatement.setString(11,objUser.getMauSac());
                preparedStatement.setString(12,objUser.getMoTa());
                preparedStatement.setBytes(13,objUser.getImgSP());
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void Updata(SanPham objUser) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE SanPham SET maLoai=?" +
                        ", soLuongSizeS=? " +
                        ", soLuongSizeM=? " +
                        ", soLuongSizeL=? " +
                        ", tenSanPham=? " +
                        ", giaNhap=? " +
                        ", giaBan=? " +
                        ", soLuong=? " +
                        ", nhaSX=? " +
                        ", chatLieu=? " +
                        ", mauSac=? " +
                        ", moTa=? " +
                        ", hinhAnh=?  WHERE maSanPham=?");
                preparedStatement.setInt(1,objUser.getMaLoai());
                preparedStatement.setInt(2,objUser.getSoLuongSizeS());
                preparedStatement.setInt(3,objUser.getSoLuongSizeM());
                preparedStatement.setInt(4,objUser.getSoLuongSizeL());
                preparedStatement.setString(5,objUser.getTenSP());
                preparedStatement.setInt(6,objUser.getGiaNhap());
                preparedStatement.setInt(7,objUser.getGiaBan());
                preparedStatement.setInt(8,objUser.getSoLuong());
                preparedStatement.setString(9,objUser.getNhaSX());
                preparedStatement.setString(10,objUser.getChatLieu());
                preparedStatement.setString(11,objUser.getMauSac());
                preparedStatement.setString(12,objUser.getMoTa());
                preparedStatement.setBytes(13,objUser.getImgSP());
                preparedStatement.setInt(14,objUser.getMaSP());
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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM SanPham WHERE maSanPham=?");
                preparedStatement.setString(1,id);
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }


    public List<SanPham> getAll(){
        List<SanPham> listCat = new ArrayList<SanPham>();
        try {
            if (this.connection != null) {
                String sqlQuery = "SELECT * FROM SanPham ";
                Statement statement = this.connection.createStatement(); // kh???i t???o c???u tr??c truy v???n
                ResultSet resultSet = statement.executeQuery(sqlQuery); // th???c thi c??u l???nh truy v???n
                while (resultSet.next()) { // ?????c d??? li???u g??n v??o ?????i t?????ng v?? ????a v??o list
                    SanPham objCat = new SanPham();
                    objCat.setMaSP(resultSet.getInt("maSanPham")); // truy???n t??n c???t d??? li???u
                    objCat.setMaLoai(resultSet.getInt("maLoai")); // t??n c???t d??? li???u l?? name
                    objCat.setSoLuongSizeS(resultSet.getInt("soLuongSizeS"));
                    objCat.setSoLuongSizeM(resultSet.getInt("soLuongSizeM"));
                    objCat.setSoLuongSizeL(resultSet.getInt("soLuongSizeL"));
                    objCat.setTenSP(resultSet.getString("tenSanPham"));

                    objCat.setGiaNhap(resultSet.getInt("giaNhap"));
                    objCat.setGiaBan(resultSet.getInt("giaBan"));
                    objCat.setSoLuong(resultSet.getInt("soLuong"));
                    objCat.setNhaSX(resultSet.getString("nhaSX"));
                    objCat.setChatLieu(resultSet.getString("chatLieu"));
                    objCat.setMauSac(resultSet.getString("mauSac"));
                    objCat.setMoTa(resultSet.getString("moTa"));
                    objCat.setImgSP(resultSet.getBytes("hinhAnh"));
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

package com.example.tadi.DAO;

import android.util.Log;

import com.example.tadi.JDBC.JDBCController;
import com.example.tadi.Model.NguoiDung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDao {
    private Connection connection;

    public NguoiDungDao() {
        JDBCController db = new JDBCController();
        connection = db.ConnnectionData();
    }

    public void Insert(NguoiDung objUser) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT NguoiDung(maNguoiDung,tenNguoiDung,sdtND,diaChi,tuoi,email,maKhauND,avatarNguoiDung) VALUES (?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1,objUser.getMaND());
                preparedStatement.setString(2,objUser.getTenND());
                preparedStatement.setString(3,objUser.getSdtND());
                preparedStatement.setString(4,objUser.getDiaChiND());
                preparedStatement.setInt(5,objUser.getTuoiND());
                preparedStatement.setString(6,objUser.getEmail());
                preparedStatement.setString(7,objUser.getMatKhauND());
                preparedStatement.setBytes(8,objUser.getImgND());
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void Updata(NguoiDung objUser) throws Exception {
        try {
            if (this.connection != null){
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE NguoiDung SET tenNguoiDung=? , sdtND=? , diaChi=? , tuoi=? ,email=? , maKhauND=?  WHERE maNguoiDung=?");
                preparedStatement.setString(1,objUser.getTenND());
                preparedStatement.setString(2,objUser.getSdtND());
                preparedStatement.setString(3,objUser.getDiaChiND());
                preparedStatement.setInt(4,objUser.getTuoiND());
                preparedStatement.setString(5,objUser.getEmail());
                preparedStatement.setString(6,objUser.getMatKhauND());
                preparedStatement.setString(7,objUser.getMaND());
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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM NguoiDung WHERE maNguoiDung=?");
                preparedStatement.setString(1,id);
                preparedStatement.execute();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }


    public List<NguoiDung> getAll(){
        List<NguoiDung> listCat = new ArrayList<NguoiDung>();
        try {
            if (this.connection != null) {
                String sqlQuery = "SELECT * FROM NguoiDung ";
                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    NguoiDung objCat = new NguoiDung();
                    objCat.setMaND(resultSet.getString("maNguoiDung")); // truyền tên cột dữ liệu
                    objCat.setTenND(resultSet.getString("tenNguoiDung")); // tên cột dữ liệu là name
                    objCat.setSdtND(resultSet.getString("sdtND"));
                    objCat.setDiaChiND(resultSet.getString("diaChi"));
                    objCat.setTuoiND(resultSet.getInt("tuoi"));
                    objCat.setEmail(resultSet.getString("email"));
                    objCat.setMatKhauND(resultSet.getString("maKhauND"));
                    objCat.setImgND(resultSet.getBytes("avatarNguoiDung"));
                    listCat.add(objCat);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }
        return  listCat;
    }

    public int checkLogin(String id, String password){
        List<NguoiDung> list = new ArrayList<>();
        try {
            if (this.connection != null) {
                String sqlQuery = "SELECT * FROM NguoiDung WHERE maNguoiDung='"+id+"' AND maKhauND='"+password+"'";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    NguoiDung objCat = new NguoiDung();
                    objCat.setMaND(resultSet.getString("maNguoiDung"));
                    objCat.setMatKhauND(resultSet.getString("maKhauND"));
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

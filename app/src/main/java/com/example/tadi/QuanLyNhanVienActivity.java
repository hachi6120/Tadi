package com.example.tadi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tadi.Adapter.AdapterNhanVien;
import com.example.tadi.DAO.QuanLyDao;
import com.example.tadi.Model.QuanLy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class QuanLyNhanVienActivity extends AppCompatActivity {

    //Cái activity này dùng để quản lý nhân viên. Có 1 listview nhân viên dùng để sửa xoá,
    // cùng 1 FloatingActionButton để thêm nhân viên

    EditText txtMaNV,txtTenNV,txtSdtNV,txtCccdNV,txtMkNV;
    TextInputLayout tilMaNV,tilTenNV,tilSdtNV,tilCccdNV,tilMkNV;
    FloatingActionButton fab;
    List<QuanLy> quanLyList;
    QuanLy quanLy;
    QuanLyDao quanLyDao;
    AdapterNhanVien adapterNhanVien;
    ListView listView;
    int a;
    int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);

        fab = findViewById(R.id.quanlynhanvien_fab);
        listView = findViewById(R.id.quanlynhanvien_listview);

        quanLy = new QuanLy();

        loadTable();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=-1;
                openDialog(Gravity.CENTER);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a = i;
                openDialog(Gravity.CENTER);
            }
        });

    }

    public void loadTable(){
        quanLyDao = new QuanLyDao();
        quanLyList = quanLyDao.getAll();
        adapterNhanVien = new AdapterNhanVien(this,R.layout.item_lv_nhanviem,quanLyList);
        listView.setAdapter(adapterNhanVien);
    }

    private void openDialog(int gravity){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_nhanvien);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        TextView tvTile = (TextView) dialog.findViewById(R.id.item_tvtile);

        txtMaNV = dialog.findViewById(R.id.dialog_themnhanvien_txtnameuser);
        txtTenNV = dialog.findViewById(R.id.dialog_themnhanvien_txtname);
        txtSdtNV = dialog.findViewById(R.id.dialog_themnhanvien_txtsdt);
        txtCccdNV = dialog.findViewById(R.id.dialog_themnhanvien_txtcccd);
        txtMkNV = dialog.findViewById(R.id.dialog_themnhanvien_txtpass);

        tilMaNV = dialog.findViewById(R.id.dialog_themnhanvien_til_username);
        tilTenNV = dialog.findViewById(R.id.dialog_themnhanvien_til_name);
        tilSdtNV = dialog.findViewById(R.id.dialog_themnhanvien_til_sdt);
        tilCccdNV = dialog.findViewById(R.id.dialog_themnhanvien_til_cccd);
        tilMkNV = dialog.findViewById(R.id.dialog_themnhanvien_til_pass);

        Button btnadd = dialog.findViewById(R.id.dialog_themnhanvien_btn_add);
        Button btncancel = dialog.findViewById(R.id.dialog_themnhanvien_btn_cancel);

        if (a==-1){
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Chỗ này insert nhân viên. viết vứt vào đây...
                    QuanLy quanLy = new QuanLy(txtMaNV.getText().toString()
                            ,txtMkNV.getText().toString()
                            ,txtTenNV.getText().toString()
                            ,txtSdtNV.getText().toString()
                            ,txtCccdNV.getText().toString());
                    try {
                        quanLyDao.Insert(quanLy);
                        Toast.makeText(QuanLyNhanVienActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadTable();
                    } catch (Exception e) {
                        Toast.makeText(QuanLyNhanVienActivity.this, "Lỗi Rồi đại thánh ơi :D", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });

            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }else{
            tvTile.setText("Sửa/Xoá Tài Khoản Nhân Viên");
            btnadd.setText("Sửa");
            btncancel.setText("Xoá");

            quanLy = quanLyList.get(a);
            txtMaNV.setText(quanLy.getMaQL());
            txtTenNV.setText(quanLy.getTenQL());
            txtSdtNV.setText(quanLy.getSdtQL());
            txtCccdNV.setText(quanLy.getCCCD());
            txtMkNV.setText(quanLy.getMatKhauQL());

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validate();
                    quanLy = new QuanLy();

                    quanLy.setMaQL(txtMaNV.getText().toString().trim());
                    quanLy.setTenQL(txtTenNV.getText().toString());
                    quanLy.setSdtQL(txtSdtNV.getText().toString());
                    quanLy.setCCCD(txtCccdNV.getText().toString());
                    quanLy.setMatKhauQL(txtMkNV.getText().toString());
                    Log.e("a",""+quanLy.getMaQL());

                    try {
                        quanLyDao.Updata(quanLy);
                        Toast.makeText(QuanLyNhanVienActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        loadTable();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(QuanLyNhanVienActivity.this, "Lỗi rồi hĩm ơi..", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });

            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (quanLyDao.Delete(txtMaNV.getText().toString())>0){
                            loadTable();
                            Toast.makeText(QuanLyNhanVienActivity.this, "Xoá Thành Công", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(QuanLyNhanVienActivity.this, "Lỗi RỒi hĩm ơi", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });

        }


        dialog.show();
    }

    private void validate(){
        if(txtMaNV.getText().length()==0){
            tilMaNV.setError("Mã không được để trống");
            temp++;
        }else{
            tilMaNV.setError("");
        }
        if(txtMkNV.getText().length()==0){
            tilMkNV.setError("Tên Loại sách không được để trống");
            temp++;
        }else{
            tilMkNV.setError("");
        }
        if(txtSdtNV.getText().length()==0){
            tilSdtNV.setError("Tên Loại sách không được để trống");
            temp++;
        }else{
            tilSdtNV.setError("");
        }
        if(txtCccdNV.getText().length()==0){
            tilCccdNV.setError("Tên Loại sách không được để trống");
            temp++;
        }else{
            tilCccdNV.setError("");
        }
        if(txtTenNV.getText().length()==0){
            tilTenNV.setError("Tên Loại sách không được để trống");
            temp++;
        }else{
            tilTenNV.setError("");
        }
    }
}
package com.example.tadi.QuanLy;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tadi.Adapter.AdapterQuanLyNguoiDung;
import com.example.tadi.DAO.NguoiDungDao;
import com.example.tadi.Model.NguoiDung;
import com.example.tadi.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class QuanLyNguoiDungActivity extends AppCompatActivity {

    EditText txtMaND,txtTenND,txtSdtND,txtEmail,txtTuoi,txtDiaChi,txtMkND;
    ImageView imgND;
    TextInputLayout tilMaND,tilTenND,tilSdtND,tilEmail,tilTuoi,tilDiaChi,tilMkND;

    NguoiDung nguoiDung;
    NguoiDungDao nguoiDungDao;
    List<NguoiDung> nguoiDungList;
    AdapterQuanLyNguoiDung adapterQuanLyNguoiDung;

    ListView listView;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nguoi_dung);

        listView = findViewById(R.id.quanlynguoidung_lv);
        nguoiDung = new NguoiDung();
        loadTable();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a=i;
                openDialog(Gravity.CENTER);
            }
        });
    }

    public void loadTable(){
        nguoiDungDao = new NguoiDungDao();
        nguoiDungList = nguoiDungDao.getAll();
        adapterQuanLyNguoiDung = new AdapterQuanLyNguoiDung(this, R.layout.item_lv_quan_ly_nguoi_dung,nguoiDungList);
        listView.setAdapter(adapterQuanLyNguoiDung);
    }
    private void openDialog(int gravity){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_quan_ly_nguoidung);

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

        txtMaND = dialog.findViewById(R.id.dialog_quanlynguoidung_txtnameuser);
        txtTenND = dialog.findViewById(R.id.dialog_quanlynguoidung_txtname);
        txtSdtND = dialog.findViewById(R.id.dialog_quanlynguoidung_txtsdt);
        txtEmail = dialog.findViewById(R.id.dialog_quanlynguoidung_txtemail);
        txtTuoi = dialog.findViewById(R.id.dialog_quanlynguoidung_txttuoi);
        txtDiaChi = dialog.findViewById(R.id.dialog_quanlynguoidung_txtdiachi);
        txtMkND = dialog.findViewById(R.id.dialog_quanlynguoidung_txtpass);

        tilMaND = dialog.findViewById(R.id.dialog_quanlynguoidung_til_username);
        tilTenND = dialog.findViewById(R.id.dialog_quanlynguoidung_til_name);
        tilSdtND = dialog.findViewById(R.id.dialog_quanlynguoidung_til_sdt);
        tilEmail = dialog.findViewById(R.id.dialog_quanlynguoidung_til_email);
        tilTuoi = dialog.findViewById(R.id.dialog_quanlynguoidung_til_tuoi);
        tilDiaChi = dialog.findViewById(R.id.dialog_quanlynguoidung_til_diachi);
        tilMkND = dialog.findViewById(R.id.dialog_quanlynguoidung_til_pass);

        imgND = dialog.findViewById(R.id.dialog_quanlynguoidung_avatar);

        Button btnadd = dialog.findViewById(R.id.dialog_quanlynguoidung_btn_add);
        Button btncancel = dialog.findViewById(R.id.dialog_quanlynguoidung_btn_cancel);

        nguoiDung = nguoiDungList.get(a);

        Log.e("a",""+nguoiDung.getMaND());

        txtMaND.setText(String.valueOf(nguoiDung.getMaND()));
        txtTenND.setText(nguoiDung.getTenND());
        txtSdtND.setText(nguoiDung.getSdtND());
        txtEmail.setText(nguoiDung.getEmail());
        txtTuoi.setText(String.valueOf(nguoiDung.getTuoiND()));
        txtDiaChi.setText(nguoiDung.getDiaChiND());
        txtMkND.setText(nguoiDung.getMatKhauND());

        byte[] hinhAnh = nguoiDung.getImgND();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imgND.setImageBitmap(bitmap);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    nguoiDungDao.Updata(nguoiDung);
                    Toast.makeText(QuanLyNguoiDungActivity.this, "Updata thanh công", Toast.LENGTH_SHORT).show();
                    loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    nguoiDungDao.Delete(txtMaND.getText().toString());
                    Toast.makeText(QuanLyNguoiDungActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
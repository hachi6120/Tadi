package com.example.tadi.QuanLy;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;

import com.example.tadi.Adapter.AdapterQuanLyLoaiSanPham;
import com.example.tadi.DAO.LoaiSanPhamDao;
import com.example.tadi.Model.LoaiSP;
import com.example.tadi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class QuanLyLoaiActivity extends AppCompatActivity {

    EditText txtMaLoai,txtTenLoai;
    TextInputLayout tilMaLoai,tilTenLoai;
    FloatingActionButton fab;
    LoaiSanPhamDao loaiSanPhamDao;
    List<LoaiSP> loaiSPList;
    AdapterQuanLyLoaiSanPham adapterQuanLyLoaiSanPham;
    ListView listView;
    LoaiSP  loaiSP;
    int a;
    int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_loai);

        fab = findViewById(R.id.quanlyloai_fab);
        listView = findViewById(R.id.quanlyloai_listview);



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
        loaiSanPhamDao = new LoaiSanPhamDao();
        loaiSPList = loaiSanPhamDao.getAll();
        adapterQuanLyLoaiSanPham = new AdapterQuanLyLoaiSanPham(this, R.layout.item_lv_quan_ly_loai,loaiSPList);
        listView.setAdapter(adapterQuanLyLoaiSanPham);
    }

    private void openDialog(int gravity){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_quanlyloai);

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

        TextView tvTile = (TextView) dialog.findViewById(R.id.dialog_quanlyloai_tvtile);

        txtMaLoai = dialog.findViewById(R.id.dialog_quanlyloai_txtmaloai);
        txtTenLoai = dialog.findViewById(R.id.dialog_quanlyloai_txttenloai);

        tilMaLoai = dialog.findViewById(R.id.dialog_quanlyloai_til_maloai);
        tilTenLoai = dialog.findViewById(R.id.dialog_quanlyloai_til_tenloai);

        Button btnadd = dialog.findViewById(R.id.dialog_quanlyloai_btn_add);
        Button btncancel = dialog.findViewById(R.id.dialog_quanlyloai_btn_cancel);


        tilMaLoai.setEnabled(false);

        if (a==-1){
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Chỗ này insert nhân viên. viết vứt vào đây...
                    LoaiSP  sp = new LoaiSP(1,txtTenLoai.getText().toString(),null);
                    try {
                        loaiSanPhamDao.Insert(sp);
                        loadTable();
                        dialog.dismiss();
                    } catch (Exception e) {
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
            tvTile.setText("Sửa/Xoá Loại Sản Phẩm");
            btnadd.setText("Sửa");
            btncancel.setText("Xoá");


            loaiSP = loaiSPList.get(a);
            txtMaLoai.setText(String.valueOf(loaiSP.getMaLoai()));
            txtTenLoai.setText(loaiSP.getTenLoai());

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoaiSP loaiSP = new LoaiSP(Integer.parseInt(txtMaLoai.getText().toString()),txtTenLoai.getText().toString(),null);
                    try {
                        loaiSanPhamDao.Updata(loaiSP);
                        dialog.dismiss();
                        loadTable();
                        Toast.makeText(QuanLyLoaiActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
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
                        loaiSanPhamDao.Delete(txtMaLoai.getText().toString());
                        dialog.dismiss();
                        loadTable();
                        Toast.makeText(QuanLyLoaiActivity.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });

        }


        dialog.show();
    }
}
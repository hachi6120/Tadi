package com.example.tadi.QuanLy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tadi.Adapter.AdapterQuanLyDonHang;
import com.example.tadi.DAO.DonHangDao;
import com.example.tadi.Model.DonHang;
import com.example.tadi.R;

import java.util.List;

public class QuanLyDonHangActivity extends AppCompatActivity {

    DonHangDao donHangDao;
    List<DonHang> donHangList;
    AdapterQuanLyDonHang adapterQuanLyDonHang;

    ListView listView;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_don_hang);

        listView = findViewById(R.id.quanlydonhang_lv);

        loadTable();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openAlertDialog(i);
            }
        });

    }

    public void loadTable(){
        donHangDao = new DonHangDao();
        donHangList = donHangDao.getAll();
        adapterQuanLyDonHang = new AdapterQuanLyDonHang(this, R.layout.item_lv_quan_ly_don_hang,donHangList);
        listView.setAdapter(adapterQuanLyDonHang);
    }

    private void openAlertDialog(int a){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Xác nhận đơn hàng");
        builder.setTitle("Xác nhận đơn hàng");
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Xác Nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DonHang donHang = new DonHang();
                donHang = donHangList.get(a);

                Intent intent = getIntent();
                String user = intent.getStringExtra("user");

                if (donHang.getTrangThai()==0){
                    try {
                        donHang.setMaQL(user);
                        donHang.setTrangThai(1);
                        donHangDao.Updata(donHang);
                        loadTable();
                        Toast.makeText(QuanLyDonHangActivity.this, "Xác nhận thành công", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(QuanLyDonHangActivity.this, "Xác nhận thất bại", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(QuanLyDonHangActivity.this, "Đơn hàng đã được xác nhận", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }
}
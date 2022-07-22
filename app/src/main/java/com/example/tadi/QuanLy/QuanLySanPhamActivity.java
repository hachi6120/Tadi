package com.example.tadi.QuanLy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tadi.Adapter.AdapterQuanLySanPham;
import com.example.tadi.DAO.LoaiSanPhamDao;
import com.example.tadi.DAO.SanPhamDao;
import com.example.tadi.Model.LoaiSP;
import com.example.tadi.Model.SanPham;
import com.example.tadi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class QuanLySanPhamActivity extends AppCompatActivity {

    EditText txtMaSP,txtTenSP,txtMau,txtChatLieu,txtNhaSX,txtGiaNhap,txtGiaBan,txtSizeS,txtSizeM,txtSizeL,txtSoLuong,txtMota;
    TextInputLayout tilMaSP,tilTenSP,tilMau,tilChatLieu,tilNhaSX,tilGiaNhap,tilGiaBan,tilSizeS,tilSizeM,tilSizeL,tilSoLuong,tilMota;
    Spinner spnLoaiSP;
    ImageView imgAnhSP;
    FloatingActionButton fab;

    ListView listView;

    SanPham sanPham;
    SanPhamDao sanPhamDao;
    List<SanPham> sanPhamList;
    AdapterQuanLySanPham adapterQuanLySanPham;

    LoaiSP loaiSP;
    List<LoaiSP> loaiSPList;
    LoaiSanPhamDao loaiSanPhamDao;

    int a;
    int temp=0;
    String maLoai;
    int REQUEST_CODE_FOLDER = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);

        fab = findViewById(R.id.quansanpham_fab);
        listView = findViewById(R.id.quansanpham_listview);

        loaiSP = new LoaiSP();
        loaiSPList = new ArrayList<>();
        loaiSanPhamDao = new LoaiSanPhamDao();

        sanPham = new SanPham();
        loadTable();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=-1;
                openDialog(Gravity.BOTTOM);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a=i;
                openDialog(Gravity.BOTTOM);
            }
        });
    }

    public void loadTable(){
        sanPhamDao = new SanPhamDao();
        sanPhamList = sanPhamDao.getAll();
        adapterQuanLySanPham = new AdapterQuanLySanPham(this, R.layout.item_lv_quan_ly_san_pham,sanPhamList);
        listView.setAdapter(adapterQuanLySanPham);
    }

    private void openDialog(int gravity){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_quanly_sanpham);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        TextView tvTile = (TextView) dialog.findViewById(R.id.quanlysanpham_tvtile);

        txtMaSP = dialog.findViewById(R.id.dialog_quanlysanpham_txtmasp);
        txtTenSP = dialog.findViewById(R.id.dialog_quanlysanpham_txttensp);
        txtMau = dialog.findViewById(R.id.dialog_quanlysanpham_txtmausac);
        txtChatLieu = dialog.findViewById(R.id.dialog_quanlysanpham_txtchatlieu);
        txtNhaSX = dialog.findViewById(R.id.dialog_quanlysanpham_txtnhasx);
        txtGiaNhap = dialog.findViewById(R.id.dialog_quanlysanpham_txtgianhap);
        txtGiaBan = dialog.findViewById(R.id.dialog_quanlysanpham_txtgiaban);
        txtSizeS = dialog.findViewById(R.id.dialog_quanlysanpham_txtsizes);
        txtSizeM = dialog.findViewById(R.id.dialog_quanlysanpham_txtsizem);
        txtSizeL = dialog.findViewById(R.id.dialog_quanlysanpham_txtsizel);
        txtSoLuong = dialog.findViewById(R.id.dialog_quanlysanpham_txtsolung);
        txtMota = dialog.findViewById(R.id.dialog_quanlysanpham_txtmota);

        tilMaSP = dialog.findViewById(R.id.dialog_quanlysanpham_til_masp);
        tilTenSP = dialog.findViewById(R.id.dialog_quanlysanpham_til_tensp);
        tilMau = dialog.findViewById(R.id.dialog_quanlysanpham_til_mausac);
        tilChatLieu = dialog.findViewById(R.id.dialog_quanlysanpham_til_chatlieu);
        tilNhaSX = dialog.findViewById(R.id.dialog_quanlysanpham_til_nhasx);
        tilGiaNhap = dialog.findViewById(R.id.dialog_quanlysanpham_til_gianhap);
        tilGiaBan = dialog.findViewById(R.id.dialog_quanlysanpham_til_giaban);
        tilSizeS = dialog.findViewById(R.id.dialog_quanlysanpham_til_sizes);
        tilSizeM = dialog.findViewById(R.id.dialog_quanlysanpham_til_sizem);
        tilSizeL = dialog.findViewById(R.id.dialog_quanlysanpham_til_sizel);
        tilSoLuong = dialog.findViewById(R.id.dialog_quanlysanpham_til_soluong);
        tilMota = dialog.findViewById(R.id.dialog_quanlysanpham_til_mota);

        Button btnadd = dialog.findViewById(R.id.dialog_quanlysanpham_btn_add);
        Button btncancel = dialog.findViewById(R.id.dialog_quanlysanpham_btn_cancel);

        spnLoaiSP = dialog.findViewById(R.id.dialog_quanlysanpham_spiner_loaisp);
        imgAnhSP = dialog.findViewById(R.id.dialog_quanlysanpham_avatar);

        loaiSPList = loaiSanPhamDao.getAll();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,loaiSPList);
        spnLoaiSP.setAdapter(adapter);

        spnLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoai = String.valueOf(loaiSPList.get(i).getMaLoai());
                Log.e("a",""+maLoai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tilMaSP.setEnabled(false);

        if (a==-1){
            imgAnhSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }
            });

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Chỗ này insert nhân viên. viết vứt vào đây...

                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhSP.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                    byte[] avatar = byteArray.toByteArray();

                    SanPham sanPham = new SanPham(1
                            ,Integer.parseInt(maLoai)
                            ,txtTenSP.getText().toString()
                            ,Integer.parseInt(txtGiaNhap.getText().toString())
                            ,Integer.parseInt(txtGiaBan.getText().toString())
                            ,Integer.parseInt(txtSoLuong.getText().toString())
                            ,txtNhaSX.getText().toString()
                            ,txtChatLieu.getText().toString()
                            ,txtMau.getText().toString()
                            ,txtMota.getText().toString()
                            ,Integer.parseInt(txtSizeS.getText().toString())
                            ,Integer.parseInt(txtSizeM.getText().toString())
                            ,Integer.parseInt(txtSizeL.getText().toString())
                            ,avatar);
                    try {
                        sanPhamDao.Insert(sanPham);
                        loadTable();
                        Toast.makeText(QuanLySanPhamActivity.this, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(QuanLySanPhamActivity.this, "Lỗi Rồi Đại Thánh Ơi :F", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });

            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }else{
            tvTile.setText("Sửa/Xoá Sản Phẩm");
            btnadd.setText("Sửa");
            btncancel.setText("Xoá");

            sanPham = sanPhamList.get(a);
            txtMaSP.setText(String.valueOf(sanPham.getMaSP()));

            for (int i=0;i<spnLoaiSP.getCount();i++){
                if (sanPhamList.get(a).getMaLoai() == loaiSPList.get(i).getMaLoai()){
                    spnLoaiSP.setSelection(i);
                    break;
                }
            }

            txtTenSP.setText(sanPham.getTenSP());
            txtGiaNhap.setText(String.valueOf(sanPham.getGiaNhap()));
            txtGiaBan.setText(String.valueOf(sanPham.getGiaBan()));
            txtSoLuong.setText(String.valueOf(sanPham.getSoLuong()));
            txtNhaSX.setText(sanPham.getNhaSX());
            txtChatLieu.setText(sanPham.getChatLieu());
            txtMau.setText(sanPham.getMauSac());
            txtMota.setText(sanPham.getMoTa());
            txtSizeS.setText(String.valueOf(sanPham.getSoLuongSizeS()));
            txtSizeM.setText(String.valueOf(sanPham.getSoLuongSizeM()));
            txtSizeL.setText(String.valueOf(sanPham.getSoLuongSizeL()));

            byte[] hinhAnh = sanPham.getImgSP();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
            imgAnhSP.setImageBitmap(bitmap);


            imgAnhSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }
            });

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SanPham sanPham = new SanPham();
                    sanPham.setMaSP(Integer.parseInt(txtMaSP.getText().toString()));
                    sanPham.setMaLoai(Integer.parseInt(maLoai));
                    sanPham.setTenSP(txtTenSP.getText().toString());
                    sanPham.setGiaNhap(Integer.parseInt(txtGiaNhap.getText().toString()));
                    sanPham.setGiaBan(Integer.parseInt(txtGiaBan.getText().toString()));
                    sanPham.setSoLuong(Integer.parseInt(txtSoLuong.getText().toString()));
                    sanPham.setNhaSX(txtNhaSX.getText().toString());
                    sanPham.setChatLieu(txtChatLieu.getText().toString());
                    sanPham.setMauSac(txtMau.getText().toString());
                    sanPham.setMoTa(txtMota.getText().toString());
                    sanPham.setSoLuongSizeS(Integer.parseInt(txtSizeS.getText().toString()));
                    sanPham.setSoLuongSizeM(Integer.parseInt(txtSizeM.getText().toString()));
                    sanPham.setSoLuongSizeL(Integer.parseInt(txtSizeL.getText().toString()));

                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhSP.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                    byte[] avatar = byteArray.toByteArray();
                    
                    sanPham.setImgSP(avatar);

                    try {
                        sanPhamDao.Updata(sanPham);
                        loadTable();
                        Toast.makeText(QuanLySanPhamActivity.this, "Updata thanh cong", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(QuanLySanPhamActivity.this, "Loi roi", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });

            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        sanPhamDao.Delete(txtMaSP.getText().toString());
                        Toast.makeText(QuanLySanPhamActivity.this, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                        loadTable();
                    } catch (Exception e) {
                        Toast.makeText(QuanLySanPhamActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });

        }
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode ==REQUEST_CODE_FOLDER && resultCode==RESULT_OK && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhSP.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
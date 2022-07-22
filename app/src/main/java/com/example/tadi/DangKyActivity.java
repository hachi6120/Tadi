package com.example.tadi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tadi.DAO.NguoiDungDao;
import com.example.tadi.Model.NguoiDung;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DangKyActivity extends AppCompatActivity {

    EditText txtHoTen,txtTuoi,txtSDT,txtEmail,txtDiaChi,txtTK,txtMK,txtReMK;
    TextInputLayout tilHoTen,tilTuoi,tilSDT,tilEmail,tilDiaCHi,tilTK,tilMK,tilReMK;
    Button btnDK;
    TextView tvTuoi,tvSDT;
    ImageView imgND;

    AlertDialog dialog;

    NguoiDungDao nguoiDungDao;


    int REQUEST_CODE_FOLDER=123;
    int REQUEST_CODE_CAMERA=456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dang_ky);

        txtHoTen = findViewById(R.id.dangky_txtten);
        txtTuoi = findViewById(R.id.dangky_txttuoi);
        txtSDT = findViewById(R.id.dangky_txtsdt);
        txtEmail = findViewById(R.id.dangky_txtemail);
        txtDiaChi = findViewById(R.id.dangky_txtdiachi);
        txtTK = findViewById(R.id.dangky_txttaikhoan);
        txtMK = findViewById(R.id.dangky_txtpass);
        txtReMK = findViewById(R.id.dangky_txtrepass);

        tilHoTen = findViewById(R.id.dangky_til_ten);
        tilTuoi = findViewById(R.id.dangky_til_tuoi);
        tilSDT = findViewById(R.id.dangky_til_sdt);
        tilEmail = findViewById(R.id.dangky_til_email);
        tilDiaCHi = findViewById(R.id.dangky_til_diachi);
        tilTK = findViewById(R.id.dangky_til_taikhoan);
        tilMK = findViewById(R.id.dangky_til_pass);
        tilReMK = findViewById(R.id.dangky_til_repass);

        tvTuoi = findViewById(R.id.dangky_tvtuoi);
        tvSDT = findViewById(R.id.dangky_tvsdt);

        btnDK = findViewById(R.id.dangky_btn_xacnhan);

        imgND = findViewById(R.id.dangky_imgNd);

        nguoiDungDao = new NguoiDungDao();

        imgND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnDK.getText().toString().equals("Đăng Ký")){

                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imgND.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                    byte[] avatar = byteArray.toByteArray();

                    NguoiDung nguoiDung = new NguoiDung(txtTK.getText().toString()
                            ,txtMK.getText().toString()
                            ,txtHoTen.getText().toString()
                            ,txtSDT.getText().toString()
                            ,txtDiaChi.getText().toString()
                            ,Integer.parseInt(txtTuoi.getText().toString())
                            ,txtEmail.getText().toString()
                            ,avatar);
                    try {
                        nguoiDungDao.Insert(nguoiDung);
                        Toast.makeText(DangKyActivity.this, "Tạo Tài khoản thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangKyActivity.this,ManLoginActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(DangKyActivity.this, "Tạo Tài khoản thất bại", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

                btnDK.setText("Đăng Ký");

                tilTK.setVisibility(View.VISIBLE);
                tilMK.setVisibility(View.VISIBLE);
                tilReMK.setVisibility(View.VISIBLE);

                tilHoTen.setVisibility(View.GONE);
                tilTuoi.setVisibility(View.GONE);
                tilSDT.setVisibility(View.GONE);
                tilEmail.setVisibility(View.GONE);
                tilDiaCHi.setVisibility(View.GONE);
                tvTuoi.setVisibility(View.GONE);
                tvSDT.setVisibility(View.GONE);
                imgND.setEnabled(false);

            }
        });
    }

    private void openAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Chọn Phương Thức Tải Ảnh Lên");
        builder.setTitle("ẢNH ĐẠI DIỆN");
        builder.setPositiveButton("File", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgND.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgND.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
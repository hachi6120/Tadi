package com.example.tadi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tadi.DAO.NguoiDungDao;
import com.example.tadi.DAO.QuanLyDao;
import com.example.tadi.Main.MainGiaoDien;
import com.google.android.material.textfield.TextInputLayout;

public class ManLoginActivity extends AppCompatActivity {
    TextView tvDabgKy;
    EditText edusername,edpassword;
    Button login;
    CheckBox cb;
    String strUser,strPass;
    TextInputLayout tilpass,tiluser;

    NguoiDungDao nguoiDungDao;
    QuanLyDao quanLyDao;
    int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_man_login);

        tilpass = findViewById(R.id.login_tilpassword);
        tiluser = findViewById(R.id.login_tilusername);

        edusername = findViewById(R.id.login_edusername);
        edpassword = findViewById(R.id.login_edpassword);
        cb = findViewById(R.id.login_checkBox);
        login = findViewById(R.id.login_btnlogin);

        tvDabgKy = findViewById(R.id.login_tvDangky);

        tvDabgKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManLoginActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edusername.setText(pref.getString("USERNAME",""));
        edpassword.setText(pref.getString("PASSWORD",""));
        cb.setChecked(pref.getBoolean("REMEMBER",false));

        nguoiDungDao = new NguoiDungDao();
        quanLyDao = new QuanLyDao();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    public void checkLogin(){
        strUser = edusername.getText().toString();
        strPass = edpassword.getText().toString();
        if (strUser.isEmpty()){
            tiluser.setError("Tên đăng nhập không được để trống");
            temp++;
        }else{
            tiluser.setError("");
        }
        if(strPass.isEmpty()){
            tilpass.setError("Tên đăng nhập không được để trống");
            temp++;
        }else{
            tilpass.setError("");
        }
        if(temp==0){
            if (nguoiDungDao.checkLogin(strUser,strPass)>0){
                tiluser.setError("");
                tilpass.setError("");
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,cb.isChecked());

                Intent intent = new Intent(ManLoginActivity.this, MainGiaoDien.class);
                intent.putExtra("checkAdmin",0);
                intent.putExtra("user",strUser);
                intent.putExtra("pass",strPass);
                startActivity(intent);
                finish();
            }else if(quanLyDao.checkLogin(strUser,strPass)>0){
                tiluser.setError("");
                tilpass.setError("");
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,cb.isChecked());

                Intent intent = new Intent(ManLoginActivity.this, MainGiaoDien.class);
                intent.putExtra("checkAdmin",1);
                intent.putExtra("user",strUser);
                intent.putExtra("pass",strPass);
                startActivity(intent);

                finish();
            } else{
                tiluser.setError("Tên đăng nhập hoặc mật khẩu không đúng");
                tilpass.setError("Tên đăng nhập hoặc mật khẩu không đúng");
            }
        }else{
            temp=0;
        }
    }

    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}
package com.example.tadi.Main.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tadi.DAO.NguoiDungDao;
import com.example.tadi.DAO.QuanLyDao;
import com.example.tadi.ManLoginActivity;
import com.example.tadi.Model.NguoiDung;
import com.example.tadi.Model.QuanLy;
import com.example.tadi.QuanLy.QuanLyDonHangActivity;
import com.example.tadi.QuanLy.QuanLyLoaiActivity;
import com.example.tadi.QuanLy.QuanLyNguoiDungActivity;
import com.example.tadi.QuanLy.QuanLyNhanVienActivity;
import com.example.tadi.QuanLy.QuanLySanPhamActivity;
import com.example.tadi.R;

import java.util.List;

public class thongtinfragment extends Fragment {
    String[] itemLv = new String[]{"Quản Lý Nhân Viên","Quản Lý Loại Sản Phẩm","Quản Lý Sản Phẩm","Quản Lý Người Dùng","Quản Lý Đơn Hàng","Đăng Xuất"};
    ListView listView;

    ImageView imageView;
    TextView tvTen;

    List<QuanLy> quanLyList;
    QuanLyDao quanLyDao;

    List<NguoiDung> nguoiDungList;
    NguoiDungDao nguoiDungDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtinfragment, container, false);

        listView = view.findViewById(R.id.thongtin_lv);
        imageView = view.findViewById(R.id.fragment_thongtin_img);
        tvTen = view.findViewById(R.id.fragment_thongtin_nameuser);

        quanLyDao = new QuanLyDao();
        quanLyList = quanLyDao.getAll();

        nguoiDungDao = new NguoiDungDao();
        nguoiDungList = nguoiDungDao.getAll();

        Intent i1 = getActivity().getIntent();
        int checkAdmin = i1.getIntExtra("checkAdmin",-999);
        String u = i1.getStringExtra("user");

        if (checkAdmin==1){
            for (int i = 0; i < quanLyList.size(); i++) {
                if (quanLyList.get(i).getMaQL().trim().equals(u.trim())){
                    Log.e("check u",""+u);
                    Log.e("check quanly",""+quanLyList.get(i).getMaQL());
                    tvTen.setText(quanLyList.get(i).getTenQL());
                    byte[] hinhAnh = quanLyList.get(i).getAvatarNhanVien();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
                    imageView.setImageBitmap(bitmap);
                    break;
                }
            }
        }else if (checkAdmin==0){
            for (int i = 0; i < nguoiDungList.size(); i++) {
                if (nguoiDungList.get(i).getMaND().trim().equals(u.trim())){
                    tvTen.setText(nguoiDungList.get(i).getTenND());
                    byte[] hinhAnh = nguoiDungList.get(i).getImgND();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
                    imageView.setImageBitmap(bitmap);
                    break;
                }
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,itemLv);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent intent = new Intent(getActivity(), QuanLyNhanVienActivity.class);
                    startActivity(intent);
                }
                if (i == 1){
                    Intent intent = new Intent(getActivity(), QuanLyLoaiActivity.class);
                    startActivity(intent);
                }
                if (i == 2){
                    Intent intent = new Intent(getActivity(), QuanLySanPhamActivity.class);
                    startActivity(intent);
                }
                if (i == 3){
                    Intent intent = new Intent(getActivity(), QuanLyNguoiDungActivity.class);
                    startActivity(intent);
                }
                if (i == 4){
                    Intent intent = new Intent(getActivity(), QuanLyDonHangActivity.class);
                    intent.putExtra("user",u);
                    startActivity(intent);
                }
                if (i == 5){
                    Intent intent = new Intent(getActivity(), ManLoginActivity.class);
                    startActivity(intent);
                }
            }
        });


        return view;
    }
}
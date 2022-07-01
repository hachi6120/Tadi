package com.example.tadi.Main.ui;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tadi.Adapter.AdapterLoaiSanPham;
import com.example.tadi.Model.LoaiSP;
import com.example.tadi.Model.SanPham;
import com.example.tadi.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class homefragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterLoaiSanPham adapterLoaiSanPham;
    ImageView imageView;
    CountDownTimer countDownTimer;
    int a = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);

        imageView = view.findViewById(R.id.img_background);


//        recyclerView = view.findViewById(R.id.rcv_home_loai_sp);
//        adapterLoaiSanPham = new AdapterLoaiSanPham(getContext());
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        adapterLoaiSanPham.setData(getListLoaiSanPham());
//        recyclerView.setAdapter(adapterLoaiSanPham);

        return view;
    }

    private List<LoaiSP> getListLoaiSanPham(){
        List<LoaiSP> listLoai = new ArrayList<>();
        List<SanPham> listSP = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.nenthoat);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);


        listSP.add(new SanPham(1,1,1,"SP1",200,200,10,"VN","Bong","Trang","Dinh",byteArray.toByteArray()));
        listSP.add(new SanPham(2,1,1,"SP1",200,200,10,"VN","Bong","Trang","Dinh",byteArray.toByteArray()));
        listSP.add(new SanPham(3,1,1,"SP1",200,200,10,"VN","Bong","Trang","Dinh",byteArray.toByteArray()));
        listSP.add(new SanPham(4,1,1,"SP1",200,200,10,"VN","Bong","Trang","Dinh",byteArray.toByteArray()));
        listSP.add(new SanPham(5,1,1,"SP1",200,200,10,"VN","Bong","Trang","Dinh",byteArray.toByteArray()));
        listSP.add(new SanPham(6,1,1,"SP1",200,200,10,"VN","Bong","Trang","Dinh",byteArray.toByteArray()));


        listLoai.add(new LoaiSP(1,"Loai1",listSP));
        listLoai.add(new LoaiSP(2,"Loai2",listSP));
        listLoai.add(new LoaiSP(3,"Loai3",listSP));
        listLoai.add(new LoaiSP(4,"Loai4",listSP));
        return listLoai;
    }
}
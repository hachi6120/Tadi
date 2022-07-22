package com.example.tadi.Main.ui;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tadi.Adapter.AdapterImageSlide;
import com.example.tadi.Adapter.AdapterLoaiSanPham;
import com.example.tadi.DAO.LoaiSanPhamDao;
import com.example.tadi.DAO.SanPhamDao;
import com.example.tadi.Model.LoaiSP;
import com.example.tadi.Model.Photo;
import com.example.tadi.Model.SanPham;
import com.example.tadi.R;

import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator3;

public class homefragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterLoaiSanPham adapterLoaiSanPham;
    int a = 0;

    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;

    private List<Photo> mPhotoList;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mViewPager2.getCurrentItem();
            if (currentPosition == mPhotoList.size()-1){
                mViewPager2.setCurrentItem(0);
            }else {
                mViewPager2.setCurrentItem(currentPosition + 1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);

        mViewPager2 = view.findViewById(R.id.home_view_page2);
        mCircleIndicator3 = view.findViewById(R.id.home_circle_indicator);

        mPhotoList = getListPhoto();

        AdapterImageSlide adapterImageSlide = new AdapterImageSlide(getActivity(),mPhotoList);
        mViewPager2.setAdapter(adapterImageSlide);
        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });

        recyclerView = view.findViewById(R.id.rcv_home_loai_sp);
        adapterLoaiSanPham = new AdapterLoaiSanPham(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterLoaiSanPham.setData(getListLoaiSanPham());
        recyclerView.setAdapter(adapterLoaiSanPham);


        return view;
    }

    private List<LoaiSP> getListLoaiSanPham(){
        LoaiSP loaiSP = new LoaiSP();
        SanPham sanPham = new SanPham();
        List<LoaiSP> listLoai = new ArrayList<>();
        List<SanPham> listSP = new ArrayList<>();

        SanPhamDao sanPhamDao = new SanPhamDao();
        LoaiSanPhamDao loaiSanPhamDao = new LoaiSanPhamDao();

        listSP = sanPhamDao.getAll();
        listLoai = loaiSanPhamDao.getAll();

        List<LoaiSP> newListLoai = new ArrayList<>();
        List<SanPham> newListSP = new ArrayList<>();

        int a = 0;
        for (int i = 0; i < listLoai.size(); i++) {
            if (a!=i){
                a=i;
                newListSP = new ArrayList<>();
            }
            for (int j = 0; j < listSP.size(); j++) {
                if (listLoai.get(i).getMaLoai() == listSP.get(j).getMaLoai()){
                    newListSP.add(new SanPham(listSP.get(j).getMaSP()
                            ,listSP.get(j).getMaLoai()
                            ,listSP.get(j).getTenSP()
                            ,listSP.get(j).getGiaNhap()
                            ,listSP.get(j).getGiaBan()
                            ,listSP.get(j).getSoLuong()
                            ,listSP.get(j).getNhaSX()
                            ,listSP.get(j).getChatLieu()
                            ,listSP.get(j).getMauSac()
                            ,listSP.get(j).getMoTa()
                            ,listSP.get(j).getSoLuongSizeS()
                            ,listSP.get(j).getSoLuongSizeM()
                            ,listSP.get(j).getSoLuongSizeL()
                            ,listSP.get(j).getImgSP()
                    ));
                }
            }
            if (newListSP.size()!=0){
                newListLoai.add(new LoaiSP(listLoai.get(i).getMaLoai(),listLoai.get(i).getTenLoai(),newListSP));
            }
        }

        return newListLoai;
    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.banner));
        list.add(new Photo(R.drawable.bannera));
        list.add(new Photo(R.drawable.bannerb));
        list.add(new Photo(R.drawable.bannerc));
        list.add(new Photo(R.drawable.bannerd));
        return list;
    }
}
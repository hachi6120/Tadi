package com.example.tadi.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.tadi.Model.SanPham;
import com.example.tadi.R;

import java.util.List;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.SanPhamViewHolder> {

    private List<SanPham> sanPhamList;


    public void setData(List<SanPham> list){
        this.sanPhamList = list;
        notifyDataSetChanged();
    }

    @Override
    public SanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_sp,parent,false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SanPhamViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        if (sanPham==null){
            return;
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(sanPham.getImgSP(),0,sanPham.getImgSP().length);
        holder.imgSP.setImageBitmap(bitmap);
        holder.tvTenSP.setText(sanPham.getTenSP());
    }

    @Override
    public int getItemCount() {
        if (sanPhamList != null){
            return sanPhamList.size();
        }
        return 0;
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgSP;
        private TextView tvTenSP;

        public SanPhamViewHolder(View itemView) {
            super(itemView);

            imgSP = itemView.findViewById(R.id.img_home_sp);
            tvTenSP = itemView.findViewById(R.id.tv_home_tenSP);
        }
    }
}

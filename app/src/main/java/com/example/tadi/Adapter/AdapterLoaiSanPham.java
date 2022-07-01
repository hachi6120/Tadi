package com.example.tadi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tadi.Model.LoaiSP;
import com.example.tadi.Model.SanPham;
import com.example.tadi.R;

import java.util.List;

public class AdapterLoaiSanPham extends RecyclerView.Adapter<AdapterLoaiSanPham.LoaiSanPhamViewHolder> {

    private List<LoaiSP> loaiSPList;
    private Context context;

    public AdapterLoaiSanPham(Context context) {
        this.context = context;
    }

    public void setData(List<LoaiSP> list){
        this.loaiSPList = list;
        notifyDataSetChanged();
    }

    @Override
    public LoaiSanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_loai_sp,parent,false);
        return new LoaiSanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoaiSanPhamViewHolder holder, int position) {
        LoaiSP loaiSP = loaiSPList.get(position);
        if (loaiSP==null){
            return;
        }
        holder.tvNameLoai.setText(loaiSP.getTenLoai());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rcvSanPham.setLayoutManager(linearLayoutManager);

        AdapterSanPham adapterSanPham  = new AdapterSanPham();
        adapterSanPham.setData(loaiSP.getSanPhams());
        holder.rcvSanPham.setAdapter(adapterSanPham);
    }

    @Override
    public int getItemCount() {
        if (loaiSPList != null){
            return loaiSPList.size();
        }
        return 0;
    }

    public class LoaiSanPhamViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameLoai;
        private RecyclerView rcvSanPham;

        public LoaiSanPhamViewHolder(View itemView) {
            super(itemView);

            tvNameLoai = itemView.findViewById(R.id.tv_item_home_loai_ten);
            rcvSanPham = itemView.findViewById(R.id.rv_item_home_loai);
        }
    }
}

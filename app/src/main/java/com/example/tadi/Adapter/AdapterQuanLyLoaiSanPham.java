package com.example.tadi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tadi.Model.LoaiSP;
import com.example.tadi.R;

import java.util.List;

public class AdapterQuanLyLoaiSanPham extends ArrayAdapter<LoaiSP> {

    private Context context;
    private int resource;
    private List<LoaiSP> objects;
    private LayoutInflater inflater;

    public AdapterQuanLyLoaiSanPham(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_lv_quan_ly_loai,null);
            holder.tvMaLoai = (TextView)convertView.findViewById(R.id.item_lv_quanlyloai_maloai);
            holder.tvTenLoai = (TextView)convertView.findViewById(R.id.item_lv_quanlyloai_tenloai);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        LoaiSP obj = objects.get(position);
        holder.tvMaLoai.setText("Mã: "+obj.getMaLoai());
        holder.tvTenLoai.setText("Tên: "+obj.getTenLoai());

        return convertView;
    }

    public class ViewHolder{
        TextView tvMaLoai,tvTenLoai;
    }
}

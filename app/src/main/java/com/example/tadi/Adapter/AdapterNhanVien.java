package com.example.tadi.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tadi.Model.QuanLy;
import com.example.tadi.R;

import java.util.List;

public class AdapterNhanVien extends ArrayAdapter<QuanLy> {

    private Context context;
    private int resource;
    private List<QuanLy> objects;
    private LayoutInflater inflater;
    String a="*";

    public AdapterNhanVien(Context context, int resource, List objects) {
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
            convertView = inflater.inflate(R.layout.item_lv_nhanviem,null);
            holder.tvusername = (TextView)convertView.findViewById(R.id.item_lv_nhanvien_manv);
            holder.tvname = (TextView)convertView.findViewById(R.id.item_lv_nhanvien_tennv);
            holder.tvpass = (TextView)convertView.findViewById(R.id.item_lv_nhanvien_pass);
            holder.tvsdt = (TextView)convertView.findViewById(R.id.item_lv_nhanvien_sdt);
            holder.tvcccd = (TextView)convertView.findViewById(R.id.item_lv_nhanvien_cccd);
            holder.avatarNV = (ImageView) convertView.findViewById(R.id.item_lv_nhanvien_avatar);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        QuanLy obj = objects.get(position);
        holder.tvusername.setText("Mã: "+obj.getMaQL());
        holder.tvname.setText("Tên: "+obj.getTenQL());
        String temp = obj.getMatKhauQL();
        for (int i = 0; i < temp.length(); i++) {
            a=a.concat("*");
        }
        holder.tvpass.setText("Mật Khẩu: "+a);
        holder.tvsdt.setText("Số Điện Thoại: "+obj.getSdtQL());
        holder.tvcccd.setText("Căn Cước Công Dân: "+obj.getCCCD());

        byte[] hinhAnh = obj.getAvatarNhanVien();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.avatarNV.setImageBitmap(bitmap);

        a="";
        return convertView;
    }

    public class ViewHolder{
        TextView tvusername,tvname,tvpass,tvsdt,tvcccd;
        ImageView avatarNV;
    }
}

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

import com.example.tadi.Model.NguoiDung;
import com.example.tadi.R;

import java.util.List;

public class AdapterQuanLyNguoiDung extends ArrayAdapter<NguoiDung> {

    private Context context;
    private int resource;
    private List<NguoiDung> objects;
    private LayoutInflater inflater;
    String a="*";

    public AdapterQuanLyNguoiDung(Context context, int resource, List objects) {
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
            convertView = inflater.inflate(R.layout.item_lv_quan_ly_nguoi_dung,null);
            holder.tvusername = (TextView)convertView.findViewById(R.id.item_lv_quanlynguoidung_maND);
            holder.tvname = (TextView)convertView.findViewById(R.id.item_lv_quanlynguoidung_ten);
            holder.tvpass = (TextView)convertView.findViewById(R.id.item_lv_quanlynguoidung_mk);
            holder.tvsdt = (TextView)convertView.findViewById(R.id.item_lv_quanlynguoidung_sdt);
            holder.tvemail = (TextView)convertView.findViewById(R.id.item_lv_quanlynguoidung_email);
            holder.tvdiachi = (TextView)convertView.findViewById(R.id.item_lv_quanlynguoidung_diachi);
            holder.tvtuoi = (TextView)convertView.findViewById(R.id.item_lv_quanlynguoidung_tuoi);
            holder.imgND = (ImageView) convertView.findViewById(R.id.item_lv_quanlynguoidung_imgND);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        NguoiDung obj = objects.get(position);
        holder.tvusername.setText("M??: "+obj.getMaND());
        holder.tvname.setText("T??n: "+obj.getTenND());
        String temp = obj.getMatKhauND();
        for (int i = 0; i < temp.length(); i++) {
            a=a.concat("*");
        }
        holder.tvpass.setText("M???t Kh???u: "+a);
        holder.tvsdt.setText("S??? ??i???n Tho???i: "+obj.getSdtND());
        holder.tvemail.setText("Email: "+obj.getEmail());
        holder.tvdiachi.setText("?????a Ch???: "+obj.getDiaChiND());
        holder.tvtuoi.setText("Tu???i: "+obj.getTuoiND());

        byte[] hinhAnh = obj.getImgND();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgND.setImageBitmap(bitmap);

        a="";
        return convertView;
    }

    public class ViewHolder{
        TextView tvusername,tvname,tvpass,tvsdt,tvemail,tvdiachi,tvtuoi;
        ImageView imgND;
    }
}

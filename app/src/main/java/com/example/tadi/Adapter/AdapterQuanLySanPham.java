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

import com.example.tadi.DAO.LoaiSanPhamDao;
import com.example.tadi.Model.LoaiSP;
import com.example.tadi.Model.SanPham;
import com.example.tadi.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterQuanLySanPham extends ArrayAdapter<SanPham> {

    private Context context;
    private int resource;
    private List<SanPham> objects;
    private LayoutInflater inflater;
    List<LoaiSP> loaiSPList = new ArrayList<>();
    LoaiSanPhamDao loaiSanPhamDao;

    public AdapterQuanLySanPham(Context context, int resource, List objects) {
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
            convertView = inflater.inflate(R.layout.item_lv_quan_ly_san_pham,null);
            holder.tvMaSP = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_ma);
            holder.tvMaLoai = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_tenloai);
            holder.tvSLSizeS = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_sizes);
            holder.tvSLSizeM = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_sizem);
            holder.tvSLSizeL = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_sizel);
            holder.tvTenSP = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_ten);
            holder.tvGiaNhap = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_gianhap);
            holder.tvGiaBan = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_giaban);
            holder.tvSoLuong = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_soluong);
            holder.tvNhaSX = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_nhasx);
            holder.tvChatLieu = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_chatlieu);
            holder.tvMauSac = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_mausac);
            holder.tvMoTa = (TextView)convertView.findViewById(R.id.item_lv_quanlysanpham_mota);
            holder.imgSP = (ImageView) convertView.findViewById(R.id.item_lv_quanlysanpham_imgsp);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        loaiSanPhamDao = new LoaiSanPhamDao();
        SanPham obj = objects.get(position);
        holder.tvMaSP.setText("Mã: "+obj.getMaSP());

        loaiSPList = loaiSanPhamDao.getAll();
        for (int i = 0; i < loaiSPList.size(); i++) {
            if (loaiSPList.get(i).getMaLoai()==obj.getMaLoai()){
                holder.tvMaLoai.setText("Tên Loại: "+loaiSPList.get(i).getTenLoai());
                break;
            }
        }

        holder.tvSLSizeS.setText("S: "+obj.getSoLuongSizeS());
        holder.tvSLSizeM.setText("M: "+obj.getSoLuongSizeM());
        holder.tvSLSizeL.setText("L: "+obj.getSoLuongSizeL());
        holder.tvTenSP.setText("Tên SP: "+obj.getTenSP());
        holder.tvGiaNhap.setText("Giá Nhập: "+obj.getGiaNhap());
        holder.tvGiaBan.setText("Giá Bán: "+obj.getGiaBan());
        holder.tvSoLuong.setText("Tổng: "+obj.getSoLuong());
        holder.tvNhaSX.setText("Nhà SX: "+obj.getNhaSX());
        holder.tvChatLieu.setText("Chất Liệu: "+obj.getChatLieu());
        holder.tvMauSac.setText("Màu Sắc: "+obj.getMauSac());
        holder.tvMoTa.setText("Mô Tả: "+obj.getMoTa());

        byte[] hinhAnh = obj.getImgSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgSP.setImageBitmap(bitmap);

        return convertView;
    }

    public class ViewHolder{
        TextView tvMaSP,tvMaLoai,tvSLSizeS,tvSLSizeM,tvSLSizeL,tvTenSP,tvGiaNhap,tvGiaBan,tvSoLuong,tvNhaSX,tvChatLieu,tvMauSac,tvMoTa;
        ImageView imgSP;
    }
}

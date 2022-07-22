package com.example.tadi.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tadi.DAO.NguoiDungDao;
import com.example.tadi.DAO.SanPhamDao;
import com.example.tadi.Model.DonHang;
import com.example.tadi.Model.NguoiDung;
import com.example.tadi.Model.SanPham;
import com.example.tadi.R;

import java.util.List;

public class AdapterQuanLyDonHang extends ArrayAdapter<DonHang> {

    private Context context;
    private int resource;
    private List<DonHang> objects;
    private LayoutInflater inflater;

    SanPhamDao sanPhamDao = new SanPhamDao();
    List<SanPham> sanPhamList = sanPhamDao.getAll();

    NguoiDungDao nguoiDungDao = new NguoiDungDao();
    List<NguoiDung> nguoiDungList = nguoiDungDao.getAll();

    public AdapterQuanLyDonHang(Context context, int resource, List objects) {
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
            convertView = inflater.inflate(R.layout.item_lv_quan_ly_don_hang,null);
            holder.tvMaDH = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_madh);
            holder.tvMaSP = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_masp);
            holder.tvTenSP = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_tensp);
            holder.tvSize = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_sizedat);
            holder.tvGia = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_gia);
            holder.tvSoLuong = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_soluong);
            holder.tvThoiGian = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_thoigian);
            holder.tvTenND = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_tennd);
            holder.tvSDT = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_sdt);
            holder.tvEmail = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_email);
            holder.tvDiaChi = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_diachi);
            holder.tvTrangThai = (TextView)convertView.findViewById(R.id.item_lv_quanlydonhang_trangthai);
            holder.imgSP = (ImageView) convertView.findViewById(R.id.item_lv_quanlydonhang_imgsp);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        DonHang obj = objects.get(position);

        holder.tvMaDH.setText("Mã Đơn: "+obj.getMaDon());
        holder.tvMaSP.setText("Mã SP: "+obj.getMaSP());

        byte[] hinhAnh = new byte[0];
        
        for (int i = 0; i < sanPhamList.size(); i++) {
            if (sanPhamList.get(i).getMaSP() == obj.getMaSP()){
                holder.tvTenSP.setText("Tên SP: "+sanPhamList.get(i).getTenSP());
                holder.tvGia.setText("Giá: "+sanPhamList.get(i).getGiaBan());
                hinhAnh = sanPhamList.get(i).getImgSP();
                break;
            }
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgSP.setImageBitmap(bitmap);
        
        holder.tvSize.setText("Size: "+obj.getSizeDathang());
        holder.tvSoLuong.setText("Số Lượng: "+obj.getSoLuongMua());
        holder.tvThoiGian.setText("Thời Gian: "+obj.getThoiGian());

        for (int i = 0; i <nguoiDungList.size(); i++) {
            if (nguoiDungList.get(i).getMaND().equals(obj.getMaND())){
                holder.tvTenND.setText("Tên Người Mua: "+nguoiDungList.get(i).getTenND());
                holder.tvSDT.setText("SDT: "+nguoiDungList.get(i).getSdtND());
                holder.tvEmail.setText("Email: "+nguoiDungList.get(i).getEmail());
                holder.tvDiaChi.setText("Địa Chỉ: "+nguoiDungList.get(i).getDiaChiND());
                break;
            }
        }

        if (obj.getTrangThai()==0){
            holder.tvTrangThai.setText("Chưa xác nhận");
            holder.tvTrangThai.setTextColor(Color.RED);
        }else if(obj.getTrangThai()==1){
            holder.tvTrangThai.setText("Đã xác nhận");
            holder.tvTrangThai.setTextColor(Color.BLUE);
        }

        return convertView;
    }

    public class ViewHolder{
        TextView tvMaDH,tvMaSP,tvTenSP,tvGia,tvSize,tvSoLuong,tvThoiGian,tvTenND,tvSDT,tvEmail,tvDiaChi,tvTrangThai;
        ImageView imgSP;
    }
}

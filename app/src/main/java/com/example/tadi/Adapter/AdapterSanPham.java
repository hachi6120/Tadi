package com.example.tadi.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tadi.DAO.DonHangDao;
import com.example.tadi.DAO.LoaiSanPhamDao;
import com.example.tadi.Model.DonHang;
import com.example.tadi.Model.LoaiSP;
import com.example.tadi.Model.SanPham;
import com.example.tadi.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.SanPhamViewHolder> {

    private List<SanPham> sanPhamList;

    private List<LoaiSP> loaiSPList;
    private LoaiSanPhamDao loaiSanPhamDao;
    Activity context;

    String size = "";
    int soLuongMua = 1;

    public void setData(Activity context, List<SanPham> list){
        this.sanPhamList = list;
        this.context = context;
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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(Gravity.BOTTOM,sanPham,bitmap);
            }
        });
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
        private CardView cardView;

        public SanPhamViewHolder(View itemView) {
            super(itemView);

            imgSP = itemView.findViewById(R.id.img_home_sp);
            tvTenSP = itemView.findViewById(R.id.tv_home_tenSP);
            cardView = itemView.findViewById(R.id.item_home_sp_carview);
        }
    }

    private void openDialog(int gravity, SanPham sanPham,Bitmap bitmap){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_home_sanpham);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        TextView txtTenSP = dialog.findViewById(R.id.dialog_home_sp_tensp);
        TextView txtMau = dialog.findViewById(R.id.dialog_home_sp_mau);
        TextView txtChatLieu = dialog.findViewById(R.id.dialog_home_sp_chatlieu);
        TextView txtNhaSX = dialog.findViewById(R.id.dialog_home_sp_nhasx);
        TextView txtGiaNhap = dialog.findViewById(R.id.dialog_home_sp_gianhap);
        TextView txtGiaBan = dialog.findViewById(R.id.dialog_home_sp_giaban);
        TextView txtSizeS = dialog.findViewById(R.id.dialog_home_sp_s);
        TextView txtSizeM = dialog.findViewById(R.id.dialog_home_sp_m);
        TextView txtSizeL = dialog.findViewById(R.id.dialog_home_sp_l);
        TextView txtMota = dialog.findViewById(R.id.dialog_home_sp_mota);
        TextView txtLoai = dialog.findViewById(R.id.dialog_home_sp_loai);

        TextView tvCongSl = dialog.findViewById(R.id.dialog_home_sp_congsl);
        TextView tvTruSl = dialog.findViewById(R.id.dialog_home_sp_trusl);

        EditText edtSoluongMua = dialog.findViewById(R.id.dialog_home_sp_soluongmua);

        ImageButton btnadd = dialog.findViewById(R.id.dialog_home_sp_xn);
        Button btns = dialog.findViewById(R.id.dialog_home_sp_btns);
        Button btnm = dialog.findViewById(R.id.dialog_home_sp_btnm);
        Button btnl = dialog.findViewById(R.id.dialog_home_sp_btnl);

        ImageView imgAnhSP = dialog.findViewById(R.id.dialog_home_sp_avatar);

        LinearLayout lnSize = dialog.findViewById(R.id.dialog_home_sp_LNsize);
        LinearLayout lnsoluong = dialog.findViewById(R.id.dialog_home_sp_LNsoluong);

        //inten nguoi dung

        //add du lieu vao dialog
        txtTenSP.setText(sanPham.getTenSP());
        txtMau.setText(sanPham.getMauSac());
        txtChatLieu.setText(sanPham.getChatLieu());
        txtNhaSX.setText(sanPham.getNhaSX());
        txtGiaNhap.setText(""+sanPham.getGiaNhap());
        txtGiaBan.setText(""+sanPham.getGiaBan());
        txtMota.setText(sanPham.getMoTa());

        //sử lý loại
        loaiSanPhamDao = new LoaiSanPhamDao();
        loaiSPList = loaiSanPhamDao.getAll();
        for (int i = 0; i < loaiSPList.size(); i++) {
            if (loaiSPList.get(i).getMaLoai() == sanPham.getMaLoai()){
                txtLoai.setText(loaiSPList.get(i).getTenLoai());
                break;
            }
        }

        //set hình
        imgAnhSP.setImageBitmap(bitmap);

        //sử lý size
        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = "S";

                soLuongMua = 1;
                edtSoluongMua.setText(""+soLuongMua);

                txtSizeS.setText(""+sanPham.getSoLuongSizeS());
                txtSizeS.setBackgroundResource(R.drawable.custom_input);
                txtSizeM.setText("");
                txtSizeM.setBackground(null);
                txtSizeL.setText("");
                txtSizeL.setBackground(null);
                lnSize.setVisibility(View.VISIBLE);
                lnsoluong.setVisibility(View.VISIBLE);
            }
        });
        btnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = "M";

                soLuongMua = 1;
                edtSoluongMua.setText(""+soLuongMua);

                txtSizeS.setText("");
                txtSizeS.setBackground(null);
                txtSizeM.setText(""+sanPham.getSoLuongSizeM());
                txtSizeM.setBackgroundResource(R.drawable.custom_input);
                txtSizeL.setText("");
                txtSizeL.setBackground(null);
                lnSize.setVisibility(View.VISIBLE);
                lnsoluong.setVisibility(View.VISIBLE);
            }
        });
        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = "L";

                soLuongMua = 1;
                edtSoluongMua.setText(""+soLuongMua);

                txtSizeS.setText("");
                txtSizeS.setBackground(null);
                txtSizeM.setText("");
                txtSizeM.setBackground(null);
                txtSizeL.setText(""+sanPham.getSoLuongSizeL());
                txtSizeL.setBackgroundResource(R.drawable.custom_input);
                lnSize.setVisibility(View.VISIBLE);
                lnsoluong.setVisibility(View.VISIBLE);
            }
        });

        //set so luong mua
        edtSoluongMua.setText(""+soLuongMua);
        tvCongSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size.equals("S")){
                    if (soLuongMua<sanPham.getSoLuongSizeS()){
                        soLuongMua++;
                        edtSoluongMua.setText(""+soLuongMua);
                    }
                }else if (size.equals("M")){
                    if (soLuongMua<sanPham.getSoLuongSizeM()){
                        soLuongMua++;
                        edtSoluongMua.setText(""+soLuongMua);
                    }
                }else {
                    if (soLuongMua<sanPham.getSoLuongSizeL()){
                        soLuongMua++;
                        edtSoluongMua.setText(""+soLuongMua);
                    }
                }
            }
        });

        tvTruSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soLuongMua>1){
                    soLuongMua--;
                    edtSoluongMua.setText(""+soLuongMua);
                }
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = context.getIntent();
                String u = intent.getStringExtra("user");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());

                DonHangDao donHangDao = new DonHangDao();
                DonHang donHang = new DonHang(1,sanPham.getMaSP(),u,null,soLuongMua,size,date,0);

                try {
                    donHangDao.Insert(donHang);
                    Toast.makeText(context, "Đặt hàng thành công. Quý khách vụi lòng đợi xác nhận đơn hàng từ Shop", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

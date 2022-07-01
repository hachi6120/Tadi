package com.example.tadi.Main.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tadi.R;
import com.example.tadi.QuanLyNhanVienActivity;

public class thongtinfragment extends Fragment {
    String[] itemLv = new String[]{"Quản Lý Nhân Viên","Đăng Xuất"};
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtinfragment, container, false);

        listView = view.findViewById(R.id.thongtin_lv);

        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,itemLv);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent intent = new Intent(getActivity(), QuanLyNhanVienActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
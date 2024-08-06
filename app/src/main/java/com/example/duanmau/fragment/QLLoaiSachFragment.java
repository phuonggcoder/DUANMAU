package com.example.duanmau.fragment;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Adapter.LoaiSachAdapter;
import com.example.duanmau.DAO.LoaiSachDAO;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLLoaiSachFragment extends Fragment {
    RecyclerView recyclerView;
    LoaiSachDAO loaiSachDAO;
    ArrayList<LoaiSach> ds;
    RelativeLayout relativeLayout;
    LoaiSachAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisach, container, false);
        relativeLayout = view.findViewById(R.id.relativeLoaiSach);
        recyclerView = view.findViewById(R.id.rv);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.float_add);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThemLoaiSach();
            }
        });

        loaiSachDAO = new LoaiSachDAO(getContext());

        loadData(view);
        setHasOptionsMenu(true); // Enable options menu in the fragment
        return view;
    }

    private void loadData(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ds = loaiSachDAO.getDSLoaiSach();
        adapter = new LoaiSachAdapter(getContext(), ds, loaiSachDAO, new ArrayList<>(ds));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        SearchManager manager = ((SearchManager)getContext().getSystemService(getContext().SEARCH_SERVICE));
        searchView.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void showDialogThemLoaiSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_loaisach, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        EditText etTenTL = view.findViewById(R.id.etTenTL);
        Button btnAdd = view.findViewById(R.id.btnAdd);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai = etTenTL.getText().toString();
                if (tenLoai.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập tên loại sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = new LoaiSachDAO(getContext()).themLoaiSach(tenLoai);
                if (check) {
                    loadData(view);
                    Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}

package com.example.duanmau.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau.DAO.PhieuMuonDAO;
import com.example.duanmau.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DoanhThuFragment extends Fragment {

    EditText edtungay, eddenngay;
    Button btdoanhthu;
    TextView tvketqua;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    PhieuMuonDAO phieuMuonDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doanhthu, container, false);
        edtungay = view.findViewById(R.id.ettungay);
        eddenngay = view.findViewById(R.id.etden);
        btdoanhthu = view.findViewById(R.id.btdoanhthu);
        tvketqua = view.findViewById(R.id.tvketqua);
        phieuMuonDAO = new PhieuMuonDAO(getContext());

        edtungay.setOnClickListener(v -> showDatePicker(edtungay));
        eddenngay.setOnClickListener(v -> showDatePicker(eddenngay));

        btdoanhthu.setOnClickListener(v -> {
            String ngaybatdau = edtungay.getText().toString();
            String ngayketthuc = eddenngay.getText().toString();

            if (ngaybatdau.isEmpty() || ngayketthuc.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng chọn ngày bắt đầu và kết thúc", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Date startDate = sdf.parse(ngaybatdau);
                Date endDate = sdf.parse(ngayketthuc);

                if (startDate != null && endDate != null && startDate.after(endDate)) {
                    Toast.makeText(getContext(), "Ngày bắt đầu không được sau ngày kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }

                int doanhthu = phieuMuonDAO.getdoanhthu(ngaybatdau, ngayketthuc);
                tvketqua.setText("Doanh thu trong khoảng từ \n" + ngaybatdau + " đến \n" + ngayketthuc + " là: \n\n" + doanhthu + " VNĐ");

            } catch (ParseException e) {
                Toast.makeText(getContext(), "Lỗi định dạng ngày", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showDatePicker(EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1; // Changed date format
                    editText.setText(date);
                },
                year, month, day);
        datePickerDialog.show();
    }
}
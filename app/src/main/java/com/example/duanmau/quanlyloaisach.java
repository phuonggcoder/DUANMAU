//package com.example.duanmau;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.duanmau.model.LoaiSach.ATFragment;
//import com.example.duanmau.model.LoaiSach.CNTTFragment;
//import com.example.duanmau.model.LoaiSach.DLFragment;
//import com.example.duanmau.model.LoaiSach.KinhTeFragment;
//import com.example.duanmau.model.LoaiSach.NTFragment;
//import com.example.duanmau.model.LoaiSach.SKFragment;
//import com.example.duanmau.model.LoaiSach.TTFragment;
//import com.example.duanmau.model.LoaiSach.TTRFragment;
//
//
//public class quanlyloaisach extends AppCompatActivity {
//    ProgressBar progressBar;
//    ImageView loadingIcon;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.fragment_qlloaisach);
//
//
//
//        Button btnTinhYeu = findViewById(R.id.btntlty);
//        Button btnKinhTe = findViewById(R.id.btnKinhTe);
//        Button btnCNTT = findViewById(R.id.btnCNTT);
//        Button btnAmThuc = findViewById(R.id.btnAmThuc);
//        Button btnSucKhoe = findViewById(R.id.btnSucKhoe);
//        Button btnTieuThuyet = findViewById(R.id.btnTieuThuyet);
//        Button btnTruyenTranh = findViewById(R.id.btnTruyenTranh);
//        Button btnNgheThuat = findViewById(R.id.btnNgheThuat);
//        Button btnDuLich = findViewById(R.id.btnDuLich);
//
//        btnTinhYeu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new CNTTFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//
//        btnKinhTe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new KinhTeFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//        btnCNTT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new CNTTFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//        btnAmThuc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new ATFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//        btnSucKhoe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new SKFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//        btnTieuThuyet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new TTFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//        btnTruyenTranh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new TTRFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//        btnNgheThuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new NTFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//        btnDuLich.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingIcon();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadFragment(new DLFragment());
//                        hideLoadingIcon();
//                    }
//                }, 2000); // Delay 2 giây
//            }
//        });
//    }
//
//
//    private void showLoadingIcon() {
//        loadingIcon.setVisibility(View.VISIBLE);
//
//        // Load animation
//        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotation_animation);
//        loadingIcon.startAnimation(rotation);
//    }
//    private void hideLoadingIcon() {
//        loadingIcon.clearAnimation();
//        loadingIcon.setVisibility(View.GONE);
//    }
//
//
//
//
//    private void loadFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.main, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
//}
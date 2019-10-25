package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileActivity extends Fragment {

    public ProfileActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);
        TextView tvNama = rootView.findViewById(R.id.txt_profile_nama);
        TextView tvTTL = rootView.findViewById(R.id.txt_ttl);
        TextView tvEmail = rootView.findViewById(R.id.txt_email);
        TextView tvMengajar = rootView.findViewById(R.id.txt_tempatmengajar);
        TextView tvBidang = rootView.findViewById(R.id.txt_bike);

        tvNama.setText(UserData.nama);
        tvTTL.setText(UserData.tempatLahir + ", " + UserData.tanggalLahir);
        tvEmail.setText(UserData.email);
        tvBidang.setText(UserData.pilihan);
        tvMengajar.setText(UserData.sekolah);
        return rootView;
    }
}

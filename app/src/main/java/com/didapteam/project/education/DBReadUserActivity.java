package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DBReadUserActivity extends Fragment {

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Users> listUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_dbread_user, container, false);
        rvView = (RecyclerView) rootView.findViewById(R.id.rv_user);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

/**
 * Mengambil data dari Firebase Realtime DB
 */
        database.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
/**
 * Saat ada data baru, masukkan datanya ke ArrayList
 */
                listUser = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
/**
 * Mapping data pada DataSnapshot ke dalam object Dosen
 * Dan juga menyimpan primary key pada object Dosen
 * untuk keperluan Edit dan Delete data
 */
                    Users users = noteDataSnapshot.getValue(Users.class);
                    users.setKey(noteDataSnapshot.getKey());
/**
 * Menambahkan object Dosen yang sudah dimapping
 * ke dalam ArrayList
 */
                    if(!users.getEmail().substring(0, users.getEmail().indexOf('@')).equals(UserChat.email)){
                        listUser.add(users);
                    }

                }
/**
 * Inisialisasi adapter dan data Dosen dalam bentuk ArrayList
 * dan mengeset Adapter ke dalam RecyclerView
 */
                adapter = new AdapterUserRecyclerView(listUser, getActivity());
                rvView.setAdapter(adapter);

    }

            @Override
            public void onCancelled(DatabaseError databaseError) {
/**
 * Kode ini akan dipanggil ketika ada error dan
 * pengambilan data gagal dan memprint error nya
 * ke LogCat
 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
        return rootView;
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DBReadUserActivity.class);
    }

}

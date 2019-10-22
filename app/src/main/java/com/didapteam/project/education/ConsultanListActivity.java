package com.didapteam.project.education;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConsultanListActivity extends AppCompatActivity {

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Users> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbread_user);

        Bundle bundle = getIntent().getBundleExtra("bidang");
        String namaBidang = bundle.getString("bidang");


        rvView = (RecyclerView) findViewById(R.id.rv_user);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ConsultanListActivity.this);
        rvView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference();

        database.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */

                listUser = new ArrayList<>();

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object User
                     * Dan juga menyimpan primary key pada object User
                     * untuk keperluan Edit dan Delete data
                     */
                    Users users = noteDataSnapshot.getValue(Users.class);
                    users.setKey(noteDataSnapshot.getKey());

                    //for (String temps : listChat)
                    if(users.getBidang().equals(namaBidang)){
                        listUser.add(users);
                    }
                    //}
                    /**
                     * Menambahkan object User yang sudah dimapping
                     * ke dalam ArrayList
                     */

                }
                /**
                 * Inisialisasi adapter dan data Dosen dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new AdapterUserRecyclerView(listUser, ConsultanListActivity.this);
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


    }
}

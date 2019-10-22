package com.didapteam.project.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

public class DBReadUserActivity extends Fragment {

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Users> listUser;
    private ArrayList<String> listChat;

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

        getUserChat();

/**
 * Mengambil data dari Firebase Realtime DB
 */

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action ", null).show();*/
                startActivity(new Intent(getActivity(), CategoryGridViewActivity.class));
            }
        });

        return rootView;
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DBReadUserActivity.class);
    }

    private void getUserChat(){
        database.child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listChat = new ArrayList<>();
                String temp;
                int indexEnd;


                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    temp = childSnapshot.getKey();
                    indexEnd = temp.length();
                    if(temp.substring(0, temp.indexOf('_')).equals(UserChat.email)){
                        listChat.add(temp.substring(temp.indexOf('_')+1, indexEnd));
                        //Log.i(TAG,temp);
                    }

                }
                getUserList(listChat);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

    }

    private void getUserList(ArrayList<String> userC){
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

                    for (String temps : userC) {
                        if((!users.getBidang().equals("null")) && users.getEmail().substring(0, users.getEmail().indexOf('@')).equals(temps)){
                            listUser.add(users);
                        }
                    }
                    /**
                     * Menambahkan object User yang sudah dimapping
                     * ke dalam ArrayList
                     */

                }
                /**
                 * Inisialisasi adapter dan data User dalam bentuk ArrayList
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
    }

}

package com.didapteam.project.education;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
public class AdapterUserRecyclerView extends RecyclerView.Adapter<AdapterUserRecyclerView.ViewHolder> {
    private ArrayList<Users> userList;
    private Context context;
    public AdapterUserRecyclerView(ArrayList<Users> users, Context ctx){
/**
 * Inisiasi data dan variabel yang akan digunakan
 */
        userList = users;
        userList = users;
        context = ctx;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;
        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_username);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
/**
 * Inisiasi ViewHolder
 */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_list, parent, false);
// mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
/**
 * Menampilkan data pada view
 */
        final String name = userList.get(position).getNama();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/**
 * Read detail data
 */
                String chatWith = userList.get(position).getEmail();
                UserChat.chatWith = chatWith.substring(0, chatWith.indexOf('@'));
                Bundle bundle = new Bundle();
                bundle.putString("nama", userList.get(position).getNama());

                Intent intent = new Intent(context, ChatRoom.class).putExtra("nama", bundle);
                context.startActivity(intent);

            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
/**
 * Delete dan update data
 */
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item
         */
        return userList.size();
    }
}

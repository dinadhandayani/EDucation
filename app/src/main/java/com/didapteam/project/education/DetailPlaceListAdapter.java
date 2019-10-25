package com.didapteam.project.education;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailPlaceListAdapter extends RecyclerView.Adapter<DetailPlaceListAdapter.CategoryViewHolder> {
    private ArrayList<School> listSchool;
    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public DetailPlaceListAdapter(ArrayList<School> list) {
        this.listSchool = list;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listSchool.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        School school = listSchool.get(position);
        holder.tvName.setText(school.getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listSchool.get(holder.getAdapterPosition()));
            }
        });
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        CategoryViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_school_name);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(School data);
    }
}
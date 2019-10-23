package com.didapteam.project.education;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.CategoryViewHolder> {
    private ArrayList<Place> listPlace;
    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public PlaceListAdapter(ArrayList<Place> list) {
        this.listPlace = list;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_place, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listPlace.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        Place place = listPlace.get(position);
        /*Glide.with(holder.itemView.getContext())
                .load(place.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto); */
        holder.tvName.setText(place.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPlace.get(holder.getAdapterPosition()));
            }
        });
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom;
        CategoryViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Place data);
    }
}
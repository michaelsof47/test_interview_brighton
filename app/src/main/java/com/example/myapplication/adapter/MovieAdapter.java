package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.SearchItem;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.RecyclerViewHolder> {

    ArrayList<SearchItem> itemList = new ArrayList<SearchItem>();
    Context context;
    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<SearchItem> itemList) {
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new RecyclerViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvHeader,tvSubtitle;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tv_header);
            tvSubtitle = itemView.findViewById(R.id.tv_subtitle);
        }

        public void bind(SearchItem item) {
            tvHeader.setText(item.getTitle());
            tvSubtitle.setText(item.getYear());
        }
    }
}

package com.example.project.Adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Domain.Category;
import com.example.project.R;
import com.example.project.databinding.ViewholderCategoryBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<Category> items;
    private int selectedposition = -1;
    private int lastselectedposition = -1;

    public CategoryAdapter(List<Category> items) {
        this.items = items;
    }

    private Context context;


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewholderCategoryBinding binding = ViewholderCategoryBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder,int position) {
        Category item = items.get(position);
        holder.binding.title.setText(item.getName());

        Glide.with(holder.itemView.getContext()).load(item.getImagePath()).error(R.drawable.star).into(holder.binding.pic);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastselectedposition = selectedposition;
                selectedposition = position;
                notifyItemChanged(lastselectedposition);
                notifyItemChanged(selectedposition);
            }
        });

        holder.binding.title.setTextColor(context.getResources().getColor(R.color.white));
        if(selectedposition == position){

            holder.binding.pic.setBackgroundResource(0);
            holder.binding.mainlayout.setBackgroundResource(R.drawable.blue_bg);
            holder.binding.title.setVisibility(View.VISIBLE);
        }
        else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg);
            holder.binding.mainlayout.setBackgroundResource(0);
            holder.binding.title.setVisibility(View.GONE);


        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderCategoryBinding binding;

        public ViewHolder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}

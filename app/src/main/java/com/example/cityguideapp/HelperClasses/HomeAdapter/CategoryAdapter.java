package com.example.cityguideapp.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguideapp.HelperClasses.Modal.CategoryClass;
import com.example.cityguideapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    ArrayList<CategoryClass> categoryLocations;

    public CategoryAdapter(ArrayList<CategoryClass> categoryLocations) {
        this.categoryLocations = categoryLocations;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design, parent, false);
        CategoryViewHolder featuredViewHolder = new CategoryViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryClass categoryClass = categoryLocations.get(position);

        holder.img.setImageResource(categoryClass.getImage_cate());
        holder.title.setText(categoryClass.getTitle_cate());
    }

    @Override
    public int getItemCount() {
        return categoryLocations.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            img = itemView.findViewById(R.id.categ_cardImg);
            title = itemView.findViewById(R.id.categ_cardTitle);
        }
    }
}
package com.example.my.controller;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my.R;
import com.example.my.model.entities.Pack;

import java.util.List;

public class packRecycleViewAdapter extends RecyclerView.Adapter<packRecycleViewAdapter.PackViewHolder> {

    private Context baseContext;
    List<Pack> packs;

    public packRecycleViewAdapter(Context baseContext, List<Pack> packs) {
        this.packs = packs;
        this.baseContext = baseContext;
    }

    @Override
    public PackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(baseContext.getApplicationContext() ).inflate(R.layout.activity_item_view,
                parent,
                false);

        return new PackViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PackViewHolder holder, int position) {

        Pack pack = packs.get(position);
        holder.nameTextView.setText(pack.getRecipient().getFirstName());
        holder.phoneTextView.setText(pack.getRecipient().getPhoneNumber());
        //Load the image using Glide

    }

    @Override
    public int getItemCount() {
        return packs.size();
    }

    class PackViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView phoneTextView;

        PackViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);

            // itemView.setOnClickListener();
            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.setHeaderTitle("Select Action");

                    MenuItem delete = menu.add(Menu.NONE, 1, 1, "Delete");

                    delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int position = getAdapterPosition();
                            String id = packs.get(position).geta();


                            return true;
                        }
                    });
                }
            });
        }
    }


}

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
        holder.FirstNameTextView.setText("Recipient First Name: "+pack.getRecipient().getFirstName());
        holder.LastNameTextView.setText("Recipient Last Name: "+pack.getRecipient().getLastName());
        holder.PhoneTextView.setText("Recipient Phone Number: "+pack.getRecipient().getPhoneNumber());
        holder.PackTypeTextView.setText("Package Type: "+pack.getPackType().toString());
        holder.PackWeightTextView.setText("Package Weight: "+pack.getPackWeight().toString());
        holder.StorageLocationTextView.setText("Storage Location: "+pack.getStorageLocation().getAddress());

    }

    @Override
    public int getItemCount() {
        return packs.size();
    }

    class PackViewHolder extends RecyclerView.ViewHolder {

        TextView FirstNameTextView;
        TextView LastNameTextView;
        TextView PhoneTextView;
        TextView PackTypeTextView;
        TextView PackWeightTextView;
        TextView StorageLocationTextView;

        PackViewHolder(View itemView) {
            super(itemView);
            FirstNameTextView = itemView.findViewById(R.id.firstNameView);
            LastNameTextView = itemView.findViewById(R.id.lastNameView);
            PhoneTextView = itemView.findViewById(R.id.phoneView);
            StorageLocationTextView = itemView.findViewById(R.id.storageLocationView);
            PackTypeTextView = itemView.findViewById(R.id.packTypeView);
            PackWeightTextView = itemView.findViewById(R.id.packWeightView);

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
                            String id = packs.get(position).getaKey();


                            return true;
                        }
                    });
                }
            });
        }
    }


}

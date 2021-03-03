package com.example.codepathprework;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Takes data and places in viewholder
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener onLongClickListener;

    public ItemsAdapter(List<String> inputList, OnLongClickListener onLongClickListener)
    {
        this.items = inputList;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        return new ViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Binds data to a viewholder.
        String item = items.get(position);
        holder.bind(item);
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    //Actually controls how the data is displayed
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item)
        {
            //Update the view to accommodate for new item
            textView.setText(item);
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //We need to tell main that we want this GONE.
                    onLongClickListener.onItemLongClicked(getAdapterPosition());
                    return true; //Callback will be consumed with true
                }
            });
        }
    }

}

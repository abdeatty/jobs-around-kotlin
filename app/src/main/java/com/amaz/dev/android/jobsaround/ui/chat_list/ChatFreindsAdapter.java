package com.amaz.dev.android.jobsaround.ui.chat_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amaz.dev.android.jobsaround.R;
import com.amaz.dev.android.jobsaround.helpers.ItemClickListener;

public class ChatFreindsAdapter extends RecyclerView.Adapter<ChatFreindsAdapter.ViewHolder> {



    private Context context;
    private  ItemClickListener itemClickListener;

    public ChatFreindsAdapter(Context context ,ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_user_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {

            itemClickListener.onItemClicked("String");
        }
    }
}

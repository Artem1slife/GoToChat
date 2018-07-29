package com.example.artemczenolegovic.pinechat;

/**
 * Created by artemczenolegovic on 23.07.2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static String[] Users = {
            "Вернулся в чат"
    };

    public static String[] times = {
            "время приключений"

    };

    public static String[] messages = {
            "ДАРОВА ПАЦАНЫ"

    };

    static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView itemTitle;
        public TextView itemDetail;
        public TextView itemTime;

        public ViewHolder(View itemView) {

            super(itemView);
            itemTitle = (TextView)itemView.findViewById(R.id.User);
            itemTime = (TextView)itemView.findViewById(R.id.tvTime);
            itemDetail = (TextView)itemView.findViewById(R.id.message);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(Users[i]);
        viewHolder.itemDetail.setText(times[i]);
        viewHolder.itemTitle.setText(messages[i]);

    }

    @Override
    public int getItemCount() {
        return Users.length;
    }

}
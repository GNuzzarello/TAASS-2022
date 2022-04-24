package com.example.project.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Activity.ShowDetailActivity;
import com.example.project.Model.TicketOrder;
import com.example.project.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<TicketOrder> ticketlist;

    public PopularAdapter(ArrayList<TicketOrder> ticketlist) {
        this.ticketlist = ticketlist;
    }


    public ArrayList<TicketOrder> getTicketlist() {
        return ticketlist;
    }

    public void setTicketlist(ArrayList<TicketOrder> ticketlist) {
        this.ticketlist = ticketlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(ticketlist.get(position).getTicket().getName());
        holder.price.setText(String.valueOf(ticketlist.get(position).getTicket().getPrice()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(ticketlist.get(position).getTicket().getPictureUrl(), "drawable", holder.itemView.getContext().getPackageName());
        String pic_Url = ticketlist.get(position).getTicket().getPictureUrl();
        Glide.with(holder.itemView.getContext())
                .load(pic_Url)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", ticketlist.get(position).getTicket());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return ticketlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView pic;
        TextView addBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}

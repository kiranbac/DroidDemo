package com.wpr.newsapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wpr.newsapplication.R;
import com.wpr.newsapplication.models.Facts;

public class FactsListAdapter extends RecyclerView.Adapter<FactsListAdapter.DataViewHolder> {

    private Facts factsList;
    Context mContext;

    public FactsListAdapter(Facts factsList, Context context) {
        this.factsList = factsList;
        mContext = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
            int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.facts_desc_layout,
                parent, false);

        return new FactsListAdapter.DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.factTitle.setText(factsList.getRows().get(position).getTitle());
        holder.factDesc.setText(factsList.getRows().get(position).getDescription());
        Picasso.with(mContext).load(factsList.getRows().get(position).getImageHref()).into(
                holder.factImage);
    }

    @Override
    public int getItemCount() {
        return factsList.getRows().size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView factTitle, factDesc;
        ImageView factImage;

        public DataViewHolder(View itemView) {
            super(itemView);
            factTitle = (TextView) itemView.findViewById(R.id.fact_title_text_view);
            factDesc = (TextView) itemView.findViewById(R.id.fact_desc_text);
            factImage = (ImageView) itemView.findViewById(R.id.fact_image);
        }
    }
}

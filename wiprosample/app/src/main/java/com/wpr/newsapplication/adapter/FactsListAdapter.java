package com.wpr.newsapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wpr.newsapplication.R;
import com.wpr.newsapplication.models.Facts;

public class FactsListAdapter extends RecyclerView.Adapter<FactsListAdapter.DataViewHolder> {

    private Facts factsList;
    private Context mContext;

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
    public void onBindViewHolder(@NonNull final DataViewHolder holder, int position) {
        if (!TextUtils.isEmpty(factsList.getRows().get(position).getTitle())) {
            holder.factTitle.setText(factsList.getRows().get(position).getTitle());
        } else {
            holder.factTitle.setText(R.string.title);
        }
        if (!TextUtils.isEmpty(factsList.getRows().get(position).getDescription())) {
            holder.factDesc.setText(factsList.getRows().get(position).getDescription());
        } else {
            holder.factDesc.setText(R.string.desc);
        }
        if (!TextUtils.isEmpty(factsList.getRows().get(position).getImageHref())) {

            Picasso.get()
                    .load(factsList.getRows().get(position).getImageHref())
                    .error(mContext.getResources().getDrawable(R.drawable.ic_image))

                    .into(holder.factImage,new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception ex) {
                            holder.factImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_image));
                        }
                    });
        } else {
            holder.factImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_image));
        }
    }

    @Override
    public int getItemCount() {
        return factsList.getRows().size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView factTitle, factDesc;
        ImageView factImage;

        DataViewHolder(View itemView) {
            super(itemView);
            factTitle = itemView.findViewById(R.id.fact_title_text_view);
            factDesc = itemView.findViewById(R.id.fact_desc_text);
            factImage = itemView.findViewById(R.id.fact_image);
        }
    }
}

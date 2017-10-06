package com.fatimamostafa.restfulwebservices;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fatimamostafa on 10/1/17.
 */

public class HomeAdapter  extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private String[] mTitle;
    private Context mContext;
    private OnClickCallback callback;


    public HomeAdapter(String[] mTitle, Context mContext, OnClickCallback callback) {
        this.mTitle = mTitle;
        this.mContext = mContext;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.btnItem.setText(mTitle[position]);
    }

    @Override
    public int getItemCount() {
        return mTitle.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.btnItem)
        Button btnItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            btnItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("HOMEADAPTER", "onClick: " + getAdapterPosition());
            callback.onClickCallback(getAdapterPosition());
        }
    }


}

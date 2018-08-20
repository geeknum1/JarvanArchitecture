package com.mustang.module_news.adapter;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mustang.module_news.R;
import com.mustang.module_news.bean.GirlsData;
import com.mustang.module_news.callback.GirlItemClickCallback;

import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by Mustang on 2018/8/15.
 */

public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder> {

    List<GirlsData.ResultsBean> girlsList;
    GirlItemClickCallback girlItemClickCallback;

    /**
     * 构造方法传入点击监听器
     * @param itemClickCallback
     */
    public GirlsAdapter(@Nullable GirlItemClickCallback itemClickCallback) {
        girlItemClickCallback = itemClickCallback;
    }

    public void setGirlsList(final List<GirlsData.ResultsBean> list){
        if(girlsList == null){
            girlsList = list;
            notifyItemRangeInserted(0, girlsList.size());
        }else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return girlsList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    GirlsData.ResultsBean oldData = girlsList.get(oldItemPosition);
                    GirlsData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    GirlsData.ResultsBean oldData = girlsList.get(oldItemPosition);
                    GirlsData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            girlsList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public GirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlsViewHolder holder = new GirlsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.girl_item, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(GirlsViewHolder holder, int position) {
       final GirlsData.ResultsBean bean =  girlsList.get(position);
        holder.tv.setText(bean.getWho());
        holder.mSimpleDraweeView.setImageURI(Uri.parse(bean.getUrl()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girlItemClickCallback.onClick(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return girlsList == null ? 0 : girlsList.size();
    }

    static class GirlsViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        SimpleDraweeView mSimpleDraweeView;
        public GirlsViewHolder(View itemView) {
            super(itemView);
           tv= itemView.findViewById(R.id.tv);
           mSimpleDraweeView = itemView.findViewById(R.id.iv);
        }
    }

}

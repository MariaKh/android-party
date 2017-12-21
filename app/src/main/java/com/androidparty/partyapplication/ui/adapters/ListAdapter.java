package com.androidparty.partyapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidparty.partyapplication.R;
import com.androidparty.partyapplication.network.entities.response.DataResponse;

import java.util.ArrayList;

/**
 * Created by 1 on 12/20/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final Context mContext;
    private ArrayList<DataResponse> mItems;
    private String mFormatedDistance;

    public ListAdapter(Context context, ArrayList<DataResponse> items) {
        this.mContext = context;
        this.mItems = items;
        mFormatedDistance = mContext.getString(R.string.distance_format);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_layout, parent, false);
            return new ItemViewHolder(view);
        } else {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.list_header_layout, parent, false);
            return new BaseItemHolder(view) {
            };
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            DataResponse item = getItem(position);
            ((ItemViewHolder) holder).mName.setText(item.getName());
            ((ItemViewHolder) holder).mDistance.setText(String.format(mFormatedDistance, item.getDistance()));
        }
    }

    private DataResponse getItem(int position) {
        return mItems.get(position - 1);
    }

    public void updateData(ArrayList<DataResponse> list) {
        this.mItems = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size() + 1;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mDistance;
        private TextView mName;

        public ItemViewHolder(View view) {
            super(view);
            mDistance = view.findViewById(R.id.distance);
            mName = view.findViewById(R.id.server);
        }
    }

    class BaseItemHolder extends RecyclerView.ViewHolder {

        public BaseItemHolder(View itemView) {
            super(itemView);
        }
    }

}

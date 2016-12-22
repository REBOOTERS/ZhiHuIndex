package home.smart.fly.zhihuindex.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import home.smart.fly.zhihuindex.R;

/**
 * Created by engineer on 2016/12/22.
 */

public class IndexLiveHListAdapter extends RecyclerView.Adapter<IndexLiveHListAdapter.MyHolder> {


    private List<String> datas;
    private Context mContext;

    public IndexLiveHListAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.live_hlist_item, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size() > 5 ? 5 : datas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}

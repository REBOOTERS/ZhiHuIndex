package home.smart.fly.zhihuindex.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import home.smart.fly.zhihuindex.Constant;
import home.smart.fly.zhihuindex.R;
import home.smart.fly.zhihuindex.util.ScreenUtil;
import home.smart.fly.zhihuindex.widget.ListItemMenu;

/**
 * Created by engineer on 2016/9/13.
 */
public class IndexRecyclerViewAdapter extends RecyclerView.Adapter<IndexRecyclerViewAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;

    private List<String> datas = new ArrayList<>();
    private Context mContext;

    private int menuW, menuH;

    public IndexRecyclerViewAdapter(Context mContext, List<String> datas) {
        this.datas = datas;
        this.mContext = mContext;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
        menuW = display.widthPixels / 2;
        menuH = LinearLayout.LayoutParams.WRAP_CONTENT;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headView);
        }


        View view = LayoutInflater.from(mContext).inflate(R.layout.index_list_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }

        final int pos = getRealPosition(holder);

        if (pos == 1) {
            holder.liveList.setVisibility(View.VISIBLE);
            holder.normalShell.setVisibility(View.GONE);
        } else {
            holder.liveList.setVisibility(View.GONE);
            holder.normalShell.setVisibility(View.VISIBLE);
        }


//        holder.text.setText(datas.get(pos));

        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.profile_pic);
        Glide.with(mContext).load(Constant.itemPics.get(pos % 3)).placeholder(R.drawable.cardpic).into(holder.pic);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListItemMenu menu = new ListItemMenu(menuW, menuH, mContext);
                menu.update();
                int offx = ScreenUtil.dip2px(mContext, 24);
                int offy = ScreenUtil.dip2px(mContext, 24);
                menu.setAnimationStyle(R.style.MenuAnim);
                menu.showAsDropDown(holder.menu, -menuW + offx, -offy);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.liveList.setLayoutManager(manager);
        IndexLiveHListAdapter adapter = new IndexLiveHListAdapter(datas);
        holder.liveList.setAdapter(adapter);
    }

    @Override
    public int getItemViewType(int position) {
        if (headView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return headView == null ? datas.size() : datas.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView menu;
        CircleImageView profile_pic;
        ImageView pic;
        LinearLayout normalShell;
        RecyclerView liveList;

        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;
            text = (TextView) itemView.findViewById(R.id.text);
            menu = (ImageView) itemView.findViewById(R.id.menu);
            profile_pic = (CircleImageView) itemView.findViewById(R.id.profile_image);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            normalShell = (LinearLayout) itemView.findViewById(R.id.normalList);
            liveList = (RecyclerView) itemView.findViewById(R.id.liveList);
        }
    }

    public void setHeadView(View view) {
        headView = view;
        notifyItemInserted(0);
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int pos = holder.getLayoutPosition();
        return headView == null ? pos : pos - 1;
    }
}

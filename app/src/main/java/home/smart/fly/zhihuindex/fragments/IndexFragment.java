package home.smart.fly.zhihuindex.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.zhihuindex.R;
import home.smart.fly.zhihuindex.adapter.IndexRecyclerViewAdapter;

public class IndexFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private IndexRecyclerViewAdapter adapter;
    //
    private View rootView;
    private FloatingActionMenu fam;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_index, null);
        InitView();
        return rootView;
    }

    private void InitView() {
        fam = (FloatingActionMenu) rootView.findViewById(R.id.menu_yellow);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.index_list_headview, null);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("This is item " + i);
        }
        adapter = new IndexRecyclerViewAdapter(mContext, datas);
        adapter.setHeadView(headView);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > 5) {
                    if (dy > 0) {
                        fam.hideMenu(true);
                    } else {
                        fam.showMenu(true);
                    }
                }
            }
        });


    }


    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }


}

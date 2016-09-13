package home.smart.fly.zhihuindex;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.zhihuindex.adapter.IndexRecyclerViewAdapter;

public class IndexFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private IndexRecyclerViewAdapter adapter;

    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;
    private IndexScrollListener listener;
    //
    private View rootView;


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
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("This is item " + i);
        }
        adapter = new IndexRecyclerViewAdapter(datas);
        recyclerView.setAdapter(adapter);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.coordinatorLayout);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                Log.e("lll", " "+dy);


            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.e("111", " " + newState);
                super.onScrollStateChanged(recyclerView, newState);
//                if(newState==0){
                    if (listener != null) {
                        if(fab.getVisibility()==View.VISIBLE){
                            listener.show();
                        }else {
                            listener.hide();
                        }
                    }
//                }
            }
        });


    }


    @Override
    public void onRefresh() {

        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 监听Fragment 内Scroll 状态的接口
     */
    public interface IndexScrollListener{
        void show();
        void hide();
    }

    public void setListener(IndexScrollListener listener) {
        this.listener = listener;
    }
}

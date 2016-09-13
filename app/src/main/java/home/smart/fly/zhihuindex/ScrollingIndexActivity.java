package home.smart.fly.zhihuindex;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.zhihuindex.adapter.IndexRecyclerViewAdapter;

public class ScrollingIndexActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private IndexRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        mContext=this;
        InitView();
    }

    private void InitView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        List<String> datas = new ArrayList<>();
        for(int i=0;i<100;i++) {
            datas.add("This is item " + i);
        }
        adapter=new IndexRecyclerViewAdapter(datas);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}

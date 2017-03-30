package home.smart.fly.zhihuindex;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.zhihuindex.adapter.FragmentTabAdapter;
import home.smart.fly.zhihuindex.fragments.FourFragment;
import home.smart.fly.zhihuindex.fragments.IndexFragment;
import home.smart.fly.zhihuindex.fragments.SecondFragment;
import home.smart.fly.zhihuindex.fragments.ThirdFragment;

/**
 * Created by co-mall on 2016/9/13.
 */
public class MainActivity extends FragmentActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;


    private FrameLayout content;
    private AppBarLayout index_app_bar;

    private List<Fragment> fragments = new ArrayList<>();

    //View
    private RadioGroup rgs;
    private RadioButton index_tab;
    private int currentIndex = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView() {
        content = (FrameLayout) findViewById(R.id.content);
        index_app_bar = (AppBarLayout) findViewById(R.id.index_app_bar);
        rgs = (RadioGroup) findViewById(R.id.tabs_rg);
        index_tab = (RadioButton) findViewById(R.id.home_tab);
        fragments.add(new IndexFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new FourFragment());

        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.content, rgs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                super.OnRgsExtraCheckedChanged(radioGroup, checkedId, index);
                Log.e("CheckedChanged", "-----" + index);
                currentIndex = index;
                resetView();
                switch (index) {
                    case 0:
                        index_app_bar.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;

                }

            }
        });




    }

    private void resetView() {
        index_app_bar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {

        if (currentIndex != 0) {
            index_tab.setChecked(true);
        } else {
            super.onBackPressed();
        }
    }
}

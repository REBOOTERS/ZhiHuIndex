package home.smart.fly.zhihuindex;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private List<Fragment> fragments = new ArrayList<>();

    //View
    private RadioGroup rgs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView() {
        rgs = (RadioGroup) findViewById(R.id.tabs_rg);
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
            }
        });

    }


}

package home.smart.fly.zhihuindex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by co-mall on 2016/9/13.
 */
public class MainActivity extends FragmentActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IndexFragment indexFragment = new IndexFragment();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, indexFragment);
        transaction.show(indexFragment);
        transaction.commitAllowingStateLoss();
    }
}

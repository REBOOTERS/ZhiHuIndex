package home.smart.fly.zhihuindex;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

/**
 * Created by co-mall on 2016/9/13.
 */
public class MainActivity extends FragmentActivity implements IndexFragment.IndexScrollListener {
    private final String TAG = MainActivity.class.getSimpleName();
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    //
    private LinearLayout bottom;
    private boolean aninmating;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();


        IndexFragment indexFragment = new IndexFragment();
        indexFragment.setListener(this);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, indexFragment);
        transaction.show(indexFragment);
        transaction.commitAllowingStateLoss();
    }

    private void InitView() {
        bottom = (LinearLayout) findViewById(R.id.bottom);
    }

    @Override
    public void show() {

        ViewPropertyAnimator animator = bottom.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                bottom.setVisibility(View.VISIBLE);
                aninmating =true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                aninmating =false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide();
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        if(!aninmating){
            animator.start();
        }

    }

    @Override
    public void hide() {

        ViewPropertyAnimator animator = bottom.animate().translationY(bottom.getY()).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                aninmating =true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                bottom.setVisibility(View.GONE);
                aninmating =false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show();
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        if(!aninmating){
            animator.start();
        }

    }
}

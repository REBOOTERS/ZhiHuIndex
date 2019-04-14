package home.smart.fly.zhihuindex.behavior;

import android.animation.Animator;
import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * Created by engineer on 2016/9/13.
 */
public class FabBehavior extends CoordinatorLayout.Behavior<View> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    /**
     * 控件距离coordinatorLayout底部距离
     */
    private float viewDistance;
    private boolean aninmating;


    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {

        if(child.getVisibility() == View.VISIBLE&& viewDistance ==0){
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewDistance =coordinatorLayout.getHeight()-child.getY();
        }

        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//判断是否竖直滚动
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        //dy大于0是向上滚动 小于0是向下滚动

        if (dy >=0&&!aninmating &&child.getVisibility()==View.VISIBLE) {
            hide(child);
        } else if (dy <0&&!aninmating &&child.getVisibility()==View.GONE) {
            show(child);
        }
    }

    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(viewDistance).setInterpolator(INTERPOLATOR).setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                aninmating =true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
                aninmating =false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }

    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                aninmating =true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                aninmating =false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }
}

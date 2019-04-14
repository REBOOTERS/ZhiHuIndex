package home.smart.fly.zhihuindex.behavior;

import android.content.Context;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by engineer on 2016/9/13.
 */
public class BottomViewBehavior extends CoordinatorLayout.Behavior<View> {
    public BottomViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        float translationY = Math.abs(dependency.getTop());
        child.setTranslationY(translationY);
        return true;
    }


}

package home.smart.fly.zhihuindex.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created on 2019/12/24.
 *
 * @author rookie
 */
public class BorderView extends RelativeLayout {
    private static final String TAG = "BorderView";

    // <editor-fold desc="构造函数" defaultstate="collapsed">
    public BorderView(Context context) {
        super(context);
        init(context);
    }

    public BorderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BorderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    // </editor-fold>

    private Paint mPaint;

    private RectF mRectF;

    private float mRadius;

    private float mStrokeWidth = 5f;

    private int count =0;

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setAlpha(0);
        mRadius = 5f;
        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(mStrokeWidth / 2, mStrokeWidth / 2,
                getMeasuredWidth() - mStrokeWidth / 2,
                getMeasuredHeight() - mStrokeWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
    }

    public void startAnim(final Callback callback) {
        count=0;
        final ValueAnimator alpha = ValueAnimator.ofInt(0,255,0);


        alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e(TAG, "onAnimationUpdate: "+animation.getAnimatedFraction() );
                int value = (int) animation.getAnimatedValue();
                callback.update(animation.getAnimatedFraction());
                mPaint.setAlpha(value);
                invalidate();
            }
        });
        alpha.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                count++;
                if (count < 3) {
                    alpha.setStartDelay(400);
                    alpha.start();
                }
            }
        });

        alpha.setDuration(800);
        alpha.start();
    }

    public interface Callback{
        void update(float delta);
    }
}

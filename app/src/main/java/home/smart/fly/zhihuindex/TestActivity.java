package home.smart.fly.zhihuindex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Observable;

import home.smart.fly.zhihuindex.widget.BorderView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final BorderView borderView = findViewById(R.id.border);
        final TextView textView = findViewById(R.id.text);
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();

        borderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borderView.startAnim(new BorderView.Callback() {
                    @Override
                    public void update(float delta) {
                        int value = (int) (5 * delta) +10;
                        int margin = dp2px(value);
                        params.setMargins(margin,margin,margin,margin);
                        textView.setLayoutParams(params);
                    }
                });

            }
        });
    }

    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
}

package home.smart.fly.zhihuindex.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import home.smart.fly.zhihuindex.R;

import static home.smart.fly.zhihuindex.R.id.headview;

/**
 * Created by engineer on 2016/9/21.
 */

public class FourFragment extends Fragment {
    private View rootView;
    private Context mContext;
    private CollapsingToolbarLayout collapsing_toolbar;
    private FloatingActionButton fab;
    private static final String picUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555240524505&di=e830aa1a09dd2fd1a66a77d8b4447d88&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F71a4a6493bf00fd5bd80726375d930bc956939bb96e1-EKBAae_fw658";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_four, container, false);
        InitView();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void InitView() {
        collapsing_toolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setTitle("个人中心");
        fab = (FloatingActionButton) rootView.findViewById(R.id.btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "编辑", Toast.LENGTH_SHORT).show();
            }
        });
        CircleImageView view = (CircleImageView) rootView.findViewById(headview);
        RequestOptions option = new RequestOptions();
        option.placeholder(R.drawable.profile);
        Glide.with(mContext).load(picUrl)
                .apply(option)
                .into(view);

    }
}

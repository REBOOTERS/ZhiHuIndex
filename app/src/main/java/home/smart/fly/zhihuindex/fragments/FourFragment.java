package home.smart.fly.zhihuindex.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
    private static final String picUrl = "http://img1.imgtn.bdimg.com/it/u=3743691986,2983459167&fm=21&gp=0.jpg";

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
        Glide.with(mContext).load(picUrl).placeholder(R.drawable.profile).into(view);

    }
}

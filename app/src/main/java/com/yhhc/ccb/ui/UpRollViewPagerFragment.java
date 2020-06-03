package com.yhhc.ccb.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yhhc.ccb.uprollview.R;
import com.yhhc.ccb.view.VViewPager;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpRollViewPagerFragment extends Fragment {


    public UpRollViewPagerFragment() {
        // Required empty public constructor
    }

    private List<String> datas = Arrays.asList(
            "凤兮凤兮归故乡，遨游四海求其凰"
            , "我宁愿犯错误，也不愿什么都不做"
            , "我们该怎么进行，这令人愉悦的折磨呢"
            , "千军万马一将在，探囊取物有何难"
            , "落叶的一生，只是为了归根么"
            , "时间不在于你拥有多少，而在于你怎样使用"
            , "想攻击我？先试试和影子玩拳击吧"
            , "我还以为你从来都不会选我呢；真可怜，让我抱抱你"
            , "断剑重铸之日，其势归来之时"
            , "真正的大师，永远都怀着一颗学徒的心"
            , "我于杀戮之中绽放，亦如黎明中的花朵"
            , "是的，只要998，就能让你爽到不能呼吸");


    private View view;
    private VViewPager vvp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_up_roll_view_pager, container, false);
        vvp = view.findViewById(R.id.vvp);
        init();
        return view;
    }

    private void init() {
        VAdapter vAdapter = new VAdapter();
        vvp.setAdapter(vAdapter);
        handler.postDelayed(runnable, 3000);
    }

    private int currentPosition;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            vvp.setCurrentItem(++currentPosition , true);
            handler.postDelayed(runnable, 3000);
        }
    };


    class VAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View v = View.inflate(getContext(), R.layout.item_rv, null);
            TextView tv = v.findViewById(R.id.tv);
            tv.setText(datas.get(position % datas.size()));
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        runnable = null;
        handler = null;
    }
}

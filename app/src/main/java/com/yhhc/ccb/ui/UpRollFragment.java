package com.yhhc.ccb.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yhhc.ccb.uprollview.R;
import com.yhhc.ccb.view.UpRollView;

import java.util.ArrayList;
import java.util.List;

/**
 * CCB simple {@link Fragment} subclass.
 * ViewFlipper 实现
 */
public class UpRollFragment extends Fragment {


    public UpRollFragment() {
        // Required empty public constructor
    }

    private View view;
    private UpRollView vf1 , vf0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_up_roll, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
    }

    String datas[] = {
            "君不见黄河之水天上来，奔流到海不复回。"
            ,"君不见高堂明镜悲白发，朝如青丝暮成雪。"
            ,"碧玉妆成一树高,万条垂下绿丝绦。"
            ,"不知细叶谁裁出,二月春风似剪刀。"
            ,"劝君更尽一杯酒, 西出阳关无故人。"
            ,"孤帆远影碧空尽, 惟见长江天际流。"
            ,"好雨知时节,当春乃发生。随风潜入夜,润物细无声。"
            ,"旧时王谢堂前燕, 飞入寻常百姓家。"
            ,"凤兮凤兮归故乡，遨游四海求其凰"
            ,"山有木兮木有枝，心悦君兮君不知"
            ,"老骥伏枥，志在千里; 烈士暮年，壮心不已。"};

    private void initView() {

        vf0 = view.findViewById(R.id.vf0);
        List<View> views0 = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            TextView tv = new TextView(getContext());
            tv.setTextColor(getContext().getResources().getColor(R.color.text));
            tv.setText(datas[i]);
            views0.add(tv);
        }
        vf0.setViews(views0);




        vf1 = view.findViewById(R.id.vf1);
        List<View> views = new ArrayList<>();
        for (int i = 0; i < datas.length; i= i+2) { //一次遍历两条数据
            View v = View.inflate(getContext(),R.layout.item_vf1,null);
            TextView tv1 = v.findViewById(R.id.tv1);
            TextView tv2 = v.findViewById(R.id.tv2);
            tv1.setText(datas[i]);
            if (datas.length > i+1){
                tv2.setText(datas[i+1]);
            }else {
                tv2.setVisibility(View.GONE);
            }
            views.add(v);
        }
        vf1.setViews(views);
    }

}

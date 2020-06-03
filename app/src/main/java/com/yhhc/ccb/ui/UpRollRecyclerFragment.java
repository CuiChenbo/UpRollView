package com.yhhc.ccb.ui;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.yhhc.ccb.uprollview.R;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * RecyclerView + 子线程睡眠（或Handler）
 */
public class UpRollRecyclerFragment extends Fragment {


    private LinearLayoutManager linearLayoutManager;
    private Thread thread;

    public UpRollRecyclerFragment() {
        // Required empty public constructor
    }

   private List<String> datas = Arrays.asList(
            "凤兮凤兮归故乡，遨游四海求其凰"
            ,"我宁愿犯错误，也不愿什么都不做"
            ,"我们该怎么进行，这令人愉悦的折磨呢"
            ,"千军万马一将在，探囊取物有何难"
            ,"落叶的一生，只是为了归根么"
            ,"时间不在于你拥有多少，而在于你怎样使用"
            ,"想攻击我？先试试和影子玩拳击吧"
            ,"我还以为你从来都不会选我呢；真可怜，让我抱抱你"
            ,"断剑重铸之日，其势归来之时"
            ,"真正的大师，永远都怀着一颗学徒的心"
            ,"我于杀戮之中绽放，亦如黎明中的花朵"
            ,"是的，只要998，就能让你爽到不能呼吸");


    private View view ;
    private RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_up_roll_recycler, container, false);
         rv = view.findViewById(R.id.rv);
         init();
        return view;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(linearLayoutManager);
        new PagerSnapHelper().attachToRecyclerView(rv); //一次滑动一页
        rv.setAdapter(new MAdapter());
        startRoll();
//        handler.sendEmptyMessageDelayed(1 , 3000);
    }

    private int cp;
    DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
    private void startRoll(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                if (!thread.isInterrupted()) {
                    rv.smoothScrollBy(0, dp2px(60), decelerateInterpolator);
                    //                rv.smoothScrollToPosition(++cp);
                    this.run();
                }
            }
        });
        thread.start();
    }


//    private Handler handler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message message) {
//            if (message.what == 1){
//                rv.smoothScrollBy(0 , dp2px(60),decelerateInterpolator);
////                rv.smoothScrollToPosition(++cp);
//                handler.sendEmptyMessageDelayed(1 , 3000);
//            }
//            return false;
//        }
//    });

    class MAdapter extends RecyclerView.Adapter<MAdapter.VH> {

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new VH(LayoutInflater.from(getContext()).inflate(R.layout.item_rv,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH viewHolder, int i) {
          viewHolder.tv.setText(datas.get(i % datas.size()));
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        class VH extends RecyclerView.ViewHolder{
          TextView tv;
            public VH(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }



    public int dp2px(float dpValue) {
        return (int) (0.5f + dpValue *  getActivity().getResources().getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (thread != null)thread.interrupt();
//        handler.removeCallbacksAndMessages(null);
//        handler = null;
    }
}

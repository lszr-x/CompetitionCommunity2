package cn.abtion.neuqercc.message.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.message.adapters.MessageRecAdapter;
import cn.abtion.neuqercc.message.models.MessageModel;
import cn.abtion.neuqercc.utils.Utility;
import cn.abtion.neuqercc.widget.SwipeItemLayout;

import static cn.abtion.neuqercc.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午12:51
 * fhyPayaso@qq.com
 */
public class ChatListFragment extends BaseFragment {


    List<MessageModel> messageModelList = new ArrayList<>();


    @BindView(R.id.message_rec)
    RecyclerView mMessageRec;
    Unbinder unbinder;
    @BindView(R.id.ly_refresh_message)
    SwipeRefreshLayout lyRefreshMessage;
    Unbinder unbinder1;

    private MessageRecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        initRec();
        initSwipe();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            MessageModel model = new MessageModel("ss", "lalala" + i, "晚安啦", "00:53");
                            messageModelList.add(model);
                        }
                        mAdapter.notifyDataSetChanged();
                        lyRefreshMessage.setRefreshing(false);
                    }
                });
            }
        }, 2000);
    }

    @Override
    protected void loadData() {


    }


    private void initSwipe() {

        lyRefreshMessage.setRefreshing(true);
        lyRefreshMessage.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        lyRefreshMessage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                for (int i = 0; i < 5; i++) {
                                    MessageModel model = new MessageModel("ss", "lalala" + i, "晚安啦", "00:53");
                                    messageModelList.add(model);
                                }
                                mAdapter.notifyDataSetChanged();
                                lyRefreshMessage.setRefreshing(false);
                            }
                        });
                    }
                }, 2000);
            }
        });
    }



    private void initRec() {


        mAdapter = new MessageRecAdapter(getContext(), messageModelList);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mMessageRec.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        mMessageRec.setLayoutManager(mLayoutManager);
        mMessageRec.setAdapter(mAdapter);
    }

}

package cn.abtion.neuqercc.message.fragments;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.message.adapters.FriendsRecAdapter;
import cn.abtion.neuqercc.message.models.FriendModel;
import cn.abtion.neuqercc.message.models.MessageModel;
import cn.abtion.neuqercc.widget.SwipeItemLayout;

import static cn.abtion.neuqercc.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午12:52
 * fhyPayaso@qq.com
 */
public class FriendListFragment extends BaseFragment {
    @BindView(R.id.rec_friends)
    RecyclerView mRecFriends;
    Unbinder unbinder;
    @BindView(R.id.ly_refresh_friends)
    SwipeRefreshLayout lyRefreshFriends;
    Unbinder unbinder1;


    private FriendsRecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<FriendModel> friendModelList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friends;
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
                            friendModelList.add(new FriendModel("sss", "username" + i));
                        }
                        mAdapter.notifyDataSetChanged();
                        lyRefreshFriends.setRefreshing(false);
                    }
                });
            }
        }, 2000);

    }

    @Override
    protected void loadData() {

    }


    private void initSwipe() {

        lyRefreshFriends.setRefreshing(true);
        lyRefreshFriends.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        lyRefreshFriends.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                for (int i = 0; i < 3; i++) {
                                    friendModelList.add(new FriendModel("sss", "username" + i));
                                }
                                mAdapter.notifyDataSetChanged();
                                lyRefreshFriends.setRefreshing(false);
                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    /**
     * 初始化好友列表
     */
    private void initRec() {

        mAdapter = new FriendsRecAdapter(getContext(), friendModelList);
        mRecFriends.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecFriends.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        mRecFriends.setLayoutManager(mLayoutManager);

    }

}

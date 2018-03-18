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
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.message.activities.FriendInfoActivity;
import cn.abtion.neuqercc.message.adapters.FriendItemListener;
import cn.abtion.neuqercc.message.adapters.FriendsRecAdapter;
import cn.abtion.neuqercc.message.models.FriendModel;
import cn.abtion.neuqercc.message.models.MessageModel;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import cn.abtion.neuqercc.widget.SwipeItemLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cn.abtion.neuqercc.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午12:52
 * fhyPayaso@qq.com
 */
public class FriendListFragment extends BaseFragment implements FriendItemListener,SwipeRefreshLayout.OnRefreshListener{
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
    }

    @Override
    protected void loadData() {

    }


    private void initSwipe() {

        lyRefreshFriends.setRefreshing(true);
        lyRefreshFriends.setOnRefreshListener(this);
        lyRefreshFriends.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        loadFriendList();
                        mAdapter.notifyDataSetChanged();
                        lyRefreshFriends.setRefreshing(false);
                    }
                });
            }
        }, 2000);

    }

    /**
     * 初始化好友列表
     */
    private void initRec() {

        mAdapter = new FriendsRecAdapter(getContext(), friendModelList);
        mAdapter.setFriendItemListener(this);

        mRecFriends.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecFriends.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        mRecFriends.setLayoutManager(mLayoutManager);

    }


    /**
     * 加载好友列表
     */
    private void loadFriendList() {


        RestClient.getService().loadFriendList(LoginActivity.phoneNumber).enqueue(new DataCallback<APIResponse<List<String>>>() {


            @Override
            public void onDataResponse(Call<APIResponse<List<String>>> call, Response<APIResponse<List<String>>> response) {

                List<String> responseList  = new ArrayList<>();
                friendModelList.clear();



                responseList = response.body().getData();
                for (int i = 0;i<responseList.size();i++) {
                    friendModelList.add(new FriendModel("",responseList.get(i)));
                }
            }

            @Override
            public void onDataFailure(Call<APIResponse<List<String>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });
    }


    @Override
    public void onAvaterClick(int pos) {
        FriendInfoActivity.startActivity(getContext(),friendModelList.get(pos).getFriendName());
    }

    @Override
    public void onSendMessageClick(int pos) {





    }

    @Override
    public void onDeleteClick(final int pos) {

        RestClient
                .getService()
                .deleteFriend(LoginActivity.phoneNumber,friendModelList.get(pos).getFriendName())
                .enqueue(new DataCallback<APIResponse>() {
                    @Override
                    public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        ToastUtil.showToast("删除好友成功");
                        mAdapter.removeItem(pos);
                    }

                    @Override
                    public void onDataFailure(Call<APIResponse> call, Throwable t) {

                    }

                    @Override
                    public void dismissDialog() {

                    }
                });

    }

    @Override
    public void onRefresh() {

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

                                loadFriendList();
                                mAdapter.notifyDataSetChanged();
                                lyRefreshFriends.setRefreshing(false);
                            }
                        });
                    }
                }, 2000);
            }
        });
    }
}

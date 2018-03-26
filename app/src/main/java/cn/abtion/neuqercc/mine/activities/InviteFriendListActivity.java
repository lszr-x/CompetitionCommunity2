package cn.abtion.neuqercc.mine.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.NEUQerCCApplication;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.constants.CacheKey;
import cn.abtion.neuqercc.message.models.FriendModel;
import cn.abtion.neuqercc.message.models.NoticeModel;
import cn.abtion.neuqercc.mine.adapters.InviteFriendListAdapter;
import cn.abtion.neuqercc.mine.adapters.InviteFriendListener;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 邀请好友入队界面
 *
 * @author FanHongyu.
 * @since 18/3/26 15:04.
 * email fanhongyu@hrsoft.net.
 */

public class InviteFriendListActivity extends ToolBarActivity implements InviteFriendListener {


    @BindView(R.id.rec_invite_friend_list)
    RecyclerView recInviteFriendList;
    private List<FriendModel> mInviteFriendList;
    private InviteFriendListAdapter mRecAdapter;
    private int teamId;
    private String teamName;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend_list;
    }

    @Override
    protected void initVariable() {

        mInviteFriendList = new ArrayList<>();
        Intent intent = getIntent();
        teamId = intent.getIntExtra("team_id", -1);
        teamName = intent.getStringExtra("team_name");
    }

    @Override
    protected void initView() {

        setActivityTitle("邀请好友");
    }

    @Override
    protected void loadData() {
        loadFriendList();
    }

    /**
     * 加载好友列表
     */
    private void loadFriendList() {


        RestClient.getService().loadFriendList(LoginActivity.phoneNumber).enqueue(new DataCallback<APIResponse<List<FriendModel>>>() {

            @Override
            public void onDataResponse(Call<APIResponse<List<FriendModel>>> call,
                                       Response<APIResponse<List<FriendModel>>> response) {

                List<FriendModel> responseList = response.body().getData();
                if (responseList != null) {
                    mInviteFriendList.clear();
                    mInviteFriendList.addAll(responseList);
                    initRecyclerView();
                }
            }

            @Override
            public void onDataFailure(Call<APIResponse<List<FriendModel>>> call, Throwable t) {
            }

            @Override
            public void dismissDialog() {
            }
        });
    }


    private void initRecyclerView() {

        mRecAdapter = new InviteFriendListAdapter(InviteFriendListActivity.this, mInviteFriendList);
        mRecAdapter.setInviteFriendListener(this);
        recInviteFriendList.setAdapter(mRecAdapter);
        recInviteFriendList.setLayoutManager(new LinearLayoutManager(InviteFriendListActivity.this,
                LinearLayoutManager.VERTICAL, false));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 邀请好友按钮点击事件
     *
     * @param pos
     */
    @Override
    public void onBtnInviteClick(int pos) {

        String username = NEUQerCCApplication.getInstance().getCacheUtil().getString(CacheKey.USER_NAME);
        FriendModel model = mInviteFriendList.get(pos);
        NoticeModel request = new NoticeModel("", LoginActivity.phoneNumber
                , model.getPhone(), teamId, teamName, 3, username);

        RestClient.getService().addNotice(request).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                ToastUtil.showToast("邀请成功");
            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });
    }


    public static void startActivity(Context context, int teamId, String teamName) {
        Intent intent = new Intent(context, InviteFriendListActivity.class);
        intent.putExtra("team_id", teamId);
        intent.putExtra("team_name", teamName);
        context.startActivity(intent);
    }
}

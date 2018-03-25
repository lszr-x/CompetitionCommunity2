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
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.message.adapters.NoticeItemListener;
import cn.abtion.neuqercc.message.adapters.NoticeRecAdapter;
import cn.abtion.neuqercc.message.models.NoticeModel;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.Utility;
import retrofit2.Call;
import retrofit2.Response;

import static cn.abtion.neuqercc.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午12:52
 * fhyPayaso@qq.com
 */
public class NoticeListFragment extends BaseFragment implements NoticeItemListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.rec_notices)
    RecyclerView mRecyclerNotices;
    @BindView(R.id.ly_refresh_notices)
    SwipeRefreshLayout mRefreshNotices;
    Unbinder unbinder;


    private List<NoticeModel> mNoticeModelList;
    private NoticeRecAdapter mRecAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void initVariable() {

        mNoticeModelList = new ArrayList<>();
    }

    @Override
    protected void initView() {

        initSwipeRefresh();
        initRecycleView();
    }

    @Override
    protected void loadData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initSwipeRefresh() {
        mRefreshNotices.setRefreshing(true);
        mRefreshNotices.setOnRefreshListener(this);
        mRefreshNotices.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        onRefresh();
    }

    private void initRecycleView() {
        mRecAdapter = new NoticeRecAdapter(getContext(), mNoticeModelList);
        mRecAdapter.setNoticeItemListener(this);
        mRecyclerNotices.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerNotices.setAdapter(mRecAdapter);
    }


    @Override
    public void onRefuseBtnClick(int pos) {

    }

    @Override
    public void onAgreeBtnClick(int pos) {

        NoticeModel model = mNoticeModelList.get(pos);
        if (model.getNoticeType() == 0 || model.getNoticeType() == 3) {

        } else {
            onDeleteNotice(model.getNoticeId(),pos);
        }
    }

    @Override
    public void onRefresh() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Utility.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadNoticeList();
                    }
                });
            }
        }, Config.SWIPE_REFRESH_DELAY_TIME);

    }


    /**
     * 加载通知列表
     */
    private void loadNoticeList() {

        RestClient.getService().loadNotice(LoginActivity.phoneNumber).enqueue(new DataCallback<APIResponse<List<NoticeModel>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<List<NoticeModel>>> call, Response<APIResponse<List<NoticeModel>>> response) {

                List<NoticeModel> noticeResponseList = response.body().getData();
                if (noticeResponseList != null && noticeResponseList.size() != 0) {
                    mNoticeModelList.clear();
                    mNoticeModelList.addAll(noticeResponseList);
                }
                mRecAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDataFailure(Call<APIResponse<List<NoticeModel>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                mRefreshNotices.setRefreshing(false);
            }
        });
    }


    private void onAgreeNotice(NoticeModel model) {


    }


    private void onRefuseNotice() {

    }


    private void onAddToTeam() {

    }


    private void onDeleteNotice(int noticeId, final int pos) {

        RestClient.getService().deleteNotice(noticeId).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                mRecAdapter.removeItem(pos);
            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });
    }
}

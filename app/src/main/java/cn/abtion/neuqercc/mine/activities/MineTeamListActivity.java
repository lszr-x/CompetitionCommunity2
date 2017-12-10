package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.mine.adapters.MineTeamListAdapter;
import cn.abtion.neuqercc.mine.models.GoodAtRequest;
import cn.abtion.neuqercc.mine.models.MineTeamListModel;
import cn.abtion.neuqercc.mine.models.MineTeamListRequest;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author fhyPayaso
 * @since 2017/11/21 12:50
 * email fhyPayaso@qq.com
 */

public class MineTeamListActivity extends ToolBarActivity {

    @BindView(R.id.rec_mine_team)
    RecyclerView recMineTeam;

    private List<MineTeamListModel> mineTeamListModelList = new ArrayList<MineTeamListModel>();
    private List<MineTeamListRequest> mineTeamListRequestList = new ArrayList<MineTeamListRequest>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_team_list;
    }

    @Override
    protected void initVariable() {

        setActivityTitle(getString(R.string.title_mine_team));
        initContestRecyclerView();

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {

    }

    public void initMineTeamList() {

        RestClient.getService().mineTeamList().enqueue(new DataCallback<APIResponse<List<MineTeamListRequest>>>() {

            @Override
            public void onDataResponse(Call<APIResponse<List<MineTeamListRequest>>> call, Response<APIResponse<List<MineTeamListRequest>>> response) {

                mineTeamListRequestList = response.body().getData();
                initContestRecyclerView();

            }

            @Override
            public void onDataFailure(Call<APIResponse<List<MineTeamListRequest>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }

        });


    }



    public void initContestRecyclerView() {


        recMineTeam.setNestedScrollingEnabled(false);

        for (int i = 0; i < mineTeamListRequestList.size(); i++) {

            MineTeamListRequest mineTeamListRequest = mineTeamListRequestList.get(i);
            mineTeamListModelList.add(new MineTeamListModel(mineTeamListRequest.getTeamId(),
                    mineTeamListRequest.getTeamName(),
                    mineTeamListRequest.getCompetitionDesc(),
                    mineTeamListRequest.getTeamMemberNum()));
        }

        MineTeamListAdapter mineTeamListAdapter =new MineTeamListAdapter(MineTeamListActivity.this,mineTeamListModelList);
        recMineTeam.setAdapter(mineTeamListAdapter);
        recMineTeam.setLayoutManager(new LinearLayoutManager(MineTeamListActivity.this, LinearLayoutManager.VERTICAL, false));

        //点击事件
        mineTeamListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<MineTeamListModel>() {

            @Override
            public void onItemClicked(MineTeamListModel mineTeamListModel, BaseRecyclerViewAdapter.ViewHolder viewHolder) {

                Intent intent = new Intent(MineTeamListActivity.this,MineTeamIfromationActivity.class);
                intent.putExtra("teamId",mineTeamListModelList.get(viewHolder.getAdapterPosition()).getId());
                startActivity(intent);

            }
        });
    }

}

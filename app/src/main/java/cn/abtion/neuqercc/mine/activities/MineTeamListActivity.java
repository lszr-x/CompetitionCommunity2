package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.mine.adapters.MineTeamListAdapter;
import cn.abtion.neuqercc.mine.models.MineTeamListModel;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2017/11/21 12:50
 * email fhyPayaso@qq.com
 */

public class MineTeamListActivity extends ToolBarActivity {

    @BindView(R.id.rec_mine_team)
    RecyclerView recMineTeam;

    private ArrayList<MineTeamListModel> mineTeamListModels;

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


    public void initContestRecyclerView() {


        recMineTeam.setNestedScrollingEnabled(false);
        mineTeamListModels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            //test
            mineTeamListModels.add(new MineTeamListModel("无敌最强队","5","组长","数学建模"));
        }

        MineTeamListAdapter mineTeamListAdapter =new MineTeamListAdapter(MineTeamListActivity.this,mineTeamListModels);
        recMineTeam.setAdapter(mineTeamListAdapter);
        recMineTeam.setLayoutManager(new LinearLayoutManager(MineTeamListActivity.this, LinearLayoutManager.VERTICAL, false));

        //点击事件
        mineTeamListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<MineTeamListModel>() {

            @Override
            public void onItemClicked(MineTeamListModel mineTeamListModel, BaseRecyclerViewAdapter.ViewHolder viewHolder) {

                Intent intent = new Intent(MineTeamListActivity.this,MineTeamIfromationActivity.class);
                startActivity(intent);

            }
        });
    }

}

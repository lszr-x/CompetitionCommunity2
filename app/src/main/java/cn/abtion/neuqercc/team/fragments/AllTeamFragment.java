package cn.abtion.neuqercc.team.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.home.activities.CompetitionActivity;
import cn.abtion.neuqercc.team.activities.TeamInformationActivity;
import cn.abtion.neuqercc.team.adapters.AllTeamListAdapter;
import cn.abtion.neuqercc.team.models.AllTeamListModel;

/**
 * @author lszr
 * @since 2017/11/14 下午8:42
 * email wsyglszr@gmail.com
 */

public class AllTeamFragment extends BaseFragment {

    @BindView(R.id.recyler_all_team)
    RecyclerView recylerAllTeam;
    private ArrayList<AllTeamListModel> allTeamListModels;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_team;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        allTeamListModels=new ArrayList<>();
        for (int i=0;i<20;i++){
            allTeamListModels.add(new AllTeamListModel());
        }
        AllTeamListAdapter allTeamListAdapter=new AllTeamListAdapter(getContext(),allTeamListModels);
        recylerAllTeam.setAdapter(allTeamListAdapter);
        recylerAllTeam.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //点击事件

        allTeamListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<AllTeamListModel>() {
            @Override
            public void onItemClicked(AllTeamListModel allTeamListModel, BaseRecyclerViewAdapter.ViewHolder holder) {
                Intent intent=new Intent(getContext(),TeamInformationActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void loadData() {

    }

}

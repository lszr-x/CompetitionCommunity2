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
import cn.abtion.neuqercc.team.activities.TeamInformationActivity;
import cn.abtion.neuqercc.team.adapters.AllTeamListAdapter;
import cn.abtion.neuqercc.team.adapters.RecommendTeamListAdapter;
import cn.abtion.neuqercc.team.models.AllTeamListModel;
import cn.abtion.neuqercc.team.models.RecommendTeamListModel;

/**
 * @author lszr
 * @since 2017/11/14 下午8:47
 * email wsyglszr@gmail.com
 */

public class RecommendTeamFragment extends BaseFragment {
    @BindView(R.id.recyler_recommend_team)
    RecyclerView recylerRecommendTeam;
    private ArrayList<RecommendTeamListModel> recommendTeamListModels;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend_team;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        recommendTeamListModels=new ArrayList<>();
        for (int i=0;i<20;i++){
            recommendTeamListModels.add(new RecommendTeamListModel());
        }
        RecommendTeamListAdapter recommendTeamListAdapter=new RecommendTeamListAdapter(getContext(),recommendTeamListModels);
        recylerRecommendTeam.setAdapter(recommendTeamListAdapter);
        recylerRecommendTeam.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //点击事件

        recommendTeamListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<RecommendTeamListModel>() {
            @Override
            public void onItemClicked(RecommendTeamListModel recommendTeamListModel, BaseRecyclerViewAdapter.ViewHolder holder) {
                Intent intent=new Intent(getContext(),TeamInformationActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void loadData() {

    }


}

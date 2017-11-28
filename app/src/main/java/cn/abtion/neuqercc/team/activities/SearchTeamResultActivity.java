package cn.abtion.neuqercc.team.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.team.adapters.AllTeamListAdapter;
import cn.abtion.neuqercc.team.adapters.SearchTeamResultAdapter;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.SearchResultTeamModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;

/**
 * @author lszr
 * @since 2017/11/28 下午7:21
 * email wsyglszr@gmail.com
 */

public class SearchTeamResultActivity extends ToolBarActivity {
    @BindView(R.id.recyler_search_team_result)
    RecyclerView recylerSearchTeamResult;

    private ArrayList<SearchResultTeamModel> searchResultTeamModels;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_team_result;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_search_result));
        initSearchResultRecylerView();

    }

    @Override
    protected void loadData() {

    }

    public void initSearchResultRecylerView(){
        searchResultTeamModels=new ArrayList<>();

        for (int i=0 ;i<20;i++){
            searchResultTeamModels.add(new SearchResultTeamModel());
        }

        SearchTeamResultAdapter searchTeamResultAdapter = new SearchTeamResultAdapter(this, searchResultTeamModels);

        recylerSearchTeamResult.setLayoutManager(new CustomLinearLayoutManager(this, CustomLinearLayoutManager.VERTICAL, false));
        recylerSearchTeamResult.setAdapter(searchTeamResultAdapter);

        //点击事件

        searchTeamResultAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<SearchResultTeamModel>() {
            @Override
            public void onItemClicked(SearchResultTeamModel searchResultTeamModel, BaseRecyclerViewAdapter.ViewHolder holder) {
                Intent intent=new Intent(SearchTeamResultActivity.this,TeamInformationActivity.class);
                startActivity(intent);
            }
        });
    }
}

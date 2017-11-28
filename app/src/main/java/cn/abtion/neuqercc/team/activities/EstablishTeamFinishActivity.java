package cn.abtion.neuqercc.team.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;

/**
 * @author lszr
 * @since 2017/11/22 下午9:40
 * email wsyglszr@gmail.com
 */

public class EstablishTeamFinishActivity extends ToolBarActivity {

    private ArrayList<TeamMemberListModel> teamMemberListModels;

    @BindView(R.id.recylerview_team_member)
    RecyclerView recylerviewTeamMember;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_establish_team_finish;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        initTitle();

        recylerviewTeamMember.setNestedScrollingEnabled(false);
        teamMemberListModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            teamMemberListModels.add(new TeamMemberListModel());
        }
        TeamMemberListAdapter teamMemberListAdapter = new TeamMemberListAdapter(this, teamMemberListModels);

        recylerviewTeamMember.setLayoutManager(new CustomLinearLayoutManager(this, CustomLinearLayoutManager.VERTICAL, false));
        recylerviewTeamMember.setAdapter(teamMemberListAdapter);

    }

    @Override
    protected void loadData() {

    }






    @OnClick(R.id.btn_invite_friend)
    public void onInviteFriendViewClicked() {

    }

    public void initTitle(){
        setActivityTitle(getString(R.string.title_team_information));
        setTextOver(getString(R.string.title_confirm));
        TextView textView=(TextView)getToolbar().findViewById(R.id.txt_toolbar_over);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EstablishTeamFinishActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

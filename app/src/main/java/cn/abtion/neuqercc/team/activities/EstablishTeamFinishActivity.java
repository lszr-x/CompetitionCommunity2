package cn.abtion.neuqercc.team.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;

/**
 * @author lszr
 * @since 2017/11/22 下午9:40
 * email wsyglszr@gmail.com
 */

public class EstablishTeamFinishActivity extends NoBarActivity {

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


    @OnClick(R.id.img_return)
    public void onReturnViewClicked() {
        finish();
    }


    @OnClick(R.id.txt_finish_establish)
    public void onFinishEstablishViewClicked() {
        finish();
    }



    @OnClick(R.id.btn_invite_friend)
    public void onInviteFriendViewClicked() {

    }
}

package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.mine.models.MineTeamInformationRequest;
import cn.abtion.neuqercc.mine.models.TeamMemberRequest;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;
import retrofit2.Call;
import retrofit2.Response;

public class MineTeamIfromationActivity extends ToolBarActivity {


    @BindView(R.id.txt_mine_team_name)
    TextView txtTeamName;
    @BindView(R.id.txt_contest_name)
    TextView txtContestName;
    @BindView(R.id.txt_team_declaration)
    TextView txtTeamDeclaration;
    private List<TeamMemberListModel> teamMemberListModels = new ArrayList<TeamMemberListModel>();
    private List<TeamMemberRequest> teamMemberRequestList = new ArrayList<TeamMemberRequest>();

    public static int teamId;

    @BindView(R.id.recylerview_team_member)
    RecyclerView recylerviewTeamMember;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_team_ifromation;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        initTitle();

    }


    @Override
    protected void loadData() {

        loadTeamInformation();
        loadTeamMemberInformation();
    }



    protected void initTitle() {

        setActivityTitle(getString(R.string.title_team_information));
        setTextOver(getString(R.string.txt_edit));


        TextView txtTitleEdit = (TextView) getToolbar().findViewById(R.id.txt_toolbar_over);

        txtTitleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineTeamIfromationActivity.this, UpdateTeamInformationActivity.class);
                intent.putExtra("teamId",teamId);
                intent.putExtra("teamName",txtTeamName.getText().toString());
                intent.putExtra("contestName",txtContestName.getText().toString());
                intent.putExtra("teamDeclaration",txtTeamDeclaration.getText().toString());

                startActivity(intent);
            }
        });
    }


    /**
     * 加载队伍信息
     */
    protected void loadTeamInformation() {

        Intent intent = getIntent();
        teamId = intent.getIntExtra("teamId", -1);

        RestClient.getService().mineTeamInformation().enqueue(new DataCallback<APIResponse<MineTeamInformationRequest>>() {
            @Override
            public void onDataResponse(Call<APIResponse<MineTeamInformationRequest>> call, Response<APIResponse<MineTeamInformationRequest>> response) {

                MineTeamInformationRequest request = response.body().getData();
                txtTeamName.setText(request.getTeamName());
                txtContestName.setText(request.getCompetitionDesc());
                txtTeamDeclaration.setText(request.getDeclaration());

            }

            @Override
            public void onDataFailure(Call<APIResponse<MineTeamInformationRequest>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });


    }


    /**
     * 加载成员信息
     */
    protected void loadTeamMemberInformation() {


        RestClient.getService().mineTeamMember().enqueue(new DataCallback<APIResponse<List<TeamMemberRequest>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<List<TeamMemberRequest>>> call, Response<APIResponse<List<TeamMemberRequest>>> response) {

                teamMemberRequestList = response.body().getData();
                initRecyclerView();

            }

            @Override
            public void onDataFailure(Call<APIResponse<List<TeamMemberRequest>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });

    }


    protected void initRecyclerView() {


        recylerviewTeamMember.setNestedScrollingEnabled(false);
        for(int i = 0; i<teamMemberRequestList.size(); i++) {

            TeamMemberRequest request= teamMemberRequestList.get(i);
            teamMemberListModels.add(new TeamMemberListModel(request.getTeamPosition(),request.getName(),request.getGoodAt()));
        }

        TeamMemberListAdapter teamMemberListAdapter = new TeamMemberListAdapter(this, teamMemberListModels);
        recylerviewTeamMember.setLayoutManager(new CustomLinearLayoutManager(this, CustomLinearLayoutManager.VERTICAL, false));
        recylerviewTeamMember.setAdapter(teamMemberListAdapter);

    }


    @OnClick(R.id.btn_join_team)
    public void onButtonJoinTeamClicked() {

    }

}

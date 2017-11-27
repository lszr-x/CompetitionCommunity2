package cn.abtion.neuqercc.team.activities;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;

/**
 * @author lszr
 * @since 2017/11/15 下午5:34
 * email wsyglszr@gmail.com
 */

public class TeamInformationActivity extends ToolBarActivity {



    private ArrayList<TeamMemberListModel> teamMemberListModels;

    @BindView(R.id.recylerview_team_member)
    RecyclerView recylerviewTeamMember;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_information;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        this.setActivityTitle(getString(R.string.title_team_information));




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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @OnClick(R.id.btn_join_team)
    public void onButtonJoinTeamClicked() {


        showDialog();



    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.item_dialog_join_team);
        View view=View.inflate(this,R.layout.item_dialog_join_team,null);


        TextView textView=(TextView)view.findViewById(R.id.qvxiao);
        textView.setText("取消");



        builder.show();

        //AlertDialog alertDialog =new AlertDialog.Builder(TeamInformationActivity.this).create();
//        alertDialog.show();
//        Window window =alertDialog.getWindow();
//        window.setContentView(R.layout.item_dialog_join_team);



    }


}

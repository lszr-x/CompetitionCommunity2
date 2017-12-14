package cn.abtion.neuqercc.team.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.mine.models.TeamMemberResponse;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;

/**
 * @author lszr
 * @since 2017/11/15 下午5:34
 * email wsyglszr@gmail.com
 */

public class TeamInformationActivity extends ToolBarActivity {



    private ArrayList<TeamMemberResponse> teamMemberListModels;

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
            teamMemberListModels.add(new TeamMemberResponse("af",12,"afa","afa"));
        }
        TeamMemberListAdapter teamMemberListAdapter = new TeamMemberListAdapter(this, teamMemberListModels);

        recylerviewTeamMember.setLayoutManager(new CustomLinearLayoutManager(this, CustomLinearLayoutManager.VERTICAL, false));
        recylerviewTeamMember.setAdapter(teamMemberListAdapter);

    }

    @Override
    protected void loadData() {

    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.btn_join_team)
    public void onButtonJoinTeamClicked() {


        View view = View.inflate(TeamInformationActivity.this, R.layout.item_dialog_join_team, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TeamInformationActivity.this);
        builder.setCancelable(true);

        TextView txtDialogTitle = (TextView) view.findViewById(R.id.txt_dialog_title);
        TextView txtDialogContent = (TextView) view.findViewById(R.id.txt_dialog_content);
        TextView txtLeftButton = (TextView) view.findViewById(R.id.txt_dialog_cancel);
        TextView txtRightButton = (TextView) view.findViewById(R.id.txt_dialog_confirm);

        //  设置Dialog内部文字
        txtDialogTitle.setText(getString(R.string.txt_if_want_join_team));
        txtDialogContent.setText(getString(R.string.txt_dialog_content));
        txtLeftButton.setText(getString(R.string.txt_cancel));
        txtRightButton.setText(getString(R.string.txt_dialog_confirm));


        //设置点击事件

        txtLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        builder.setView(view);


        builder.show();
    }
}

package cn.abtion.neuqercc.mine.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.team.activities.EstablishTeamActivity;
import cn.abtion.neuqercc.team.activities.EstablishTeamFinishActivity;


/**
 * @author fhyPayaso
 * @since 2017/11/27 21:30
 * email fhyPayaso@qq.com
 */


public class UpdateTeamInformationActivity extends ToolBarActivity {





    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_team_information;
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

    }


    protected  void initTitle() {

        setActivityTitle(getString(R.string.title_edit_team_information));
        setTextOver(getString(R.string.title_over));


        TextView txtTitleOver=(TextView)getToolbar().findViewById(R.id.txt_toolbar_over);

        txtTitleOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

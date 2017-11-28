package cn.abtion.neuqercc.team.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;

/**
 * @author lszr
 * @since 2017/11/20 下午7:16
 * email wsyglszr@gmail.com
 */

public class EstablishTeamActivity extends ToolBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_establish_team;
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




    public void initTitle(){

        setActivityTitle(getString(R.string.title_establish_team));
        setTextOver(getString(R.string.title_over));
        TextView textView=(TextView)getToolbar().findViewById(R.id.txt_toolbar_over);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EstablishTeamActivity.this, EstablishTeamFinishActivity.class);
                startActivity(intent);
            }
        });

    }
}

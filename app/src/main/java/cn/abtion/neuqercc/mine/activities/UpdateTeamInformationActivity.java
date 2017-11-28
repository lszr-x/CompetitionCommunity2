package cn.abtion.neuqercc.mine.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.utils.ToastUtil;


/**
 * @author fhyPayaso
 * @since 2017/11/27 21:30
 * email fhyPayaso@qq.com
 */


public class UpdateTeamInformationActivity extends ToolBarActivity {


    @BindView(R.id.edit_team_name)
    EditText editTeamName;
    @BindView(R.id.edit_want_join)
    EditText editWantJoin;

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


    protected void initTitle() {

        setActivityTitle(getString(R.string.title_edit_team_information));
        setTextOver(getString(R.string.title_over));


        TextView txtTitleOver = (TextView) getToolbar().findViewById(R.id.txt_toolbar_over);

        txtTitleOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isDataTrue()) {
                    finish();
                } else {
                    ToastUtil.showToast(getString(R.string.toast_lack_information));
                }


            }
        });
    }



    private boolean isDataTrue() {

        boolean flag=true;

        if(editTeamName.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = false;
        } else if(editWantJoin.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = false;
        }
        return flag;
    }


}

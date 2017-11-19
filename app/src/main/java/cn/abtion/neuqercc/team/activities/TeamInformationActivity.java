package cn.abtion.neuqercc.team.activities;

import android.view.View;

import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.BaseActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author lszr
 * @since 2017/11/15 下午5:34
 * email wsyglszr@gmail.com
 */

public class TeamInformationActivity extends ToolBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_information;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        this.setActivityTitle("队伍信息");

    }

    @Override
    protected void loadData() {

    }
    @OnClick(R.id.btn_join_team)
    public void onViewClicked() {
        //      弹出提示

        ToastUtil.showToast("已提交加入队伍申请");


        //      发送加入队伍请求


    }



}

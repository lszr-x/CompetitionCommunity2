package cn.abtion.neuqercc.team.activities;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;

/**
 * @author lszr
 * @since 2017/11/20 下午7:16
 * email wsyglszr@gmail.com
 */

public class EstablishTeamActivity extends NoBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_establish_team;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.txt_finish_establish)
    public void onFinishEstablishViewClicked() {
        Intent intent = new Intent(EstablishTeamActivity.this, EstablishTeamFinishActivity.class);
        startActivity(intent);

    }



    @OnClick(R.id.img_return)
    public void onReturnViewClicked() {
        finish();
    }
}

package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;

/**
 * @author fhyPayaso
 * @since 2017/11/20 17:52
 * email fhyPayaso@qq.com
 */

public class MineUpdatePasswordActivity extends ToolBarActivity {

    @BindView(R.id.btn_mine_confirm_changes)
    Button btnMineConfirmChanges;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_update_password;
    }

    @Override
    protected void initVariable() {
        setActivityTitle(getString(R.string.txt_update_password));

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }



    @OnClick(R.id.btn_mine_confirm_changes)
    public void onBtnConfirmClicked() {

        Intent intent = new Intent(MineUpdatePasswordActivity.this,SettingActivity.class);
        startActivity(intent);
        finish();

    }
}

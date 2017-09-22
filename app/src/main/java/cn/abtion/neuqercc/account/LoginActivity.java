package cn.abtion.neuqercc.account;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.MainActivity;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;

/**
 * @author abtion.
 * @since 17/9/22 17:59.
 * email caiheng@hrsoft.net
 */

public class LoginActivity extends NoBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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

    @OnClick(R.id.btn_register)
    public void onBtnRegisterClicked() {
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

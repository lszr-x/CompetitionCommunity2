package cn.abtion.neuqercc.main;


import android.content.Intent;
import android.os.Handler;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.NoBarActivity;

/**
 * @author FanHongyu.
 * @since 18/3/19 20:43.
 * email fanhongyu@hrsoft.net.
 */

public class SplashActivity extends NoBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, 1000);


    }

    @Override
    protected void loadData() {

    }
}

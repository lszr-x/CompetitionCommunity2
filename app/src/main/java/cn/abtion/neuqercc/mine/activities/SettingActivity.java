package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.NEUQerCCApplication;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.main.MainActivity;

/**
 * @author fhyPayaso
 * @since 2017/11/17 00:51
 * email fhyPayaso@qq.com
 */

public class SettingActivity extends ToolBarActivity {

    @BindView(R.id.txt_mine_update_password)
    TextView txtUpdatePassword;
    @BindView(R.id.txt_mine_feedback)
    TextView txtFeedback;
    @BindView(R.id.txt_mine_about_us)
    TextView txtAboutUs;
    @BindView(R.id.btn_mine_sign_out)
    Button btnSignOut;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_setting;
    }


    @Override
    protected void initVariable() {

        setActivityTitle(getString(R.string.title_setting));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    /**
     * 修改密码
     */
    @OnClick(R.id.txt_mine_update_password)
    public void onTxtUpdatePasswordClicked() {

        Intent intent =new Intent(SettingActivity.this,MineUpdatePasswordActivity.class);
        startActivity(intent);
    }

    /**
     * 意见反馈点击
     */
    @OnClick(R.id.txt_mine_feedback)
    public void onTxtFeedbackClicked() {

        Intent intent =new Intent(SettingActivity.this,FeedbackActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.txt_mine_about_us)
    public void onTxtAboutUsClicked() {


        Intent intent =new Intent(SettingActivity.this,AboutUsActivity.class);
        startActivity(intent);


    }

    @OnClick(R.id.btn_mine_sign_out)
    public void onBtnSignOutClicked() {



        Intent intent =new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(intent);

        NEUQerCCApplication neuQerCCApplication=new NEUQerCCApplication();
        neuQerCCApplication.exitAccount();

    }

}

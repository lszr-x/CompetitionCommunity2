package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2017/11/20 17:52
 * email fhyPayaso@qq.com
 */

public class MineUpdatePasswordActivity extends ToolBarActivity {


    public static final int FLAG_CORRECT = 0;
    public static final int FLAG_LACK_ERROR = 1;
    public static final int FLAG_PASSWORD_ERROR = 2;
    public static final int FLAG_PASSWORD_DIFFERENT = 3;


    @BindView(R.id.btn_mine_confirm_changes)
    Button btnMineConfirmChanges;
    @BindView(R.id.edit_input_old_password)
    EditText editOldPassword;
    @BindView(R.id.edit_input_new_password)
    EditText editNewPassword;
    @BindView(R.id.edit_input_repeat_password)
    EditText editRepeatPassword;

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

        switch (isDataTrue()) {

            case FLAG_LACK_ERROR:
                ToastUtil.showToast(getString(R.string.toast_lack_information));
                break;
            case FLAG_PASSWORD_ERROR:
                ToastUtil.showToast(getString(R.string.toast_password_error));
                break;
            case FLAG_PASSWORD_DIFFERENT:
                ToastUtil.showToast(getString(R.string.error_passwords_inconsistent));
                break;
            default:

                Intent intent = new Intent(MineUpdatePasswordActivity.this, SettingActivity.class);
                startActivity(intent);
                ToastUtil.showToast(getString(R.string.toast_update_successful));
                finish();
                break;

        }
    }


    private int isDataTrue() {

        int flag=FLAG_CORRECT;

        if(editOldPassword.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag =FLAG_LACK_ERROR;
        } else if (editNewPassword.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = FLAG_LACK_ERROR;
        } else if(editRepeatPassword.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = FLAG_LACK_ERROR;
        } else if (editOldPassword.getText().toString().trim().equals(LoginActivity.password)) {
            flag = FLAG_PASSWORD_ERROR;
        } else if (!editNewPassword.getText().toString().trim().equals(editRepeatPassword.getText().toString().trim())) {
            flag = FLAG_PASSWORD_DIFFERENT;
        }
        return flag;
    }


}

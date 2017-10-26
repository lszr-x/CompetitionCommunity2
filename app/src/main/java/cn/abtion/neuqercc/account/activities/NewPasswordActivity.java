package cn.abtion.neuqercc.account.activities;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.models.NewPasswordRequest;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Response;


/**
 * @author lszr
 * @since 2017/10/16 下午9:29
 * email wsyglszr@gmail.com
 */

public class NewPasswordActivity extends NoBarActivity {

    @BindView(R.id.edit_password)
    TextInputEditText editPassword;
    @BindView(R.id.edit_repeat_password)
    TextInputEditText editRepeatPassword;

    private NewPasswordRequest newPasswordRequest;

    private int PASSWORD_LOW_LIMIT=6;
    private int PASSWORD_HIGH_LIMIT=16;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_password;
    }

    @Override
    protected void initVariable() {
        newPasswordRequest = new NewPasswordRequest();
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(300);
            getWindow().setEnterTransition(explode);

            Slide slide = new Slide();
            slide.setDuration(300);
            getWindow().setExitTransition(slide);
        }
    }

    @Override
    protected void loadData() {

    }


    /**
     * 点击事件
     */
    @OnClick(R.id.btn_over)
    public void onBtnOverClicked() {

        newPasswordRequest.setPhone(getIntent().getStringExtra("phoneNumber"));
        newPasswordRequest.setPassword(editPassword.getText().toString().trim());

        if (isDataTrue()) {
            newPassword();
        }
    }

    @OnClick(R.id.btn_return)
    public void onBtnReturnClicked() {
        Intent intent = new Intent(NewPasswordActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 注册按钮相关方法
     */
    public void newPassword() {

        //弹出progressDialog
        progressDialog.setMessage("请稍候");
        progressDialog.show();

        //网络请求

        RestClient.getService().newPassword(newPasswordRequest).enqueue(new DataCallback<APIResponse>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                ToastUtil.showToast("修改成功");

                Intent intent = new Intent(NewPasswordActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }

            //请求失败时回调
            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {

            }

            //无论成功或者失败时都回调，用于dismissDialog或隐藏其他控件
            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });
    }


    /**
     * 用于TextInputEditText控件显示错误信息
     *
     * @param textInputEditText 控件对象
     * @param error             错误信息
     */
    private void showError(TextInputEditText textInputEditText, String error) {
        textInputEditText.setError(error);
        textInputEditText.setFocusable(true);
        textInputEditText.setFocusableInTouchMode(true);
        textInputEditText.requestFocus();
    }

    /**
     * 验证用户输入是否正确
     *
     * @return 正确为true
     */
    private boolean isDataTrue() {
        boolean flag = true;
        if (editPassword.getText().toString().trim().length() < PASSWORD_LOW_LIMIT) {
            showError(editPassword, "密码不得少于6位");
            flag = false;
        } else if (editPassword.getText().toString().trim().length() > PASSWORD_HIGH_LIMIT) {
            showError(editPassword, "密码不得多于16位");
            flag = false;
        }  else if (editRepeatPassword.getText().toString().trim().length() == 0) {
            showError(editPassword, "重复密码不得为空");
            flag = false;
        } else if (!editRepeatPassword.getText().toString().trim().equals(editPassword.getText().toString().trim())) {
            showError(editRepeatPassword, "两次输入密码需要一致");
            flag = false;
        }
        return flag;
    }


}

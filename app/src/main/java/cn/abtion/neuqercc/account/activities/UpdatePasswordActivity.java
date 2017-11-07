package cn.abtion.neuqercc.account.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.transition.Explode;
import android.transition.Slide;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.models.NewPasswordRequest;
import cn.abtion.neuqercc.account.models.SmsRequest;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.RegexUtil;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Response;


/**
 * @author lszr
 * @since 2017/10/16 下午9:29
 * email wsyglszr@gmail.com
 */

public class UpdatePasswordActivity extends NoBarActivity {

    @BindView(R.id.edit_password)
    TextInputEditText editPassword;
    @BindView(R.id.edit_repeat_password)
    TextInputEditText editRepeatPassword;

    @BindView(R.id.edit_phone)
    TextInputEditText editPhone;
    @BindView(R.id.edit_captcha)
    TextInputEditText editCaptcha;
    @BindView(R.id.btn_get_verify_code)
    Button btnGetVerifyCode;

    private NewPasswordRequest newPasswordRequest;

    private SmsRequest smsRequest;
    private String verifyCode;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initVariable() {
        smsRequest = new SmsRequest();
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
     * 获取验证码按钮点击事件
     */
    @OnClick(R.id.btn_get_verify_code)
    public void onBtnGetVerifyCodeClicked() {

        smsRequest.setPhone(editPhone.getText().toString().trim());
        timer.start();

        if (isPhoneTrue()) {
            getVerifyCode();
        }

    }


    /**
     * 短信验证码
     */
    public void getVerifyCode() {

        //网络请求

        RestClient.getService().captch(smsRequest).enqueue(new DataCallback<APIResponse>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                verifyCode = response.body().getData().toString().trim();
                ToastUtil.showToast("发送成功,请注意查收验证码");

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
     * 延时对象
     */
    CountDownTimer timer = new CountDownTimer(6000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            btnGetVerifyCode.setEnabled(false);
            btnGetVerifyCode.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {

            btnGetVerifyCode.setEnabled(true);
            btnGetVerifyCode.setText("获取验证码");
        }
    };


    /**
     * 验证手机号正则
     *
     * @return
     */
    private boolean isPhoneTrue() {
        boolean flag = true;
        if (editPhone.getText().toString().trim().length() == 0) {
            showError(editPhone, "手机号不得为空");
            flag = false;
        } else if (RegexUtil.checkMobile(editPhone.getText().toString().trim())) {
            showError(editPhone, "手机号不合法");
            flag = false;
        }
        return flag;
    }


    /**
     * 完成改密码点击事件
     */
    @OnClick(R.id.btn_over)
    public void onBtnOverClicked() {

        newPasswordRequest.setPhone(getIntent().getStringExtra("phoneNumber"));
        newPasswordRequest.setPassword(editPassword.getText().toString().trim());

        if (isDataTrue()) {
            newPassword();
        }
    }


    /**
     * 改密码按钮相关方法
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

                Intent intent = new Intent(UpdatePasswordActivity.this, MainActivity.class);
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
     * 返回按钮点击事件
     */
    @OnClick(R.id.btn_return)
    public void onBtnReturnClicked() {
        Intent intent = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
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
        if (editPhone.getText().toString().trim().length() == 0) {
            showError(editPhone, "手机号不得为空");
            flag = false;
        } else if (RegexUtil.checkMobile(editPhone.getText().toString().trim())) {
            showError(editPhone, "手机号不合法");
            flag = false;
        } else if (editCaptcha.getText().toString().trim().length() == 0) {
            showError(editCaptcha, "验证码不得为空");
            flag = false;
        } else if (!editCaptcha.getText().toString().trim().equals(verifyCode)) {
            showError(editCaptcha, "验证码不正确");
            flag = false;
        } else if (editPassword.getText().toString().trim().length() < Config.PASSWORD_MIN_LIMIT) {
            showError(editPassword, "密码不得少于6位");
            flag = false;
        } else if (editPassword.getText().toString().trim().length() > Config.PASSWORD_MAX_LIMIT) {
            showError(editPassword, "密码不得多于16位");
            flag = false;
        } else if (!editRepeatPassword.getText().toString().trim().equals(editPassword.getText().toString().trim())) {
            showError(editRepeatPassword, "两次输入密码不一致");
            flag = false;
        }
        return flag;
    }

}

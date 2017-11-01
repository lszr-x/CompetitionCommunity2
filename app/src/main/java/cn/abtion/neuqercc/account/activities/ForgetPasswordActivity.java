package cn.abtion.neuqercc.account.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.models.SmsRequest;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
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

public class ForgetPasswordActivity extends NoBarActivity {


    @BindView(R.id.edit_phone)
    TextInputEditText editPhone;
    @BindView(R.id.edit_captcha)
    TextInputEditText editCaptcha;
    @BindView(R.id.btn_get_verify_code)
    Button btnGetVerifyCode;

    private SmsRequest smsRequest;
    private String verifyCode;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initVariable() {
        smsRequest = new SmsRequest();
    }

    @Override
    protected void initView() {

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
     * 下一步按钮点击事件
     */

    @OnClick(R.id.btn_next)
    public void onBtnNextClicked() {
        smsRequest.setCaptcah(editCaptcha.getText().toString().trim());
        smsRequest.setPhone(editPhone.getText().toString().trim());

        if (isDataTrue()) {
            ToastUtil.showToast("手机号验证成功，请重新输入密码");

            Intent intent = new Intent(ForgetPasswordActivity.this, UpdatePasswordActivity.class);
            intent.putExtra("phoneNumber",editPhone.getText().toString().trim());
            startActivity(intent);
            finish();
        }
    }

    /**
     * 返回按钮点击事件
     */
    @OnClick(R.id.btn_return)
    public void onBtnReturnClicked() {
        Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
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
            showError(editPhone, "手机号为11位");
            flag = false;
        } else if (editCaptcha.getText().toString().trim().length() == 0) {
            showError(editCaptcha, "验证码不得为空");
            flag = false;
        } else if (!editCaptcha.getText().toString().trim().equals(verifyCode)) {
            showError(editCaptcha, "验证码不正确");
            flag = false;
        }
        return flag;
    }


}

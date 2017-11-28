package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2017/11/20 17:10
 * email fhyPayaso@qq.com
 */

public class FeedbackActivity extends ToolBarActivity {


    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.edit_feedback)
    EditText editFeedback;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_feedback;
    }

    @Override
    protected void initVariable() {

        setActivityTitle(getString(R.string.txt_feedback));

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_submit)
    public void onBtnSubmitClicked() {

        if (editFeedback.getText().toString().trim().equals(Config.EMPTY_FIELD)) {

            ToastUtil.showToast(getString(R.string.toast_feedback_empty));

        } else {

            Intent intent = new Intent(FeedbackActivity.this, SettingActivity.class);
            startActivity(intent);
            ToastUtil.showToast(getString(R.string.toast_commit_successful));
            finish();
        }
    }

}

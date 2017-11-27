package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2017/11/20 17:10
 * email fhyPayaso@qq.com
 */

public class FeedbackActivity extends ToolBarActivity {


    @BindView(R.id.btn_submit)
    Button btnSubmit;

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

        Intent intent=new Intent(FeedbackActivity.this,SettingActivity.class);
        startActivity(intent);
        ToastUtil.showToast("提交成功");
        finish();

    }
}

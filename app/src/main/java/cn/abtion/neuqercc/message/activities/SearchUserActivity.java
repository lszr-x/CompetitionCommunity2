package cn.abtion.neuqercc.message.activities;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/1/23 on 下午1:24
 * fhyPayaso@qq.com
 */
public class SearchUserActivity extends NoBarActivity {

    @BindView(R.id.edit_search_team)
    EditText editSearchUser;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_user;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        searchUser();
    }

    @Override
    protected void loadData() {

    }

    public void searchUser() {


        SpannableString ss = new SpannableString("请输入查询的用户名");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        // 一定要进行转换,否则属性会消失
        editSearchUser.setHint(new SpannedString(ss));

        editSearchUser.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    String searchContent = editSearchUser.getText().toString();
                    if (searchContent.equals(Config.EMPTY_FIELD)) {
                        ToastUtil.showToast("请输入用户关键字");
                    } else {
                        Intent intent = new Intent(SearchUserActivity.this, SearchUseResActivity.class);
                        intent.putExtra("searchUserName", searchContent);
                        startActivity(intent);
                        finish();
                    }
                }
                return false;
            }
        });
    }

    public static void startActivity(Context context) {

        context.startActivity(new Intent(context, SearchUserActivity.class));
    }


    @OnClick(R.id.img_return)
    public void onImgReturnClicked() {
        finish();
    }

    @OnClick(R.id.img_search_team)
    public void onImgSearchTeamClicked() {

        String searchContent = editSearchUser.getText().toString();
        if (searchContent.equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("请输入用户关键字");
        } else {
            Intent intent = new Intent(SearchUserActivity.this, SearchUseResActivity.class);
            intent.putExtra("searchUserName", searchContent);
            startActivity(intent);
            finish();
        }
    }
}

package cn.abtion.neuqercc.message.activities;

import android.content.Context;
import android.content.Intent;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;

/**
 * @author fhyPayaso
 * @since 2018/1/23 on 下午1:22
 * fhyPayaso@qq.com
 */
public class ChatWindowActivity extends ToolBarActivity{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
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



    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,ChatWindowActivity.class));
    }


}

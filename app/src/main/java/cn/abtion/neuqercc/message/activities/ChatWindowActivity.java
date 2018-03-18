package cn.abtion.neuqercc.message.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.message.adapters.ChatWindowRecAdapter;
import cn.abtion.neuqercc.utils.Utility;
import io.reactivex.schedulers.Schedulers;

/**
 * @author fhyPayaso
 * @since 2018/1/23 on 下午1:22
 * fhyPayaso@qq.com
 */
public class ChatWindowActivity extends ToolBarActivity implements EMMessageListener{


    @BindView(R.id.rec_chat)
    RecyclerView recChat;
    @BindView(R.id.edit_chat_content)
    EditText editChatContent;
    @BindView(R.id.btn_send)
    TextView btnSend;

    private EMMessage emMessage;
    private EMConversation conversation;
    private ChatWindowRecAdapter mRecAdapter;
    private String chatId = "111111";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initVariable() {

        setActivityTitle(chatId);
    }

    @Override
    protected void initView() {

        initChatRec();
    }

    @Override
    protected void loadData() {


    }

    private void initChatRec(){


        //添加信息监听
        EMClient.getInstance().chatManager().addMessageListener(this);
        conversation = EMClient.getInstance().chatManager().getConversation(chatId,
                EMConversation.EMConversationType.Chat, true);

        mRecAdapter = new ChatWindowRecAdapter(ChatWindowActivity.this,conversation);
        recChat.setAdapter(mRecAdapter);
        recChat.setLayoutManager(new LinearLayoutManager(ChatWindowActivity.this,LinearLayoutManager.VERTICAL,false));
        refresh();
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ChatWindowActivity.class));
    }


    //刷新消息列表
    private void refresh() {
        if (mRecAdapter != null) {
            mRecAdapter.refresh();
        }
        scrollToBottom();
    }

    //让最新消息位于最底部
    private void scrollToBottom() {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recChat.scrollToPosition(mRecAdapter.getItemCount() - 1);
            }
        }, 200);
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {


        emMessage = EMMessage.createTxtSendMessage(editChatContent.getText().toString(), chatId);
        EMClient.getInstance().chatManager().sendMessage(emMessage);
        editChatContent.setText("");
        refresh();
    }



    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        refresh();
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }

}

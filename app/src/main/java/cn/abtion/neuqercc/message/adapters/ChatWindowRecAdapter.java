package cn.abtion.neuqercc.message.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author fhyPayaso
 * @since 2018/1/24 on 上午12:04
 * fhyPayaso@qq.com
 */

public class ChatWindowRecAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    private static final int MESSAGE_REFRESH_LIST = 0;
    private static final int MESSAGE_SELECT_LAST = 1;
    private static final int MESSAGE_SEEK_TO = 2;
    private static final int MESSAGE_TYPE_RECV_TXT = 0;
    private static final int MESSAGE_TYPE_SENT_TXT = 1;


    private List<EMMessage> messages;
    private LayoutInflater inflater;
    private Context context;
    private EMConversation conversation;
    private String mRecvAvatarUrl;
    private String mSendAvatarUrl;


    public ChatWindowRecAdapter(Context context, EMConversation conversation, String recvAvatarUrl, String
            sendAvatarUrl) {
        this.context = context;
        this.conversation = conversation;
        messages = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        mRecvAvatarUrl = recvAvatarUrl;
        mSendAvatarUrl = sendAvatarUrl;
    }

    @Override
    public BaseRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ItemHolder itemHolder = null;

        switch (viewType) {
            case MESSAGE_TYPE_RECV_TXT:
                view = inflater.inflate(R.layout.item_recv_message, parent, false);
                itemHolder = new ItemHolder(view);
                break;
            case MESSAGE_TYPE_SENT_TXT:
                view = inflater.inflate(R.layout.item_send_message, parent, false);
                itemHolder = new ItemHolder(view);
                break;
            default:
        }

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        EMMessage emMessage = messages.get(position);
        if (emMessage == null) {
            return -1;
        }
        switch (emMessage.getType()) {
            case TXT:
                return emMessage.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TXT : MESSAGE_TYPE_SENT_TXT;
            default:
        }
        return -1;
    }


    public EMMessage getItem(int position) {
        return messages.get(position);
    }

    class ItemHolder extends BaseRecyclerViewAdapter.ViewHolder<EMMessage> {

        @BindView(R.id.txt_message)
        TextView txtMessage;
        @BindView(R.id.img_chat_avatar)
        CircleImageView imgChatAvatar;


        public ItemHolder(View itemView) {
            super(itemView);
        }


        @Override
        protected void onBind(EMMessage emMessage) {
            EMTextMessageBody textMessageBody = (EMTextMessageBody) emMessage.getBody();
            txtMessage.setText(textMessageBody.getMessage());


            //渲染头像
            int type = getItemViewType();
            if (type == MESSAGE_TYPE_RECV_TXT && mRecvAvatarUrl != null) {
                Glide.with(context).load(mRecvAvatarUrl).into(imgChatAvatar);
            } else if (type == MESSAGE_TYPE_SENT_TXT && mSendAvatarUrl != null) {
                Glide.with(context).load(mSendAvatarUrl).into(imgChatAvatar);
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_REFRESH_LIST:
                    refreshList();
                    break;
                case MESSAGE_SELECT_LAST:
                    break;
                case MESSAGE_SEEK_TO:
                    break;
                default:
                    break;
            }
        }
    };

    private void refreshList() {


        //获取最新消息
        List<EMMessage> newMessage = conversation.getAllMessages();
        String msgId = newMessage.get(newMessage.size() - 1).getMsgId();
        int pageSize = 20;

        //从数据库里获取更多消息记录
        List<EMMessage> dbMessage = conversation.loadMoreMsgFromDB(msgId, pageSize);
        messages.clear();
        messages.addAll(dbMessage);
        messages.addAll(newMessage);

        conversation.markAllMessagesAsRead();
        notifyDataSetChanged();
    }

    public void refresh() {
        if (handler.hasMessages(MESSAGE_REFRESH_LIST)) {
            return;
        }
        Message message = handler.obtainMessage(MESSAGE_REFRESH_LIST);
        handler.sendMessage(message);
    }

}
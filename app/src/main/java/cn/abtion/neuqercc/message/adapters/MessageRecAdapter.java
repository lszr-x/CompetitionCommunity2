package cn.abtion.neuqercc.message.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.message.models.MessageModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午1:39
 * fhyPayaso@qq.com
 */
public class MessageRecAdapter extends BaseRecyclerViewAdapter<MessageModel> {



    private FriendItemListener mFriendItemListener;

    private List<MessageModel> mMessageModels;

    public MessageRecAdapter(Context context, List<MessageModel> messageModels) {
        super(context, messageModels);
        mMessageModels = messageModels;
    }

    public void setFriendItemListener(FriendItemListener friendItemListener) {
        mFriendItemListener = friendItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = inflater.inflate(R.layout.item_message, parent, false);
        return new ItemHolder(view);
    }


    class ItemHolder extends ViewHolder<MessageModel> implements View.OnClickListener {

        @BindView(R.id.message_name)
        TextView mMessageName;
        @BindView(R.id.message_last)
        TextView mMessageLast;
        @BindView(R.id.txt_last_time)
        TextView mTxtLastTime;
        @BindView(R.id.rl_message_main)
        RelativeLayout rlMessageMain;
        @BindView(R.id.txt_delete_message)
        TextView txtDeleteMessage;
        @BindView(R.id.message_avatar)
        CircleImageView messageAvatar;


        public ItemHolder(final View itemView) {
            super(itemView);

            rlMessageMain.setOnClickListener(this);
            txtDeleteMessage.setOnClickListener(this);
        }

        @Override
        protected void onBind(final MessageModel messageModel) {

            mMessageName.setText(messageModel.getUserName() == null ? "N/A" : messageModel.getUserName());
            mMessageLast.setText(messageModel.getLastMessage() == null ? "N/A" : messageModel.getLastMessage());
            mTxtLastTime.setText(messageModel.getLastTime() == null ? "N/A" : messageModel.getLastTime());

            if (messageModel.getImgRes() != null) {
                Glide.with(context).load(messageModel.getImgRes()).into(messageAvatar);
            }


        }
        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            switch (v.getId()) {
                case R.id.rl_message_main:
                    mFriendItemListener.onSendMessageClick(pos);
                    break;
                case R.id.txt_delete_message:
                    mFriendItemListener.onDeleteClick(pos);
                    break;
                default:
                    break;
            }
        }
    }

}
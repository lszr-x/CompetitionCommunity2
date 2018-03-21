package cn.abtion.neuqercc.message.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.message.models.FriendModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author fhyPayaso
 * @since 2018/1/23 on 上午12:04
 * fhyPayaso@qq.com
 */
public class FriendsRecAdapter extends BaseRecyclerViewAdapter<FriendModel> {


    private FriendItemListener mFriendItemListener;


    public FriendsRecAdapter(Context context, List<FriendModel> friendModels) {
        super(context, friendModels);
    }


    public void setFriendItemListener(FriendItemListener itemListener) {
        mFriendItemListener = itemListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = inflater.inflate(R.layout.item_friends, parent, false);
        return new ItemHolder(view);
    }

    class ItemHolder extends ViewHolder<FriendModel> implements View.OnClickListener {

        @BindView(R.id.img_friends_avatar)
        CircleImageView mImgFriendsAvatar;
        @BindView(R.id.txt_friend_name)
        TextView mTxtFriendName;
        @BindView(R.id.txt_send_message)
        TextView txtSendMessage;
        @BindView(R.id.txt_delete_friend)
        TextView txtDeleteFriend;


        public ItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mImgFriendsAvatar.setOnClickListener(this);
            txtDeleteFriend.setOnClickListener(this);
            txtSendMessage.setOnClickListener(this);

        }

        @Override
        protected void onBind(FriendModel friendModel) {

            mTxtFriendName.setText(friendModel.getUsername() == null ? "N/A" : friendModel.getUsername());
        }


        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            switch (v.getId()) {
                //好友信息跳转回调
                case R.id.img_friends_avatar:
                    mFriendItemListener.onAvaterClick(pos);
                    break;
                //发送信息回调
                case R.id.txt_send_message:
                    mFriendItemListener.onSendMessageClick(pos);
                    break;
                //删除好友回调
                case R.id.txt_delete_friend:
                    mFriendItemListener.onDeleteClick(pos);
                default:
                    break;
            }
        }
    }
}

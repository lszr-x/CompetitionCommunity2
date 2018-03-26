package cn.abtion.neuqercc.mine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.message.models.FriendModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author FanHongyu.
 * @since 18/3/26 15:56.
 * email fanhongyu@hrsoft.net.
 */

public class InviteFriendListAdapter extends BaseRecyclerViewAdapter<FriendModel> {


    private Context mContext;
    private InviteFriendListener mInviteFriendListener;

    public InviteFriendListAdapter(Context context, List<FriendModel> friendModels) {
        super(context, friendModels);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_invite_friend, parent, false);
        return new ItemHolder(view);
    }

    public void setInviteFriendListener(InviteFriendListener inviteFriendListener) {
        mInviteFriendListener = inviteFriendListener;
    }

    class ItemHolder extends ViewHolder<FriendModel> {

        @BindView(R.id.img_friends_avatar)
        CircleImageView imgFriendsAvatar;
        @BindView(R.id.txt_friend_name)
        TextView txtFriendName;
        @BindView(R.id.btn_invite_friend)
        TextView btnInviteFriend;

        public ItemHolder(View itemView) {
            super(itemView);

            btnInviteFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mInviteFriendListener.onBtnInviteClick(getAdapterPosition());
                }
            });
        }

        @Override
        protected void onBind(FriendModel friendModel) {

            if (friendModel.getPic() != null) {
                Glide.with(context).load(friendModel.getPic()).into(imgFriendsAvatar);
            }
            txtFriendName.setText(friendModel.getUsername() == null ? "" : friendModel.getUsername());
        }
    }
}

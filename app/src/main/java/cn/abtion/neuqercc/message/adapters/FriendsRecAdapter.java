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
import cn.abtion.neuqercc.message.activities.ChatWindowActivity;
import cn.abtion.neuqercc.message.activities.FriendInfoActivity;
import cn.abtion.neuqercc.message.models.FriendModel;
import cn.abtion.neuqercc.utils.ToastUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author fhyPayaso
 * @since 2018/1/23 on 上午12:04
 * fhyPayaso@qq.com
 */
public class FriendsRecAdapter extends BaseRecyclerViewAdapter<FriendModel> implements View.OnClickListener{


    private ItemHolder itemHolder;

    public FriendsRecAdapter(Context context, List<FriendModel> friendModels) {
        super(context, friendModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_friends, parent, false);


        itemHolder = new ItemHolder(view);


        View main = itemHolder.itemView.findViewById(R.id.ly_friend_info);
        View send = itemHolder.itemView.findViewById(R.id.txt_send_message);
        View delete = itemHolder.itemView.findViewById(R.id.txt_delete_friend);

        main.setOnClickListener(this);
        send.setOnClickListener(this);
        delete.setOnClickListener(this);

        return itemHolder;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ly_friend_info:
                FriendInfoActivity.startActivity(v.getContext());
                break;
            case R.id.txt_send_message:
                ChatWindowActivity.startActivity(v.getContext());
                break;
            case R.id.txt_delete_friend:
                
                remove(getItem(itemHolder.getLayoutPosition()));
                break;
            default:
                break;
        }

    }


    public static class ItemHolder extends ViewHolder<FriendModel> {

        @BindView(R.id.img_friends_avatar)
        CircleImageView mImgFriendsAvatar;
        @BindView(R.id.txt_friend_name)
        TextView mTxtFriendName;


        public ItemHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void onBind(FriendModel friendModel) {

            mTxtFriendName.setText(friendModel.getFriendName() == null ? "N/A":friendModel.getFriendName());

        }
    }
}

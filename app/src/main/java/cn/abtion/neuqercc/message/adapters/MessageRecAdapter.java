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
import cn.abtion.neuqercc.message.models.MessageModel;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午1:39
 * fhyPayaso@qq.com
 */
public class MessageRecAdapter extends BaseRecyclerViewAdapter<MessageModel> {


    public MessageRecAdapter(Context context, List<MessageModel> messageModels) {
        super(context, messageModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = inflater.inflate(R.layout.item_message, parent, false);
        final ItemHolder itemHolder = new ItemHolder(view);

        View main = view.findViewById(R.id.rl_message_main);
        View delete = view.findViewById(R.id.txt_delete_message);

        //进入聊天窗口
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatWindowActivity.startActivity(view.getContext());
            }
        });

        //删除该条信息
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(getItem(itemHolder.getLayoutPosition()));
            }
        });


        return itemHolder;
    }


    public static class ItemHolder extends ViewHolder<MessageModel> {

        @BindView(R.id.message_name)
        TextView mMessageName;
        @BindView(R.id.message_last)
        TextView mMessageLast;
        @BindView(R.id.txt_last_time)
        TextView mTxtLastTime;


        public ItemHolder(final View itemView) {
            super(itemView);

        }

        @Override
        protected void onBind(final MessageModel messageModel) {

            mMessageName.setText(messageModel.getUserName() == null ? "N/A" : messageModel.getUserName());
            mMessageLast.setText(messageModel.getLastMessage() == null ? "N/A" : messageModel.getLastMessage());
            mTxtLastTime.setText(messageModel.getLastTime() == null ? "N/A" : messageModel.getLastTime());
        }
    }

}
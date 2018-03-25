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
import cn.abtion.neuqercc.message.models.NoticeModel;

/**
 * 通知列表adapter
 *
 * @author FanHongyu.
 * @since 18/3/25 21:26.
 * email fanhongyu@hrsoft.net.
 */

public class NoticeRecAdapter extends BaseRecyclerViewAdapter<NoticeModel> {


    private static final String BTN_AGREE = "同意";
    private static final String BTN_REFUSE = "拒绝";
    private static final String BTN_CONFIRM = "确认";
    private Context mContext;


    public NoticeRecAdapter(Context context, List<NoticeModel> noticeModels) {
        super(context, noticeModels);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notice, parent, false);
        return new ItemHolder(view);
    }


    class ItemHolder extends ViewHolder<NoticeModel> implements View.OnClickListener {

        @BindView(R.id.txt_user_name)
        TextView txtUserName;
        @BindView(R.id.txt_notice_action)
        TextView txtNoticeAction;
        @BindView(R.id.txt_team_name)
        TextView txtTeamName;
        @BindView(R.id.btn_left)
        TextView btnLeft;
        @BindView(R.id.btn_right)
        TextView btnRight;


        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(NoticeModel noticeModel) {




        }

        @Override
        public void onClick(View v) {

        }
    }
}

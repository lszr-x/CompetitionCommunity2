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

    /**
     * 申请入队
     */
    private static final int FLAG_APPLY = 0;
    /**
     * 同意入队
     */
    private static final int FLAG_AGREE_FOR_APPLY = 1;
    /**
     * 拒绝入队
     */
    private static final int FLAG_REFUSE_FOR_APPLY = 2;
    /**
     * 邀请入队
     */
    private static final int FLAG_INVITE = 3;
    /**
     * 拒绝邀请
     */
    private static final int FLAG_AGREE_FOR_INVITE = 4;
    /**
     * 统一邀请
     */
    private static final int FLAG_REFUSE_FOR_INVITE = 5;


    private NoticeItemListener mNoticeItemListener;
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

    public void setNoticeItemListener(NoticeItemListener noticeItemListener) {
        mNoticeItemListener = noticeItemListener;
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
            btnLeft.setOnClickListener(this);
            btnRight.setOnClickListener(this);
        }

        @Override
        protected void onBind(NoticeModel noticeModel) {

            txtUserName.setText(noticeModel.getSendUserName() == null ? "" : noticeModel.getSendUserName());
            txtTeamName.setText(noticeModel.getTeamName() == null ? "" : noticeModel.getTeamName());
            switch (noticeModel.getNoticeType()) {
                case FLAG_APPLY:
                    txtNoticeAction.setText("申请加入");
                    setButton(2);
                    break;
                case FLAG_AGREE_FOR_APPLY:
                    txtNoticeAction.setText("同意您加入");
                    setButton(1);
                    break;
                case FLAG_REFUSE_FOR_APPLY:
                    txtNoticeAction.setText("拒绝您加入");
                    setButton(1);
                    break;
                case FLAG_INVITE:
                    txtNoticeAction.setText("邀请您加入");
                    setButton(2);
                    break;
                case FLAG_AGREE_FOR_INVITE:
                    txtNoticeAction.setText("同意加入");
                    setButton(1);
                    break;
                case FLAG_REFUSE_FOR_INVITE:
                    txtNoticeAction.setText("拒绝加入");
                    setButton(1);
                    break;
                default:
                    break;
            }
        }

        private void setButton(int num) {
            if (num == 1) {
                btnLeft.setVisibility(View.GONE);
                btnRight.setVisibility(View.VISIBLE);
                btnRight.setText(BTN_CONFIRM);
            } else {
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setText(BTN_REFUSE);
                btnRight.setVisibility(View.VISIBLE);
                btnRight.setText(BTN_AGREE);
            }
        }


        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            switch (v.getId()) {
                case R.id.btn_left:
                    mNoticeItemListener.onRefuseBtnClick(pos);
                    break;
                case R.id.btn_right:
                    mNoticeItemListener.onAgreeBtnClick(pos);
                    break;
                default:
                    break;

            }
        }
    }
}

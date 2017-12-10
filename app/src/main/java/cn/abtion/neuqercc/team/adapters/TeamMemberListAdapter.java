package cn.abtion.neuqercc.team.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;

/**
 * @author lszr
 * @since 2017/11/21 下午3:09
 * email wsyglszr@gmail.com
 */

public class TeamMemberListAdapter extends BaseRecyclerViewAdapter<TeamMemberListModel> {

    @BindView(R.id.txt_team_position)
    TextView txtTeamPosition;
    @BindView(R.id.txt_member_name)
    TextView txtMemberName;
    @BindView(R.id.txt_good_at)
    TextView txtGoodAt;

    public TeamMemberListAdapter(Context context, List<TeamMemberListModel> teamMemberListModel) {
        super(context, teamMemberListModel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_team_member, parent, false);
        return new ItemHolder(view);
    }

    private class ItemHolder extends ViewHolder<TeamMemberListModel> {


        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(TeamMemberListModel teamMemberListModel) {


            txtMemberName.setText(teamMemberListModel.getName() == null ? "N/A" : teamMemberListModel.getName());
            txtTeamPosition.setText(teamMemberListModel.getPosition() == null ? "N/A" : teamMemberListModel.getPosition());
            txtGoodAt.setText(teamMemberListModel.getGoodAt() == null ? "N/A" : teamMemberListModel.getGoodAt());
        }

    }
}

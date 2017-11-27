package cn.abtion.neuqercc.team.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.team.models.AllTeamListModel;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;

/**
 * @author lszr
 * @since 2017/11/26 下午8:20
 * email wsyglszr@gmail.com
 */

public class AllTeamListAdapter extends BaseRecyclerViewAdapter<AllTeamListModel> {


    public AllTeamListAdapter(Context context, List<AllTeamListModel> allTeamListModels) {
        super(context, allTeamListModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_all_team, parent, false);
        return new ItemHolder(view);
    }

    private class ItemHolder extends ViewHolder<AllTeamListModel> {


        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(AllTeamListModel allTeamListModel) {

        }

    }

}

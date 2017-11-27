package cn.abtion.neuqercc.team.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.team.models.RecommendTeamListModel;

/**
 * @author lszr
 * @since 2017/11/27 下午6:26
 * email wsyglszr@gmail.com
 */

public class RecommendTeamListAdapter extends BaseRecyclerViewAdapter<RecommendTeamListModel> {

    public RecommendTeamListAdapter(Context context, List<RecommendTeamListModel> recommendTeamListModels) {
        super(context, recommendTeamListModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_recommen_team, parent, false);
        return new ItemHolder(view);
    }

    private class ItemHolder extends ViewHolder<RecommendTeamListModel> {


        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecommendTeamListModel recommendTeamListModel) {

        }

    }
}

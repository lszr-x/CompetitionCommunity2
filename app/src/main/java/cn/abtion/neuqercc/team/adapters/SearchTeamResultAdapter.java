package cn.abtion.neuqercc.team.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.team.models.AllTeamListModel;
import cn.abtion.neuqercc.team.models.SearchResultTeamModel;

/**
 * @author lszr
 * @since 2017/11/28 下午7:56
 * email wsyglszr@gmail.com
 */

public class SearchTeamResultAdapter extends BaseRecyclerViewAdapter {
    public SearchTeamResultAdapter(Context context, List<SearchResultTeamModel> searchResultTeamModels) {
        super(context, searchResultTeamModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_all_team, parent, false);
        return new ItemHolder(view);
    }

    private class ItemHolder extends ViewHolder<SearchResultTeamModel> {


        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(SearchResultTeamModel searchResultTeamModel) {

        }

    }
}

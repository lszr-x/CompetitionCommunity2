package cn.abtion.neuqercc.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.home.models.ContestModel;

/**
 * @author abtion.
 * @since 17/9/25 17:07.
 * email caiheng@hrsoft.net.
 */

public class HomeAdapter extends BaseRecyclerViewAdapter<ContestModel> {



    public HomeAdapter(Context context, List<ContestModel> contestModel) {
        super(context, contestModel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_home, parent, false);
        return new ItemHolder(view);

    }

    static class ItemHolder extends ViewHolder<ContestModel> {

        @BindView(R.id.txt_contest_name)
        TextView txtContestName;
        @BindView(R.id.txt_size)
        TextView txtSize;
        @BindView(R.id.txt_weight)
        TextView txtWeight;


        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(ContestModel contestModel) {
            txtContestName.setText(contestModel.getTitle() == null ? "N/A" : contestModel.getTitle());
            txtSize.setText(contestModel.getCotestTime() == null ? "N/A" : contestModel.getCotestTime());
            txtWeight.setText(contestModel.getSignTime() == null ? "N/A" : contestModel.getSignTime());

        }
    }

}

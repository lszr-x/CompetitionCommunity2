package cn.abtion.neuqercc.message.adapters;

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
import cn.abtion.neuqercc.message.models.SearchUserModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author FanHongyu.
 * @since 18/1/24 21:33.
 * email fanhongyu@hrsoft.net.
 */

public class SearchUseRecAdapter extends BaseRecyclerViewAdapter<SearchUserModel> {


    private Context mContext;


    public SearchUseRecAdapter(Context context, List<SearchUserModel> searchUserModels) {
        super(context, searchUserModels);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_rec_search_user_res, parent, false);
        return new ItemHolder(view);
    }


    class ItemHolder extends ViewHolder<SearchUserModel> {

        @BindView(R.id.img_user_avatar)
        CircleImageView imgUserAvatar;
        @BindView(R.id.txt_user_name)
        TextView txtUserName;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(SearchUserModel searchUserModel) {


            if (searchUserModel.getPic() != null) {
                Glide.with(mContext).load(searchUserModel.getPic()).into(imgUserAvatar);
            }
            txtUserName.setText(searchUserModel.getUsername() == null ? "N/A" : searchUserModel.getUsername());
        }

    }
}

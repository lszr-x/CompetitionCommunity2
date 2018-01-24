package cn.abtion.neuqercc.message.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.message.adapters.SearchUseRecAdapter;
import cn.abtion.neuqercc.message.models.SearchUserModel;
import cn.abtion.neuqercc.widget.SwipeItemLayout;

/**
 * @author FanHongyu.
 * @since 18/1/24 21:11.
 * email fanhongyu@hrsoft.net.
 */

public class SearchUseResActivity extends ToolBarActivity {


    @BindView(R.id.rec_user)
    RecyclerView recUser;

    private List<SearchUserModel> mUserModelList;
    private SearchUseRecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_user_res;
    }

    @Override
    protected void initVariable() {

        mUserModelList = new ArrayList<>();
        for (int i=0;i < 10;i++) {
            mUserModelList.add(new SearchUserModel("11","用户"+i));
        }
    }

    @Override
    protected void initView() {
        setActivityTitle("搜索结果");
        initRec();
    }

    @Override
    protected void loadData() {

    }

    private void initRec() {

        mAdapter = new SearchUseRecAdapter(SearchUseResActivity.this,mUserModelList);
        mLayoutManager = new LinearLayoutManager(SearchUseResActivity.this, LinearLayoutManager.VERTICAL,false);
        recUser.setLayoutManager(mLayoutManager);
        recUser.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(SearchUseResActivity.this));
        recUser.setAdapter(mAdapter);
    }
}

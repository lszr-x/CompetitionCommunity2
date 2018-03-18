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
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.widget.SwipeItemLayout;
import retrofit2.Call;
import retrofit2.Response;

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
    private String content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_user_res;
    }

    @Override
    protected void initVariable() {

        mUserModelList = new ArrayList<>();
        content = getIntent().getStringExtra("searchUsername");
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

        loadUserList();
        mAdapter = new SearchUseRecAdapter(SearchUseResActivity.this, mUserModelList);
        mLayoutManager = new LinearLayoutManager(SearchUseResActivity.this, LinearLayoutManager.VERTICAL, false);
        recUser.setLayoutManager(mLayoutManager);
        recUser.setAdapter(mAdapter);
    }


    private void loadUserList() {


        RestClient.getService().searchUser(content).enqueue(new DataCallback<APIResponse<List<String>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<List<String>>> call, Response<APIResponse<List<String>>> response) {

                List<String> reponseList = new ArrayList<>();
                reponseList = response.body().getData();
                mUserModelList.clear();

                for (int i=0 ;i<reponseList.size();i++) {
                    mUserModelList.add(new SearchUserModel("",mUserModelList.get(i).getUserName()));
                }
            }

            @Override
            public void onDataFailure(Call<APIResponse<List<String>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });
    }
}

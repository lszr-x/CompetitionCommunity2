package cn.abtion.neuqercc.home.fragments;


import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.home.activities.CompetitionActivity;
import cn.abtion.neuqercc.home.adapters.HomeAdapter;
import cn.abtion.neuqercc.home.models.ContestListModel;
import cn.abtion.neuqercc.main.MainActivity;


/**
 * @author abtion.
 * @since 17/9/24 上午2:16.
 * email caiheng@hrsoft.net.
 */

public class HomeFragment extends BaseFragment {

    private ArrayList<ContestListModel> contestListModels;


    @BindView(R.id.spinner_home)
    Spinner spinnerHome;
    @BindView(R.id.search_home)
    SearchView searchHome;
    @BindView(R.id.rec_home)
    RecyclerView recHome;
    @BindView(R.id.view_container)
    FrameLayout viewContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        recHome.setNestedScrollingEnabled(false);
        contestListModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            contestListModels.add(new ContestListModel("数学建模", "前端、热血、创意", "比赛时间：2017.10.15-2017.10.18","报名时间：2017.10.15-2017.10.18"));
        }

        HomeAdapter homeAdapter = new HomeAdapter(getContext(), contestListModels);
        recHome.setAdapter(homeAdapter);
        recHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //点击事件
        homeAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<ContestListModel>() {

            @Override
            public void onItemClicked(ContestListModel contestListModel, BaseRecyclerViewAdapter.ViewHolder viewHolder) {
                Intent intent=new Intent(getContext(),CompetitionActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void loadData() {

    }

}

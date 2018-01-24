package cn.abtion.neuqercc.message.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.message.activities.FriendInfoActivity;
import cn.abtion.neuqercc.message.adapters.FriendsRecAdapter;
import cn.abtion.neuqercc.message.models.FriendModel;
import cn.abtion.neuqercc.utils.ToastUtil;
import cn.abtion.neuqercc.widget.SwipeItemLayout;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午12:52
 * fhyPayaso@qq.com
 */
public class FriendListFragment extends BaseFragment {
    @BindView(R.id.rec_friends)
    RecyclerView mRecFriends;
    Unbinder unbinder;



    private FriendsRecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friends;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        initRecView();
    }

    @Override
    protected void loadData() {

    }


    /**
     * 初始化好友列表
     */
    private void initRecView() {

        List<FriendModel> friendModelList = new ArrayList<>();
        for(int i=0 ;i<20 ;i++) {
            friendModelList.add(new FriendModel("sss","username"+i));
        }


        mAdapter = new FriendsRecAdapter(getContext(),friendModelList);
        mRecFriends.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecFriends.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        mRecFriends.setLayoutManager(mLayoutManager);

    }
}

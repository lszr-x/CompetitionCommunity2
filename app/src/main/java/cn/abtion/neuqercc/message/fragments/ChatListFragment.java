package cn.abtion.neuqercc.message.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.message.adapters.MessageRecAdapter;
import cn.abtion.neuqercc.message.models.MessageModel;
import cn.abtion.neuqercc.widget.SwipeItemLayout;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午12:51
 * fhyPayaso@qq.com
 */
public class ChatListFragment extends BaseFragment {


    @BindView(R.id.message_rec)
    RecyclerView mMessageRec;
    Unbinder unbinder;

    private MessageRecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {


        initRec();

    }

    @Override
    protected void loadData() {

    }


    private void initRec() {

        List<MessageModel> messageModelList = new ArrayList<>();
        MessageModel model = new MessageModel("ss", "lalala", "晚安啦", "00:53");
        for (int i = 1; i < 10; i++) {
            messageModelList.add(model);
        }
        mAdapter = new MessageRecAdapter(getContext(), messageModelList);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mMessageRec.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        mMessageRec.setLayoutManager(mLayoutManager);
        mMessageRec.setAdapter(mAdapter);
    }
}

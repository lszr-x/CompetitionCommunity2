package cn.abtion.neuqercc.message.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.message.adapters.MessageViewPagerAdapter;

/**
 * @author abtion.
 * @since 17/9/24 上午2:15.
 * email caiheng@hrsoft.net.
 */

public class MessageFragment extends BaseFragment {


    @BindView(R.id.table_header_message)
    TabLayout tableHeaderMessage;
    @BindView(R.id.viewpager_message)
    ViewPager viewpagerMessage;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {


        MessageViewPagerAdapter messageViewPagerAdapter = new MessageViewPagerAdapter(getFragmentManager());
        viewpagerMessage.setAdapter(messageViewPagerAdapter);
        tableHeaderMessage.setupWithViewPager(viewpagerMessage);
    }

    @Override
    protected void loadData() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

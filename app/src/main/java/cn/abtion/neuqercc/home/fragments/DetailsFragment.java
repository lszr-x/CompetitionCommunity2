package cn.abtion.neuqercc.home.fragments;

import android.webkit.WebView;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;

/**
 * @author fhyPayaso
 * @since 2017/11/7 16:39
 * email fhyPayaso@qq.com
 */

public class DetailsFragment extends BaseFragment {


    @BindView(R.id.web_test)
    WebView webView;


    @Override
    protected int getLayoutId(){
        return R.layout.fragment_details;
    }

    @Override
    protected void initVariable() {

        //webView.loadUrl("https://nba.hupu.com/");
    }

    @Override
    protected  void initView() {

    }

    @Override
    protected void loadData() {

    }


}

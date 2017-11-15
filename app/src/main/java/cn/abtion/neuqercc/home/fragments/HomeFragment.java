package cn.abtion.neuqercc.home.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.content.Intent;
import android.provider.ContactsContract;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewFlipper;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.home.activities.CompetitionActivity;
import cn.abtion.neuqercc.home.adapters.HomeAdapter;
import cn.abtion.neuqercc.home.models.ContestListModel;
import cn.abtion.neuqercc.main.MainActivity;

import static android.view.MotionEvent.ACTION_UP;
import static cn.abtion.neuqercc.common.Config.FLING_MIN_DISTANCE;


/**
 * @author abtion.
 * @since 17/9/24 上午2:16.
 * email caiheng@hrsoft.net.
 */

public class HomeFragment extends BaseFragment {



    //private GestureDetector gestureDetector;    //手势检测
    Unbinder unbinder;
    @BindView(R.id.vf_contest)
    ViewFlipper vfContest;

    private ArrayList<ContestListModel> contestListModels;

    private int downX = 0;


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

        initViewFlipper();

        initContestRecylerview();

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



    public void initContestRecylerview(){


        recHome.setNestedScrollingEnabled(false);
        contestListModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            contestListModels.add(new ContestListModel("数学建模", "前端、热血、创意", "比赛时间：2017.10.15-2017.10.18", "报名时间：2017.10.15-2017.10.18"));
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

    private ImageView getImageView(int id) {
        ImageView imageView =new ImageView(this.getContext());
        imageView.setImageResource(id);
        return imageView;
    }


    public void initViewFlipper() {

        vfContest.addView(getImageView(R.drawable.img_home_view));
        vfContest.addView(getImageView(R.drawable.ic_register_password));
        vfContest.addView(getImageView(R.drawable.ic_back));
        vfContest.addView(getImageView(R.drawable.ic_contest_before_title));

        vfContest.setInAnimation(inFromRightAnimation());
        vfContest.setOutAnimation(outToLeftAnimation());
        vfContest.setFlipInterval(3000);
        vfContest.startFlipping();
        vfContest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //解决viewFlipper和view的冲突
                vfContest.getParent().requestDisallowInterceptTouchEvent(true);
                //手势滑动
                int x;
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        //记录点击时的坐标
                        downX = (int) event.getRawX();
                        vfContest.stopFlipping();
                        break;
                    case ACTION_UP:
                        //记录离开时的坐标
                        x = (int) event.getRawX();
                        if (downX - x   > FLING_MIN_DISTANCE) {

                            vfContest.setInAnimation(inFromRightAnimation());
                            vfContest.setOutAnimation(outToLeftAnimation());
                            vfContest.showNext();
                            vfContest.startFlipping();
                        } else if (x - downX > FLING_MIN_DISTANCE) {

                            vfContest.setOutAnimation(outToRightAnimation());
                            vfContest.setInAnimation(inFromLeftAnimation());
                            vfContest.showPrevious();

                            //从左向右滑之后，重新设置动画效果
                            vfContest.startFlipping();
                            vfContest.setInAnimation(inFromRightAnimation());
                            vfContest.setOutAnimation(outToLeftAnimation());

                        } else {

                            vfContest.showNext();
                            vfContest.startFlipping();
                        }


                        break;
                    default:
                        break;
                }

                return true;
            }
        });



    }


    /**
     * 定义从右侧进入的动画效果
     *
     * @return
     */
    protected Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(200);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    /**
     * 定义从左侧退出的动画效果
     *
     * @return
     */
    protected Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(200);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    /**
     * 定义从左侧进入的动画效果
     *
     * @return
     */
    protected Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(200);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    /**
     * 定义从右侧退出时的动画效果
     *
     * @return
     */
    protected Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(200);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

}

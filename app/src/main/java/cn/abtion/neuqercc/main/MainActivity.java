package cn.abtion.neuqercc.main;

import android.content.Intent;
import android.support.transition.Fade;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.BaseActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.home.adapters.HomeViewPagerAdapter;
import cn.abtion.neuqercc.home.fragments.HomeFragment;
import cn.abtion.neuqercc.message.MessageFragment;
import cn.abtion.neuqercc.mine.MineFragment;
import cn.abtion.neuqercc.team.fragments.TeamFragment;
import cn.abtion.neuqercc.utils.ToastUtil;

public class MainActivity extends ToolBarActivity {

    public static final int FLAG_HOME = 0;
    public static final int FLAG_TEAM = 1;

    @BindView(R.id.img_tab_menu_home)
    ImageView imgTabMenuHome;
    @BindView(R.id.txt_tab_menu_home)
    TextView txtTabMenuHome;
    @BindView(R.id.img_tab_menu_order)
    ImageView imgTabMenuOrder;
    @BindView(R.id.txt_tab_menu_order)
    TextView txtTabMenuTeam;
    @BindView(R.id.img_tab_menu_message)
    ImageView imgTabMenuMessage;
    @BindView(R.id.txt_tab_menu_message)
    TextView txtTabMenuMessage;
    @BindView(R.id.img_tab_menu_mine)
    ImageView imgTabMenuMine;
    @BindView(R.id.txt_tab_menu_mine)
    TextView txtTabMenuMine;
    @BindView(R.id.vp_main_container)
    ViewPager mainViewPager;

    private HomeViewPagerAdapter homeViewPagerAdapter;
    private HomeFragment homeFragment;
    private TeamFragment teamFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private int flag;

    public static void start(BaseActivity context, int flag) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Config.KEY_MAIN_FLAG, flag);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initVariable() {


        //有关viewPager的初始化
        homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        mainViewPager.setCurrentItem(0);
        mainViewPager.setOffscreenPageLimit(4);
        mainViewPager.setAdapter(homeViewPagerAdapter);

        mainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                changePager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    protected void initView() {


        setActivityTitle("赛事信息");

        onLyTabMenuHomeClicked();

        getToolbar().setNavigationIcon(R.drawable.ic_nav);

        //从其他活动转入后指定显示的fragment
        Intent intent = getIntent();
        flag = intent.getIntExtra(Config.KEY_MAIN_FLAG, -1);
        mainViewPager.setCurrentItem(flag);


        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(android.R.id.content), new Fade());


//        switch (flag) {
//
//            case FLAG_HOME:
//                onLyTabMenuHomeClicked();
//                replaceFragment(R.id.frame_main_container, homeFragment, null);
//                changeHomeMenuStatus();
//                break;
//
//            case FLAG_TEAM:
//                onLyTabMenuOrderClicked();
//                onLyTabMenuHomeClicked();
//                replaceFragment(R.id.frame_main_container, teamFragment, null);
//                changeTeamMenuStatus();
//                break;
//
//            default:
//        }

    }

    @Override
    protected void loadData() {

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ToastUtil.showToast("home");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


//    private void hideFragment(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
//    }
//
//    private void showFragment(Fragment fragment) {
//        //hideAllFragment();
//        getSupportFragmentManager().beginTransaction().show(fragment).commit();
//    }
//
//    private void addFragment(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().add(R.id.frame_main_container, fragment).commit();
//    }
//
//
//    private void hideAllFragment() {
//        if (homeFragment != null) {
//            hideFragment(homeFragment);
//        }
//        if (teamFragment != null) {
//            hideFragment(teamFragment);
//        }
//        if (messageFragment != null) {
//            hideFragment(messageFragment);
//        }
//        if (mineFragment != null) {
//            hideFragment(mineFragment);
//        }
//    }




    /**
     * 用于viewPager更换pager
     */
    private void changePager(int position) {
        switch (position) {
            case 0:
                changeHomeMenuStatus();
                break;
            case 1:
                changeTeamMenuStatus();
                break;
            case 2:
                changeMessageMenuStatus();
                break;
            case 3:
                changeMineMenuStatus();
                break;
            default:
                break;
        }
    }

    /**
     * 清除选中状态
     */
    private void clearChoiceStatus() {


        imgTabMenuHome.setSelected(false);
        imgTabMenuOrder.setSelected(false);
        imgTabMenuMessage.setSelected(false);
        imgTabMenuMine.setSelected(false);

        txtTabMenuHome.setTextColor(getResources().getColor(R.color.subtitle));
        txtTabMenuTeam.setTextColor(getResources().getColor(R.color.subtitle));
        txtTabMenuMessage.setTextColor(getResources().getColor(R.color.subtitle));
        txtTabMenuMine.setTextColor(getResources().getColor(R.color.subtitle));

    }

    /**
     * 高亮Home
     */
    private void changeHomeMenuStatus() {
        clearChoiceStatus();
        setActivityTitle("赛事信息");
        imgTabMenuHome.setSelected(true);
        txtTabMenuHome.setTextColor(getResources().getColor(R.color.text_warning));
    }

    /**
     * 高亮Team
     */
    private void changeTeamMenuStatus() {
        clearChoiceStatus();
        setActivityTitle("组队");
        imgTabMenuOrder.setSelected(true);
        txtTabMenuTeam.setTextColor(getResources().getColor(R.color.text_warning));

    }

    /**
     * 高亮Message
     */
    private void changeMessageMenuStatus() {
        clearChoiceStatus();
        setActivityTitle("聊天");
        imgTabMenuMessage.setSelected(true);
        txtTabMenuMessage.setTextColor(getResources().getColor(R.color.text_warning));

    }

    /**
     * 高亮Mine
     */
    private void changeMineMenuStatus() {
        clearChoiceStatus();
        setActivityTitle("我的");
        imgTabMenuMine.setSelected(true);
        txtTabMenuMine.setTextColor(getResources().getColor(R.color.text_warning));
    }


    /**
     * 点击Home
     */
    @OnClick(R.id.ly_tab_menu_home)
    public void onLyTabMenuHomeClicked() {

        changeHomeMenuStatus();
        mainViewPager.setCurrentItem(0);

//        hideAllFragment();
//        if (homeFragment == null) {
//            homeFragment = new HomeFragment();
//            addFragment(homeFragment);
//        } else {
//            showFragment(homeFragment);
//        }
    }

    /**
     * 点击Team
     */
    @OnClick(R.id.ly_tab_menu_order)
    public void onLyTabMenuOrderClicked() {
        changeTeamMenuStatus();
        mainViewPager.setCurrentItem(1);

//        hideAllFragment();
//        if (teamFragment == null) {
//            teamFragment = new TeamFragment();
//            addFragment(teamFragment);
//        } else {
//
//            //showFragment(teamFragment);
//        }
    }

    /**
     * 点击Message
     */
    @OnClick(R.id.ly_tab_menu_message)
    public void onLyTabMenuMessageClicked() {

        changeMessageMenuStatus();
        mainViewPager.setCurrentItem(2);
//        hideAllFragment();
//        if (messageFragment == null) {
//            messageFragment = new MessageFragment();
//            addFragment(messageFragment);
//        } else {
//
//            //showFragment(messageFragment);
//        }
    }

    /**
     *Mine
     */
    @OnClick(R.id.ly_tab_menu_mine)
    public void onLyTabMenuMineClicked() {

        changeMineMenuStatus();
        mainViewPager.setCurrentItem(3);

//        hideAllFragment();
//        if (mineFragment == null) {
//            mineFragment = new MineFragment();
//            addFragment(mineFragment);
//        } else {
//
//
//            //showFragment(mineFragment);
//        }

    }

}

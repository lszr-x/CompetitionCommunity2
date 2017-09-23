package cn.abtion.neuqercc;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.home.HomeFragment;
import cn.abtion.neuqercc.message.MessageFragment;
import cn.abtion.neuqercc.mine.MineFragment;
import cn.abtion.neuqercc.team.TeamFragment;

public class MainActivity extends ToolBarActivity {


    @BindView(R.id.img_tab_menu_home)
    ImageView imgTabMenuHome;
    @BindView(R.id.txt_tab_menu_home)
    TextView txtTabMenuHome;
    @BindView(R.id.img_tab_menu_order)
    ImageView imgTabMenuOrder;
    @BindView(R.id.txt_tab_menu_order)
    TextView txtTabMenuOrder;
    @BindView(R.id.img_tab_menu_message)
    ImageView imgTabMenuMessage;
    @BindView(R.id.txt_tab_menu_message)
    TextView txtTabMenuMessage;
    @BindView(R.id.img_tab_menu_mine)
    ImageView imgTabMenuMine;
    @BindView(R.id.txt_tab_menu_mine)
    TextView txtTabMenuMine;

    private HomeFragment homeFragment;
    private TeamFragment teamFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable() {
        homeFragment = new HomeFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    private void hideFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    private void showFragment(Fragment fragment) {
        hideAllFragment();
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.frame_main_container, fragment).commit();
    }

    private void hideAllFragment() {
        if (homeFragment != null) {
            hideFragment(homeFragment);
        }
        if (teamFragment != null) {
            hideFragment(teamFragment);
        }
        if (messageFragment != null) {
            hideFragment(messageFragment);
        }
        if (mineFragment != null) {
            hideFragment(mineFragment);
        }
    }

    /**
     * 清除选中状态
     */
    private void clearChoiceStatus() {


    }

    /**
     * 高亮Home
     */
    private void changeHomeMenuStatus() {
        clearChoiceStatus();

    }

    /**
     * 高亮Team
     */
    private void changeTeamMenuStatus() {
        clearChoiceStatus();

    }

    /**
     * 高亮Message
     */
    private void changeMessageMenuStatus() {
        clearChoiceStatus();

    }

    /**
     * 高亮Mine
     */
    private void changeMineMenuStatus() {
        clearChoiceStatus();

    }



    @OnClick(R.id.ly_tab_menu_home)
    public void onLyTabMenuHomeClicked() {
        changeHomeMenuStatus();
        hideAllFragment();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            addFragment(homeFragment);
        } else {
            showFragment(homeFragment);
        }
    }

    @OnClick(R.id.ly_tab_menu_order)
    public void onLyTabMenuOrderClicked() {
        changeTeamMenuStatus();
        hideAllFragment();
        if (teamFragment == null) {
            teamFragment = new TeamFragment();
            addFragment(teamFragment);
        } else {
            showFragment(teamFragment);
        }
    }

    @OnClick(R.id.ly_tab_menu_message)
    public void onLyTabMenuMessageClicked() {
        changeMessageMenuStatus();
        hideAllFragment();
        if (messageFragment == null) {
            messageFragment = new MessageFragment();
            addFragment(messageFragment);
        } else {
            showFragment(messageFragment);
        }
    }

    @OnClick(R.id.ly_tab_menu_mine)
    public void onLyTabMenuMineClicked() {
        changeMineMenuStatus();
        hideAllFragment();
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            addFragment(mineFragment);
        } else {
            showFragment(mineFragment);
        }
    }


}

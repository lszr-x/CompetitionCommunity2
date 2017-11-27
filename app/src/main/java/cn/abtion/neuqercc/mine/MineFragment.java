package cn.abtion.neuqercc.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.mine.activities.MineTeamListActivity;
import cn.abtion.neuqercc.mine.activities.SettingActivity;
import cn.abtion.neuqercc.mine.activities.UpdateInformationActivity;
import cn.abtion.neuqercc.widget.GradientScrollView;

/**
 * @author abtion.
 * @since 17/9/24 上午2:15.
 * email caiheng@hrsoft.net.
 */

public class MineFragment extends BaseFragment {


    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.rlayout_mine_team)
    RelativeLayout rlayoutMineTeam;
    @BindView(R.id.scroll_mine_information)
    GradientScrollView scrollMineInformation;
    @BindView(R.id.rlayout_mine_title)
    RelativeLayout rlayoutMineTitle;
    @BindView(R.id.mine_header_bg)
    LinearLayout mineHeaderBg;
    @BindView(R.id.txt_team_title)
    TextView txtTeamTitle;
    @BindView(R.id.title_mine_edit)
    ImageView titleMineEdit;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

       initScrollView();

    }


    public void initScrollView() {

        scrollMineInformation.setScrollViewListener(new GradientScrollView.ScrollViewListener() {

            @Override
            public void onScrollChanged(GradientScrollView scrollView, int x, int y, int oldx, int oldy) {

                txtTeamTitle.setTextColor(Color.argb((int) Config.COLOR_MIN, Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX));

                if (y <= 0) {

                    rlayoutMineTitle.setBackgroundColor(Color.argb((int) Config.COLOR_MIN, Config.TITLE_BG_R, Config.TITLE_BG_G, Config.TITLE_BG_B));
                    txtTeamTitle.setTextColor(Color.argb((int) Config.COLOR_MIN, Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX));

                } else if (y > 0 && y < (mineHeaderBg.getHeight() - rlayoutMineTitle.getHeight())) {

                    float scale = (float) y / (mineHeaderBg.getHeight() - rlayoutMineTitle.getHeight());
                    float alpha = (Config.COLOR_MAX * scale);
                    rlayoutMineTitle.setBackgroundColor(Color.argb((int) alpha, Config.TITLE_BG_R, Config.TITLE_BG_G, Config.TITLE_BG_B));
                    txtTeamTitle.setTextColor(Color.argb((int) alpha, Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX));

                } else {
                    rlayoutMineTitle.setBackgroundColor(Color.argb((int) Config.COLOR_MAX, Config.TITLE_BG_R, Config.TITLE_BG_G, Config.TITLE_BG_B));
                    txtTeamTitle.setTextColor(Color.argb((int) Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX));
                }
            }
        });

    }


    @Override
    protected void loadData() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * 账号设置按钮点击事件
     */
    @OnClick(R.id.btn_setting)
    public void onBtnSettingClicked() {

        Intent intent = new Intent(getContext(), SettingActivity.class);
        startActivity(intent);

    }

    /**
     * 我的队伍点击事件
     */
    @OnClick(R.id.rlayout_mine_team)
    public void onRlayoutMineTeamClicked() {

        Intent intent = new Intent(getContext(), MineTeamListActivity.class);
        startActivity(intent);

    }


    @OnClick(R.id.title_mine_edit)
    public void onImgEditClicked() {

        Intent intent = new Intent(getContext(), UpdateInformationActivity.class);
        startActivity(intent);

    }
}

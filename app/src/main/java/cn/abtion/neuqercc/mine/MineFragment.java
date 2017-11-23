package cn.abtion.neuqercc.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.mine.activities.MineTeamListActivity;
import cn.abtion.neuqercc.mine.activities.SettingActivity;
import cn.abtion.neuqercc.mine.activities.UpdateInformationActivity;

/**
 * @author abtion.
 * @since 17/9/24 上午2:15.
 * email caiheng@hrsoft.net.
 */

public class MineFragment extends BaseFragment {


    @BindView(R.id.mine_edit)
    TextView txtEdit;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.rlayout_mine_team)
    RelativeLayout rlayoutMineTeam;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.mine_edit)
    public void onTxtEditClicked() {

        Intent intent = new Intent(getContext(), UpdateInformationActivity.class);
        startActivity(intent);

    }


    @OnClick(R.id.btn_setting)
    public void onBtnSettingClicked() {

        Intent intent = new Intent(getContext(), SettingActivity.class);
        startActivity(intent);

    }



    @OnClick(R.id.rlayout_mine_team)
    public void onRlayoutMineTeamClicked() {

        Intent intent =new Intent(getContext(), MineTeamListActivity.class);
        startActivity(intent);

    }
}

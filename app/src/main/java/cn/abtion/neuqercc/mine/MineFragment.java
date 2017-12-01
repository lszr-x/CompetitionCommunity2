package cn.abtion.neuqercc.mine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.mine.activities.HonorInformationActivity;
import cn.abtion.neuqercc.mine.activities.MineTeamListActivity;
import cn.abtion.neuqercc.mine.activities.SettingActivity;
import cn.abtion.neuqercc.mine.activities.UpdateInformationActivity;
import cn.abtion.neuqercc.mine.adapters.GridHonorAdapter;
import cn.abtion.neuqercc.mine.models.HonorCertificateModel;
import cn.abtion.neuqercc.utils.DensityUtil;
import cn.abtion.neuqercc.widget.GradientScrollView;
import cn.abtion.neuqercc.widget.HonorGridView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author abtion.
 * @since 17/9/24 上午2:15.
 * email caiheng@hrsoft.net.
 */

public class MineFragment extends BaseFragment {



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
    @BindView(R.id.mine_grid_honor)
    HonorGridView gridHonor;
    @BindView(R.id.mine_avatar)
    CircleImageView imgAvatar;



    private GridHonorAdapter gridHonorAdapter;
    private List<HonorCertificateModel> honorCertificateModelList = new ArrayList<HonorCertificateModel>();

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
        initGrid();

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

    @OnClick(R.id.title_mine_edit)
    public void onImgEditClicked() {

        showDialog();

    }

    public void showDialog() {


        final  AlertDialog dialog = new AlertDialog.Builder(getContext(),R.style.dialog_bottom).create();
        dialog.show();
        dialog.getWindow().setContentView(R.layout.pop_window_edit);
        dialog.setCancelable(true);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.END|Gravity.TOP);
        window.getDecorView().setPadding(0, DensityUtil.dip2px(getContext(),73), DensityUtil.dip2px(getContext(),25), 0);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DensityUtil.dip2px(getContext(),170);
        lp.height = DensityUtil.dip2px(getContext(),300);
        window.setAttributes(lp);



        CardView txtEditInformation = (CardView)dialog.findViewById(R.id.card_update_information);
        CardView txtMineSetting = (CardView)dialog.findViewById(R.id.card_setting);
        CardView txtMineTeam = (CardView)dialog.findViewById(R.id.card_mine_team);


        txtEditInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(getContext(), UpdateInformationActivity.class);
                startActivity(intent);
            }
        });

        txtMineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        txtMineTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(getContext(), MineTeamListActivity.class);
                startActivity(intent);
            }
        });


    }


    /**
     * 初始化荣誉墙
     */
    public void initGrid() {


        initData();

        gridHonorAdapter = new GridHonorAdapter(getContext(), honorCertificateModelList, false);
        gridHonor.setAdapter(gridHonorAdapter);

        gridHonor.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getContext(), HonorInformationActivity.class);
                startActivity(intent);

            }
        });

    }

    /**
     * 初始化item
     */
    public void initData() {

        for (int i = 1; i <= 9; i++) {

            HonorCertificateModel honorCertificateAdd = new HonorCertificateModel(R.drawable.bg_account_title);
            honorCertificateModelList.add(honorCertificateAdd);
        }
    }


    @OnClick(R.id.mine_avatar)
    public void onImgAvaterClicked() {

        showFullImg();
    }

    public void showFullImg() {

        View view = View.inflate(getContext(), R.layout.item_dialog_full_img, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        ImageView imageView = (ImageView)view.findViewById(R.id.img_full_dialog);
        imageView.setImageResource(R.drawable.bg_about_us);

        builder.show();

    }

}

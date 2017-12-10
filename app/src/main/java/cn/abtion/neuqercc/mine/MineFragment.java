package cn.abtion.neuqercc.mine;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
import cn.abtion.neuqercc.mine.models.PersonInformationRequest;
import cn.abtion.neuqercc.mine.models.ShowHonorRequest;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.DensityUtil;
import cn.abtion.neuqercc.widget.GradientScrollView;
import cn.abtion.neuqercc.widget.HonorGridView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Response;

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
    @BindView(R.id.txt_mine_major)
    TextView txtMajor;
    @BindView(R.id.txt_mine_grade)
    TextView txtGrade;
    @BindView(R.id.txt_mine_student_id)
    TextView txtStudentId;
    @BindView(R.id.txt_mine_good_at)
    TextView txtGoodAt;
    @BindView(R.id.txt_mine_team_num)
    TextView txtTeamNum;
    @BindView(R.id.txt_mine_name)
    TextView txtName;
    @BindView(R.id.txt_mine_phone_number)
    TextView txtPhoneNumber;
    @BindView(R.id.txt_mine_user_name)
    TextView txtUserName;
    @BindView(R.id.img_mine_gender)
    ImageView imgGender;
    @BindView(R.id.mine_team)
    TextView mineTeam;
    @BindView(R.id.mine_information)
    RelativeLayout mineInformation;
    @BindView(R.id.ic_name)
    ImageView icName;
    @BindView(R.id.ic_phone)
    ImageView icPhone;
    @BindView(R.id.mine_phone)
    RelativeLayout minePhone;
    @BindView(R.id.mine_profession)
    TextView mineProfession;
    @BindView(R.id.mine_grade)
    TextView mineGrade;
    @BindView(R.id.mine_id)
    TextView mineId;
    @BindView(R.id.mine_message)
    RelativeLayout mineMessage;
    @BindView(R.id.mine_txt_honor)
    TextView mineTxtHonor;
    @BindView(R.id.mine_honor)
    RelativeLayout mineHonor;


    private List<ShowHonorRequest> showHonorRequestList = new ArrayList<ShowHonorRequest>();
    private List<HonorCertificateModel> honorCertificateModelList = new ArrayList<HonorCertificateModel>();
    private String imgAvatarUrl;
    private int gender = 0;

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

    @Override
    protected void loadData() {

        initPersonalInformation();
        initHonorWall();
    }

    /**
     * 渐变式ToolBar
     */
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

    /**
     * 获取个人信息网络请求
     */
    public void initPersonalInformation() {

        RestClient.getService().personalInformation().enqueue(new DataCallback<APIResponse<PersonInformationRequest>>() {

            @Override
            public void onDataResponse(Call<APIResponse<PersonInformationRequest>> call, Response<APIResponse<PersonInformationRequest>> response) {

                setPersonalInformation(call, response);
            }

            @Override
            public void onDataFailure(Call<APIResponse<PersonInformationRequest>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }

        });
    }

    /**
     * 初始化个人信息
     *
     * @param call
     * @param response
     */
    public void setPersonalInformation(Call<APIResponse<PersonInformationRequest>> call, Response<APIResponse<PersonInformationRequest>> response) {

        txtGoodAt.setText(response.body().getData().getGoodAt().trim());
        txtTeamNum.setText(response.body().getData().getTeamNum() + "");
        txtPhoneNumber.setText(response.body().getData().getPhone().trim());
        txtName.setText(response.body().getData().getName().trim());
        txtUserName.setText(response.body().getData().getUsername().trim());
        txtMajor.setText(response.body().getData().getMajor().trim());
        txtGrade.setText(response.body().getData().getGrade().trim());
        txtStudentId.setText(response.body().getData().getStudentId().trim());


        if (response.body().getData().getGender().equals("0")) {
            gender = 0;
            imgGender.setImageResource(R.drawable.ic_mine_man);
        } else if (response.body().getData().getGender().equals("1")) {
            gender = 1;
            imgGender.setImageResource(R.drawable.ic_mine_woman);
        }

        imgAvatarUrl = response.body().getData().getPicture();
        Glide.with(this).load(imgAvatarUrl).into(imgAvatar);

    }


    /**
     * 初始化荣誉墙
     */
    public void initHonorWall() {


        RestClient.getService().showHonorRequest().enqueue(new DataCallback<APIResponse<List<ShowHonorRequest>>>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse<List<ShowHonorRequest>>> call, Response<APIResponse<List<ShowHonorRequest>>> response) {

                showHonorRequestList = response.body().getData();

                for (int i = 0; i < showHonorRequestList.size(); i++) {

                    HonorCertificateModel honorCertificateAdd = new HonorCertificateModel();

                    honorCertificateAdd.setOrder(showHonorRequestList.get(i).getOrder());
                    honorCertificateAdd.setGloryName(showHonorRequestList.get(i).getGloryName());
                    honorCertificateAdd.setGloryTime(showHonorRequestList.get(i).getGloryTime());
                    honorCertificateAdd.setGloryPicUrl(showHonorRequestList.get(i).getGloryPicUrl());

                    honorCertificateModelList.add(honorCertificateAdd);
                }

                initGrid();

            }

            //请求失败时回调
            @Override
            public void onDataFailure(Call<APIResponse<List<ShowHonorRequest>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });
    }

    /**
     * 初始化Grid
     */
    public void initGrid() {

        GridHonorAdapter gridHonorAdapter = new GridHonorAdapter(getContext(), honorCertificateModelList, false);
        gridHonor.setAdapter(gridHonorAdapter);
        gridHonor.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), HonorInformationActivity.class);
                intent.putExtra("picUrl", honorCertificateModelList.get(position).getGloryPicUrl());
                intent.putExtra("honorName", honorCertificateModelList.get(position).getGloryName());
                intent.putExtra("honorTime", honorCertificateModelList.get(position).getGloryTime());
                startActivity(intent);

            }
        });
    }


    /**
     * 更多信息图标点击事件
     */
    @OnClick(R.id.title_mine_edit)
    public void onImgEditClicked() {

        showDialog();
    }

    /**
     * 显示右上角弹窗
     */
    public void showDialog() {

        final AlertDialog dialog = new AlertDialog.Builder(getContext(), R.style.dialog_bottom).create();
        dialog.show();
        dialog.getWindow().setContentView(R.layout.pop_window_edit);
        dialog.setCancelable(true);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.END | Gravity.TOP);
        window.getDecorView().setPadding(0, DensityUtil.dip2px(getContext(), 73), DensityUtil.dip2px(getContext(), 25), 0);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DensityUtil.dip2px(getContext(), 170);
        lp.height = DensityUtil.dip2px(getContext(), 300);
        window.setAttributes(lp);

        CardView txtEditInformation = (CardView) dialog.findViewById(R.id.card_update_information);
        CardView txtMineSetting = (CardView) dialog.findViewById(R.id.card_setting);
        CardView txtMineTeam = (CardView) dialog.findViewById(R.id.card_mine_team);

        txtEditInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                toUpdateInformationActivity();
            }
        });

        txtMineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        txtMineTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(getContext(), MineTeamListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void toUpdateInformationActivity() {

        Intent intent = new Intent(getContext(), UpdateInformationActivity.class);

        intent.putExtra("goodAt", txtGoodAt.getText().toString().trim());
        //intent.putExtra("teamNum",txtTeamNum.getText().toString().trim());
        intent.putExtra("phoneNumber", txtPhoneNumber.getText().toString().trim());
        intent.putExtra("name", txtName.getText().toString().trim());
        intent.putExtra("userName", txtUserName.getText().toString().trim());
        intent.putExtra("major", txtMajor.getText().toString().trim());
        intent.putExtra("grade", txtGrade.getText().toString().trim());
        intent.putExtra("studentId", txtStudentId.getText().toString().trim());
        intent.putExtra("avatarUrl", imgAvatarUrl);
        intent.putExtra("gender", gender);

        startActivity(intent);

    }


    /**
     * 查看头像大图点击事件
     */
    @OnClick(R.id.mine_avatar)
    public void onImgAvaterClicked() {

        showFullImg();
    }

    public void showFullImg() {

        View view = View.inflate(getContext(), R.layout.item_dialog_full_img, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_full_dialog);
        Glide.with(this).load(imgAvatarUrl).into(imageView);
        builder.show();

    }


}

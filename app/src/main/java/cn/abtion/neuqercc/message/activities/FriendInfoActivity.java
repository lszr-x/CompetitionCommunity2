package cn.abtion.neuqercc.message.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.message.models.AddFriendRequest;
import cn.abtion.neuqercc.mine.activities.HonorInformationActivity;
import cn.abtion.neuqercc.mine.adapters.GridHonorAdapter;
import cn.abtion.neuqercc.mine.models.PersonInformationResponse;
import cn.abtion.neuqercc.mine.models.ShowHonorResponse;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import cn.abtion.neuqercc.widget.GradientScrollView;
import cn.abtion.neuqercc.widget.HonorGridView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author fhyPayaso
 * @since 2018/1/23 on 下午1:47
 * fhyPayaso@qq.com
 */
public class FriendInfoActivity extends NoBarActivity {



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


    public static String mFriendPhoneNumber;
    private List<ShowHonorResponse> showHonorResponseList = new ArrayList<ShowHonorResponse>();
    private PersonInformationResponse informationResponse;
    private String imgAvatarUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_info;
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




    /**
     * 获取个人信息网络请求
     */
    public void initPersonalInformation() {

        RestClient.getService().personalInformation(mFriendPhoneNumber).enqueue(new
                                                                       DataCallback<APIResponse<PersonInformationResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<PersonInformationResponse>> call, Response<APIResponse<PersonInformationResponse>> response) {

                informationResponse = response.body().getData();
                setPersonalInformation();
            }

            @Override
            public void onDataFailure(Call<APIResponse<PersonInformationResponse>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }

        });
    }


    /**
     * 初始化个人信息
     */
    public void setPersonalInformation() {

        txtPhoneNumber.setText(informationResponse.getPhone().trim());
        txtUserName.setText(informationResponse.getUsername().trim());
        txtName.setText(informationResponse.getName().trim());
        txtGoodAt.setText(informationResponse.getGoodAt().trim());
        txtMajor.setText(informationResponse.getMajor().trim());
        txtTeamNum.setText(String.valueOf(informationResponse.getTeamNum()));
        txtGrade.setText(String.valueOf(informationResponse.getGrade()));
        txtStudentId.setText(String.valueOf(informationResponse.getStudentId()));

        if (informationResponse.getGender() == Config.FLAG_BOY) {
            imgGender.setImageResource(R.drawable.ic_mine_man);
        } else if (informationResponse.getGender() == Config.FLAG_GIRL) {
            imgGender.setImageResource(R.drawable.ic_mine_woman);
        }

        imgAvatarUrl = informationResponse.getPicture();
        Glide.with(this).load(imgAvatarUrl).into(imgAvatar);
    }


    /**
     * 初始化荣誉墙
     */
    public void initHonorWall() {

        RestClient.getService().showHonorRequest(LoginActivity.phoneNumber).enqueue(new DataCallback<APIResponse<List<ShowHonorResponse>>>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse<List<ShowHonorResponse>>> call, Response<APIResponse<List<ShowHonorResponse>>> response) {

                showHonorResponseList = response.body().getData();
                initGrid();
            }

            //请求失败时回调
            @Override
            public void onDataFailure(Call<APIResponse<List<ShowHonorResponse>>> call, Throwable t) {

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

        GridHonorAdapter gridHonorAdapter = new GridHonorAdapter(this, showHonorResponseList, false);
        gridHonor.setAdapter(gridHonorAdapter);
        gridHonor.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ShowHonorResponse showHonorResponse = showHonorResponseList.get(position);
                Intent intent = new Intent(FriendInfoActivity.this, HonorInformationActivity.class);
                intent.putExtra(Config.INTENT_EXTRA_HONOR, new Gson().toJson(showHonorResponse));
                startActivity(intent);
            }
        });
    }





    public static void startActivity(Context context, String friendPhoneNumber) {
        context.startActivity(new Intent(context, FriendInfoActivity.class));
        FriendInfoActivity.mFriendPhoneNumber = friendPhoneNumber;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add_friend)
    public void onViewClicked() {


        RestClient
                .getService()
                .addFriend(new AddFriendRequest(LoginActivity.phoneNumber, mFriendPhoneNumber))
                .enqueue(new DataCallback<APIResponse>() {
                    @Override
                    public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {


                        ToastUtil.showToast("添加成功");
                        finish();
                    }

                    @Override
                    public void onDataFailure(Call<APIResponse> call, Throwable t) {

                    }

                    @Override
                    public void dismissDialog() {

                    }
                });
    }
}

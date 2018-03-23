package cn.abtion.neuqercc.message.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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


    public static String mFriendPhoneNumber;
    @BindView(R.id.friend_header_bg)
    LinearLayout friendHeaderBg;
    @BindView(R.id.img_friend_gender)
    ImageView imgFriendGender;
    @BindView(R.id.friend_dividing_one)
    View friendDividingOne;
    @BindView(R.id.friend_dividing_two)
    View friendDividingTwo;
    @BindView(R.id.friend_direction)
    TextView friendDirection;
    @BindView(R.id.txt_friend_good_at)
    TextView txtFriendGoodAt;
    @BindView(R.id.friend_team)
    TextView friendTeam;
    @BindView(R.id.txt_friend_team_num)
    TextView txtFriendTeamNum;
    @BindView(R.id.rlayout_friend_team)
    RelativeLayout rlayoutFriendTeam;
    @BindView(R.id.friend_information)
    RelativeLayout friendInformation;
    @BindView(R.id.friend_avatar)
    CircleImageView friendAvatar;
    @BindView(R.id.txt_friend_user_name)
    TextView txtFriendUserName;
    @BindView(R.id.ic_name)
    ImageView icName;
    @BindView(R.id.ic_phone)
    ImageView icPhone;
    @BindView(R.id.txt_friend_name)
    TextView txtFriendName;
    @BindView(R.id.txt_friend_phone_number)
    TextView txtFriendPhoneNumber;
    @BindView(R.id.friend_phone)
    RelativeLayout friendPhone;
    @BindView(R.id.friend_profession)
    TextView friendProfession;
    @BindView(R.id.friend_grade)
    TextView friendGrade;
    @BindView(R.id.friend_id)
    TextView friendId;
    @BindView(R.id.txt_friend_major)
    TextView txtFriendMajor;
    @BindView(R.id.txt_friend_grade)
    TextView txtFriendGrade;
    @BindView(R.id.txt_friend_student_id)
    TextView txtFriendStudentId;
    @BindView(R.id.friend_message)
    RelativeLayout friendMessage;
    @BindView(R.id.mine_txt_honor)
    TextView mineTxtHonor;
    @BindView(R.id.mine_grid_honor)
    HonorGridView mineGridHonor;
    @BindView(R.id.mine_honor)
    RelativeLayout mineHonor;
    @BindView(R.id.scroll_friend_information)
    GradientScrollView scrollFriendInformation;
    @BindView(R.id.btn_add_friend)
    Button btnAddFriend;

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

        RestClient.getService().personalInformation(mFriendPhoneNumber).enqueue(new DataCallback<APIResponse<PersonInformationResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<PersonInformationResponse>> call,
                                       Response<APIResponse<PersonInformationResponse>> response) {

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

        txtFriendPhoneNumber.setText(informationResponse.getPhone().trim());
        txtFriendUserName.setText(informationResponse.getUsername().trim());
        txtFriendName.setText(informationResponse.getName().trim());
        txtFriendGoodAt.setText(informationResponse.getGoodAt().trim());
        txtFriendMajor.setText(informationResponse.getMajor().trim());
        txtFriendTeamNum.setText(String.valueOf(informationResponse.getTeamNum()));
        txtFriendGrade.setText(String.valueOf(informationResponse.getGrade()));
        txtFriendStudentId.setText(String.valueOf(informationResponse.getStudentId()));

        if (informationResponse.getGender() == Config.FLAG_BOY) {
            imgFriendGender.setImageResource(R.drawable.ic_mine_man);
        } else if (informationResponse.getGender() == Config.FLAG_GIRL) {
            imgFriendGender.setImageResource(R.drawable.ic_mine_woman);
        }

        imgAvatarUrl = informationResponse.getPicture();
        if (imgAvatarUrl != null) {
            Glide.with(this).load(imgAvatarUrl).into(friendAvatar);
        }
    }


    /**
     * 初始化荣誉墙
     */
    public void initHonorWall() {

        RestClient.getService().showHonorRequest(mFriendPhoneNumber).enqueue(new DataCallback<APIResponse<List<ShowHonorResponse>>>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse<List<ShowHonorResponse>>> call,
                                       Response<APIResponse<List<ShowHonorResponse>>> response) {

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
        mineGridHonor.setAdapter(gridHonorAdapter);
        mineGridHonor.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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

package cn.abtion.neuqercc.network;

import java.util.List;
import java.util.Map;

import cn.abtion.neuqercc.account.models.LoginRequest;
import cn.abtion.neuqercc.account.models.UpdatePasswordRequest;
import cn.abtion.neuqercc.account.models.RegisterRequest;
import cn.abtion.neuqercc.account.models.SmsRequest;
import cn.abtion.neuqercc.mine.activities.MineTeamIfromationActivity;
import cn.abtion.neuqercc.mine.activities.UpdateInformationActivity;
import cn.abtion.neuqercc.mine.models.FeedBackRequest;
import cn.abtion.neuqercc.mine.models.GoodAtRequest;
import cn.abtion.neuqercc.mine.models.MineTeamInformationRequest;
import cn.abtion.neuqercc.mine.models.MineTeamListRequest;
import cn.abtion.neuqercc.mine.models.PersonInformationRequest;
import cn.abtion.neuqercc.mine.models.ShowHonorRequest;
import cn.abtion.neuqercc.mine.models.StudentGradeRequest;
import cn.abtion.neuqercc.mine.models.TeamMemberRequest;
import cn.abtion.neuqercc.mine.models.UpdatePersonInformationRequest;
import cn.abtion.neuqercc.mine.models.UpdateTeamInformationRequest;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * retrofit service interface.
 * @author abtion.
 * @since 17/9/22 18:04.
 * email caiheng@hrsoft.net
 */

public interface APIService {
    /**
     * 登录
     */
    @POST("saiyou/public/index.php/login")
    Call<APIResponse> login(@Body LoginRequest loginRequest);

    /**
     * 验证码
     */

    @POST("saiyou/public/index.php/sms")
    Call<APIResponse> captch(@Body SmsRequest smsRequest);

    /**
     * 注册
     */
    @POST("saiyou/public/index.php/register")
    Call<APIResponse> register(@Body RegisterRequest registerRequest);

    /**
     * 重新设置密码
     */
    @POST("saiyou/public/index.php/forgot")
    Call<APIResponse> updatePassword(@Body UpdatePasswordRequest updatePasswordRequest);


    /**
     * 获取个人资料
     */
    @GET("saiyou/public/index.php/user/show?phone=15076035390")
    Call<APIResponse<PersonInformationRequest>> personalInformation();


    /**
     * 提交修改资料
     * @param updatePersonInformationRequest
     * @return
     */

    @Multipart
    @POST("saiyou/public/index.php/user/edit")
    Call<APIResponse> updatePersonalInformation(@Body UpdatePersonInformationRequest updatePersonInformationRequest,
                                                @Part RequestBody requestBody);


    /**
     * 提交意见反馈
     * @param feedBackRequest
     * @return
     */
    @POST("saiyou/public/index.php/feedback")
    Call<APIResponse> feedBack(@Body FeedBackRequest feedBackRequest);


    /**
     * 我的队伍列表
     * @return
     */
    @GET("saiyou/public/index.php/user/team_list?phone=15076035390")
    Call<APIResponse<List<MineTeamListRequest>>> mineTeamList();

    /**
     * 我的队伍信息
     */
    @GET("saiyou/public/index.php/team/show?id=1")
    Call<APIResponse<MineTeamInformationRequest>> mineTeamInformation();

    /**
     * 我的队伍成员
     * @return
     */
    @GET("saiyou/public/index.php/team/member_show?id=1")
    Call<APIResponse<List<TeamMemberRequest>>> mineTeamMember();

    /**
     * 修改我的队伍信息
     * @param updateTeamInformationRequest
     * @return
     */
    @POST("saiyou/public/index.php/team/edit")
    Call<APIResponse> updateTeamInformation(UpdateTeamInformationRequest updateTeamInformationRequest);



    /**
     * 获取荣誉墙证书
     * @return
     */

    @GET("saiyou/public/index.php/user/glory_show?phone=15076035390")
    Call<APIResponse<List<ShowHonorRequest>>> showHonorRequest();





    /**
     * 获取默认年级选项
     * @return
     */
    @GET("saiyou/public/index.php/other/year_show")
    Call<APIResponse<List<StudentGradeRequest>>> studentGradeRequest();


    /**
     * 获取默认擅长领域
     * @return
     */
    @GET("saiyou/public/index.php/other/field_show")
    Call<APIResponse<List<GoodAtRequest>>> goodAtRequest();



}

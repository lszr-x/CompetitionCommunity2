package cn.abtion.neuqercc.mine.models;

/**
 * 添加队伍成员请求体
 *
 * @author FanHongyu.
 * @since 18/3/26 13:43.
 * email fanhongyu@hrsoft.net.
 */

public class AddTeamMemberRequest {


    private String team_id;
    private String phone;
    private String team_position;


    public AddTeamMemberRequest(String team_id, String phone, String team_position) {
        this.team_id = team_id;
        this.phone = phone;
        this.team_position = team_position;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTeam_position() {
        return team_position;
    }

    public void setTeam_position(String team_position) {
        this.team_position = team_position;
    }
}

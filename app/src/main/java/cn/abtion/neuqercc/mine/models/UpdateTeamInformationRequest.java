package cn.abtion.neuqercc.mine.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/12/10 21:54
 * email fhyPayaso@qq.com
 */

public class UpdateTeamInformationRequest extends BaseModel {

    private int team_id;
    private String team_name;
    private String competition_desc;
    private String declaration;
    private String good_at;


    public void UpdateTeamInformationRequest() {

    }


    /**
     * 获取队伍id
     * @return
     */
    public int getTeamId() {
        return team_id;
    }

    public void setTeamId(int teamId) {
        this.team_id = teamId;
    }


    /**
     * 获取队伍名称
     * @return
     */
    public String getTeamName() {
        return  team_name;
    }

    public void setTeamName(String teamName) {
        this.team_name = teamName;
    }


    /**
     * 获取比赛名称
     * @return
     */
    public String getCompetitionDesc() {
        return competition_desc;
    }

    public void setCompetitionDesc(String competitionDesc) {
        this.competition_desc = competitionDesc;
    }


    /**
     * 获取队伍宣言
     * @return
     */
    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    /**
     * 获取擅长方向
     * @return
     */
    public String getGoodAt() {
        return good_at;
    }

    public void setGoodAt(String goodAt) {

        this.good_at = goodAt;
    }
}

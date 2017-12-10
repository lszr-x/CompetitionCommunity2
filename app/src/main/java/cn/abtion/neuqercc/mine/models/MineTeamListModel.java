package cn.abtion.neuqercc.mine.models;

/**
 * @author fhyPayaso
 * @since 2017/11/21 13:28
 * email fhyPayaso@qq.com
 */

public class MineTeamListModel {

    private int id;
    private String teamName;
    private String competitionName;
    private int number;
    //private String position;


    public MineTeamListModel(int id,String teamName,String competitionName ,int number ) {

        this.id = id;
        this.teamName = teamName;
        this.competitionName = competitionName;
        this.number = number;
        //this.position = position;

    }


    /**
     * 获取队伍id
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /**
     * 获得队伍名称
     * @return
     */
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String title) {
        this.teamName = title;
    }


    /**
     * 获得比赛名称
     * @return
     */

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }


    /**
     * 获得成员数量
     * @return
     */
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

//    public String getPosition() {
//        return position;
//    }
//
//    public void setPosition(String position) {
//        this.position = position;
//    }

}

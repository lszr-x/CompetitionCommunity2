package cn.abtion.neuqercc.message.models;

/**
 * 有关通知的响应体和请求体
 *
 * @author FanHongyu.
 * @since 18/3/25 21:01.
 * email fanhongyu@hrsoft.net.
 */

public class NoticeModel {


    private String noticeId;
    private String sendNum;
    private String recvNum;
    private int teamId;
    private String teamName;
    private int type;
    private String userName;


    public NoticeModel(String noticeId, String sendNum, String recvNum, int teamId, String teamName, int type, String userName) {
        this.noticeId = noticeId;
        this.sendNum = sendNum;
        this.recvNum = recvNum;
        this.teamId = teamId;
        this.teamName = teamName;
        this.type = type;
        this.userName = userName;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getSendPhoneNumber() {
        return sendNum;
    }

    public void setSendPhoneNumber(String sendPhoneNumber) {
        this.sendNum = sendPhoneNumber;
    }

    public String getRecivePhoneNumber() {
        return recvNum;
    }

    public void setRecivePhoneNumber(String recivePhoneNumber) {
        this.recvNum = recivePhoneNumber;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getNoticeType() {
        return type;
    }

    public void setNoticeType(int noticeType) {
        this.type = noticeType;
    }

    public String getSendUserName() {
        return userName;
    }

    public void setSendUserName(String sendUserName) {
        this.userName = sendUserName;
    }
}

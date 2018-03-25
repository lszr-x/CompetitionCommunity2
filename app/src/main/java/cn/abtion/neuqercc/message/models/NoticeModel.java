package cn.abtion.neuqercc.message.models;

/**
 * 有关通知的响应体和请求体
 *
 * @author FanHongyu.
 * @since 18/3/25 21:01.
 * email fanhongyu@hrsoft.net.
 */

public class NoticeModel {


    private int noticeId;
    private String sendPhoneNumber;
    private String recivePhoneNumber;
    private int teamId;
    private String teamName;
    private int noticeType;
    private String sendUserName;


    public NoticeModel(int noticeId, String sendPhoneNumber, String recivePhoneNumber, int teamId, String teamName,
                       int noticeType, String sendUserName) {
        this.noticeId = noticeId;
        this.sendPhoneNumber = sendPhoneNumber;
        this.recivePhoneNumber = recivePhoneNumber;
        this.teamId = teamId;
        this.teamName = teamName;
        this.noticeType = noticeType;
        this.sendUserName = sendUserName;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getSendPhoneNumber() {
        return sendPhoneNumber;
    }

    public void setSendPhoneNumber(String sendPhoneNumber) {
        this.sendPhoneNumber = sendPhoneNumber;
    }

    public String getRecivePhoneNumber() {
        return recivePhoneNumber;
    }

    public void setRecivePhoneNumber(String recivePhoneNumber) {
        this.recivePhoneNumber = recivePhoneNumber;
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
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }
}

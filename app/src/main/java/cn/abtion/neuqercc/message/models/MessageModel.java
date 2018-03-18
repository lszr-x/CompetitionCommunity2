package cn.abtion.neuqercc.message.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午1:31
 * fhyPayaso@qq.com
 */
public class MessageModel extends BaseModel{



    private String imgRes;
    private String userName;
    private String lastMessage;
    private String lastTime;

    public MessageModel(String imgRes, String userName, String lastMessage, String lastTime) {
        this.imgRes = imgRes;
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
    }

    public String getImgRes() {
        return imgRes;
    }

    public void setImgRes(String imgRes) {
        this.imgRes = imgRes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}

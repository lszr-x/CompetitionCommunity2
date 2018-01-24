package cn.abtion.neuqercc.message.models;

/**
 * @author fhyPayaso
 * @since 2018/1/23 on 下午12:27
 * fhyPayaso@qq.com
 */
public class FriendModel {


    private String imgRes;
    private String friendName;

    public FriendModel(String imgRes, String friendName) {
        this.imgRes = imgRes;
        this.friendName = friendName;
    }


    public String getImgRes() {
        return imgRes;
    }

    public void setImgRes(String imgRes) {
        this.imgRes = imgRes;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}

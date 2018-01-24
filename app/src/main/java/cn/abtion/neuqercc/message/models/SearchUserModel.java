package cn.abtion.neuqercc.message.models;

/**
 * @author FanHongyu.
 * @since 18/1/24 21:31.
 * email fanhongyu@hrsoft.net.
 */

public class SearchUserModel {


    private String imgRes;
    private String userName;

    public SearchUserModel(String imgRes, String userName) {
        this.imgRes = imgRes;
        this.userName = userName;
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
}

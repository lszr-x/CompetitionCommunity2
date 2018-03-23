package cn.abtion.neuqercc.message.models;

/**
 * @author fhyPayaso
 * @since 2018/1/23 on 下午12:27
 * fhyPayaso@qq.com
 */
public class FriendModel {


    private String pic;
    private String username;
    private String phone;


    public FriendModel(String pic, String username, String phone) {
        this.pic = pic;
        this.username = username;
        this.phone = phone;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

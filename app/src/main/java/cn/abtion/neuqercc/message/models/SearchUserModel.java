package cn.abtion.neuqercc.message.models;

/**
 * @author FanHongyu.
 * @since 18/1/24 21:31.
 * email fanhongyu@hrsoft.net.
 */

public class SearchUserModel {


    private String pic;
    private String username;
    private String phone;


    public SearchUserModel(String pic, String username, String phone) {
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

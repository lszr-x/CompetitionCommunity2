package cn.abtion.neuqercc.message.models;

/**
 * @author FanHongyu.
 * @since 18/3/17 16:33.
 * email fanhongyu@hrsoft.net.
 */

public class AddFriendRequest {


    private String phone;
    private String friend_phone;


    public AddFriendRequest(String phone, String friend_phone) {
        this.phone = phone;
        this.friend_phone = friend_phone;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFriend_phone() {
        return friend_phone;
    }

    public void setFriend_phone(String friend_phone) {
        this.friend_phone = friend_phone;
    }
}

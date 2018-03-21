package cn.abtion.neuqercc.account.models;

/**
 * @author FanHongyu.
 * @since 18/3/21 14:47.
 * email fanhongyu@hrsoft.net.
 */

public class CheckTokenResponse {

    private String phone;


    public CheckTokenResponse(String phone) {
        this.phone = phone;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

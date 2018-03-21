package cn.abtion.neuqercc.account.models;

/**
 * @author FanHongyu.
 * @since 18/3/21 14:21.
 * email fanhongyu@hrsoft.net.
 */

public class TokenResponse {

    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

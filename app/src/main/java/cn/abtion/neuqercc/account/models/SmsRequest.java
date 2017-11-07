package cn.abtion.neuqercc.account.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * Created by 41061 on 2017/10/21.
 */

public class SmsRequest extends BaseModel{

    private String phone;

    private String captcah;

    public SmsRequest(){

    }

    public String getCaptcah() {
        return captcah;
    }

    public void setCaptcah(String captcah) {
        this.captcah = captcah;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

package cn.abtion.neuqercc.message.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午1:31
 * fhyPayaso@qq.com
 */
public class MessageModel extends BaseModel{



    private String message;

    public MessageModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package cn.abtion.neuqercc.mine.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/12/7 15:23
 * email fhyPayaso@qq.com
 */

public class ShowHonorRequest extends BaseModel {

    private int order;
    private String glory_name;
    private String glory_time;
    private String glory_pic;


    public void ShowHonorRequest() {

    }


    /**
     * 获取荣誉序号
     * @return
     */

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    /**
     * 获取赛事名称
     * @return
     */

    public String getGloryName() {
        return glory_name;
    }

    public void setGloryName(String gloryName){
        this.glory_name = gloryName;
    }


    /**
     * 获取获奖时间
     * @return
     */

    public String getGloryTime() {
        return glory_time;
    }

    public void setGloryTime(String gloryTime) {

        this.glory_time = gloryTime;
    }


    /**
     * 获取证书图片url
     * @return
     */

    public String getGloryPicUrl() {
        return glory_pic;
    }

    public void setGloryPicUrl(String gloryPicUrl) {
        this.glory_pic = gloryPicUrl;
    }
}

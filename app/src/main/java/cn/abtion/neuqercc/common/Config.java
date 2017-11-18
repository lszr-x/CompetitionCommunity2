package cn.abtion.neuqercc.common;

/**
 * all of the configuration for this application
 * @author abtion.
 * @since 17/9/22 17:52.
 * email caiheng@hrsoft.net
 */

public class Config {
    /**
     * 验证手机号正则
     */
    public static final String MOBILE_REGEX = "[1][3,4,5,7,8][0-9]{9}$";

    /**
     * 验证邮箱正则
     */
    public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * APP Server 网络请求BaseUrl
     */
    /**
     * public static final String APP_SERVER_BASE_URL = "http://oj.marklux.cn/";
     */
    public static final String APP_SERVER_BASE_URL = "http://www.thmaoqiu.cn/";

    /**
     * APP Server 网络请求连接超时时间，单位：s
     */
    public static final int APP_SERVER_CONNECT_TIME_OUT = 15;

    /**
     * 启动页面到首页启动延时，单位：毫秒
     */
    public static final int SPLASH_GO_MAIN_DELAY = 1500;

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LIMIT =6;
    public static final int PASSWORD_MAX_LIMIT =16;


    public static final String KEY_MAIN_FLAG = "keyMainFlag";


    public static final int FLING_MIN_DISTANCE=100;
    public static final int FLING_MIN_VELOCITY=10;


    /**
     * 倒计时及每次回调时间
     */

    public static final int COUNT_DOWN_TIME_TOTAL=60000;
    public static final int COUNT_DOWN_TIME_PER=1000;

}

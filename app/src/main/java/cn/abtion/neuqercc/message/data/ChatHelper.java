package cn.abtion.neuqercc.message.data;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.Iterator;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;
import static cn.abtion.neuqercc.BuildConfig.DEBUG;

/**
 * @author FanHongyu.
 * @since 18/3/17 15:46.
 * email fanhongyu@hrsoft.net.
 */

public class ChatHelper {


    /**
     * 在Application的onCreate方法中初始化环信sdk
     */
    public static void initEM(Context appContext) {
        int pid = android.os.Process.myPid();
        String processName = getProcessAppName(pid, appContext);
        if (processName == null || !processName.equalsIgnoreCase(appContext.getPackageName())) {
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMOptions options = new EMOptions();
        options.setAutoLogin(false);
        EMClient.getInstance().init(appContext, options);
        EMClient.getInstance().setDebugMode(DEBUG);
    }


    /**
     * 获取processAppName,防止sdk被重复初始化
     *
     * @param pID pid
     * @return name
     */
    private static String getProcessAppName(int pID, Context appContext) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) appContext.getSystemService(ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) iterator.next();
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                Log.d("Process", "Error>> :" + e.toString());
            }
        }
        return processName;
    }


    public static void loginEM(String username, String password) {

    }


}

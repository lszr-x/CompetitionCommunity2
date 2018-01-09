package cn.abtion.neuqercc.message.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.message.fragments.ChatFragment;
import cn.abtion.neuqercc.message.fragments.FriendsFragment;
import cn.abtion.neuqercc.message.fragments.GroupFragment;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午12:53
 * fhyPayaso@qq.com
 */
public class MessageViewPagerAdapter extends FragmentPagerAdapter {


    private String[] titles = new String[]{"消息", "好友", "群组"};

    private ChatFragment chatFragment;
    private FriendsFragment friendsFragment;
    private GroupFragment groupFragment;
    private Fragment currentFrgment;


    public MessageViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                if (chatFragment == null) {
                    chatFragment = new ChatFragment();
                }
                currentFrgment = chatFragment;
                break;
            case 1:
                if (friendsFragment == null) {
                    friendsFragment = new FriendsFragment();
                }
                currentFrgment = friendsFragment;
                break;
            case 2:
                if (groupFragment == null) {
                    groupFragment = new GroupFragment();
                }
                currentFrgment = groupFragment;
                break;
            default:
                break;
        }
        return currentFrgment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }


}

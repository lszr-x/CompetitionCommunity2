package cn.abtion.neuqercc.message.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.neuqercc.message.fragments.ChatListFragment;
import cn.abtion.neuqercc.message.fragments.FriendListFragment;
import cn.abtion.neuqercc.message.fragments.GroupListFragment;

/**
 * @author fhyPayaso
 * @since 2018/1/7 on 上午12:53
 * fhyPayaso@qq.com
 */
public class MessageViewPagerAdapter extends FragmentPagerAdapter {


    private String[] titles = new String[]{"消息", "好友", "群组"};

    private ChatListFragment mChatListFragment;
    private FriendListFragment mFriendListFragment;
    private GroupListFragment mGroupListFragment;
    private Fragment currentFrgment;


    public MessageViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                if (mChatListFragment == null) {
                    mChatListFragment = new ChatListFragment();
                }
                currentFrgment = mChatListFragment;
                break;
            case 1:
                if (mFriendListFragment == null) {
                    mFriendListFragment = new FriendListFragment();
                }
                currentFrgment = mFriendListFragment;
                break;
            case 2:
                if (mGroupListFragment == null) {
                    mGroupListFragment = new GroupListFragment();
                }
                currentFrgment = mGroupListFragment;
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

package cn.abtion.neuqercc.home.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.neuqercc.home.fragments.HomeFragment;
import cn.abtion.neuqercc.message.MessageFragment;
import cn.abtion.neuqercc.mine.MineFragment;
import cn.abtion.neuqercc.team.fragments.TeamFragment;

/**
 * @author fhyPayaso
 * @since 2017/11/12 14:09
 * email fhyPayaso@qq.com
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter{

    private HomeFragment homeFragment;
    private TeamFragment teamFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private Fragment currentFragment;


    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {


        switch (position){

            case 0:
                if (homeFragment==null){
                    homeFragment = new HomeFragment();
                }
                currentFragment = homeFragment;
                break;
            case 1:
                if (teamFragment==null){
                    teamFragment=new TeamFragment();
                }
                currentFragment=teamFragment;
                break;
            case 2:
                if (messageFragment==null){
                    messageFragment = new MessageFragment();
                }
                currentFragment = messageFragment;
                break;
            case 3:
                if (mineFragment==null){
                    mineFragment=new MineFragment();
                }
                currentFragment=mineFragment;
                break;

            default:

        }

        return currentFragment;
    }


    @Override
    public  int getCount(){
        return 4;
    }


}

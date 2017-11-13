package cn.abtion.neuqercc.home.adapters;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import cn.abtion.neuqercc.home.fragments.DetailsFragment;
import cn.abtion.neuqercc.home.fragments.RaidersFragment;

/**
 * @author fhyPayaso
 * @since 2017/11/7 16:56
 * email fhyPayaso@qq.com
 */

public class CompetitionViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"赛事详情","大神攻略"};
    private DetailsFragment detailsFragment;
    private RaidersFragment raidersFragment;
    private android.support.v4.app.Fragment currentFragment;

    public CompetitionViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {


        switch (position){
            case 0:
                if (detailsFragment==null){
                    detailsFragment = new DetailsFragment();
                }
                currentFragment = detailsFragment;
                break;
            case 1:
                if (raidersFragment==null){
                    raidersFragment=new RaidersFragment();
                }
                currentFragment=raidersFragment;
                break;
            default:

        }

        return currentFragment;
    }


    @Override
    public  int getCount(){
        return 2;
    }

    @Override
    public  CharSequence getPageTitle(int position){
        return titles[position];
    }

}

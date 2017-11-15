package cn.abtion.neuqercc.team.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.neuqercc.home.fragments.DetailsFragment;
import cn.abtion.neuqercc.home.fragments.RaidersFragment;
import cn.abtion.neuqercc.team.fragments.AllTeamFragment;
import cn.abtion.neuqercc.team.fragments.RecommendTeamFragment;

/**
 * @author lszr
 * @since 2017/11/14 下午8:25
 * email wsyglszr@gmail.com
 */

public class TeamViewPagerAdapter extends FragmentPagerAdapter {


    private String[] titles = new String[]{"全部队伍", "推荐队伍"};
    private AllTeamFragment allTeamFragment;
    private RecommendTeamFragment recommendTeamFragment;
    private android.support.v4.app.Fragment currentFragment;

    public TeamViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if (allTeamFragment==null){
                    allTeamFragment = new AllTeamFragment();
                }
                currentFragment = allTeamFragment;
                break;
            case 1:
                if (recommendTeamFragment==null){
                    recommendTeamFragment=new RecommendTeamFragment();
                }
                currentFragment=recommendTeamFragment;
                break;
            default:
        }
        return currentFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public  CharSequence getPageTitle(int position){
        return titles[position];
    }


}

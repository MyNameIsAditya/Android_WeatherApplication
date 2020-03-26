package com.example.weatherapplication;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    private ArrayList<Fragment> FragmentList = new ArrayList<Fragment>();
    private ArrayList<String> FragmentTitleList = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager supportFragmentManager)
    {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0)
        {
            return new MapTabFragment();
        }
        else if (position == 1)
        {
            return new WeatherTabFragment();
        }
        else
        {
            return FragmentList.get(position);
        }
    }

    @Override
    public int getCount()
    {
        return FragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return FragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title)
    {
        FragmentList.add(fragment);
        FragmentTitleList.add(title);
    }
}


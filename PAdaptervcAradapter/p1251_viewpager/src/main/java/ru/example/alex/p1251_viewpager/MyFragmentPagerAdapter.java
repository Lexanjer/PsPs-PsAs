package ru.example.alex.p1251_viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.io.Serializable;

import static ru.example.alex.p1251_viewpager.MainActivity.PAGE_COUNT;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter implements Serializable {


    MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position);
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


    @Override
    public CharSequence getPageTitle(int position) {

        //  Log.d(TAG, "------------------------"+title);
        return DataBase.getCheckName(position);
    }
}
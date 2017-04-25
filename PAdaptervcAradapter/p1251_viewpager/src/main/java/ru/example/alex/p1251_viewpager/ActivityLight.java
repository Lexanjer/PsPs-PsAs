package ru.example.alex.p1251_viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class ActivityLight extends AppCompatActivity {

    static int PAGE_COUNT;
    private DataBase dataBase;
    public final static String DATA_BASE = "database";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppRed);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Log.d(MainActivity.TAG, "Activiti start1");
        } else {
            Log.d(MainActivity.TAG, "Activiti start no One");
        }

        initToolbar();

        Intent intent = getIntent();
        dataBase = (DataBase) intent.getSerializableExtra(MainActivity.DATA_BASE);

        if(dataBase == null) {
            dataBase = new DataBase(this);
        }


        PAGE_COUNT = dataBase.getCount();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(MainActivity.TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_nametb);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.restart:
                        goToActivityLight();
                        finish();
                        break;
                    case R.id.changetheme:
                        goToActivityDark();
                        finish();
                        break;


                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    public void goToActivityLight() {
        Intent intent = new Intent(this, ActivityLight.class);
        intent.putExtra(DATA_BASE, dataBase);
        startActivity(intent);

    }

    public void goToActivityDark() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(DATA_BASE, dataBase);
        startActivity(intent);

    }

  /*  private class MyFragmentPagerAdapter extends FragmentPagerAdapter {


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
    }*/

}

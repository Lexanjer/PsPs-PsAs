package ru.example.alex.p1251_viewpager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "myLogs";
    public final static String DATA_BASE = "database";
    static int PAGE_COUNT;
    private DataBase dataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Log.d(TAG, "MainActivity: onCreate()");

        initToolbar();
        newInstance();


    }

    public void goToActivityLight() {

        Intent activityLight = new Intent(this, ActivityLight.class);
        activityLight.putExtra(DATA_BASE, dataBase);
        startActivity(activityLight);
    }

    public void goToActivityDark() {

        Intent activityDark = new Intent(this, MainActivity.class);
        activityDark.putExtra(DATA_BASE, dataBase);
        startActivity(activityDark);
    }


    private void newInstance() {



        Intent intent = getIntent();
        dataBase = (DataBase) intent.getSerializableExtra(ActivityLight.DATA_BASE);

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
                Log.d(TAG, "onPageSelected, position = " + position);
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
                        goToActivityDark();
                        finish();
                        break;
                    case R.id.changetheme:
                        goToActivityLight();
                        finish();
                        break;
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy()");

    }
}

package ru.example.alex.checkbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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

        initToolbar();
        Intent intent = getIntent();
        dataBase = (DataBase) intent.getSerializableExtra(ActivityDark.DATA_BASE);

        if(dataBase == null) {
            dataBase = new DataBase(this);
        }


        PAGE_COUNT = dataBase.getCount();

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.restart:
//                        goToActivityLight();
//                        finish();
                        break;
                    case R.id.changetheme:
//                        goToActivityDark();
//                        finish();
                        break;


                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }
}

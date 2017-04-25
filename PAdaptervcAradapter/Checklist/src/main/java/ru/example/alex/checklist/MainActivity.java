package ru.example.alex.checklist;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<CheckItem> checkList = new ArrayList();
    ListView listView;
    final String LOG_TAG = "myLogs";
    private CheckAdapter checkAdapter;
    static Intent mainActivityIntent;
    Bundle savedInstanceState;

    //  Log.d(LOG_TAG, "itemSelect: position = всем привет");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (this.savedInstanceState != null){
            savedInstanceState = this.savedInstanceState;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageView = (ImageView) findViewById(R.id.imageView);
       // imageView.setImageResource(R.mipmap.android_black);
        Log.d(LOG_TAG, "itemSelect: position = всем привет");
        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        listView = (ListView) findViewById(R.id.listView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainActivityIntent = getIntent();


                Intent intent = new Intent(MainActivity.this, Activity2.class);

                startActivity(intent);
                Log.d(LOG_TAG, "CLICK float Button ");
            }
        });

        // создаем адаптер
   //     checkAdapter = (CheckAdapter) getLastNonConfigurationInstance();
        checkAdapter = new CheckAdapter(this, R.layout.item, checkList);
        // устанавливаем адаптер
        listView.setAdapter(checkAdapter);

        AdapterView.OnItemClickListener itemListener = (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long l) {
                if (checkAdapter.isSpecialItem(position))
                    checkAdapter.removeSpecialItem(position);
                else
                    checkAdapter.addSpecialItem(position);
                checkAdapter.notifyDataSetChanged();

                if (l == 0){

                   open( "data1.json");
                    checkAdapter.notifyDataSetChanged();
                }
            }
        });
        listView.setOnItemClickListener(itemListener);


        Log.d(LOG_TAG, " всем привет list otrabotal");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("SpecialItem 0", checkAdapter.isSpecialItem(0));
        outState.putBoolean("SpecialItem 1", checkAdapter.isSpecialItem(1));
        outState.putBoolean("SpecialItem 2", checkAdapter.isSpecialItem(2));
        outState.putBooleanArray("specialItemArray",checkAdapter.getSpecialItemArray());
        Log.d(LOG_TAG, "onSaveInstanceState");
        savedInstanceState = outState;

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        checkAdapter.setSpecialItem(0,savedInstanceState.getBoolean("SpecialItem 0"));
        checkAdapter.setSpecialItem(1,savedInstanceState.getBoolean("SpecialItem 1"));
        checkAdapter.setSpecialItem(2,savedInstanceState.getBoolean("SpecialItem 2"));
        checkAdapter.setSpecialItemArray(savedInstanceState.getBooleanArray("specialItemArray"));

    }




 /*   public Object onRetainNonConfigurationInstance() {
        return checkAdapter;
    }*/

    private void setInitialData() {

        for (int i = 0; i< 10 ; i++) {
            checkList.add(new CheckItem("Бразилия", "Бразилиа"));
            checkList.add(new CheckItem("Аргентина", "Буэнос-Айрес"));
            checkList.add(new CheckItem("Колумбия", "Богота"));
            checkList.add(new CheckItem("Уругвай", "Монтевидео"));
            checkList.add(new CheckItem("Чили", "Сантьяго"));
            checkList.add(new CheckItem("DC Voltmeter Selector (Verify You Have A Minimum Of 22 Volts",
                    "BAT"));
            checkList.add(new CheckItem("Standby Power", "AUTO/GUARDED"));

        }
        open( "data.json");
        checkList.add(new CheckItem("Standby Power", "AUTO/GUARDED"));
    }
    public void save(Context context) {

        boolean result = JSONHelper.exportToJSONdata(context, checkList);
        if (result) {
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }


    public void open( String filename) {
        checkList = JSONHelper.importFromJSONassets(this,filename );
        if (checkList != null) {

    //        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phones);
    //        listView.setAdapter(adapter);
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MainActivity: onDestroy()");

    }

}

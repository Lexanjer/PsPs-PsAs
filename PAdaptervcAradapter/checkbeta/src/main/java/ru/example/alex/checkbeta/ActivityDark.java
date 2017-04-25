package ru.example.alex.checkbeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityDark extends AppCompatActivity {

    public final static String DATA_BASE = "database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark);
    }
}

package ru.example.alex.fragmentr;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnList1 =(Button) findViewById(R.id.btnList1);
        Button btnList2 =(Button) findViewById(R.id.btnList2);


        btnList1.setOnClickListener(this);
        btnList2.setOnClickListener(this);









    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnList1:


                Toast.makeText(this, "vybor1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnList2:
                Toast.makeText(this, "vybor2", Toast.LENGTH_SHORT).show();
                break;

        }
        return;
    }
}

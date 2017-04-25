package ru.example.alex.checklist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alex on 27.03.2017.
 */

public class CheckAdapter extends ArrayAdapter<CheckItem> {


    private LayoutInflater inflater;
    private int layout;
    private List<CheckItem> checkList;
    private  boolean[] specialItem;



    public CheckAdapter(Context context, int resource, List<CheckItem> checkList) {
        super(context, resource, checkList);

        this.checkList = checkList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        specialItem = new boolean[checkList.size()];

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.flag);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView title1 = (TextView) view.findViewById(R.id.title1);

        CheckItem checkItem = checkList.get(position);
        title.setText(checkItem.getOption());
        title1.setText(checkItem.getChecked());

        if (!specialItem[position]) {


            title1.setTextColor(0xff5e6060);
            flagView.setImageResource(R.mipmap.android_black);
        } else {

            title1.setTextColor(Color.GREEN);
            flagView.setImageResource(R.mipmap.android_green);
        }


        return view;
    }


    public void addSpecialItem(final int position) {
        specialItem[position] = true;
    }

    public void removeSpecialItem(final int position) {
        specialItem[position] = false;
    }

    public void setSpecialItem(final int position,final boolean specItem) {
        specialItem[position] = specItem;
    }

    public boolean isSpecialItem(final int position) {
        return specialItem[position];
    }


    public boolean[] getSpecialItemArray() {
        return specialItem;
    }

    public void setSpecialItemArray(boolean[] specialItem) {
        this.specialItem = specialItem;
    }
}

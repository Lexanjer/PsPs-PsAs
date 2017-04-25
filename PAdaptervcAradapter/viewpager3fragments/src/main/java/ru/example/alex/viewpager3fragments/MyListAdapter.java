package ru.example.alex.viewpager3fragments;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    final String[] catNames = new String[]{"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Кристина", "Пушок", "Дымка",
            "Кузя", "Китти", "Масяня", "Симба"};


    public MyListAdapter(Context context, int textViewResourceId,
                         String[] objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listfragment_row, parent,
                false);
        TextView catNameTextView = (TextView) row.findViewById(R.id.textViewName);
        catNameTextView.setText(catNames[position]);
        ImageView iconImageView = (ImageView) row.findViewById(R.id.imageViewIcon);

        // Присваиваем значок
        iconImageView.setImageResource(R.mipmap.ic_launcher);

        return row;
    }
}
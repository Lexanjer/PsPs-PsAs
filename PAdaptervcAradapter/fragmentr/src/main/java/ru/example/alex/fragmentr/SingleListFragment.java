package ru.example.alex.fragmentr;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleListFragment extends ListFragment {

    // определяем массив типа String
    final String[] catNames = new String[]{"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Кристина", "Пушок", "Дымка",
            "Кузя", "Китти", "Масяня", "Симба"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
                R.layout.listfragment_row, catNames);
        setListAdapter(myListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listfragment, null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getActivity(),
                getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();
    }

    public class MyListAdapter extends ArrayAdapter<String> {

        private Context mContext;

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
}
package ru.example.alex.fragmentr;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleListFragment1 extends ListFragment {

    // определяем массив типа String
    final String[] catNames = new String[]{"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Кристина", "Пушок", "Дымка",
            "Кузя", "Китти", "Масяня", "Симба"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListAdapter adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, catNames);
        setListAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listfragment, null);
    }

 /*   @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getActivity(), "Вы выбрали позицию: " + position, Toast.LENGTH_SHORT).show();
    }*/


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        TextView textView = (TextView) v;
        String itemText = textView.getText().toString(); // получаем текст нажатого элемента

        Toast.makeText(getActivity(), "Вы выбрали " + itemText, Toast.LENGTH_SHORT).show();
    }


}

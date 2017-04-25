package ru.example.alex.p1251_viewpager;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PageFragment extends ListFragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String TAG = "myLogs";

    int pageNumber;
    int backColor;
    private ArrayList<CheckItem> checkListFrag;
    private MyListAdapter myListAdapter;


    static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);


        //  DataBase data = new DataBase();
        ArrayList<CheckItem> checkListFrag = DataBase.getCheckList(pageNumber);   // data.getCheckList(pageNumber) ;
        this.checkListFrag = checkListFrag;
        myListAdapter = new MyListAdapter(getActivity(),
                R.layout.fragment_row, checkListFrag);
        setListAdapter(myListAdapter);

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.listfragment, null);
        LinearLayout iflayout = (LinearLayout) view.findViewById(R.id.lflayout);
        iflayout.setBackgroundColor(backColor);

/*
          TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
        tvPage.setText("Page " + pageNumber);
        tvPage.setBackgroundColor(backColor);
*/

        return view;


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (myListAdapter.isSpecialItem(position)) {
            myListAdapter.removeSpecialItem(position);
        } else {
            myListAdapter.addSpecialItem(position);
        }
        Toast.makeText(getActivity(),
                getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();
        //     System.out.println(myListAdapter.isSpecialItem(position));
        //    Log.d(TAG, "boolean position = " + myListAdapter.isSpecialItem(position));
        myListAdapter.notifyDataSetChanged();

    }

    public  void resetCheckList() {
        myListAdapter.resetSpecialItem();
        myListAdapter.notifyDataSetChanged();
    }

    private class MyListAdapter extends ArrayAdapter<CheckItem> {

        private Context mContext;
        private boolean[] specialItem;

        public MyListAdapter(Context context, int textViewResourceId,
                             ArrayList<CheckItem> objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
            specialItem = new boolean[checkListFrag.size()];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.fragment_row, parent,
                    false);
            TextView item = (TextView) row.findViewById(R.id.itemView);
            TextView subitem = (TextView) row.findViewById(R.id.subItemView);
            ImageView imageView = (ImageView) row.findViewById(R.id.imageView);

            CheckItem checkItem = checkListFrag.get(position);
            item.setText(checkItem.getOption());
            subitem.setText(checkItem.getValue());


            //     imageView.setImageResource(R.mipmap.ic_launcher_round);

            if (!specialItem[position]) {


                subitem.setTextColor(0xff5e6060);
                imageView.setImageResource(R.mipmap.ic_launcher_round);
            } else {

                subitem.setTextColor(Color.GREEN);
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
            //   Log.d(TAG, "boolean position = " + myListAdapter.isSpecialItem(position));
            return row;
        }

        public void addSpecialItem(final int position) {
            specialItem[position] = true;
        }

        public void removeSpecialItem(final int position) {
            specialItem[position] = false;
        }

        public boolean isSpecialItem(final int position) {
            return specialItem[position];
        }

        public void resetSpecialItem() {

            for (int i = 0; i < specialItem.length; i++) {
                specialItem[i] = false;
            }
        }


    }

}
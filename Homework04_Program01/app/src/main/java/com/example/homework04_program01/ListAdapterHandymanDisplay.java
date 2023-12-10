package com.example.homework04_program01;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapterHandymanDisplay extends BaseAdapter
{
    Context context;
    ArrayList<User> handymanList;

    public ListAdapterHandymanDisplay(Context c, ArrayList<User> hl)
    {
        context = c;
        handymanList = hl;
    }

    @Override
    public int getCount() {
        return handymanList.size();
    }

    @Override
    public Object getItem(int position) {
        return handymanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_cell_handyman_display, null);
        }

        TextView tv_j_cc_handyman = view.findViewById(R.id.tv_v_cc_handyman);
        TextView tv_j_cc_handymanDescription = view.findViewById(R.id.tv_v_cc_handymanDescription);

        User user = handymanList.get(position);

        tv_j_cc_handyman.setText(user.getName());
        tv_j_cc_handymanDescription.setText(user.getDescription());

        return view;
    }
}

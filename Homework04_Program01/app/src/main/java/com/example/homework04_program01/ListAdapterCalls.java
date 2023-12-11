package com.example.homework04_program01;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapterCalls extends BaseAdapter
{
    Context context;
    ArrayList<Appointment> listOfCalls;

    public ListAdapterCalls(Context c, ArrayList<Appointment> l)
    {
        context = c;
        listOfCalls = l;
    }

    @Override
    public int getCount() {
        return listOfCalls.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfCalls.get(position);
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
            view = mInflater.inflate(R.layout.custom_cell_calls, null);
        }

        TextView tv_j_ccc_handyman = view.findViewById(R.id.tv_v_ccc_handyman);
        TextView tv_j_ccc_caller = view.findViewById(R.id.tv_v_ccc_caller);
        TextView tv_j_ccc_job = view.findViewById(R.id.tv_v_ccc_job);
        TextView tv_j_ccc_date = view.findViewById(R.id.tv_v_ccc_date);
        TextView tv_j_ccc_address = view.findViewById(R.id.tv_v_ccc_address);

        Appointment ap = listOfCalls.get(position);

        tv_j_ccc_handyman.setText("Handyman: " + ap.getHandyman().getName());
        tv_j_ccc_caller.setText("Caller: " + ap.getCaller().getName());
        tv_j_ccc_job.setText("Job: " + ap.getJob());
        tv_j_ccc_date.setText("Date: " + ap.getDate());
        tv_j_ccc_address.setText("Address: " + ap.getCaller().getAddress());

        return view;
    }
}

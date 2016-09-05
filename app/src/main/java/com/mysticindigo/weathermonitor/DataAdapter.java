package com.mysticindigo.weathermonitor;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Alex on 7/18/2016.
 */
public class DataAdapter extends ArrayAdapter<DataHolder> {

    private AppCompatActivity myContext;

    public DataAdapter(AppCompatActivity context, int editTextResourceId, ArrayList<DataHolder> objects) {
        super(context, editTextResourceId, objects);
        myContext = context;
    }

    // We keep this ViewHolder object to save time. It's quicker than findViewById() when repainting.
    static class ViewHolder {
        protected DataHolder data;
        protected Spinner spin;
        protected EditText text;
        protected Button button;
    }

    public void addNewItem(DataHolder newRow) {
        // items represents List<RowData> in your Adapter class
        this.add(newRow);

        // sends request to update ListAdapter
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        System.out.println("getView " + position + " " + convertView);

        // Check to see if this row has already been painted once.
        if (view == null) {
            // If it hasn't, set up everything:
            // LayoutInflater inflator = myContext.getLayoutInflater();
            // view = inflator.inflate(R.layout.activity_weatherlistview, null);
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_weatherlistview, parent, false);

            /*final ViewHolder viewHolder = new ViewHolder();
            viewHolder.data = new DataHolder(getContext());
            viewHolder.spin = (Spinner) view.findViewById(R.id.spin);
            viewHolder.spin.setAdapter(viewHolder.data.getAdapter());
            viewHolder.text = (EditText) view.findViewById(R.id.label);
            viewHolder.button = (Button) view.findViewById(R.id.button);*/
        }

        // Make a new ViewHolder for this row, and modify its data and spinner:
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.data = new DataHolder(getContext());
        viewHolder.spin = (Spinner) view.findViewById(R.id.spin);
        viewHolder.spin.setAdapter(viewHolder.data.getAdapter());
        viewHolder.spin.setSelection(getItem(position).getSelected());
        viewHolder.text = (EditText) view.findViewById(R.id.label);
        viewHolder.text.setText(getItem(position).getText());
        viewHolder.button = (Button) view.findViewById(R.id.button);

        viewHolder.button.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == viewHolder.button){
                    viewHolder.spin.setEnabled(false);
                    viewHolder.text.setEnabled(false);
                    viewHolder.button.setEnabled(false);
                    addNewItem(new DataHolder(getContext()));
                }
            }
        });

        view.setTag(viewHolder);

        Log.d("DBGINF", viewHolder.text.getText() + "");

        // Update the EditText to reflect what's in the Spinner
        // viewHolder.text.setText(viewHolder.data.getText());

        // This is what gets called every time the ListView refreshes
        // ViewHolder holder = (ViewHolder) view.getTag();
        // holder.text.setText(getItem(position).getText());
        // holder.spin.setSelection(getItem(position).getSelected());

        return view;
    }
}

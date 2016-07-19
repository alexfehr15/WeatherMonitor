package com.mysticindigo.weathermonitor;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Alex on 7/18/2016.
 */
public class DataAdapter extends ArrayAdapter<DataHolder> {

    private AppCompatActivity myContext;

    public DataAdapter(AppCompatActivity context, int editTextResourceId, DataHolder[] objects) {
        super(context, editTextResourceId, objects);
        myContext = context;
    }

    // We keep this ViewHolder object to save time. It's quicker than findViewById() when repainting.
    static class ViewHolder {
        protected DataHolder data;
        protected EditText text;
        protected Spinner spin;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        // Check to see if this row has already been painted once.
        if (convertView == null) {

            // If it hasn't, set up everything:
            LayoutInflater inflator = myContext.getLayoutInflater();
            view = inflator.inflate(R.layout.activity_weatherlistview, null);

            // Make a new ViewHolder for this row, and modify its data and spinner:
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (EditText) view.findViewById(R.id.label);
            viewHolder.data = new DataHolder(myContext);
            viewHolder.spin = (Spinner) view.findViewById(R.id.spin);
            viewHolder.spin.setAdapter(viewHolder.data.getAdapter());

            // Used to handle events when the user changes the Spinner selection:
            /*viewHolder.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    viewHolder.data.setSelected(arg2);
                    viewHolder.text.setText(viewHolder.data.getText());
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }

            });*/

            // Update the EditText to reflect what's in the Spinner
            // viewHolder.text.setText(viewHolder.data.getText());

            view.setTag(viewHolder);

            Log.d("DBGINF", viewHolder.text.getText() + "");
        } else {
            view = convertView;
        }

        // This is what gets called every time the ListView refreshes
        // ViewHolder holder = (ViewHolder) view.getTag();
        // holder.text.setText(getItem(position).getText());
        // holder.spin.setSelection(getItem(position).getSelected());

        return view;
    }
}

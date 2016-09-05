package com.mysticindigo.weathermonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Array of strings...
    public String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

    public enum Gender {
        PWI("Personal Weather Station", 0),
        CITY_STATE("City and State", 1);

        private String stringValue;
        private int intValue;
        private Gender(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.weather_list);

        DataHolder data = new DataHolder(this);
        ArrayList<DataHolder> dataHolderArrayList = new ArrayList<DataHolder>();
        dataHolderArrayList.add((data));
        /*DataHolder data1 = new DataHolder(this);
        DataHolder data2 = new DataHolder(this);
        DataHolder data3 = new DataHolder(this);
        DataHolder data4 = new DataHolder(this);
        DataHolder data5 = new DataHolder(this);
        DataHolder data6 = new DataHolder(this);
        DataHolder data7 = new DataHolder(this);
        DataHolder data8 = new DataHolder(this);
        DataHolder data9 = new DataHolder(this);
        DataHolder data10 = new DataHolder(this);
        DataHolder data11 = new DataHolder(this);*/

        // DataAdapter d = new DataAdapter(this, R.layout.activity_weatherlistview, new DataHolder[] { data });
        DataAdapter d = new DataAdapter(this, R.layout.activity_weatherlistview, dataHolderArrayList);

        listView.setAdapter(d);
    }
}

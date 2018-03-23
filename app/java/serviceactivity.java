package com.eserv.client;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sourcey.materiallogindemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class serviceactivity extends AppCompatActivity {
    ListView mListView;
    List<String> service_id = new ArrayList<String>();
    String[] services = new String[20];
    int[] service_images = new int[6];
    String path = "C://wamp//www//eserv//eservweb//img//services";
    String ex = ".jpg";
    int[] images = {R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs,
            R.drawable.hhs};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceslist);
        mListView = (ListView)findViewById(R.id.listview);
        String type = "getservices";
        servicesHandler backgroundWorker = new servicesHandler(this);
        JSONObject p = null;
        try {
            p = (backgroundWorker.execute(type,"504312")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            String data = p.getString("data");
            JSONArray news = new JSONArray(data);
            for (int i = 0; i < news.length(); i++) {
                JSONObject jsonobject = news.getJSONObject(i);
                service_id.add(jsonobject.getString("id"));
                service_images[i] = images[Integer.parseInt(jsonobject.getString("id"))];
                services[i] = jsonobject.getString("name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Servicelist servicelist = new Servicelist();
        mListView.setAdapter(servicelist);
    }
    class Servicelist extends BaseAdapter {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.activity_serviceactivity,null);
            ImageView mImageview = (ImageView) view.findViewById(R.id.imageView);
            TextView mTextview = (TextView) view.findViewById(R.id.textview);
            mTextview.setText(services[position]);
            mImageview.setImageResource(service_images[position]);
            return view;
        }
    }
}

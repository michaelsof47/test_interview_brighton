package com.example.myapplication.api;


import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.MainActivity;
import com.example.myapplication.model.SearchItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieController {

    public interface movielistener {
        void retrieveData(ArrayList<SearchItem> item);
    }


    Context context;
    public MovieController(Context context) {
        this.context = context;

    }

    public void retrieveMovie(movielistener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.GET, "http://www.omdbapi.com/?apiKey=83eba856&i=tt3896198&s=batman", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject dataobj = new JSONObject(response);
                    JSONArray dataarr = dataobj.getJSONArray("Search");
                    ArrayList<SearchItem> itemList = new ArrayList<>();
                    for(int i = 0; i < dataarr.length(); i++) {
                        JSONObject dataobj2 = dataarr.getJSONObject(i);
                        SearchItem item = new SearchItem();
                        item.setTitle(dataobj2.getString("Title"));
                        item.setYear(dataobj2.getString("Year"));
                        item.setImdbID(dataobj2.getString("imdbID"));
                        item.setType(dataobj2.getString("Type"));
                        item.setPoster(dataobj2.getString("Poster"));
                        itemList.add(item);
                    }
                    listener.retrieveData(itemList);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_data",error.getMessage());
            }
        }) ;
        sr.setRetryPolicy(new DefaultRetryPolicy(60000,60000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES));
        queue.add(sr);
    }
}

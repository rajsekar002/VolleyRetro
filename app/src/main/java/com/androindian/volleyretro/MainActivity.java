package com.androindian.volleyretro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androindian.volleyretro.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    String url="http://androindian.com/apps/my_apps/api.php";
    ArrayList<String> ID=new ArrayList<String>();
    ArrayList<String> APP=new ArrayList<String>();
    ArrayList<String> PIC=new ArrayList<String>();
    ArrayList<String> jul=new ArrayList<String>();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView
                (MainActivity.this,R.layout.activity_main);

        requestQueue= Volley.newRequestQueue(MainActivity.this);


        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_all_apps");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("response").equalsIgnoreCase("success")){
                                JSONArray jsonArray=response.getJSONArray("data");

                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject j2=jsonArray.getJSONObject(i);

                                    String appname=j2.getString("app_name");
                                    String id=j2.getString("id");
                                    String ul=j2.getString("url");
                                    String pic=j2.getString("pic");

                                    APP.add(appname);
                                    ID.add(id);
                                    jul.add(ul);
                                    PIC.add(pic);

                                }

                                @SuppressLint("WrongConstant") LinearLayoutManager ll=new LinearLayoutManager(
                                        MainActivity.this,LinearLayoutManager.VERTICAL,false );
                                binding.rec.setLayoutManager(ll);
                                binding.rec.setAdapter(new RecAdp(MainActivity.this,
                                        ID,PIC,jul,APP));



                            }else {
                                Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }
        );

        requestQueue.add(jsonObjectRequest);
    }
}

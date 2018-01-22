package com.example.jasmin.colorpalettejasmin;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Jasmin on 18/01/2018.
 */

public class MyThread extends AsyncTask<String, Integer, String> {

    TextView t;
 //   SeekBar s;
    ProgressBar p;
    RadioButton rb1, rb2;
    String title;
    String json;
    JSONObject jsonO;
    JSONArray jsonA, colours;
    int counter;
    String[] array_colours;
    Activity act;


    public MyThread(TextView t_par, ProgressBar p_par, RadioButton rb1_par, RadioButton rb2_par, Activity act_par){
t= t_par;
p=p_par;
rb1 = rb1_par;
rb2 = rb2_par;
act = new Activity();
act = act_par;
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("TITLE" + title);
        act.setTitle(title);
        if(array_colours.length > 4){
            rb1.setTextColor(Color.parseColor(array_colours[0].toString()));
            t.setTextColor(Color.parseColor(array_colours[1].toString()));
            rb2.setTextColor(Color.parseColor(array_colours[2].toString()));
            p.getProgressDrawable().setColorFilter(Color.parseColor(array_colours[3].toString()), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    protected String doInBackground(String... urls) {
        for (String urlString : urls) {
            URL url = null;
            try {

                url = new URL(urlString);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = "";
                json ="";
                counter = 0;
                while ((line = reader.readLine()) != null) {
                    json += line;
                }
                System.out.println("json=" + json);
                jsonA  = new JSONArray(json);
                json = "";
                for(int i = 0; i < jsonA.length(); i++){
                    try {
                        jsonO = jsonA.getJSONObject(i);
                        counter ++;
                        title = jsonO.getString("title");
                        System.out.println("counter + "+ counter);
                        colours = jsonO.getJSONArray("colors");
                        array_colours = new String[colours.length()];
                        //json = "\n" + "\n" + (counter) + "." + jsonO.getString("userid") +  ":   " + jsonO.getString("text") + json;
                   System.out.println("Here" + colours.length());
                   if(colours.length()!=0){
                       for(int y =0; y < colours.length();y++){
                           System.out.println("Far" + colours.getString(y));
                           array_colours[y] = "#" + colours.getString(y);
                           System.out.println("" + array_colours[y]);
                       }
                   }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json;
    }
}

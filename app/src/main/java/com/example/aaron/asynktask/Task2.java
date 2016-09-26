package com.example.aaron.asynktask;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by User on 9/26/2016.
 */

public class Task2 extends AsyncTask<Object, Integer, String>{

    TextView tv;
    TextView tv2;

    @Override
    protected String doInBackground(Object... objects) {
        int integer = (int) objects[0];
        tv = (TextView) objects[1];
        tv2 = (TextView) objects[2];

        for(int i = 1; i < integer*10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);
        }

        return "Done "+ integer;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        tv.setText(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        tv2.setText(values[0].toString());
    }
}

package com.example.aaron.asynktask;

import android.os.AsyncTask;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    int idx = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.textView));
    }

    public void goButton(View view) {
        tv.setText("Workin ...");
        //Execute a thread in serial way
        new MFAT().execute(idx);
        //Execute a thread in parallel way
        //new MFAT().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 5);
        idx++;
    }

    class MFAT extends AsyncTask<Integer, Integer, String>{

        @Override
        protected String doInBackground(Integer... integer) {//Void ... it equals to Void[]
            try {
                Thread.sleep(integer[0] * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }
    }
}

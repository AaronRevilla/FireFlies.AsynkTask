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
    TextView tv2;
    int idx = 10;
    boolean taskRunning = false;
    Task2 task2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.textView));
        tv2 = ((TextView) findViewById(R.id.textView2));
        task2 = new Task2();
    }

    public void goButton(View view) {
        startThread();
    }

    public void startThread(){
        tv.setText("Working ...");
        //Execute a thread in serial way
        //new MFAT().execute(idx);
        //Execute a thread in parallel way
        taskRunning = true;
        task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{idx, tv, tv2});
        idx++;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(taskRunning){
            task2.cancel(true);
        }
        outState.putBoolean("key", taskRunning);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        taskRunning = savedInstanceState.getBoolean("key", false);
        if(taskRunning){
            startThread();
        }
    }

    /*
    class MFAT extends AsyncTask<Integer, Integer, String>{

        @Override
        protected String doInBackground(Integer... integer) {//Void ... it equals to Void[]
            for(int i = 1; i < integer[0]*10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }

            return "Done "+ integer[0];
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
    */
}

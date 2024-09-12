package com.example.bt_th1;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownloadWebPageTask extends AsyncTask<String, Void, String>
{
    private TextView textView;

    public DownloadWebPageTask(TextView textView) {
        this.textView = textView;
    }
    @Override
    protected String doInBackground(String... urls) {
        StringBuilder result  = new StringBuilder();
        URL url;
        HttpURLConnection urlConnection = null;
        try
        {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while(data !=-1)
            {
                char current = (char) data;
                result.append(current);
                data = reader.read();
            }
            return result.toString();
        }catch (Exception e)
        {
            e.printStackTrace();
            Log.e("DownloadTask", "Error: " + e.getMessage());
            return "Failed";
        }
        finally
        {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
    }

    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        textView.setText(result);
    }
}

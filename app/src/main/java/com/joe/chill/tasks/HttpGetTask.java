package com.joe.chill.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.joe.chill.interfaces.JsonHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Joe on 4/1/16.
 */
public class HttpGetTask extends AsyncTask<String,Void,String> {

  public static final String TAG = "HttpGetTask";
  protected JsonHandler delegate;

  public HttpGetTask(JsonHandler delegate) {
    this.delegate = delegate;
  }

  protected void onPreExecute() {
    //display progress dialog.

  }

  protected String doInBackground(String... urls) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      URL url = new URL(urls[0]);
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

      InputStream in = new BufferedInputStream(urlConnection.getInputStream());
      BufferedReader r = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = r.readLine()) != null) {
        stringBuilder.append(line);
      }
      urlConnection.disconnect();

    } catch (MalformedURLException e) {
      Log.e(TAG, e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      Log.e(TAG, e.getMessage());;
    }
    return stringBuilder.toString();
  }



  protected void onPostExecute(String result) {
    delegate.processJson(result);
    Log.i(TAG, result);
  }
}

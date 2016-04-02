package com.joe.chill.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.joe.chill.interfaces.JsonHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Joe on 4/1/16.
 */
public class HttpPostTask extends AsyncTask<String,Void,String> {

  public static final String TAG = "HttpPostTask";
  protected JsonHandler delegate;
  protected Map<String, String> data;

  public HttpPostTask(JsonHandler delegate, Map<String, String> data) {
    this.delegate = delegate;
    this.data = data;
  }

  protected void onPreExecute() {
    //display progress dialog.

  }

  protected String doInBackground(String... urls) {
    StringBuilder response = new StringBuilder();
    try {
      URL url = new URL(urls[0]);
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

      urlConnection.setReadTimeout(15000);
      urlConnection.setConnectTimeout(15000);
      urlConnection.setRequestMethod("POST");
      urlConnection.setDoInput(true);
      urlConnection.setDoOutput(true);

      OutputStream os = urlConnection.getOutputStream();
      BufferedWriter writer = new BufferedWriter(
          new OutputStreamWriter(os, "UTF-8"));
      writer.write(getPostDataString(data));

      writer.flush();
      writer.close();
      os.close();

      int responseCode = urlConnection.getResponseCode();

      if (responseCode == HttpsURLConnection.HTTP_OK) {
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream
            ()));
        while ((line=br.readLine()) != null) {
          response.append(line);
        }
      }
      else {
        response.append("");

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return response.toString();
  }



  protected void onPostExecute(String result) {
    delegate.processJson(result);
    Log.i(TAG, result);
  }

  private String getPostDataString(Map<String, String> params) throws UnsupportedEncodingException {
    StringBuilder result = new StringBuilder();
    boolean first = true;
    for(Map.Entry<String, String> entry : params.entrySet()) {
      if (first) {
        first = false;
      } else {
        result.append("&");
      }

      result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
      result.append("=");
      result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
    }

    return result.toString();
  }
}
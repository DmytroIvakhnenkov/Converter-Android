package com.example.user.project_x;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class WebAction extends AsyncTask<String, String, String> {
    public static String WebActionResult;
    public String getJson(String url)throws IOException {

        StringBuilder build = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        String con;
        while ((con = reader.readLine()) != null) {
            build.append(con);
        }
        return build.toString();
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            WebActionResult = getJson(params[0]);
            return "";
        } catch (IOException e) {
            return "";
        }
    }
}
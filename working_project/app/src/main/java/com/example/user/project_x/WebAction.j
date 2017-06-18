package com.example.user.project_x;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


 class WebAction extends AsyncTask<String, String, JSONObject> {
    static JSONObject WebActionResult;

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            return JsonReader.readJsonFromUrl(params[0]);
        } catch (IOException e) {
            return null;
        }
        catch (JSONException js){
            return null;
        }
    }
    @Override
    protected void onPostExecute(JSONObject result){
    }


}
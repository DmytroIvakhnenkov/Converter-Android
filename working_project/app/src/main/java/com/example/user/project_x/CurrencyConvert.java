package com.example.user.project_x;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CurrencyConvert extends AppCompatActivity {
    static String input= "";
    public int to;
    public int from;
    static int i=0;
    static int j=0;
    static int z=0;
    public  String[] val;
    TextView answer;
    EditText in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_convert);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.name, android.R.layout.simple_spinner_item);
        val = getResources().getStringArray(R.array.value);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        answer = (TextView)findViewById(R.id.textView2_cur);
        in = (EditText)findViewById(R.id.editText_cur);
        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input = (in.getText()).toString();
                Check a = new Check();
                if(a.check(input) && input.length()>=1){
                    if(input.equals(".") && input.length()<2){
                        if(z>input.length()){}
                        else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                            z=input.length();
                        }
                    }
                    else if(input.equals("-") && input.length()<2){}
                    else{Currency_converter(from, to, input);}}
                else{
                    if(!(a.check(input)) && input.length()>=1){
                        if(z>input.length()){}
                        else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}}
                    else{answer.setText("result");}
                    z=input.length();}
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });}
    @Override
    protected void onStart() {
        i=0;
        j=0;
        input= "";
        super.onStart();
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_cur);
        from_spinner.setAdapter(a);
        from_spinner.setPrompt("Select type");
        from_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                Check a = new Check();
                from = position;
                if(i<1){i+=1;}
                else{
                    if(a.check(input) && input.length()>=1){
                        if(input.equals(".") && input.length()<2){
                            Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Currency_converter(from, to, input);}}
                    else{
                        if(!a.check(input) && input.length()>=1){
                            Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                            else{answer.setText("result");}
                }}}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}
    void spinner_to_init(ArrayAdapter a){
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_cur);
        to_spinner.setAdapter(a);
        to_spinner.setPrompt("Select type");
        to_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                Check a = new Check();
                to = position;
                if(j<1){j+=1;}
                else{
                    if(a.check(input) && input.length()>=1){
                        if(input.equals(".") && input.length()<2){
                            Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Currency_converter(from, to, input);}}
                    else{
                        if(!a.check(input) && input.length()>=1){
                            Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                        else{answer.setText("result");}
                }}}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    void Currency_converter(int from, int to, String amount){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            String[] link = {"http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22" + val[from] + val[to] + "%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback="};
            WebAction act = new WebAction();
            act.execute(link);
        }
        else
            Toast.makeText(CurrencyConvert.this, "Please connect to a network to use Currency Converter", Toast.LENGTH_SHORT).show();


    }
    static String getJson(String url)throws IOException {
        StringBuilder build = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
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
    private class WebAction extends AsyncTask<String, String, JSONObject> {
        JSONObject WebActionResult;

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
            try {
                double res = Double.parseDouble(result.getJSONObject("query").getJSONObject("results").getJSONObject("rate").getString("Rate")) * Double.parseDouble(input);
                answer.setText(String.valueOf(res));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch (NullPointerException n){
                Toast.makeText(CurrencyConvert.this, "Failed to connect to Yahoo! Finance", Toast.LENGTH_SHORT).show();
            }
            catch(NumberFormatException e){
            }
        }}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_1:
                finish();
                return true;
            case R.id.action_2:
                Intent intent1 = new Intent(this, Authors.class);
                startActivity(intent1);
                return true;
            case R.id.action_3:
                Intent intent2 = new Intent(this, Help.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }}

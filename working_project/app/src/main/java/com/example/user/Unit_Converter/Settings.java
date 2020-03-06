package com.example.user.Unit_Converter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    public static int scaleS = 10;
    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_settings);
         final EditText in = (EditText)findViewById(R.id.editText_S);
         sharedPreferences= getSharedPreferences("mypref", MODE_PRIVATE);
        in.setText(String.valueOf(sharedPreferences.getInt("scale",10)));
        final SharedPreferences.Editor editor;
        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CheckSett a = new CheckSett();
                if(a.check(in.getText().toString())){
                    if(in.getText().toString().equals("")){
                     scaleS = 10;
                    //   Toast.makeText(getApplicationContext(), "please, enter the number", Toast.LENGTH_SHORT).show();
                 }
                  else{scaleS=Integer.parseInt(((in.getText()).toString()));}
                 // editor.putInt("scale", scaleS);
                  //editor.apply();
                    setDefaults("scale",scaleS,Settings.this);
                }
                else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
             }
        });}
    @Override
   protected void onStart() {
        final EditText in = (EditText)findViewById(R.id.editText_S);
        in.setText(String.valueOf(getDefaults("scale", Settings.this)));
        super.onStart();
    }
    public static void setDefaults(String key, int value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public static int getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 10);
    }
        int scaaale(){
       scaleS = sharedPreferences.getInt("scale",10);
           return scaleS;
      }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusett_main, menu);
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


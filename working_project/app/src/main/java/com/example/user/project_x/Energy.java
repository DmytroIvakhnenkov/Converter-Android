package com.example.user.project_x;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;

public class Energy extends AppCompatActivity {
    String[] data = {"joules","kilojoules", "megajoules", "horsepowers hour", "calories", "kilocalories", "megacalories", "kilowatts hour","watts hour","watts second","electronvolt"};
    Map<String, Double> types = new TreeMap<>();
    static String from = "";
    static String to = "";
    static String input= "";
    static EditText in;
    static TextView answer;
    static int i=0;
    static int j=0;
    static int z=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        type_init();
        answer = (TextView)findViewById(R.id.textView2_e);
        answer.setText("result");
        final EditText in = (EditText)findViewById(R.id.editText_e);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
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
                    else{Energy_convetrer(from, to, input);}}
                else{
                    if(!(a.check(input)) && input.length()>=1){
                        if(z>input.length()){}
                        else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}}
                    else{answer.setText("result");}
                    z=input.length();}
            }
        });}
    @Override
    protected void onStart() {
        i=0;
        j=0;
        input= "";
        super.onStart();
    }
   void type_init(){
       types.put("joules", 1.0);
       types.put("kilojoules", 1000.0);
       types.put("megajoules", 1000000.0);
       types.put("horsepowers hour", 2685000.0);
       types.put("calories", 4.187);
       types.put("kilocalories", 4187.0);
       types.put("megacalories", 4187000.0);
       types.put("kilowatts hour", 3600000.0);
       types.put("watts hour", 3600.0);
       types.put("watts second", 1.0);
       types.put("electronvolt", 0.000000000000000000162);
   }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_e);
        from_spinner.setAdapter(a);
        from_spinner.setPrompt("Select type");
        from_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                from = data[position];
                Check a = new Check();
                if(i<1){i+=1;}
                else{
                    if(a.check(input) && input.length()>=1){
                        if(input.equals(".") && input.length()<2){
                            Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                        }
                        else{Energy_convetrer(from, to, input);}}
                    else{
                        if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                        else{answer.setText("result");}}
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}
    void spinner_to_init(ArrayAdapter a){
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_e);
        to_spinner.setAdapter(a);
        to_spinner.setPrompt("Select type");
        to_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                to = data[position];
                Check a = new Check();
                if(j<1){j+=1;}
                else{
                    if(a.check(input) && input.length()>=1){
                        if(input.equals(".") && input.length()<2){
                            Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                        }
                        else{Energy_convetrer(from, to, input);}}
                    else{
                        if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                        else{answer.setText("result");}}
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    void Energy_convetrer(String from, String to, String amount){
        double from_amount = Double.parseDouble(amount);
        double to_amount = types.get(from)*from_amount/types.get(to);
        if(to_amount%1 == 0){long myInt = (long) to_amount;
            String output = Long.toString(myInt);
            answer.setText(output);}
        else{
            String output = Double.toString(to_amount);
            answer.setText(output);}
    }
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
package com.example.user.project_x;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;

public class Length extends AppCompatActivity {
    String[] data = {"kilometers", "meters", "centimeters", "millimeters", "miles", "inches", "yards", "feets", "fathoms", "light years"};
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
        setContentView(R.layout.activity_length);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        type_init();
        answer = (TextView)findViewById(R.id.textView2_l);
        answer.setText("result");
        final EditText in = (EditText)findViewById(R.id.editText_l);
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
                    else{Length_convetrer(from, to, input);}}
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
        types.put("kilometers", 1000.0);
        types.put("meters", 1.0);
        types.put("centimeters", 0.01);
        types.put("millimeters", 0.001);
        types.put("miles", 1609.34);
        types.put("inches", 0.0254);
        types.put("yards", 0.9144);
        types.put("feets", 0.3048);
        types.put("fathoms", 1.8288);
        types.put("light years", 9.461*(10^15));
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_l);
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
                    else{Length_convetrer(from, to, input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}
            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}
    void spinner_to_init(ArrayAdapter a){
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_l);
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
                    else{Length_convetrer(from, to, input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}
            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    void Length_convetrer(String from, String to, String amount){
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


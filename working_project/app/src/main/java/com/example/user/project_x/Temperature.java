package com.example.user.project_x;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;

public class Temperature extends AppCompatActivity {
    String[] data = {"Celsius", "Fahrenheit", "Kelvin", "Rankine", "Reaumur"};
    Map<String, String> types = new TreeMap<>();
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
        setContentView(R.layout.activity_temperature);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        type_init();
        answer = (TextView)findViewById(R.id.textView2_t);
        answer.setText("result");
        final EditText in = (EditText)findViewById(R.id.editText_t);
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
                Check1 a = new Check1();
                if(a.check(input) && input.length()>=1){
                    if((input.equals(".") && input.length()<2)||(input.equals(".-") && input.length()<3)||(input.equals("-.") && input.length()<3)||(input.substring(input.length()-1).equals("-") && input.length()>2)){
                        if(z>input.length()){}
                        else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                        z=input.length();
                        }
                    }
                    else if(input.equals("-") && input.length()<2){}
                    else{Temp_convetrer(types.get(from), types.get(to), input);}}
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
        types.put("Celsius", "c");
        types.put("Fahrenheit", "f");
        types.put("Kelvin", "k");
        types.put("Rankine", "r");
        types.put("Reaumur", "re");
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_t);
        from_spinner.setAdapter(a);
        from_spinner.setPrompt("Select type");
        from_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                from = data[position];
                Check1 a = new Check1();
                if(i<1){i+=1;}
                else{
                if(a.check(input) && input.length()>=1){
                    if((input.equals(".") && input.length()<2)||(input.equals(".-") && input.length()<3)||(input.equals("-.") && input.length()<3)||(input.equals("-") && !(input.length()<2))){
                        Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                    }
                    else if(input.equals("-") && input.length()<2){}
                    else{Temp_convetrer(types.get(from), types.get(to), input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}

            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}
    void spinner_to_init(ArrayAdapter a){
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_t);
        to_spinner.setAdapter(a);
        to_spinner.setPrompt("Select type");
        to_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                to = data[position];
                Check1 a = new Check1();
                if(j<1){j+=1;}
                else{
                if(a.check(input) && input.length()>=1){
                    if((input.equals(".") && input.length()<2)||(input.equals(".-") && input.length()<3)||(input.equals("-.") && input.length()<3||(input.equals("-") && !(input.length()<2)))){
                        Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                    }
                    else if(input.equals("-") && input.length()<2){}
                    else{Temp_convetrer(types.get(from), types.get(to), input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}
            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    void Temp_convetrer(String from, String to, String amount) {
        double from_amount = Double.parseDouble(amount);
       double to_amount = converter(to, from, from_amount);
        if(to_amount%1 == 0){Long myInt = (long) to_amount;
            String output = Long.toString(myInt);
            answer.setText(output);}
        else{
            String output = Double.toString(to_amount);
            answer.setText(output);}
    }
    double converter(String to, String from, double a){
        switch (from){
            case "c":
                switch(to){
                    case "c": return a;
                    case "f": return a*1.8 +32;
                    case "k": return a+273.15;
                    case "r": return (a+273.15)*1.8;
                    case "re": return a*0.8;
                }
            case "f":
                switch(to){
                    case "c": return (a - 32)/1.8;
                    case "f": return a;
                    case "k": return (a + 459.67)/1.8;
                    case "r": return a + 459.67;
                    case "re": return (a - 32)/2.25;
                }
            case "k":
                switch(to){
                    case "c": return a - 273.15;
                    case "f": return a*1.8 - 459.67;
                    case "k": return a;
                    case "r": return a*1.8;
                    case "re": return (a - 273.15)*0.8;
                }
            case "r":
                switch(to){
                    case "c": return (a - 491.67)/1.8;
                    case "f": return a - 459.67;
                    case "k": return a/1.8;
                    case "r": return a;
                    case "re": return (a - 491.67)/2.25;

                }
            case "re":
                switch(to){
                    case "c": return a*1.25;
                    case "f": return a*2.25+32;
                    case "k": return a*1.25 + 273.15;
                    case "r": return a*2.25 +491.67;
                    case "re": return a;
                }
        }
        return 0.1488;
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


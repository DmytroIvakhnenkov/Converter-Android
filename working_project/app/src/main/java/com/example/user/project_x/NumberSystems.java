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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.pow;

public class NumberSystems extends AppCompatActivity {
    String[] data = {"2","3", "4", "5", "6", "7", "8", "9","10"};
    Map<String, Double> types = new TreeMap<>();
    static long from = 0;
    static long to = 0;
    static String from1 = "";
    static String to1 = "";
    static String input= "";
    static EditText in;
    static TextView answer;
    static int i=0;
    static int j=0;
    static int z=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_systems);
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
                Check2 a = new Check2();
                if(a.check(input,from) && input.length()>=1){
                    if(input.equals(".") && input.length()<2){
                        if(z>input.length()){}
                        else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                            z=input.length();
                        }
                    }
                    else if(input.equals("-") && input.length()<2){}
                    else{Energy_convetrer(from1, to1, input);}}
                else{
                    if(!(a.check(input,from)) && input.length()>=1){
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
        types.put("2", 1000.0);
        types.put("3", 1000000.0);
        types.put("4", 2685000.0);
        types.put("5", 4.187);
        types.put("6", 4187.0);
        types.put("7", 4187000.0);
        types.put("8", 3600000.0);
        types.put("9", 3600.0);
        types.put("10", 1.0);
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_e);
        from_spinner.setAdapter(a);
        from_spinner.setPrompt("Select type");
        from_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                from = position+2;
                from1 = Long.toString(from);
                Check2 a = new Check2();
                if(i<1){i+=1;}
                else{
                    if(a.check(input,from) && input.length()>=1){
                        if(input.equals(".") && input.length()<2){
                            Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                        }
                        else{Energy_convetrer(from1, to1, input);}}
                    else{
                        if(a.check(input,from)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
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
                to = position+2;
                to1 = Long.toString(to);
                Check2 a = new Check2();
                if(j<1){j+=1;}
                else{
                    if(a.check(input,from) && input.length()>=1){
                        if(input.equals(".") && input.length()<2){
                            Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                        }
                        else{Energy_convetrer(from1, to1, input);}}
                    else{
                        if(a.check(input,from)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                        else{answer.setText("result");}}
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    long step(int a, int b){
        int n = a;
        for(int i = 0;i<b-1;i++){
        a=a*n;
        }
        return a;
    }
    long smallconvert(long x,int z){
        long sum=0;
        String kil = Long.toString(x);
        for(int i = 0; i<kil.length(); i++){
            sum=step(z,i)*(x % 10)+sum;
            x=x/10;
        }
        return sum;
    }
    long bigconvert(long x,int z){
        String sum1 = "";
        while (x>0){
            long a =x % z;
            String a1 = Long.toString(a);
            sum1=a1+sum1;
            x=x/z;
        }
      long sum = Long.parseLong(sum1);
      return sum;
    }
    long converter(String to, String from, long a){
        switch (from){
            case "2":
                switch(to){
                    case "2": return a;
                    case "3": return bigconvert(smallconvert(a,2),3);
                    case "4": return bigconvert(smallconvert(a,2),4);
                    case "5": return bigconvert(smallconvert(a,2),5);
                    case "6": return bigconvert(smallconvert(a,2),6);
                    case "7": return bigconvert(smallconvert(a,2),7);
                    case "8": return bigconvert(smallconvert(a,2),8);
                    case "9": return bigconvert(smallconvert(a,2),9);
                    case "10":return smallconvert(a,2);
                }
            case "3":
                switch(to){
                    case "2": return bigconvert(smallconvert(a,3),2);
                    case "3": return a;
                    case "4": return bigconvert(smallconvert(a,3),4);
                    case "5": return bigconvert(smallconvert(a,3),5);
                    case "6": return bigconvert(smallconvert(a,3),6);
                    case "7": return bigconvert(smallconvert(a,3),7);
                    case "8": return bigconvert(smallconvert(a,3),8);
                    case "9": return bigconvert(smallconvert(a,3),9);
                    case "10":return smallconvert(a,3);
                }
            case "4":
                switch(to){
                    case "2": return bigconvert(smallconvert(a,4),2);
                    case "3": return bigconvert(smallconvert(a,4),3);
                    case "4": return a;
                    case "5": return bigconvert(smallconvert(a,4),5);
                    case "6": return bigconvert(smallconvert(a,4),6);
                    case "7": return bigconvert(smallconvert(a,4),7);
                    case "8": return bigconvert(smallconvert(a,4),8);
                    case "9": return bigconvert(smallconvert(a,4),9);
                    case "10":return smallconvert(a,4);
                }
            case "5":
                switch(to){
                    case "2": return bigconvert(smallconvert(a,5),2);
                    case "3": return bigconvert(smallconvert(a,5),3);
                    case "4": return bigconvert(smallconvert(a,5),4);
                    case "5": return a;
                    case "6": return bigconvert(smallconvert(a,5),6);
                    case "7": return bigconvert(smallconvert(a,5),7);
                    case "8": return bigconvert(smallconvert(a,5),8);
                    case "9": return bigconvert(smallconvert(a,5),9);
                    case "10":return smallconvert(a,5);
                }
            case "6":
                switch(to){
                    case "2": return bigconvert(smallconvert(a,6),2);
                    case "3": return bigconvert(smallconvert(a,6),3);
                    case "4": return bigconvert(smallconvert(a,6),4);
                    case "5": return bigconvert(smallconvert(a,6),5);
                    case "6": return a;
                    case "7": return bigconvert(smallconvert(a,6),7);
                    case "8": return bigconvert(smallconvert(a,6),8);
                    case "9": return bigconvert(smallconvert(a,6),9);
                    case "10":return smallconvert(a,6);
                }
            case "7":
                switch(to){
                    case "2": return bigconvert(smallconvert(a,7),2);
                    case "3": return bigconvert(smallconvert(a,7),3);
                    case "4": return bigconvert(smallconvert(a,7),4);
                    case "5": return bigconvert(smallconvert(a,7),5);
                    case "6": return bigconvert(smallconvert(a,7),6);
                    case "7": return a;
                    case "8": return bigconvert(smallconvert(a,7),8);
                    case "9": return bigconvert(smallconvert(a,7),9);
                    case "10":return smallconvert(a,7);
                }
            case "8":
                switch(to){
                    case "2": return bigconvert(smallconvert(a,8),2);
                    case "3": return bigconvert(smallconvert(a,8),3);
                    case "4": return bigconvert(smallconvert(a,8),4);
                    case "5": return bigconvert(smallconvert(a,8),5);
                    case "6": return bigconvert(smallconvert(a,8),6);
                    case "7": return bigconvert(smallconvert(a,8),7);
                    case "8": return a;
                    case "9": return bigconvert(smallconvert(a,8),9);
                    case "10":return smallconvert(a,8);
                }
            case "9":
                switch(to){
                    case "2": return bigconvert(smallconvert(a,9),2);
                    case "3": return bigconvert(smallconvert(a,9),3);
                    case "4": return bigconvert(smallconvert(a,9),4);
                    case "5": return bigconvert(smallconvert(a,9),5);
                    case "6": return bigconvert(smallconvert(a,9),6);
                    case "7": return bigconvert(smallconvert(a,9),7);
                    case "8": return bigconvert(smallconvert(a,9),8);
                    case "9": return a;
                    case "10":return smallconvert(a,9);
                }
            case "10":
                switch(to){
                    case "2": return bigconvert(a,2);
                    case "3": return bigconvert(a,3);
                    case "4": return bigconvert(a,4);
                    case "5": return bigconvert(a,5);
                    case "6": return bigconvert(a,6);
                    case "7": return bigconvert(a,7);
                    case "8": return bigconvert(a,8);
                    case "9": return bigconvert(a,9);
                    case "10":return a;

                }
        }
        return 457;
    }
    void Energy_convetrer(String from, String to, String amount){
        long from_amount = Long.parseLong(amount);
        long to_amount = converter(to,from,from_amount);
            String output = Long.toString(to_amount);
            answer.setText(output);}
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



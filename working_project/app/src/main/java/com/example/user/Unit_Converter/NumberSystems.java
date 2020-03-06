package com.example.user.Unit_Converter;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.math.BigInteger;

public class NumberSystems extends AppCompatActivity {
    String[] data = {"2","3", "4", "5", "6", "7", "8", "9","10","11","12","13","14","15","16"};
 //   Map<String, Double> types = new TreeMap<>();
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.my_spinner, data);
       // type_init();
        answer = (TextView)findViewById(R.id.textView2_e);
        answer.setText("result");
        final EditText in = (EditText)findViewById(R.id.editText_e);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        Spinner spinner = (Spinner) findViewById(R.id.from_type_e);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_e);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);
            android.widget.ListPopupWindow popupWindow1 = (android.widget.ListPopupWindow) popup.get(spinner1);
            popupWindow.setHeight(getResources().getDimensionPixelSize(R.dimen._126sdp));
            popupWindow1.setHeight(getResources().getDimensionPixelSize(R.dimen._126sdp));
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
        }
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
                    else{Notation_converter(from1, to1, input);}}
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
   // void type_init(){
   //     types.put("2", 1000.0);
   //     types.put("3", 1000000.0);
   //     types.put("4", 2685000.0);
   //     types.put("5", 4.187);
   //     types.put("6", 4187.0);
   //     types.put("7", 4187000.0);
   //     types.put("8", 3600000.0);
    //    types.put("9", 3600.0);
    //    types.put("10", 1.0);
   // }
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
                        else{Notation_converter(from1, to1, input);}}
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
                        else{Notation_converter(from1, to1, input);}}
                    else{
                        if(a.check(input,from)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                        else{answer.setText("result");}}
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    BigInteger step(BigInteger a, int b){
        BigInteger one = new BigInteger("1");
        BigInteger n = a;
        if(b==0){return one;}
        else{
        for(int i = 0;i<b-1;++i){
        a=a.multiply(n);
        }
        return a;}
    }
    String check(String a){
        switch (a){
            case "A":return "10";
            case "B":return "11";
            case "C":return "12";
            case "D":return "13";
            case "E":return "14";
            case "F":return "15";
            case "a":return "10";
            case "b":return "11";
            case "c":return "12";
            case "d":return "13";
            case "e":return "14";
            case "f":return "15";
                default:return a;
        }
    }
    BigInteger smallconvert(String x1,BigInteger z){
        BigInteger sum= new BigInteger("0");
        String x = x1;
        for(int i = 0; i<x.length(); ++i){
            StringBuilder sb = new StringBuilder(x1);
            BigInteger xz= new BigInteger(check(String.valueOf(x1.charAt(x1.length()-1))));
            sum=(step(z,i)).multiply(xz).add(sum);
            sb.deleteCharAt(x1.length()-1);
            x1 = sb.toString();
        }
        return sum;
    }
    String bigconvert(BigInteger x1, BigInteger z){
        String sum1 = "";
        if(x1.compareTo(BigInteger.ZERO)==0){sum1 = "0";}
        while (x1.compareTo(BigInteger.ZERO)>0){
            String a = String.valueOf(x1.mod(z));
            int myInt = Integer.parseInt(a);
            String a1 = "";
            switch(myInt)
                {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9: a1 = a; break;
                    case 10: a1="A"; break;
                    case 11: a1="B"; break;
                    case 12: a1="C"; break;
                    case 13: a1="D"; break;
                    case 14: a1="E"; break;
                    case 15: a1="F"; break;}
            sum1=a1+sum1;
            x1=x1.divide(z);
        }
        return sum1;
    }
    String converter(String to, String from, String a){
       BigInteger from_1 = new BigInteger(from);
        BigInteger to_1 = new BigInteger(to);
        if(to == "10"){return String.valueOf(smallconvert(a,from_1));}
        else if(to.equals(from)){return String.valueOf(a);}
        else{return (bigconvert(smallconvert(a,from_1),to_1));
             }
    }
    void Notation_converter(String from, String to, String amount){
        String to_amount = String.valueOf(converter(to,from, amount));
            answer.setText(to_amount);}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_upgraded, menu);
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
            case R.id.action_4:
                Intent intent3 = new Intent(this, Settings.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }}



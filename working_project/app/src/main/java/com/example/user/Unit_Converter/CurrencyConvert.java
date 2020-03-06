package com.example.user.Unit_Converter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class CurrencyConvert extends AppCompatActivity {

    Map<String, Double> types = new TreeMap<>();
    static String input= "";
    public int to;
    public int from;
    static int i=0;
    static int j=0;
    static int k=0;
    static int z=0;
    public static String[] rate = new String[2];
    public  String[] val;
    TextView answer;
    EditText in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_convert);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.name, R.layout.my_spinner);
        val = getResources().getStringArray(R.array.value);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        Spinner spinner = (Spinner) findViewById(R.id.from_type_cur);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_cur);
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
    protected void aftersmthhappened(){
        Check a = new Check();
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
            }
    }
    @Override
    protected void onStart() {
        if(k==1){
            aftersmthhappened();}
        if(k==0){
            input= "";
            i=0;
            j=0;}
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        k=0;
        super.onDestroy();
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
        String rate1 = "";
        String rate2 = "";
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                CurrencyRatesWeb getXML = new CurrencyRatesWeb();
                try {
                    getXML.USD[0] = val[from];
                    getXML.USD[1] = val[to];
                    getXML.execute();
                    rate = getXML.get();
                    rate2=rate[0];
                    rate1=rate[1];
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                int scale = 10;
                Settings sss = new Settings();
                scale = sss.getDefaults("scale", CurrencyConvert.this);
                int count = 0;
                BigDecimal from_amount = new BigDecimal(amount);
                BigDecimal from1 = new BigDecimal(rate2);
                BigDecimal to1 = new BigDecimal(rate1);
                BigDecimal ten = new BigDecimal("10");
                BigDecimal one = new BigDecimal("1");
                while (from_amount.compareTo(ten) >= 0) {
                    from_amount = from_amount.divide(ten, 1000, ROUND_HALF_UP);
                    ++count;
                }
                while ((from_amount.compareTo(one) < 0) && from_amount.compareTo(BigDecimal.ZERO) > 0) {
                    from_amount = from_amount.multiply(ten);
                    --count;
                }
                while (from1.compareTo(ten) >= 0) {
                    from1 = from1.divide(ten, 1000, ROUND_HALF_UP);
                    --count;
                }
                while (from1.compareTo(one) < 0) {
                    from1 = from1.multiply(ten);
                    ++count;
                }
                while (to1.compareTo(ten) >= 0) {
                    to1 = to1.divide(ten, 1000, ROUND_HALF_UP);
                    ++count;
                }
                while (to1.compareTo(one) < 0) {
                    to1 = to1.multiply(ten);
                    --count;
                }
                BigDecimal to_amount1 = to1.divide(from1, scale, ROUND_HALF_UP);
                BigDecimal to_amount = from_amount.multiply(to_amount1);
                if(to_amount.compareTo(BigDecimal.ZERO)==0){answer.setText("0");}
                else{while (to_amount.compareTo(ten) >= 0) {
                    to_amount = to_amount.divide(ten, 1000, ROUND_HALF_UP);
                    ++count;
                }
                while (to_amount.compareTo(one) < 0 && to_amount.compareTo(BigDecimal.ZERO) > 0) {
                    to_amount = to_amount.multiply(ten);
                    --count;
                }
                if (count <= 7 && count >= -7) {
                    if (scale<= -count && count<0){
                        BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                        String output = to_amountf.toPlainString() + "E" + Integer.toString(count);
                        answer.setText(output);}
                    else{
                        BigDecimal BigTen = new BigDecimal(degree(count));
                        to_amount = to_amount.multiply(BigTen);
                        BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                        String output = to_amountf.toPlainString();
                        answer.setText(output);
                    }
                } else if (count > 7) {
                    count -= 7;
                    BigDecimal BigTen = new BigDecimal("10000000");
                    to_amount = to_amount.multiply(BigTen);
                    BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                    String output = to_amountf.toPlainString() + "E" + Integer.toString(count);
                    answer.setText(output);
                }
                else if (count < -7) {
                    if (scale>= -count){
                    count += 7;
                    BigDecimal BigTen = new BigDecimal("10000000");
                    to_amount = to_amount.divide(BigTen);
                    BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                    String output = to_amountf.toPlainString() + "E" + Integer.toString(count);
                    answer.setText(output);}
                else {
                    BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                    String output = to_amountf.toPlainString() + "E" + Integer.toString(count);
                    answer.setText(output);
                }}}

            } else
                Toast.makeText(CurrencyConvert.this, "Please connect to a network to use Currency Converter", Toast.LENGTH_SHORT).show();
        }

    String degree(int count)
    {
        String BigTen = "1";
        if(count > 0){
        for(int x = 0; x < count; x++)
        {
            BigTen = BigTen + "0";
        }}
        else if(count<0){
            for(int x = 0; x < -(count+1); x++)
            {
                BigTen = "0" + BigTen;
            }
            BigTen = "0."+BigTen;
        }
        return BigTen;
    }
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
                k=1;
                startActivity(intent1);
                return true;
            case R.id.action_3:
                Intent intent2 = new Intent(this, Help.class);
                k=1;
                startActivity(intent2);
                return true;
            case R.id.action_4:
                Intent intent3 = new Intent(this, Settings.class);
                k=1;
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }}

           /*     if(val[from].equals("EUR")){rate2="1";}
                        else{
                        try {
                        getXML.USD = val[from];
                        getXML.execute();
                        rate2 = getXML.get();
                        } catch (InterruptedException e) {
                        e.printStackTrace();
                        } catch (ExecutionException e) {
                        e.printStackTrace();
                        }}
                        if(val[to].equals("EUR")){rate1="1";}
                        else{
                        getXML = new CurrencyRatesWeb();
                        try {
                        getXML.USD = val[to];
                        getXML.execute();
                        rate1 = getXML.get();
                        } catch (InterruptedException e) {
                        e.printStackTrace();
                        } catch (ExecutionException e) {
                        e.printStackTrace();
                        }}*/
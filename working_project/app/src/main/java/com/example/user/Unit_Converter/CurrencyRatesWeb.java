package com.example.user.Unit_Converter;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class CurrencyRatesWeb extends AsyncTask<Void, Void, String[]> {
    public String[] USD = new String[2];
    public String[] rate = new String[2];
    String curr;
    URL url;

    @Override
    protected String[] doInBackground(Void... params) {

        try {
            url = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            // We will get the XML from an input stream
            xpp.setInput(getInputStream(url), "UTF_8");
            if ((Objects.equals(USD[1], "EUR")) && (Objects.equals(USD[0], "EUR"))) {rate[0]="1";rate[1]="1";}
            else if (Objects.equals(USD[0], "EUR")) {
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    if (eventType == XmlPullParser.START_TAG) {


                        if (xpp.getName().equalsIgnoreCase("Cube")) {
                            curr = xpp.getAttributeValue(null, "currency");
                            if (Objects.equals(USD[1], curr)) {
                                rate[1] = xpp.getAttributeValue(null, "rate");
                                rate[0] = "1";
                            }

                        }
                    }

                    eventType = xpp.next(); //move to next element
                }
            }
            else if (Objects.equals(USD[1], "EUR")) {
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    if (eventType == XmlPullParser.START_TAG) {


                        if (xpp.getName().equalsIgnoreCase("Cube")) {
                            curr = xpp.getAttributeValue(null, "currency");
                            if (Objects.equals(USD[0], curr)) {
                                rate[0] = xpp.getAttributeValue(null, "rate");
                                rate[1] = "1";
                            }

                        }
                    }

                    eventType = xpp.next(); //move to next element
                }
            }
             else{
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    if (eventType == XmlPullParser.START_TAG) {


                        if (xpp.getName().equalsIgnoreCase("Cube")) {
                            curr = xpp.getAttributeValue(null, "currency");
                            if (Objects.equals(USD[0], curr)) {
                                rate[0] = xpp.getAttributeValue(null, "rate");
                            }
                            if (Objects.equals(USD[1], curr)) {
                                rate[1] = xpp.getAttributeValue(null, "rate");
                            }

                        }
                    }

                    eventType = xpp.next(); //move to next element
                }}

            } catch(MalformedURLException e){
                e.printStackTrace();
            } catch(XmlPullParserException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }

        return rate;

    }

    @Override
    protected void onPostExecute(String[] result) {
        super.onPostExecute(result);
    }



    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

}

package com.example.user.Unit_Converter;

public class CheckSett {
    public static boolean check(String vvodimoechislo){
        int t=0;
        int q=0;
        char[] a = new char[vvodimoechislo.length()];
        for(int i=0;i<vvodimoechislo.length();i++){
            a[i]=vvodimoechislo.charAt(i);
        }
        for(int j=0;j<vvodimoechislo.length();j++){
            switch (a[j]) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':break;
                default: t=t+1;
                    break;
            }}
        if (t>0){return false;}
        else{return true;}

    }}

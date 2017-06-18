package com.example.user.project_x;

public class Check1 {
    public static boolean check(String vvodimoechislo){
        int t=0;
        int q=0;
        int z=0;
        char[] a = new char[vvodimoechislo.length()];
        for(int i=0;i<vvodimoechislo.length();i++){
            a[i]=vvodimoechislo.charAt(i);
        }
        for(int j=0;j<vvodimoechislo.length();j++){
            switch (a[j]) {
                case '.':q=q+1;
                    break;
                case '-':z=z+1;
                    break;
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
        if (q>1 || t>0 || z>1){return false;}
        else{return true;}

    }}
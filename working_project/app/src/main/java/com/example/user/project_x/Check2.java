package com.example.user.project_x;

public class Check2 {
    public static boolean check(String vvodimoechislo,long from){
        int t=0;
        int q=0;
        char[] a = new char[vvodimoechislo.length()];
        for(int i=0;i<vvodimoechislo.length();i++){
            a[i]=vvodimoechislo.charAt(i);
        }
        for(int j=0;j<vvodimoechislo.length();j++){
            switch (a[j]) {
                case '.':q=q+1;
                    break;
                case '0':break;
                case '1':break;
                case '2':
                    if(from==2){t=t+1;}
                    break;
                case '3':
                    if(from==2||from==3){t=t+1;}
                    break;
                case '4':
                    if(from==2||from==3||from==4){t=t+1;}
                    break;
                case '5':
                    if(from==2||from==3||from==4||from==5){t=t+1;}
                    break;
                case '6':
                    if(from==2||from==3||from==4||from==5||from==6){t=t+1;}
                    break;
                case '7':
                    if(from==2||from==3||from==4||from==5||from==6||from==7){t=t+1;}
                    break;
                case '8':
                    if(from==2||from==3||from==4||from==5||from==6||from==7||from==8){t=t+1;}
                    break;
                case '9':
                    if(from==2||from==3||from==4||from==5||from==6||from==7||from==8||from==9){t=t+1;}
                    break;
                default: t=t+1;
                    break;
            }}
        if (q>0 || t>0){return false;}
        else{return true;}

    }}

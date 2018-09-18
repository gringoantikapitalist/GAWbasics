package com.example.tequilagrexit.testme;

import android.widget.TextView;


public class seconddate {
    String schicht;
    static final String[] pikmonat = {"01/JA", "02/FE", "03/MR", "04/AP", "05/MA", "06/JN",
                                     "07/JL", "08/AU", "09/SE", "10/OC", "11/NO", "12/DE"};
    seconddate(){

    }
   public int readlotyear(TextView lot) {
        String message = lot.getText().toString();
        return 2010 + Integer.parseInt(String.valueOf(message.charAt(2)));

    }
    public int readlotday(TextView lot) {
        String message = lot.getText().toString();
        int nutag = Integer.parseInt(String.valueOf(message.charAt(5)));
        if (nutag == 7)
            nutag = 1;
        else
            nutag = nutag + 1;
        return nutag;
    }
    public int readlotweek(TextView lot){
        String message = lot.getText().toString();
        String woche = message.substring(3, 5);
        return Integer.parseInt(String.valueOf(woche));
    }
    public String readlotshift(TextView lot){
        int nuschicht;
        String message = lot.getText().toString();
       //tring schicht = "";
        if(lot.length()==6)
            nuschicht = 4;
        else

        nuschicht = Integer.parseInt(String.valueOf(message.charAt(6)));
        switch (nuschicht){
            case 1:
               schicht = "  Früh";
                break;
            case 2:
               schicht = "  Spät";
                break;
            case 3:
               schicht = "  Nacht";
                break;
            default:
                schicht = "";
                break;
        }
        return schicht;
    }
    public boolean string_ok(TextView lot){
        String help = lot.getText().toString();
        if (help.length()>=6)
            return true;
        else return false;
    }
}

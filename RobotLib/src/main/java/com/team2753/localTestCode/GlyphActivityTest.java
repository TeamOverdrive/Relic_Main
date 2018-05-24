package com.team2753.localTestCode;

import java.util.ArrayList;

/**
 * Created by joshua9889 on 4/24/2018.
 */

public class GlyphActivityTest {
    public static void main(String[] args){

        // From this...
        ArrayList<Double> positions = new ArrayList<>();

        // Adding numbers to future string
        positions.add(4.0);
        positions.add(3.0);

        // Saving to pref
        String positionsString = "";
        for (int i=0;i<positions.size();i++) {
            if(i+1<positions.size())
                positionsString += String.valueOf(positions.get(i)) + ",";
            else
                positionsString += String.valueOf(positions.get(i));
        }
        System.out.println(positionsString);


        // Reading from Prefs
        String moddedString = positionsString;
        String[] newT = moddedString.split(",");

        // Converting to doubles
        ArrayList<Double> outputNumbers = new ArrayList<>();
        for (String s:newT) {
            outputNumbers.add(Double.valueOf(s));
        }

        // ...to this.
        for (Double numb:outputNumbers
             ) {
            System.out.println(numb);
        }
    }
}

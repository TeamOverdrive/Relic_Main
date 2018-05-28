package com.team2753.localTestCode.control;

/**
 * Created by joshua9889 on 5/25/2018.
 */

public class test {
    public static void main(String... args){
        String newRow = "                                             ";

        int[] data = new int[]{
                1, 5, 4, 3
        };

        int max = 0;
        for(int currentCheck:data){
            if(currentCheck>max)
                max = currentCheck;
        }

        String[] rows = new String[max];
        for(int i=0;i<max;i++)
            rows[i] = newRow;

        System.out.println(data.length);
        System.out.println("--------");
        for(int x=0;x<data.length;x++){
            StringBuilder myName = new StringBuilder(newRow);
            myName.setCharAt(x, 'o');
            rows[data[x]-1] = myName.toString() ;
        }


        int i = rows.length;
        while (i>0){
            System.out.println(rows[i-1]);
            i--;
        }
    }
}

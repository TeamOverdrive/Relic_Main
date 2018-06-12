package com.team2753.auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 4/27/2018.
 */

public class TelemeteryGlyphChoser {

    private Telemetry telemetry = null;

    public TelemeteryGlyphChoser(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    private static final char POINTER = '^';
    private static final char GLYPH = 'g';

    private String[] glyphMap = {"#________#________#________#",
            "11_______1________1_______11",
            "1_1______1________1______1_1",
            "1__1_____1________1_____1__1",
            "1___1____1________1____1___1",
            "1____1___1________1___1____1",
            "1_____1__1________1__1_____1",
            "1______1_1________1_1______1",
            "1_______11________11_______1",
            "#________#________#________#",
    };
    private String[] map = {"#________#________#________#",
            "11_______1________1_______11",
            "1_1______1________1______1_1",
            "1__1_____1________1_____1__1",
            "1___1____1________1____1___1",
            "1____1___1________1___1____1",
            "1_____1__1________1__1_____1",
            "1______1_1________1_1______1",
            "1_______11________11_______1",
            "#________#________#________#",
    };

    private int pointerX = 0;
    private int pointerY = 0;

    public static void drawPoint(String[] drawTo, String[] drawOn, int pointX, int pointY, char pointChar) {

        char[] rowToArray = drawOn[pointY].toCharArray();
        rowToArray[pointX] = pointChar;

        drawTo[pointY] = new String(rowToArray);
    }

    public static void clearPoint(String[] map, int pointX, int pointY) {

        String[] mapCleared = {"#________#________#________#",
                "11_______1________1_______11",
                "1_1______1________1______1_1",
                "1__1_____1________1_____1__1",
                "1___1____1________1____1___1",
                "1____1___1________1___1____1",
                "1_____1__1________1__1_____1",
                "1______1_1________1_1______1",
                "1_______11________11_______1",
                "#________#________#________#",
        };

        char[] clearedRowToArray = mapCleared[pointY].toCharArray();
        char replacingChar = clearedRowToArray[pointX];
        char[] rowToArray = map[pointY].toCharArray();
        rowToArray[pointX] = replacingChar;
        map[pointY] = new String(rowToArray);
    }

    public static void copyMapTo(String[] copyFrom, String[] copyTo) {

        if (copyFrom.length == copyTo.length) {
            for (int copyIteration = 0; copyIteration < copyFrom.length; ++copyIteration) {
                copyTo[copyIteration] = copyFrom[copyIteration];
            }
        }
    }

    public static void clearPointer(String[] map, int pointX, int pointY) {

        char[] clearedRowToArray = map[pointY].toCharArray();
        char replacingChar = clearedRowToArray[pointX];
        char[] rowToArray = map[pointY].toCharArray();
        rowToArray[pointX] = replacingChar;
        map[pointY] = new String(rowToArray);

        //System.out.println("Point Removed");
    }

    public void drawMap(String[] args) {
        for (String row : args) {
            //System.out.println(row);
            telemetry.addLine(row);
        }
    }

    public void clearMap(String[] clearThisMap) {
        String[] mapCleared = {"#________#________#________#",
                "11_______1________1_______11",
                "1_1______1________1______1_1",
                "1__1_____1________1_____1__1",
                "1___1____1________1____1___1",
                "1____1___1________1___1____1",
                "1_____1__1________1__1_____1",
                "1______1_1________1_1______1",
                "1_______11________11_______1",
                "#________#________#________#",
        };

        for (int copyIteration = 0; copyIteration < clearThisMap.length; ++copyIteration) {
            clearThisMap[copyIteration] = mapCleared[copyIteration];
        }

        drawMap(clearThisMap);
    }


}

package com.team2753.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team2753.Team2753Linear;


/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 5/29/2018.
 */

@TeleOp(name = "Glyph Picker")

public class gLyphitypickerteLeop extends Team2753Linear {

    /*
    #________#________#________#
    11_______1________1_______11
    1_1______1________1______1_1
    1__1_____1________1_____1__1
    1___1____1________1____1___1
    1____1___1________1___1____1
    1_____1__1________1__1_____1
    1______1_1________1_1______1
    1_______11________11_______1
    #________#________#________#
     */


    /*
    ++++++++++++++++++++++++++++
    ++++++++++++++++++++++++++++
    ++++++++++++++++++++++++++++
    ++++++++++++++++++++++++++++
    ++++++++++++++++++++++++++++
    ++++++++++++++++++++++++++++
    ++++++++++++++++++++++++++*+
    ++++++++++++++++++++++++++++

     */

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


    private String[] newMap = {
            "+++++++++#++++++++++++++++++++++++#+++++++++",
            "+1++++++++11++++++++++++++++++++11++++++++1+",
            "++1+++++++11++++++++++++++++++++11+++++++1++",
            "+++1++++++11++++++++++++++++++++11++++++1+++" ,
            "++++1+++++11++++++++++++++++++++11+++++1++++" ,
            "+++++1++++11++++++++++++++++++++11++++1+++++" ,
            "++++++1+++11++++++++++++++++++++11+++1++++++" ,
            "+++++++1++11++++++++++++++++++++11++1+++++++" ,
            "++++++++1+11++++++++++++++++++++11+1++++++++" ,
            "+++++++++11111111111111111111111111+++++++++"
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

    public static void drawPointer(String[] drawTo, String[] drawOn) {

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

    @Override
    public void runOpMode() {

        //Init
        drawMap(newMap);

        int lastPointerX, lastPointerY;


        //the boolean names are probably extremely misleading but i'm too retarded to change them appropriately

        boolean upPressed = true;
        boolean upLastPressed = false;

        boolean downPressed = true;
        boolean downLastPressed = false;

        boolean leftPressed = true;
        boolean leftLastPressed = false;

        boolean rightPressed = true;
        boolean rightLastPressed = false;

        //navigation and draw/clear points
        while (!opModeIsActive() && !isStopRequested()) {

            lastPointerX = pointerX;
            lastPointerY = pointerY;

            //Pointer Controls

            if (gamepad1.dpad_up) {
                if (upLastPressed != upPressed) {
                    pointerY++;
                }
                upLastPressed = upPressed;
            } else {
                upLastPressed = !upPressed;
            }

            if (gamepad1.dpad_down) {
                if (downLastPressed != downPressed) {
                    pointerY--;
                }
                downLastPressed = downPressed;
            } else {
                downLastPressed = !downPressed;
            }

            if (gamepad1.dpad_right) {
                if (rightLastPressed != rightPressed) {
                    pointerX++;
                }
                rightLastPressed = rightPressed;
            } else {
                rightLastPressed = !rightPressed;
            }

            if (gamepad1.dpad_left) {
                if (leftLastPressed != leftPressed) {
                    pointerX--;
                }
                leftLastPressed = leftPressed;
            } else {
                leftLastPressed = !leftPressed;
            }


            //Pointer Boundaries
            if (pointerY > 0) {
                pointerY = 0;
            }
            if (pointerY < -9) {
                pointerY = -9;
            }
            if (pointerX > 27) {
                pointerX = 27;
            }
            if (pointerX < 0) {
                pointerX = 0;
            }

            //Glyph controls

            if (gamepad1.x) {
                drawPoint(glyphMap, glyphMap, pointerX, -pointerY, GLYPH);
            }
            if (gamepad1.b) {
                clearPoint(glyphMap, pointerX, -pointerY);
            }

            //Clear last Pointer if we moved
            if ((lastPointerX != pointerX) || (lastPointerY != pointerY)) {
                //clear pointer
                clearPoint(map, lastPointerX, -lastPointerY);
            }

//            //place glyphs on displayed map
//            copyMapTo(glyphMap, map);
//
//            //Draw Pointer onto displayed map
//            drawPoint(map, map, pointerX, -pointerY, POINTER);
//
//            drawMap(map);
            telemetry.update();
        }

        telemetry.clearAll();
        //output points


        //waitForTick(5000);
        requestOpModeStop();
    }
}

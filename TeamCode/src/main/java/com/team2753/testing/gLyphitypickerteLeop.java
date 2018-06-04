package com.team2753.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team2753.Team2753Linear;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 5/29/2018.
 */

@TeleOp(name = "Glyph Picker")

public class gLyphitypickerteLeop extends Team2753Linear{

    String[] glyphMap = {"+--------+--------+--------+",
            "|\\       |        |       /|",
            "| \\      |        |      / |",
            "|  \\     |        |     /  |",
            "|   \\    |        |    /   |",
            "|    \\   |        |   /    |",
            "|     \\  |        |  /     |",
            "|      \\ |        | /      |",
            "|       \\|        |/       |",
            "+--------+--------+--------+"
    };

    //pointerMap probably unneeded

    /*
    String[] pointerMap = {"+--------+--------+--------+",
            "|\\       |        |       /|",
            "| \\      |        |      / |",
            "|  \\     |        |     /  |",
            "|   \\    |        |    /   |",
            "|    \\   |        |   /    |",
            "|     \\  |        |  /     |",
            "|      \\ |        | /      |",
            "|       \\|        |/       |",
            "+--------+--------+--------+"
    };
    */

    String[] map = {"+--------+--------+--------+",
            "|\\       |        |       /|",
            "| \\      |        |      / |",
            "|  \\     |        |     /  |",
            "|   \\    |        |    /   |",
            "|    \\   |        |   /    |",
            "|     \\  |        |  /     |",
            "|      \\ |        | /      |",
            "|       \\|        |/       |",
            "+--------+--------+--------+"
    };

    private static final char POINTER = 'X';
    private static final char GLYPH = 'G';

    private int pointerX = 0;
    private int pointerY = 0;

    public void drawMap(String[] args){
        for (String row : args) {
            //System.out.println(row);
            telemetry.addLine(row);
        }
        telemetry.update();
    }

    public void clearMap(String[] clearThisMap){
        String[] mapCleared = {"+--------+--------+--------+",
                "|\\       |        |       /|",
                "| \\      |        |      / |",
                "|  \\     |        |     /  |",
                "|   \\    |        |    /   |",
                "|    \\   |        |   /    |",
                "|     \\  |        |  /     |",
                "|      \\ |        | /      |",
                "|       \\|        |/       |",
                "+--------+--------+--------+",
        };

        for(int copyIteration = 0; copyIteration < clearThisMap.length; ++copyIteration){
            clearThisMap[copyIteration] = mapCleared[copyIteration];
        }

        drawMap(clearThisMap);
    }

    public static void drawPoint(String[] drawTo, String[] drawOn, int pointX, int pointY, char pointChar){

        char[] rowToArray = drawOn[pointY].toCharArray();
        rowToArray[pointX] = pointChar;

        drawTo[pointY] = new String(rowToArray);

        //System.out.println("Point Drawn");
    }

    public static void clearPoint(String[] map, int pointX, int pointY){

        String[] mapCleared = {"+--------+--------+--------+",
                "|\\       |        |       /|",
                "| \\      |        |      / |",
                "|  \\     |        |     /  |",
                "|   \\    |        |    /   |",
                "|    \\   |        |   /    |",
                "|     \\  |        |  /     |",
                "|      \\ |        | /      |",
                "|       \\|        |/       |",
                "+--------+--------+--------+",
        };

        char[] clearedRowToArray = mapCleared[pointY].toCharArray();
        char replacingChar = clearedRowToArray[pointX];
        char[] rowToArray = map[pointY].toCharArray();
        rowToArray[pointX] = replacingChar;
        map[pointY] = new String(rowToArray);

        //System.out.println("Point Removed");
    }

    public static void drawPointer(String[] drawTo, String[] drawOn){

    }

    public static void copyMapTo(String[] copyFrom, String[] copyTo){

        if(copyFrom.length == copyTo.length) {
            for (int copyIteration = 0; copyIteration < copyFrom.length; ++copyIteration) {
                copyTo[copyIteration] = copyFrom[copyIteration];
            }
        }
    }

    public static void clearPointer(String[] map, int pointX, int pointY){

        char[] clearedRowToArray = map[pointY].toCharArray();
        char replacingChar = clearedRowToArray[pointX];
        char[] rowToArray = map[pointY].toCharArray();
        rowToArray[pointX] = replacingChar;
        map[pointY] = new String(rowToArray);

        //System.out.println("Point Removed");
    }

    @Override
    public void runOpMode(){

        //Init
        drawMap(map);

        //navigation and draw/clear points
        while(!opModeIsActive()){

            int lastPointerX = pointerX;
            int lastPointerY = pointerY;

            //Pointer Controls
            if(gamepad1.dpad_up || gamepad2.dpad_up){
                pointerY++;
            }
            else if(gamepad1.dpad_down || gamepad2.dpad_down){
                pointerY--;
            }
            else if(gamepad1.dpad_right || gamepad2.dpad_right){
                pointerX++;
            }
            else if(gamepad1.dpad_left || gamepad2.dpad_left){
                pointerX--;
            }

            //Pointer Boundaries
            if(pointerY > 0){
                pointerY = 0;
            }
            if(pointerY < -9){
                pointerY = -9;
            }
            if(pointerX > 27){
                pointerX = 27;
            }
            if(pointerX < 0){
                pointerX = 0;
            }

            //Glyph controls

            if(gamepad1.x){
                drawPoint(glyphMap, glyphMap, pointerX, -pointerY, GLYPH);
            }
            if(gamepad1.b){
                clearPoint(glyphMap, pointerX, -pointerY);
            }

            //Clear last Pointer if we moved
            if((lastPointerX != pointerX) || (lastPointerY != pointerY)){
                //clear pointer
                clearPoint(map, lastPointerX, -lastPointerY);
            }

            //place glyphs on displayed map
            copyMapTo(glyphMap, map);

            //Draw Pointer onto displayed map
            drawPoint(map, map, pointerX, -pointerY, POINTER);

            drawMap(map);
            telemetry.update();

        }

        telemetry.clearAll();
        //output points


        sleep(5000);
        requestOpModeStop();
    }
}

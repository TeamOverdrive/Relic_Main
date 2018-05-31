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

    String[] map = {"+--------+--------+--------+",
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



    private static final char POINTER = 'X';
    private static final char GLYPH = 'G';

    private int pointerX = 0;
    private int pointerY = 0;

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

            //Draw Pointer
            //everything past this point is broken
            //need to add layer for pointer
            drawPoint(map, pointerX, -pointerY, POINTER);
            clearPoint(map, lastPointerX, -lastPointerY);


            if(gamepad1.x){
                drawPoint(map, pointerX, -pointerY, GLYPH);
            }
            if(gamepad1.b){
                clearPoint(map, pointerX, -pointerY);
            }


            drawMap(map);
            telemetry.update();

        }

        telemetry.clearAll();
        //output points

        sleep(400);
    }

    public void drawMap(String[] args){
        for (String row : args) {
            //System.out.println(row);
            telemetry.addLine(row);
        }
        telemetry.update();
    }

    public void clearMap(String[] map){
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

        for(int copyIteration = 0; copyIteration < map.length; ++copyIteration){
            map[copyIteration] = mapCleared[copyIteration];
        }

        //System.out.println("Map Cleared");

        drawMap(map);
    }

    public static void drawPoint(String[] map, int pointX, int pointY, char pointChar){

        char[] rowToArray = map[pointY].toCharArray();
        rowToArray[pointX] = pointChar;

        map[pointY] = new String(rowToArray);

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

    public static void clearPointer(String[] map, int pointX, int pointY){

        char[] clearedRowToArray = map[pointY].toCharArray();
        char replacingChar = clearedRowToArray[pointX];
        char[] rowToArray = map[pointY].toCharArray();
        rowToArray[pointX] = replacingChar;
        map[pointY] = new String(rowToArray);

        //System.out.println("Point Removed");
    }
}

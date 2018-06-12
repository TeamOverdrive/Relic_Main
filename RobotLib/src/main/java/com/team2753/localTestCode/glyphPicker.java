package com.team2753.localTestCode;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 5/24/2018.
 */

public class glyphPicker {

    public static void main(String[] args){

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

        //init map
        clearMap(map);

        //movement controls
        boolean changed = true;

        drawPoint(map, 20, 3);
        drawPoint(map, 15, 5);
        drawPoint(map, 9,0);
        //clearPoint(map, 10, 0);

        //if changed clear and redraw

        if(changed) {
            //clear?

            drawMap(map);
            //clearMap(map);
        }
    }

    public static void drawMap(String[] args){
        for (String row : args) {
            System.out.println(row);
        }
    }

    public static void clearMap(String[] map){
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

        System.out.println("Map Cleared");

        //drawMap(map);
    }

    public static void drawPoint(String[] map, int pointX, int pointY){

        char[] rowToArray = map[pointY].toCharArray();
        rowToArray[pointX] = 'O';

        map[pointY] = new String(rowToArray);

        System.out.println("Point Drawn");
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

        System.out.println("Point Removed");
    }
}

package com.team2753.localTestCode;

// Lol im retarded

public class TestingVectorCode {
    public static void main(String[] args) throws InterruptedException {
        double currentVectorDistance = 10;
        double currentVectorAngle = -90;

        double lastVectorX = 10;
        double lastVectorY = 10;

        double nextVectorX = currentVectorDistance * Math.cos(Math.toRadians(currentVectorAngle));
        double nextVectorY = currentVectorDistance * Math.sin(Math.toRadians(currentVectorAngle));

        double currentVectorX = nextVectorX + lastVectorX;
        double currentVectorY = nextVectorY + lastVectorY;

        System.out.println("X: " + currentVectorX + " |Y:" + currentVectorY);

        lastVectorX = currentVectorX;
        lastVectorY = currentVectorY;

        currentVectorDistance = 10;
        currentVectorAngle = 90;

        nextVectorX = currentVectorDistance * Math.cos(Math.toRadians(currentVectorAngle));
        nextVectorY = currentVectorDistance * Math.sin(Math.toRadians(currentVectorAngle));

        currentVectorX = nextVectorX + lastVectorX;
        currentVectorY = nextVectorY + lastVectorY;

        System.out.println("X: " + currentVectorX + " |Y:" + currentVectorY);
    }
}

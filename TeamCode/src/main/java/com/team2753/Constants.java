package com.team2753;

/**
 * Created by heinr_000 on 5/19/2018.
 */

public class Constants {
    public static double COUNTS_PER_MOTOR_REV = 1120;     // AndyMark NeveRest 40
    private static final double DRIVE_GEAR_REDUCTION = 0.6666 ;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference w/ wheel base
    public static double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.141592);
    public static final double WHEEL_BASE = 12.625;
}

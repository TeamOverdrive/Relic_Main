package com.team2753;

import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.libs.PhoneFileReader;
import com.team2753.trajectory.FollowerConfig;

/**
 * Created by joshua9889 on 5/19/2018.
 */

public class Constants {

    public static double COUNTS_PER_MOTOR_REV = 1120;     // AndyMark NeveRest 40
    public static final double DRIVE_GEAR_REDUCTION = 0.6666 ;     // This is < 1.0 if geared UP
    public static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference w/ wheel base
    public static double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.141592);
    public static final double WHEEL_BASE = 12.625;

    // Config for the based on what we want the robot to do, not
    // based on the characteristics of the robot.
    public static TrajectoryGenerator.Config defaultTrajectoryConfig = new TrajectoryGenerator.Config();
    public static TrajectoryGenerator.Config aggressiveTrajectoryConfig = new TrajectoryGenerator.Config();

    public static FollowerConfig defaultFollowerConfig;

    // This is refreshed once every time the app is opened
    // The data is stored in a txt file so we can tune on-the-fly.
    public static PhoneFileReader.Constant p = new PhoneFileReader.Constant("p", 0.00085);
    public static PhoneFileReader.Constant d = new PhoneFileReader.Constant("d", 0);
    public static PhoneFileReader.Constant v = new PhoneFileReader.Constant("v", 0.0175);
    public static PhoneFileReader.Constant a = new PhoneFileReader.Constant("a", 0.0000002803);
    public static PhoneFileReader.Constant headingP = new PhoneFileReader.Constant("h", 0.03);

    static{
        PhoneFileReader.readConstantsFromFile();

        defaultFollowerConfig = new FollowerConfig(Constants.p.getDouble(), Constants.d.getDouble(),
                Constants.v.getDouble(), Constants.a.getDouble(), Constants.headingP.getDouble());

        // Found by using "FindVA" class
        defaultTrajectoryConfig.max_vel = 23.832; // In/s
        defaultTrajectoryConfig.max_acc = 100; // In/s^2
        defaultTrajectoryConfig.max_jerk = 100; // In/s^3
        defaultTrajectoryConfig.dt = 0.01; // seconds, change of time in each update

        aggressiveTrajectoryConfig = defaultTrajectoryConfig;
        aggressiveTrajectoryConfig.max_acc = 2000;
        aggressiveTrajectoryConfig.max_jerk = 2000;
    }
}

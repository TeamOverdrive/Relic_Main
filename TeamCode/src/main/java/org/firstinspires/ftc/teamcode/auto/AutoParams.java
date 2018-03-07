package org.firstinspires.ftc.teamcode.auto;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/4/2018.
 */

public class AutoParams {

    //Parameters for All Jewel and Vuforia autonomous programs

    public static final boolean AUTO = true;
    public static final boolean TELEOP = false;

    /**
     * Color boolean values
     */

    public static final boolean RED = true;
    public static final boolean BLUE = false;

    /**
     * General speed params for autonomous
     * driving speeds range from -1 to 1
     *
     * @param autoSpeed general driving speed
     * @param autoTurnSpeed speed when turning
     */

    public static final double autoSpeed = 0.4;
    public static final double autoTurnSpeed = 0.4;


    /**
     * Jewel params during the jewel phase of autonomous
     *
     * @param jewelArmDelayMS amount of time in ms to wait for the jewel arm to deploy and retract
     * @param jewelColorTimeoutS amount of time in seconds
     * @param jewelTurn degrees to turn when hitting the jewel off
     * @param jewelTurnTimeoutS amount of time in s before timing out from the jewelTurn
     */

    public static final int jewelArmDelayMS = 400;
    public static final double jewelColorTimeoutS = 5;
    public static final int jewelTurn = 25;
    public static final double jewelTurnSpeed = 0.3;
    public static final double jewelTurnTimeoutS = 3;


    //how many votes before making a decision on VuMark.
    public static final int vuMarkVotes = 50;

    public static final int jewelVotes = 100;



    /*PID Values for encoderPIDDrive*/
    //these need to be tuned
    public static final double Kp = 1;
    public static final double Ki = 0.3;
    public static final double Kd = 0.1;

}

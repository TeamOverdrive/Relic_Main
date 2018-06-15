package com.team2753.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Arrays;
import java.util.List;

/**
 * Robot class combines all of subsystems into one importable class.
 * Used for init all robot hardware.
 * Created by joshua9889 on 3/23/2018.
 */

public class Robot {

    private static Robot mInstance = new Robot();
    public static Robot getInstance(){
        return mInstance;
    }

    private Drive mDrive = new Drive();
    private Intake mIntake = new Intake();
    private Jewel mJewel = new Jewel();
    private Lift mLift = new Lift();
    private Phone mPhone = new Phone();
    private Relic mRelic = new Relic();
    private Slammer mSlammer = new Slammer();
    private FollowerWheel mFollowerWheel = new FollowerWheel();

    private List<Subsystem> subsystems = Arrays.asList(
            mDrive, mJewel, mLift, mIntake, mSlammer, mPhone, mFollowerWheel, mRelic);

    /**
     * @param telemetry Opmode Telemetry object
     */
    public void outputToTelemetry(Telemetry telemetry){
        for (Subsystem subsystem:subsystems)
            subsystem.outputToTelemetry(telemetry);
    }

    /**
     * Init all subsystems in this method.
     * @param opmode The OpMode Object
     * @param autonomous If the OpMode is for autonomous mode or not.
     */
    public void init(LinearOpMode opmode, boolean autonomous) {
        // Init all subsystems
        RobotLog.v("================ Robot Subsystems Init Loop Started =============");
        for (Subsystem subsystem:subsystems){
            RobotLog.v("================ Robot Subsystem " + subsystem.toString() +
                    " Init Started =============");
            subsystem.init(opmode, autonomous);
        }
        RobotLog.v("================ Robot Subsystems Init Loop Finished =============");
    }

    /**
     * Stop all subsystems with this method.
     */
    public void stop() {
        try {
            for (Subsystem subsystem:subsystems)
                subsystem.stop();
        } catch (Exception e){}
    }


    /**
     * Reset all subsystems with this method.
     */
    public void zeroSensors() {
        try {
            for (Subsystem subsystem:subsystems)
                subsystem.zeroSensors();
        } catch (Exception e){}
    }

    /**
     * Get Drivetrain object
     * @return mDrive
     */
    public Drive getDrive(){
        return mDrive;
    }

    public Intake getIntake() {
        return mIntake;
    }

    public Jewel getJewel() {
        return mJewel;
    }

    public Lift getLift() {
        return mLift;
    }

    public Phone getPhone() {
        return mPhone;
    }

    public Relic getRelic(){
        return mRelic;
    }

    public Slammer getSlammer() {
        return mSlammer;
    }

    public FollowerWheel getFollowerWheel(){
        return mFollowerWheel;
    }
}

package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 12/10/2017.
 */

public class Jewel implements Subsystem {

    // Robot Hardware
    private Servo jewelArm = null;
    private ColorSensor jewelColor = null;

    // Used for color jewel
    public enum JewelColor{
        RED, BLUE, UNKNOWN
    }

    final private static double ARMUP = 0.76;
    final private static double ARMDOWN = 0.72;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        jewelArm = linearOpMode.hardwareMap.servo.get("jewel_arm");
        jewelColor = linearOpMode.hardwareMap.colorSensor.get("jewel_color");
        jewelColor.enableLed(true);
        retract();
    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void stop() {
        //retract();
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Jewel arm pos", jewelArm.getPosition());
        telemetry.addData("Jewel Color", this.jewelColor());
    }

    // Deploy jewel mech
    public void deploy(){
        jewelArm.setPosition(ARMDOWN);
    }

    // Retract jewel mech
    public void retract(){
        jewelArm.setPosition(ARMUP);
    }

    // Get current Jewel color
    public JewelColor jewelColor() {
        if(jewelColor.red()>jewelColor.blue())
            return JewelColor.RED;
        else if (jewelColor.red()<jewelColor.blue())
            return JewelColor.BLUE;
        else
            return JewelColor.UNKNOWN;
    }

    protected ElapsedTime runtime = new ElapsedTime();
    // Counts "votes" based on how many times it sees red/blue

    /**
     * Returns the jewel color as an enum.  Values are RED and BLUE.
     * If a timeout occurs and the sensor has not recognized a color, the method returns UNKOWN.
     *
     * @param linearOpMode  linearOpMode (in an opmode just use the keyword "this".
     * @param timeoutS      The amount of time this method is allowed to execute.
     * @return              The color of the jewel.
     */
    public JewelColor vote(LinearOpMode linearOpMode, double timeoutS) {
        int redVotes = 0;
        int blueVotes = 0;
            runtime.reset();
            while (linearOpMode.opModeIsActive()
                    &&redVotes < 300 && blueVotes < 300
                    && runtime.seconds() < timeoutS) {
                switch (jewelColor()) {
                    case RED:
                        redVotes++;
                        break;
                    case BLUE:
                        blueVotes++;
                        break;
                }
                linearOpMode.telemetry.addData("Red Votes", redVotes);
                linearOpMode.telemetry.addData("Blue Votes", blueVotes);
                linearOpMode.telemetry.addData("Red Value", jewelColor.red());
                linearOpMode.telemetry.addData("Blue Value", jewelColor.blue());
                linearOpMode.telemetry.update();
            }

            if (redVotes == 300)
                return JewelColor.RED;
            else if (blueVotes == 300)
                return JewelColor.BLUE;
            else
                return JewelColor.UNKNOWN;
    }
}

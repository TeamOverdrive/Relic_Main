package org.firstinspires.ftc.teamcode.OriginalCode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Thread.sleep;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 11/4/2017.
 */


/*This program is based off of Vortex_Main-master's Init by S Turner*/

public class Init {
    private HardwareMap hardwareMap;

    /* Motors */
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    //private DcMotor flipMotor;
    private DcMotor slideMotor;
    private DcMotor liftMotor;

    /* Servos */
    private Servo jewelArm;
    private Servo grab1;
    private Servo grab2;
    private Servo grabBottom;
    private Servo grabTop;

    /* Sensors */
    private ColorSensor jewelColor;

    public Init ()
    {
        leftMotor = null;
        rightMotor = null;
        //flipMotor = null;
        slideMotor = null;
        liftMotor = null;
        jewelArm = null;
        grab1 = null;
        grab2 = null;
        grabBottom = null;
        grabTop = null;
        jewelColor = null;
    }

    public void initTeleop(HardwareMap map)
    {
        hardwareMap  = map;

        initAll();

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //flipMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void initAuto(HardwareMap map, Telemetry telemetry, LinearOpMode opMode)
    {
        hardwareMap = map;

        initAll();

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //flipMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        opMode.idle();

        // Set all motors to run with or without encoders.  Switch to use RUN_USING_ENCODERS when encoders are installed.
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //flipMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d", leftMotor.getCurrentPosition(),rightMotor.getCurrentPosition());
        telemetry.update();

        telemetry.addData("Status", "Ready to Run");
        telemetry.update();

    }

    private void initAll ()
    {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor  = hardwareMap.dcMotor.get("right_drive");
        slideMotor = hardwareMap.dcMotor.get("slide_motor");
        //flipMotor = hardwareMap.dcMotor.get("flip_motor");
        liftMotor = hardwareMap.dcMotor.get("lift_motor");

        jewelArm = hardwareMap.servo.get("jewel_arm");
        grab1 = hardwareMap.servo.get("grab1");
        grab2 = hardwareMap.servo.get("grab2");
        grabBottom = hardwareMap.servo.get("grab_bottom");
        grabTop = hardwareMap.servo.get("grab_top");

        jewelArm.setPosition(0.75);
        grab1.setPosition(0.4);
        grab2.setPosition(0.4);
        //grabTop.setDirection(0.0);
        //grabBottom.setDirection(0.0);

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        //flipMotor.setDirection(DcMotor.Direction.FORWARD);
        slideMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
        //flipMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //flipMotor.setPower(0);
        slideMotor.setPower(0);
        liftMotor.setPower(0);

        jewelColor = hardwareMap.colorSensor.get("jewel_color");
    }

    /*Getter Methods*/

    public DcMotor getLeftMotor() {return leftMotor; }

    public DcMotor getRightMotor() {return rightMotor; }

    //public DcMotor getFlipMotor() {return flipMotor; }

    public DcMotor getSlideMotor () {return slideMotor;}

    public DcMotor getLiftMotor () {return liftMotor;}

    public Servo getJewelArm () {return jewelArm;}

    public Servo getGrab1 () {return grab1;}

    public Servo getGrab2 () {return grab2;}

    public Servo getGrabBottom () {return  grabBottom;}

    public Servo getGrabTop () {return  grabTop;}

    public ColorSensor getJewelColor() {return jewelColor;}

}

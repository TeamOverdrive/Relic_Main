package org.firstinspires.ftc.teamcode.OriginalCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 11/4/2017.
 */

/* This program is based off of Vortex_Main-master's TeleopMain_Vortex by S Turner*/

@TeleOp(name = "Teleop Main: Relic Recovery", group = "Relic_Recovery")
@Disabled
public class TeleopMain_Relic extends LinearOpMode
{

    /* Motors */
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    //private DcMotor flipMotor;
    private DcMotor slideMotor;
    private DcMotor liftMotor;

    /*Servos*/

    private Servo jewelArm;
    //private Servo claw1;
    //private Servo claw2;
    private Servo grab1;
    private Servo grab2;
    private Servo grabTop;
    private Servo grabBottom;

    /*Sensors*/
    private ColorSensor jewelColor;

    final static double CLAW1OPEN = 1.0;
    final static double CLAW1CLOSED = 0.0;
    final static double CLAW2OPEN = 0.0;
    final static double CLAW2CLOSED = 1.0;
    final static double ARMUP = 0.75;
    final static double ARMDOWN = 0.72;
    final static double GRAB1OPEN = 0.4;
    final static double GRAB1CLOSED = 0.0;
    final static double GRAB2OPEN =0.4;
    final static double GRAB2CLOSED = 0.95;

    @Override
    public void runOpMode()  {

        //Initialize the motors.
        Init init = new Init();
        init.initTeleop(hardwareMap);

        leftMotor = init.getLeftMotor();
        rightMotor = init.getRightMotor();
        //flipMotor = init.getFlipMotor();
        slideMotor = init.getSlideMotor();
        liftMotor = init.getLiftMotor();
        jewelArm = init.getJewelArm();
        grab1 = init.getGrab1();
        grab2 = init.getGrab2();
        grabBottom = init.getGrabBottom();
        grabTop = init.getGrabTop();

        //jewelColor = init.getJewelColor();

        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        //jewelArm.setPosition(0.5);


        waitForStart();

        //jewelArm.setPosition(ARMUP);

        //claw1.setPosition(0.2);
        //claw2.setPosition(0.8);

        while(opModeIsActive()) {

            /*Controls*/

            /* Drivetrain Controls */
            float leftThrottle = gamepad1.left_stick_y;
            float rightThrottle = gamepad1.right_stick_y;


            /* Clip the left and right throttle values so that they never exceed +/- 1.  */
            leftThrottle = Range.clip(leftThrottle,-1,1);
            rightThrottle = Range.clip(rightThrottle,-1,1);

            /* Scale the throttle values to make it easier to control the robot more precisely at slower speeds.  */
            leftThrottle = (float) scaleInput(leftThrottle);
            rightThrottle = (float) scaleInput(rightThrottle);

            rightMotor.setPower(rightThrottle);
            leftMotor.setPower(leftThrottle);

            //claw1.setPosition(gamepad2.left_trigger);

            if(gamepad2.left_bumper){
                jewelArm.setPosition(ARMDOWN);

            }
            else{
                jewelArm.setPosition(ARMUP);
            }

            //lift motor
            float liftThrottle = gamepad2.left_stick_y;
            liftThrottle = Range.clip(liftThrottle, -1, 1);
            liftThrottle = (float) liftScale(liftThrottle);
            liftThrottle = liftThrottle * -1;
            liftMotor.setPower(liftThrottle);



            //slide motor
            float slideThrottle = gamepad2.right_stick_y;
            slideThrottle = Range.clip(slideThrottle, -1, 1);
            slideThrottle = (float) scaleInput(slideThrottle);
            slideMotor.setPower(slideThrottle);

            //grab
            if(gamepad1.right_bumper)
            {
                grab1.setPosition(GRAB1CLOSED);
                grab2.setPosition(GRAB2CLOSED);
            }
            else if(gamepad2.right_bumper){
                grab1.setPosition(GRAB1CLOSED);
                grab2.setPosition(GRAB2CLOSED);
            }
            else
            {
                grab1.setPosition(GRAB1OPEN);
                grab2.setPosition(GRAB2OPEN);
            }

/*
            if(gamepad2.dpad_up){
                flipMotor.setPower(-0.9);
            }
            else if (gamepad2.dpad_down){
                flipMotor.setPower(0.9);
            }
            else
            {
                flipMotor.setPower(0.0);
            }
*/

/*
            if(gamepad2.left_bumper){
                claw1.setPosition(CLAW1CLOSED);
            }
            else{
                claw1.setPosition(CLAW1OPEN);
            }

            if(gamepad2.right_bumper){
                claw2.setPosition(CLAW2CLOSED);
            }
            else{
                claw2.setPosition(CLAW2OPEN);
            }
*/

        }

    }

    double scaleInput(double dVal)   {
        double[] scaleArray = {
                0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24, 0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00
                //to use a different scale,0 list alternate scale values here and comment out the line above
        };

        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16)  {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0)  {
            dScale = -scaleArray[index];
        }  else {
            dScale = scaleArray[index];
        }

        return dScale;
    }

    double liftScale(double dVal)   {
        double[] scaleArray = {
                0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24, 0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00
                //to use a different scale,0 list alternate scale values here and comment out the line above
        };

        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16)  {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0)  {
            dScale = -scaleArray[index];
        }  else {
            dScale = scaleArray[index];
        }

        return dScale;
    }

}

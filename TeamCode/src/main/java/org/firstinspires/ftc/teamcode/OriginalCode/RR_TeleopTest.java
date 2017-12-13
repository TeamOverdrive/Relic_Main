package org.firstinspires.ftc.teamcode.OriginalCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by David Zheng | Team Overdrive 2753 on 10/30/2017.
 */

@TeleOp(name="RR_TeleopTest", group="Test")

@Disabled
public class RR_TeleopTest extends LinearOpMode {

    /* Declare Motors */
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    /*Declare Sensors*/
    private ColorSensor colorSensorJewel;

@Override
    public void runOpMode(){

        leftMotor = hardwareMap.get(DcMotor.class, "left_drive");
        rightMotor = hardwareMap.get(DcMotor.class, "right_drive");
        colorSensorJewel = hardwareMap.colorSensor.get("jewel_color");

        //colorSensorJewel.setI2cAddress(I2cAddr.create8bit(0x3a));

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        /* See if colorSensorJewel is connected to the correct port*/

        //colorSensorJewel.enableLed(true);


    while (opModeIsActive()) {


        telemetry.addData("Bumper Position float", "$s", gamepad1.left_trigger);
        telemetry.update();


            /* See if  colorSensorJewel can see the color red. */
            /* Used to see if color Sensor Jewel is connected  */
            /* to the correct port with correct orientation.   */
            /* 11/1/17 */

           /* colorSensorJewel.enableLed(true);

            if(colorSensorJewel.red() >= 2){
                telemetry.clearAll();
                telemetry.addLine("colorSensorJewel.red = true");
            }

            else {
                telemetry.clearAll();
                telemetry.addLine("colorSensorJewel.red = false");
            }

            /* Gamepad 1 - Controls */

            /* Drivetrain Controls */
            float leftThrottle = gamepad1.left_stick_y;
            float rightThrottle = gamepad1.right_stick_y;

            /* Clip the left and right throttle values so that they never exceed +/- 1.  */
            leftThrottle = Range.clip(leftThrottle, -1, 1);
            rightThrottle = Range.clip(rightThrottle, -1, 1);

            /* Scale the throttle values to make it easier to control the robot more precisely at slower speeds.  */
            leftThrottle = (float) scaleInput(leftThrottle);
            rightThrottle = (float) scaleInput(rightThrottle);

            /*if (Math.abs(leftThrottle) == 0 && Math.abs(rightThrottle) == 0) {
                if (gamepad1.dpad_up) {
                    rightMotor.setPower(-1.0);
                    leftMotor.setPower(-1.0);
                } else if (gamepad1.dpad_down) {
                    rightMotor.setPower(1.0);
                    leftMotor.setPower(1.0);
                } else {
                    rightMotor.setPower(0.0);
                    leftMotor.setPower(0.0);
                }
            }
            /* Set power to the drive motors  */
            rightMotor.setPower(rightThrottle);
            leftMotor.setPower(leftThrottle);
        }

    }

    double scaleInput(double dVal)   {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24, 0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

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



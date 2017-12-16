package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

import static java.lang.Math.abs;
import static java.lang.Math.round;

/**
 * Created by joshua9889 on 12/10/2017.
 *
 * Replicated TeleopMain_Relic with new backend
 */

@TeleOp
public class Teleop extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        // Use this in place of waitForStart();
        waitForStart(this, false);

        // Loop while we are running teleop
        while (opModeIsActive()){
            /* Drivetrain Controls */
            float leftThrottle = gamepad1.left_stick_y;
            float rightThrottle = gamepad1.right_stick_y;

            /* Clip the left and right throttle values so that they never exceed +/- 1.  */
            leftThrottle = Range.clip(leftThrottle,-1,1);
            rightThrottle = Range.clip(rightThrottle,-1,1);

            /* Scale the throttle values to make it easier to control the robot more precisely at slower speeds.  */
            leftThrottle = (float) OverdriveLib.scaleInput(leftThrottle);
            rightThrottle = (float) OverdriveLib.scaleInput(rightThrottle);

            getDrive().setLeftRightPowers(leftThrottle, rightThrottle);

            // Da Jewel
            if(gamepad1.left_bumper)
                getJewel().deploy();
            else
                getJewel().retract();

            // Hand
            if(gamepad1.dpad_down)
                getHand().closeBottom();
            else if(gamepad1.dpad_up)
                getHand().closeTop();
            else if(gamepad1.dpad_left)
                getHand().openClaw();

            //Lift Control
            float liftThrottle = gamepad2.left_stick_y;
            //CLip
            liftThrottle = Range.clip(liftThrottle, -1, 1);
            //Scale
            liftThrottle = (float) OverdriveLib.scaleInput(liftThrottle);
            //Invert
            liftThrottle = liftThrottle*-1;

            getLift().setLiftPower(liftThrottle);

            /*if(abs(liftThrottle) <= 0.6)
                getLift().setPower(0.6);*/


            //Slide Control
            float slideThrottle = gamepad2.right_stick_y;
            //Clip
            slideThrottle = Range.clip(slideThrottle, -1, 1);
            //Scale
            slideThrottle = (float) OverdriveLib.scaleInput(slideThrottle);
            //Invert
            slideThrottle = slideThrottle;
            getLift().setSlidePower(slideThrottle);

            updateTelemetry(this);

        }

        finalAction();
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

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
            if(gamepad2.left_bumper)
                getJewel().deploy();
            else
                getJewel().retract();

            // Hand
            if(gamepad1.a)
                getHand().closeBottom();
            else if(gamepad1.y)
                getHand().closeTop();
            else if(gamepad1.b)
                getHand().openClaw();

            updateTelemetry(this);
        }

        finalAction();
    }
}

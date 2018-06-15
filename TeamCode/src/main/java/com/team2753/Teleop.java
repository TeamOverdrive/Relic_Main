package com.team2753;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.team2753.libs.OverdriveLib;
import com.team2753.subsystems.Slammer;

import static com.team2753.auto.AutoParams.TELEOP;

/**
 * Created by joshua9889 on 12/10/2017.
 *
 * Replicated 2753's Teleop Main_Relic with new backend.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team2753Linear {
    double angle = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("Teleop", TELEOP);
        SetStatus("Teleop");

        waitForStart();

        // Loop while we are running Teleop
        while (opModeIsActive()) {

            /*Gamepad 1 Controls*/

            /* Drivetrain Controls */ //Gamepad 1 joysticks
            //D-pad controls for slower movement
            if (Math.abs(Ryan.right_stick_y) < 0.01 && Math.abs(Ryan.left_stick_y) < 0.01) {
                if (Ryan.dpad_up) {
                    Robot.getDrive().setLeftRightPower(-0.3, -0.3);
                } else if (Ryan.dpad_down) {
                    Robot.getDrive().setLeftRightPower(0.3, 0.3);
                } else if (Ryan.dpad_left) {
                    Robot.getDrive().setLeftRightPower(-0.35, 0.35);
                } else if (Ryan.dpad_right) {
                    Robot.getDrive().setLeftRightPower(0.35, -0.35);
                } else {
                    Robot.getDrive().setLeftRightPower(0, 0);
                }
            } else {
                float leftThrottle = Ryan.left_stick_y;
                float rightThrottle = Ryan.right_stick_y;

                /* Clip the left and right throttle values so that they never exceed +/- 1.  */
                leftThrottle = Range.clip(leftThrottle, -1, 1);
                rightThrottle = Range.clip(rightThrottle, -1, 1);

                /* Scale the throttle values to make it easier to control the robot more precisely at slower speeds.  */
                leftThrottle = (float) OverdriveLib.scaleInput(leftThrottle);
                rightThrottle = (float) OverdriveLib.scaleInput(rightThrottle);

                Robot.getDrive().setLeftRightPower(leftThrottle, rightThrottle);
            }

            /* Intake Controls */

            //Both Gamepad 1 and 2

            //Press and hold control
            if (Ryan.left_bumper)
                Robot.getIntake().reverse();
            else if (Ryan.right_bumper)
                Robot.getIntake().intake();
            else if (Ryan.left_trigger > 0)
                Robot.getIntake().shiftLeft();
            else if (Ryan.right_trigger > 0)
                Robot.getIntake().shiftRight();
            else
                Robot.getIntake().stop();


            if(Ryan.x)
                Robot.getJewel().deploy(true);
            else
                Robot.getJewel().retract(true);

            /*  Gamepad 2 Controls  */

            // Lift
            Robot.getLift().setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            float liftThrottle = Seth.left_stick_y;
            //CLip
            liftThrottle = Range.clip(liftThrottle, -1, 1);
            //Scale
            liftThrottle = (float) OverdriveLib.scaleInput(liftThrottle);
            //Invert
            liftThrottle = liftThrottle * -1;
            //Apply power to motor
            Robot.getLift().setLiftPower(liftThrottle);

            // Slammer
            boolean scoring = Seth.a;

            if(scoring){
                Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING);
            } else if(Robot.getLift().getPosition()>10){
                Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING);
            } else {
                Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING);
            }

            // Relic
            float relicThrottle = -Seth.right_stick_y;
            relicThrottle = Range.clip(relicThrottle, -1, 1);
            relicThrottle = (float)OverdriveLib.scaleInput(relicThrottle);
            Robot.getRelic().setPower(relicThrottle);


            if(Seth.dpad_up){
                angle -= 0.5;
            } else if (Seth.dpad_down){
                angle += 0.5;
            } else if(Seth.dpad_right){
                angle = 150;
            } else if(Seth.dpad_left){
                angle = 100;
            }
            Robot.getRelic().setWristAngle(angle*10);

            if (Seth.right_bumper){
                Robot.getRelic().close();
            } else if (Seth.left_bumper){
                Robot.getRelic().open();
            }


            SetStatus("Running OpMode");
            updateTelemetry();

        }

        finalAction();
    }
}

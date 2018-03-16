package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drive;

/**
 * Created by joshua9889 on 3/16/2018.
 */

@TeleOp
@Disabled
public class TestGyro extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Drive mDrive = new Drive();
        mDrive.init(this, true);

        telemetry.addData("wait","");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("Gyro Left", mDrive.getGyroAngleDegrees());
            telemetry.update();
        }


    }
}

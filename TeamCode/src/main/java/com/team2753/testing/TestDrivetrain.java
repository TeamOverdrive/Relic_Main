package com.team2753.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team2753.auto.AutoModeBase;

@TeleOp
public class TestDrivetrain extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("Test");

        telemetry.addData("PID Drive Test", "");
        telemetry.update();
        Robot.getDrive().encoderDrive(0.4, 10, 10, 4);
        sleep(500);

        telemetry.addData("Trajectory Drive Test", "");
        telemetry.update();
        Robot.getDrive().driveTrajectory(-10, -10, 3000);
        sleep(500);

        telemetry.addData("PID Turn Drive Test", "");
        telemetry.update();
        Robot.getDrive().turnCW(-90, 0.4, 3);
        sleep(500);

        telemetry.addData("Trajectory Turn Drive Test", "");
        telemetry.update();
        Robot.getDrive().turnTrajectory(-90, 4000);
        sleep(500);
    }
}

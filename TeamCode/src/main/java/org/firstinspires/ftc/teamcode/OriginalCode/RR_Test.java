package org.firstinspires.ftc.teamcode.OriginalCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by David Zheng | Team Overdrive 2753 on 10/23/2017.
 */

@TeleOp(name="RR_Test", group="Test")


@Disabled
public class RR_Test extends LinearOpMode {

    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    @Override
    public void runOpMode() {

        waitForStart();

        leftMotor = hardwareMap.get(DcMotor.class, "left_drive");
        rightMotor = hardwareMap.get(DcMotor.class, "right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        while(opModeIsActive()) {
            leftMotor.setPower(1.0);
            rightMotor.setPower(1.0);

        }
    }

}

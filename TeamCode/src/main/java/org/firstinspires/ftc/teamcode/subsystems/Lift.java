package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 12/10/2017.
 */

public class Lift implements Subsystem {
    private DcMotor slideMotor, liftMotor = null;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        slideMotor = linearOpMode.hardwareMap.dcMotor.get("slide_motor");
        liftMotor = linearOpMode.hardwareMap.dcMotor.get("lift_motor");

        slideMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void zeroSensors() {

    }


    @Override
    public void stop() {

    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {

    }

    public void setLiftPower(double power){
        liftMotor.setPower(power);
    }

    public void setSlidePower (double power){
        slideMotor.setPower(power);
    }
}

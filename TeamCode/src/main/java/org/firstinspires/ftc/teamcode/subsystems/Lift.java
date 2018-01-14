package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 12/10/2017.
 */

public class Lift implements Subsystem {
    private DcMotor liftMotor = null;
    private static final double brakepower = 0;


    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        liftMotor = linearOpMode.hardwareMap.dcMotor.get("lift_motor");
        liftMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void zeroSensors() {}

    @Override
    public void stop() {
        brakeLift();
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {}


    public void setLiftPower(double power){
        liftMotor.setPower(power);
    }

    public void brakeLift(){
        liftMotor.setPower(brakepower);
    }

}

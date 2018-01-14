package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.security.PrivateKey;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/13/2018.
 */

public class Relic implements Subsystem{

    private DcMotor relicMotor = null;
    private Servo relicServo = null;
    private Servo relicGrab1, relicGrab2 = null;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        relicMotor = linearOpMode.hardwareMap.dcMotor.get("relic_motor");

        relicServo = linearOpMode.hardwareMap.servo.get("relic_servo");
        relicGrab1 = linearOpMode.hardwareMap.servo.get("relic_grab1");
        relicGrab2 = linearOpMode.hardwareMap.servo.get("relic_grab2");

        stop();
    }

    @Override
    public void zeroSensors() {}

    @Override
    public void stop() {
        brakeRelic();
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {}


    public void brakeRelic(){
        relicMotor.setPower(0);
    }
}

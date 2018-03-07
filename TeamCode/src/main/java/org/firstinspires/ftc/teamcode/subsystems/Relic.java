package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 2/26/2018.
 */

public class Relic implements Subsystem{

    private LinearOpMode linearOpMode = null;
    private Servo turretServo = null;
    private Servo relicServo = null;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        this.linearOpMode = linearOpMode;
        turretServo = linearOpMode.hardwareMap.servo.get("relic_turret");
        relicServo = linearOpMode.hardwareMap.servo.get("relic_elbow");
        stop();
    }

    @Override
    public void zeroSensors() {
        turretServo.setPosition(0);
        relicServo.setPosition(1);
    }

    @Override
    public void stop() {

    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {

    }
}

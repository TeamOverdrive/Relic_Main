package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 2/26/2018.
 */

public class Relic implements Subsystem{

    private Servo turretServo = null;
    private Servo relicServo = null;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        turretServo = linearOpMode.hardwareMap.get(Servo.class, "relic_turret");
        relicServo = linearOpMode.hardwareMap.get(Servo.class, "relic_elbow");
        stop();
    }

    @Override
    public void zeroSensors() {
        turretAndElbow(0,1);
    }

    @Override
    public void stop() {

    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {

    }

    public void turretAndElbow(double turret, double elbow){
        turretServo.setPosition(turret);
        relicServo.setPosition(elbow);
    }

    public void setPosition(double x, double y){
        turretAndElbow((Math.toDegrees(Math.acos(x)))/160, Math.toDegrees((Math.acos(x)))/185);
    }

}

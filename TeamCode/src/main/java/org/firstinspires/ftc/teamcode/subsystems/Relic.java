package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.libs.OverdriveLib.SolveInverseKinematics;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 2/26/2018.
 */

public class Relic implements Subsystem{

    private Servo turretServo = null;
    private Servo elbowServo = null;
    private Servo wristServo = null;
    private Servo fingerServo = null;

    private double l1 = 17.5; // Inches of first section
    private double l2 = 15; // Inches of second section

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        turretServo = linearOpMode.hardwareMap.get(Servo.class,"relic_turret");
        elbowServo = linearOpMode.hardwareMap.get(Servo.class, "relic_elbow");
        wristServo = linearOpMode.hardwareMap.get(Servo.class, "yellow");
        fingerServo = linearOpMode.hardwareMap.get(Servo.class, "blue");

        turretServo.setDirection(Servo.Direction.REVERSE);
        elbowServo.setDirection(Servo.Direction.REVERSE);
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
        telemetry.addData("Turret Position", turretServo.getPosition());
        telemetry.addData("Elbow Position", elbowServo.getPosition());
        telemetry.addData("Wrist Position", wristServo.getPosition());
    }

    public void turretAndElbow(double turret, double elbow){
        try {
            if(turret<0)
                turretServo.setPosition(0);
            else if(turret>1)
                turretServo.setPosition(1);
            else
                turretServo.setPosition(turret);

            if(elbow<0)
                elbowServo.setPosition(0);
            else if(elbow>1)
                elbowServo.setPosition(1);
            else
                elbowServo.setPosition(elbow);
        } catch (Exception e){
            RobotLog.a(e.toString());
        }

    }

    public void setAngles(double turret, double elbow){
        turretAndElbow(turret/140, elbow/190);
    }

    public void setPosition(double x, double y){

        double[] thedas = SolveInverseKinematics(l1, l2, x, y);

        if(thedas[0] < 360 && thedas[0] >-360) {
            double newTheda0 = (90-thedas[0]);
            double newTheda1 = (90-thedas[1]);

            if(newTheda0>=0 && newTheda0<=180 && newTheda1>=0 && newTheda1<=190) {
                setAngles(thedas[1], thedas[0]);
            }
        }
    }

    public void open(){
        fingerServo.setPosition(1);
    }

    public void close(){
        fingerServo.setPosition(0);
    }

    public void lock(){
        fingerServo.setPosition(0.35);
    }

    double oneRotation = ((16.0/24.0)/8.0);
    double oneDegree = (oneRotation/360);

    public void setWristAngle(double angle){
        angle -= 360;
        wristServo.setPosition(angle*oneDegree);
    }

    public void setWristPostion(double pos){
        wristServo.setPosition(pos/255.0);
    }

    @Override
    public String toString() {
        return "Relic";
    }
}

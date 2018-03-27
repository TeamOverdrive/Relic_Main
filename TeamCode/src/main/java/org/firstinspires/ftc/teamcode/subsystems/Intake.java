package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.FLOAT;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/25/2018.
 */

public class Intake implements Subsystem{

    private DcMotor leftIntake, rightIntake = null;
    private Servo intakeRelease = null;


    private ModernRoboticsI2cRangeSensor intakeDistanceLeft, intakeDistanceRight = null;
//
//    private I2cAddr leftAddr = new I2cAddr(0x28);
//    private I2cAddr rightAddr = new I2cAddr(0x30);

    private DeviceInterfaceModule cdi = null;

    private static final double intakePower = 1.0;


    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        leftIntake = linearOpMode.hardwareMap.dcMotor.get("intake_left");
        rightIntake = linearOpMode.hardwareMap.dcMotor.get("intake_right");
        intakeRelease = linearOpMode.hardwareMap.servo.get("intake_servo");

        leftIntake.setDirection(FORWARD);
        rightIntake.setDirection(REVERSE);
        leftIntake.setZeroPowerBehavior(BRAKE);
        rightIntake.setZeroPowerBehavior(BRAKE);

        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        cdi = linearOpMode.hardwareMap.get(DeviceInterfaceModule.class, "Device Interface Module");

        intakeDistanceLeft = linearOpMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "left_range");
        intakeDistanceLeft.setI2cAddress(I2cAddr.create8bit(0x28));

        intakeDistanceRight = linearOpMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "right_range");
        intakeDistanceRight.setI2cAddress(I2cAddr.create8bit(0x30));

        releaseLock();

        if(!auto)
            releaseIntake();
    }

    public double getLeftReading(){
        return intakeDistanceLeft.cmUltrasonic();
    }

    public double getRightReading(){
        return intakeDistanceRight.cmUltrasonic();
    }

    public double avgReading(){
        return (getLeftReading()+getRightReading())/2;
    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void stop() {
        leftIntake.setZeroPowerBehavior(FLOAT);
        rightIntake.setZeroPowerBehavior(FLOAT);
        setPower(0);
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Left Intake Power", leftIntake.getPower());
        telemetry.addData("Right Intake Power", rightIntake.getPower());
        telemetry.addData("Left Ping", getLeftReading());
        telemetry.addData("Right Ping", getRightReading());
        telemetry.addData("Front Ods", this.frontDetected());
        telemetry.addData("Back Ods", this.backDetected());
    }

    public void setPower(double power){
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }

    public void setLeftRightPower(double leftPower, double rightPower){
        leftIntake.setPower(leftPower);
        rightIntake.setPower(rightPower);
    }

    public void setRunMode(DcMotor.RunMode runMode){
        leftIntake.setMode(runMode);
        rightIntake.setMode(runMode);
    }

    public void intake(){setPower(intakePower);}

    public void reverse(){setPower(-intakePower);}

    public void shiftLeft(){
        leftIntake.setPower(intakePower);
        rightIntake.setPower(0.8);
    }

    public void shiftRight(){
        rightIntake.setPower(intakePower);
        leftIntake.setPower(0.8);
    }

    public void releaseLock(){intakeRelease.setPosition(0.16);}

    public void releaseIntake(){intakeRelease.setPosition(0.35);}

    public boolean backDetected(){
        return cdi.getAnalogInputVoltage(1) > 0.02;
    }

    public boolean frontDetected(){
        return cdi.getAnalogInputVoltage(4) > 0.02;
    }

    @Override
    public String toString() {
        return "Intake";
    }
}

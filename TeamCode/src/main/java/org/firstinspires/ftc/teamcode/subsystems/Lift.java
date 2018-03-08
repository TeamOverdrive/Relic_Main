package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.INTAKING;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.LOWER;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.UPPER;

/**
 * Created by joshua9889 on 12/10/2017.
 */

public class Lift implements Subsystem {

    private DcMotor liftMotor = null;
    private static final double brakepower = 0;

    private DigitalChannel limitSwitch;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        liftMotor = linearOpMode.hardwareMap.get(DcMotor.class, "lift_motor");
        limitSwitch = linearOpMode.hardwareMap.get(DigitalChannel.class, "limit");

        liftMotor.setDirection(REVERSE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        zeroSensors();
    }

    @Override
    public void zeroSensors() {
        stop();
        while(liftMotor.getCurrentPosition() != 0) {
            setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void stop() {
        brakeLift();
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Intake Power", liftMotor.getPower());
        telemetry.addData("Current Lift Position", liftMotor.getCurrentPosition());
        telemetry.addData("Current Lift State", currentState);
        telemetry.addData("Lift Limit Switch Voltage", limitSwitch.getState());
    }

    public void setRunMode(DcMotor.RunMode runMode){
        liftMotor.setMode(runMode);
    }

    public enum liftState{
        ZEROING,
        INTAKING,
        LOWER,
        UPPER
    }

    private liftState currentState = INTAKING;

    public void goTo(liftState wanted){
        if(wanted != currentState){
            setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
            switch(wanted){
                case INTAKING:
                    setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    liftMotor.setTargetPosition(0);
                    if(isLiftInPosition())
                        currentState=INTAKING;
                    break;

                case LOWER:
                    setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    liftMotor.setTargetPosition(0);
                    if(isLiftInPosition())
                        currentState=LOWER;
                    break;

                case UPPER:
                    setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    liftMotor.setTargetPosition(1500);
                    if(isLiftInPosition())
                        currentState=UPPER;
                    break;

                case ZEROING:
                    setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    liftMotor.setPower(-0.1);
                    if(isLimitPressed()) {
                        liftMotor.setPower(0);
                        zeroSensors();
                        currentState = wanted;
                    }
                    break;
            }
            liftMotor.setPower(0.8);
        }
        else {
            setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
    }

    private boolean isLimitPressed(){
        return limitSwitch.getState();
    }

    public void setLiftPower(double power){
        liftMotor.setPower(power);
    }

    public void brakeLift(){
        liftMotor.setPower(brakepower);
    }

    public boolean isLiftInPosition(){
        return Math.abs(liftMotor.getTargetPosition()-liftMotor.getCurrentPosition())<20;
    }

}

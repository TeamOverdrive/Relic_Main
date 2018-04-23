package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
        RobotLog.v("============= Lift Init Started =============");
        liftMotor = linearOpMode.hardwareMap.get(DcMotor.class, "lift_motor");

        liftMotor.setDirection(REVERSE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RobotLog.v("============= Lift Init Finished =============");
    }

    @Override
    public void zeroSensors() {
        stop();
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
        //telemetry.addData("Lift Limit Switch Voltage", limitSwitch.getState());
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
        if(liftMotor.getMode()!= DcMotor.RunMode.RUN_WITHOUT_ENCODER)
            setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setPower(power);
    }

    public void brakeLift(){
        liftMotor.setPower(brakepower);
    }

    public boolean isLiftInPosition(){
        return Math.abs(liftMotor.getTargetPosition()-liftMotor.getCurrentPosition())<20;
    }

    public boolean shouldLiftStop(){
        return liftMotor.getCurrentPosition()>400;
    }

    @Override
    public String toString() {
        return "Lift";
    }
}

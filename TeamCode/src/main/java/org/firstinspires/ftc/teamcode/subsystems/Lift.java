package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.INTAKING;

/**
 * Created by joshua9889 on 12/10/2017.
 */

public class Lift implements Subsystem {

    private LinearOpMode linearOpMode = null;
    private DcMotor liftMotor = null;
    private static final double brakepower = 0;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        this.linearOpMode = linearOpMode;
        liftMotor = linearOpMode.hardwareMap.dcMotor.get("lift_motor");
        liftMotor.setDirection(REVERSE);
        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        stop();
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

                    break;
                case LOWER:
                    setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    liftMotor.setTargetPosition(0);
                    break;
                case UPPER:
                    setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    liftMotor.setTargetPosition(1500);
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
            liftMotor.setPower(0.5);
            //setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        else {
            setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            stop();
        }
    }

    private boolean isLimitPressed(){
        return true;
    }

    public void setLiftPower(double power){
        liftMotor.setPower(power);
    }

    public void brakeLift(){
        liftMotor.setPower(brakepower);
    }

}

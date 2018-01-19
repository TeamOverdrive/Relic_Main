package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Math.PI;
import static java.lang.Thread.sleep;

/**
 * Created by joshua9889 on 12/10/2017.
 * Used for the Drivetrain
 */

public class Drive implements Subsystem {
    /* Motors */
    private DcMotor leftMotor, rightMotor = null;

    // Used to output telemetry and to stop when stop is pressed
    private LinearOpMode linearOpMode = null;

    protected ElapsedTime runtime = new ElapsedTime();// FORWARD_SPEED was running the robot in reverse to the TeleOp program setup.  Speed is reversed to standardize the robot orientation.

    private static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    private static final double DRIVE_GEAR_REDUCTION = 0.75;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double WHEEL_BASE = 12.25;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        this.linearOpMode = linearOpMode;
        leftMotor = linearOpMode.hardwareMap.dcMotor.get("left_drive");
        rightMotor  = linearOpMode.hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        if(auto){
            // Reset Encoders to move goodly.
            zeroSensors();

            // Set all motors to run with or without encoders.  Switch to use RUN_USING_ENCODERS when encoders are installed.
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        } else {
            // We are running teleop, we don't need them
            setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    @Override
    public void zeroSensors() {
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Thread.yield();
    }

    @Override
    public void stop() {
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        setLeftRightPowers(0,0);
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Left Power", leftMotor.getPower());
        telemetry.addData("Right Power", rightMotor.getPower());
        telemetry.addData("Left Pos", leftMotor.getCurrentPosition());
        telemetry.addData("Right Pos", rightMotor.getCurrentPosition());
    }

    /**
     * Basic setPower method for both sides of the drive train.
     *
     * @param left  left power
     * @param right right power
     */

    public void setLeftRightPowers(double left, double right){
        left = Range.clip(left, -1., 1.);
        right = Range.clip(right, -1., 1.);

        leftMotor.setPower(left);
        rightMotor.setPower(right);
    }

    public void setRunMode(DcMotor.RunMode runMode){
        leftMotor.setMode(runMode);
        rightMotor.setMode(runMode);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        leftMotor.setZeroPowerBehavior(zeroPowerBehavior);
        rightMotor.setZeroPowerBehavior(zeroPowerBehavior);
    }

    /**
     * Method to perform a Clockwise turn
     *
     * @param degrees       The degrees (in a circle to turn.
     * @param timeoutS      The amount of time this method is allowed to execute.
     */
    public void turnCW(double degrees, double speed, double timeoutS){

        double leftDistance = (WHEEL_BASE*PI*degrees)/-360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/360;

        encoderDrive(speed, leftDistance, rightDistance, timeoutS);

    }

    /**
     * Method to perform a Counter-clockwise turn
     *
     * @param degrees       The degrees (in a circle) to turn.
     * @param timeoutS      The amount of time this method is allowed to execute.
     */

    public void turnCCW(double degrees, double speed, double timeoutS){

        double leftDistance = (WHEEL_BASE*PI*degrees)/360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/-360;

        encoderDrive(speed, leftDistance, rightDistance, timeoutS);

    }

    /**
     * Method to perform a relative move, based on encoder counts.
     * Encoders are not reset as the move is based on the current position.
     * Move will stop if any of three conditions occur:
     * 1) Move gets to the desired position
     * 2) Move runs out of time
     * 3) Driver stops the opmode running.
     *
     * @param speed       The speed that the motors are moving.
     * @param leftInches  The distance that the robot should move to the left.
     * @param rightInches The distance that the robot should move to the right.
     * @param timeoutS    The amount of time this method is allowed to execute.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);
            int counter1 = 0;
            int counter2 = 0;

            // Turn On RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            setLeftRightPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {
                counter1++;
                if (Math.abs(newLeftTarget - leftMotor.getCurrentPosition()) < (6.0 * COUNTS_PER_INCH)) {
                    setLeftRightPowers(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                    counter2++;
                }
                // Display it for the driver.
                linearOpMode.telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                this.outputToTelemetry(linearOpMode.telemetry);
                linearOpMode.telemetry.addData("Distance", Math.abs(newLeftTarget - leftMotor.getCurrentPosition()));
                linearOpMode.telemetry.addData("While counter", counter1);
                linearOpMode.telemetry.addData("If counter", counter2);
                linearOpMode.telemetry.update();
            }

            // Stop all motion;
            setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  linearOpMode.sleep(250);   // optional pause after each move
        }
    }


}
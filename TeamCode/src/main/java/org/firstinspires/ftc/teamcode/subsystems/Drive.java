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
import static org.firstinspires.ftc.teamcode.auto.AutoParams.Kd;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.Ki;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.Kp;

/**
 * Created by joshua9889 on 12/10/2017.
 * Used for the Drivetrain
 */

public class Drive implements Subsystem {
    /* Motors */
    private DcMotor leftMotor, rightMotor = null;

    // Used to output telemetry and to stop when stop is pressed
    private LinearOpMode linearOpMode = null;

    protected ElapsedTime runtime = new ElapsedTime();
    // FORWARD_SPEED was running the robot in reverse to the TeleOp program setup.  Speed is reversed to standardize the robot orientation.

    private static final double COUNTS_PER_MOTOR_REV = 1120;     // AndyMark NeveRest 40
    private static final double DRIVE_GEAR_REDUCTION = 0.6666 ;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference w/ wheel base
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.141592);
    private static final double WHEEL_BASE = 12.625;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        this.linearOpMode = linearOpMode;
        leftMotor = linearOpMode.hardwareMap.dcMotor.get("left_drive");
        rightMotor  = linearOpMode.hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        if(auto){
            zeroSensors();
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
        //telemetry.addData("Left Pos", leftMotor.getCurrentPosition());
        //telemetry.addData("Right Pos", rightMotor.getCurrentPosition());

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

    public void setLeftRightTarget(int leftTarget, int rightTarget){
        leftMotor.setTargetPosition(leftTarget);
        rightMotor.setTargetPosition(rightTarget);
    }

    public void setRunMode(DcMotor.RunMode runMode){
        leftMotor.setMode(runMode);
        rightMotor.setMode(runMode);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        leftMotor.setZeroPowerBehavior(zeroPowerBehavior);
        rightMotor.setZeroPowerBehavior(zeroPowerBehavior);
    }

    public int getLeftCurrentPosition(){return leftMotor.getCurrentPosition();}

    public int getRightCurrentPosition(){return rightMotor.getCurrentPosition();}

    public boolean leftIsBusy(){return leftMotor.isBusy();}

    public boolean rightIsBusy(){return rightMotor.isBusy();}

    /**
     * Method to perform a Clockwise turn
     *
     * @param degrees       The degrees (in a circle) to turn.
     * @param speed         The speed that the motors are moving.
     * @param timeoutS      The amount of time this method is allowed to execute.
     */
    public void turnCW(double degrees, double speed, double timeoutS){

        double leftDistance = (WHEEL_BASE*PI*degrees)/-360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/360;

        encoderDrive(speed, leftDistance, rightDistance, timeoutS);

    }

    public void turnProportionCW(double degrees, double timeoutS){

        double leftDistance = (WHEEL_BASE*PI*degrees)/-360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/360;

        encoderProportionDrive(0.0114286, 0, 0.0755714, leftDistance, rightDistance, timeoutS);
    }

    public void turnDirectCW(double degrees, double speed, double timeoutS){
        double leftDistance = (WHEEL_BASE*PI*degrees)/-360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/360;

        encoderDirectDrive(speed, leftDistance, rightDistance, timeoutS);
    }

    public void turnPIDCW(LinearOpMode linearOpMode, double degrees, double timeoutS, double P, double I, double D, double bias){

        double leftDistance = (WHEEL_BASE*PI*degrees)/-360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/360;

        encoderPIDDrive(linearOpMode, leftDistance, rightDistance, 10, P, I, D, bias);
    }


    /**
     * Method to perform a Counter-clockwise turn
     *
     * @param degrees       The degrees (in a circle) to turn.
     * @param speed         The speed that the motors are moving.
     * @param timeoutS      The amount of time this method is allowed to execute.
     */

    public void turnCCW(double degrees, double speed, double timeoutS){

        double leftDistance = (WHEEL_BASE*PI*degrees)/360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/-360;

        encoderDrive(speed, leftDistance, rightDistance, timeoutS);

    }

    public void turnProportionCCW(double degrees, double timeoutS){

        double leftDistance = (WHEEL_BASE*PI*degrees)/360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/-360;

        encoderProportionDrive(0.0114286, 0, 0.0755714, leftDistance, rightDistance, timeoutS);
    }

    public void turnDirectCCW(double degrees, double speed, double timeoutS){
        double leftDistance = (WHEEL_BASE*PI*degrees)/360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/-360;

        encoderDirectDrive(speed, leftDistance, rightDistance, timeoutS);
    }

    public void turnPIDCCW(LinearOpMode linearOpMode, double degrees, double timeoutS, double P, double I, double D, double bias){

        double leftDistance = (WHEEL_BASE*PI*degrees)/360;
        double rightDistance = (WHEEL_BASE*PI*degrees)/-360;

        encoderPIDDrive(linearOpMode, leftDistance, rightDistance, 10, P, I, D, bias);
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

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);
            //int counter1 = 0;
            //int counter2 = 0;

            // Turn On RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            setLeftRightPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() || rightMotor.isBusy())) {
                //slow the motors down to half the original speed when we get within 4 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(newLeftTarget - leftMotor.getCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && (Math.abs(newRightTarget - rightMotor.getCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    setLeftRightPowers(Math.abs(speed * 0.75), Math.abs(speed * 0.75));
                }
                //slow the motors down to 0.35 of the original speed when we get within 2 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(newLeftTarget - leftMotor.getCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && (Math.abs(newRightTarget - rightMotor.getCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    setLeftRightPowers(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                }
            }
            // Stop all motion;
            setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  linearOpMode.sleep(250);   // optional pause after each move
        }
    }

    public void encoderTargetDrive(double speed, double leftTarget, double rightTarget, double timeoutS) {
        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            leftMotor.setTargetPosition((int) leftTarget);
            rightMotor.setTargetPosition((int) rightTarget);
            //int counter1 = 0;
            //int counter2 = 0;

            // Turn On RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            setLeftRightPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() || rightMotor.isBusy())) {
                //slow the motors down to half the original speed when we get within 4 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(leftTarget - leftMotor.getCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && (Math.abs(rightTarget - rightMotor.getCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    setLeftRightPowers(Math.abs(speed * 0.75), Math.abs(speed * 0.75));
                }
                //slow the motors down to 0.35 of the original speed when we get within 2 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(leftTarget - leftMotor.getCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && (Math.abs(rightTarget - rightMotor.getCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    setLeftRightPowers(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                }
            }
            // Stop all motion;
            setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  linearOpMode.sleep(250);   // optional pause after each move
        }
    }

    public void encoderProportionDrive(double squareProportion, double proportion, double bias, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);
            //int counter1 = 0;
            //int counter2 = 0;

            // Turn On RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            //Change these to parameters when we want to tune
            /*
            final double squareProportion = 0.0375;
            final double proportion = 0.0;
            final double bias = 0.15; //constant in the polynomial
            */

            double leftDistanceLeft = Math.abs(newLeftTarget - leftMotor.getCurrentPosition());
            double rightDistanceLeft = Math.abs(newRightTarget - rightMotor.getCurrentPosition());

            double newLeftSpeed = Math.abs(Math.pow(squareProportion*leftDistanceLeft,2) + proportion*leftDistanceLeft + bias);
            double newRightSpeed = Math.abs(Math.pow(squareProportion*rightDistanceLeft,2) + proportion*rightDistanceLeft + bias);

            newLeftSpeed = Range.clip(newLeftSpeed, -1, 1);
            newRightSpeed = Range.clip(newRightSpeed, -1, 1);

            // reset the timeout time and start motion.
            runtime.reset();
            setLeftRightPowers(Math.abs(newLeftSpeed), Math.abs(newRightSpeed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() || rightMotor.isBusy())) {

                leftDistanceLeft = Math.abs(newLeftTarget - leftMotor.getCurrentPosition());
                rightDistanceLeft = Math.abs(newRightTarget - rightMotor.getCurrentPosition());
                newLeftSpeed = Math.abs(Math.pow(squareProportion*leftDistanceLeft,2) + proportion*leftDistanceLeft + bias);
                newRightSpeed = Math.abs(Math.pow(squareProportion*rightDistanceLeft,2) + proportion*rightDistanceLeft + bias);

                newLeftSpeed = Range.clip(newLeftSpeed, -1, 1);
                newRightSpeed = Range.clip(newRightSpeed, -1, 1);

                setLeftRightPowers(newLeftSpeed, newRightSpeed);
            }
            // Stop all motion;
            setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void encoderPDDrive(LinearOpMode linearOpMode, double Kp2, double Kp, double Kd, double bias, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);
            //int counter1 = 0;
            //int counter2 = 0;

            // Turn On RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            //Change these to parameters when we want to tune
            /*
            final double squareProportion = 0.0375;
            final double proportion = 0.0;
            final double bias = 0.15; //constant in the polynomial
            */

            double leftDistanceLeft = Math.abs(newLeftTarget - leftMotor.getCurrentPosition());
            double rightDistanceLeft = Math.abs(newRightTarget - rightMotor.getCurrentPosition());

            double leftDerivative = 0;
            double rightDerivative = 0;
            double leftErrorPrior = 0;
            double rightErrorPrior = 0;

            double iteration_time;
            double startIteration = linearOpMode.getRuntime();

            double newLeftSpeed = Math.abs(Math.pow(Kp2*leftDistanceLeft,2) + (Kp*leftDistanceLeft) + (Kd*leftDerivative) + bias);
            double newRightSpeed = Math.abs(Math.pow(Kp2*rightDistanceLeft,2) + (Kp*rightDistanceLeft) + (Kd*rightDerivative) + bias);

            // reset the timeout time and start motion.
            runtime.reset();
            setLeftRightPowers(Math.abs(newLeftSpeed), Math.abs(newRightSpeed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() || rightMotor.isBusy())) {

                iteration_time = Math.abs(linearOpMode.getRuntime() - startIteration);
                startIteration = linearOpMode.getRuntime();

                leftDistanceLeft = Math.abs(newLeftTarget - leftMotor.getCurrentPosition());
                rightDistanceLeft = Math.abs(newRightTarget - rightMotor.getCurrentPosition());

                leftDerivative = (leftDistanceLeft - leftErrorPrior)/iteration_time;
                rightDerivative = (rightDistanceLeft - rightErrorPrior)/iteration_time;

                newLeftSpeed = Math.abs(Math.pow(Kp2*leftDistanceLeft,2) + (Kp*leftDistanceLeft) + (Kd*leftDerivative) + bias);
                newRightSpeed = Math.abs(Math.pow(Kp2*rightDistanceLeft,2) + (Kp*rightDistanceLeft) + (Kd*rightDerivative) + bias);

                setLeftRightPowers(newLeftSpeed, newRightSpeed);


                leftErrorPrior = leftDistanceLeft;
                rightErrorPrior = rightDistanceLeft;
            }
            // Stop all motion;
            setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    //same as encoderDrive except without the slowdown before reaching target
    public void encoderDirectDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            setLeftRightPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() || rightMotor.isBusy())) {}

            // Stop all motion;
            setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  linearOpMode.sleep(250);   // optional pause after each move
        }
    }

    public void encoderPIDDrive(LinearOpMode linearOpMode, double leftInches, double rightInches, double timeoutS, double P, double I, double D, double bias){
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            //setLeftRightTarget(newLeftTarget, newRightTarget);

            setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            double left_error = Math.abs(newLeftTarget - leftMotor.getCurrentPosition());
            double right_error = Math.abs(newRightTarget - rightMotor.getCurrentPosition());
            double left_error_prior = 0;
            double right_error_prior = 0;
            double iterationStartS = linearOpMode.getRuntime();
            double iterationtimeS = 0;


            double integralL = 0;
            integralL = integralL + (left_error*iterationtimeS);
            double derivativeL = (left_error - left_error_prior)/iterationtimeS;
            double outputL = P*left_error + I*integralL + D*derivativeL + bias;

            double integralR = 0;
            integralR = integralR + (right_error*iterationtimeS);
            double derivativeR = (right_error - right_error_prior)/iterationtimeS;
            double outputR = P*right_error + I*integralR + D*derivativeR + bias;

            runtime.reset();

            outputL = Range.clip(outputL, -1, 1);
            outputR = Range.clip(outputR, -1,1);
            setLeftRightPowers(Math.abs(outputL), Math.abs(outputR));
            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() || rightMotor.isBusy())) {

                iterationtimeS = Math.abs(iterationStartS-linearOpMode.getRuntime());
                iterationStartS = linearOpMode.getRuntime();

                left_error = Math.abs(newLeftTarget - leftMotor.getCurrentPosition());
                integralL = integralL + (left_error*iterationtimeS);
                derivativeL = (left_error - left_error_prior)/iterationtimeS;
                outputL = P*left_error + I*integralL + D*derivativeL;
                left_error_prior = left_error;

                right_error = Math.abs(newRightTarget - rightMotor.getCurrentPosition());
                integralR = integralR + (right_error*iterationtimeS);
                derivativeR = (right_error - right_error_prior)/iterationtimeS;
                outputR = P*right_error + I*integralR + D*derivativeR;
                right_error_prior = right_error;

                outputL = Range.clip(outputL, -1, 1);
                outputR = Range.clip(outputR, -1,1);
                setLeftRightPowers(Math.abs(outputL), Math.abs(outputR));
            }
            // Stop all motion;
            setLeftRightPowers(0,0);

            // Turn off RUN_WITHOUT_ENCODER
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public double startEncoderPIDControl(DcMotor motor, double target, double P, double I, double D) {
        double error_prior = 0;
        double time_prior = 0;
        double integral = 0;
        double error = Math.abs(target - motor.getCurrentPosition());
        double iterationtime = 0;
        integral = integral + (error * iterationtime);
        double derivative = (error - error_prior) / iterationtime;
        double output = P*error + I*integral + D*derivative;
        error_prior = error;
        return output;
    }

    //only implements Kp right now.
    //if only Kp returns a desired result, i will leave it like that
    public void proportionControl(double leftTarget, double rightTarget, double speed, double P, double I, double D){
        //double leftError = Math.abs(leftTarget - leftMotor.getCurrentPosition());
        //double rightError = Math.abs(rightTarget - rightMotor.getCurrentPosition());

    }
}
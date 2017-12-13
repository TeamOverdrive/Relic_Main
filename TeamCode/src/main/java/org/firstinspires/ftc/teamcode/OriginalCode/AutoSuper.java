package org.firstinspires.ftc.teamcode.OriginalCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 11/4/2017.
 */

/*This program was based off of Vortex_Main-master's AutoSuper by S Turner*/

public class AutoSuper extends LinearOpMode{
    protected ElapsedTime runtime = new ElapsedTime();// FORWARD_SPEED was running the robot in reverse to the TeleOp program setup.  Speed is reversed to standardize the robot orientation.
    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 0.75;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;  //modified speed from 0.6
    static final double TURN_SPEED = 0.3;  //modified turn speed from 0.5
    final static double ARMUP = 0.75;
    final static double ARMDOWN = 0.725;
    final static int ARMWAIT = 3000;

    /*
    static final int UNKNOWN = 0;
    static final int LEFT = 1;
    static final int CENTER = 2;
    static final int RIGHT = 3;
    */

    static final boolean RED = true;
    static final boolean BLUE = false;

    public static final String TAG = "Vuforia VuMark Sample";

    /*Vuforia*/
    //OpenGLMatrix lastLocation = null;
    //VuforiaLocalizer vuforia;

    /*Motors*/
    protected DcMotor leftMotor;
    protected DcMotor rightMotor;
    protected DcMotor slideMotor;
    //protected DcMotor flipMotor;
    protected DcMotor liftMotor;

    /*Servos*/
    protected Servo jewelArm;
    protected Servo grab1;
    protected Servo grab2;
    protected Servo grabTop;
    protected Servo grabBottom;

    /*Sensors*/
    //ModernRoboticsI2cColorSensor color;
    protected ColorSensor jewelColor;


    @Override
    public void runOpMode()
    {

        //OpenGLMatrix lastLocation = null;
        //VuforiaLocalizer vuforia;

        Init init = new Init();
        init.initAuto(hardwareMap, telemetry, this);

        //Define Motors
        leftMotor = init.getLeftMotor();
        rightMotor = init.getRightMotor();
        slideMotor = init.getSlideMotor();
        //flipMotor = init.getFlipMotor();
        liftMotor = init.getLiftMotor();

        //Define Servos
        jewelArm = init.getJewelArm();
        grab1 = init.getGrab1();
        grab2 = init.getGrab2();
        grabBottom = init.getGrabBottom();
        grabTop = init.getGrabTop();

        //Define Sensors
        jewelColor = init.getJewelColor();



        /* Vuforia */

        /*int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        //constructor not to initialize the camera
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        //Vuforia License
        parameters.vuforiaLicenseKey = "AeUsQDb/////AAAAGXsDAQwNS0SWopXJpAHyRntcnTcoWD8TnsR6PWGX9OwmlIhNxQgn8RX/1cH2RXXTsuSkHh6OjfMoCuHt35rhumaUsLnk8MZZJ7P9PEu+uSsUbH1hHcnnB6GzJnX/FqlZJX5HWWfeQva5s4OHJEwSbPR2zxhkRxntAjeuIPGVSHeIseAikPB0NF0SqEiPZea+PWrxpryP/bxKqy7VA77krKFtgDi6amam+vWvBCqyIo6tXxbo0w8q/HCXo4v/4UYyoFLRx1l1d2Wya5an5SwFfU3eKxy0BYc3tnsaaDJww59RNJ6IK9D3PZM+oPDrmF9ukQrc/jw+u+6Zm4wQHieHt9urSwLR7dgz0V3aatDx1V7y";

        //Camera Direction
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        //Load targets
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);*/
    }

    //Place Auto Functions here

    /**
     * Method for Jewel pushing
     *
     * Pushes the jewel by moving forward when true and backward when false.
     *
     * @param red A boolean answer to whether or not the desired color is red.
     */

    public void pushJewel(boolean red) {

        if (opModeIsActive())
        {
            jewelArm.setPosition(ARMDOWN);
            sleep(ARMWAIT);
            if(!colorJewel(red)) {

                colorJewel(red);

                encoderDrive(0.5, -6, -6, 10);
            }
            else {

                encoderDrive(0.5, 6, 6, 10);
            }
            jewelArm.setPosition(ARMUP);
            sleep(ARMWAIT);
        }




    }

    /**
     * Method for reading the jewel color
     *
     * Detects the color of the jewel in front of jewelColor and returns @param red as a boolean.
     *
     * @param red A boolean answer to whether or not the desired color is red.
     */

    boolean colorJewel (boolean red){
        jewelColor.enableLed(true);
        telemetry.addData("color red", jewelColor.red());
        telemetry.addData("color blue", jewelColor.blue());
        telemetry.update();

        if (red) {

            if(jewelColor.red() >= 2) {
                return true;
            }
        }
        else {
            return false;
        }
        return false;

    }


    //Methods from Vortex

    /**
     * Methods for Beacon Pushing
     * <p>
     * Pushes the buttons on the beacon starting by moving forward, then reversing to
     * the second beacon.
     *
     * @param red A boolean answer to whether or not the desired color is red.
     */
    public void pushBeaconForward(boolean red) {
        if (!pushBeacon(red)) pushBeacon(red);
        encoderDrive(DRIVE_SPEED * 0.8, 26.0, 26.0, 4.0);
        encoderDrive(DRIVE_SPEED * 0.4, 4.0, 4.0, 3.0);
        if (!pushBeacon(red)) pushBeacon(red);
    }

    /**
     * Pushes the button on the beacon based on alliance color and the randomized side that should
     * be used.
     *
     * @param red A boolean representing whether or not the desired color is red.
     */
    public boolean pushBeacon (boolean red) {
        jewelColor.enableLed(true);
        telemetry.addData("color red", jewelColor.red());
        telemetry.addData("color blue", jewelColor.blue());
        telemetry.update();
        if (red) { //We are red so we want to knock the blue jewel off
            if (jewelColor.red() >= 2 && jewelColor.red() >= 2) {
                return true;
            }
            if (jewelColor.red() >= 2) {
                encoderDrive(DRIVE_SPEED * 0.4, -6.0, -6.0, 3.0);
                sleep(1200);
            } else if (jewelColor.red() >= 2) {
                encoderDrive(DRIVE_SPEED * 0.4, -6.0, -6.0, 3.0);
                sleep(1200);
            }
            sleep(500);
        } else {//knock red jewel off
            if (jewelColor.blue() >= 2 && jewelColor.blue() >= 2) {
                return true;
            }
            if (jewelColor.blue() >= 2) {
                encoderDrive(DRIVE_SPEED * 0.4, -6.0, -6.0, 3.0);
                sleep(1000);
                encoderDrive(DRIVE_SPEED * 0.4, -6.0, -6.0, 3.0);
            } else if (jewelColor.blue() >= 2) {
                encoderDrive(DRIVE_SPEED * 0.4, -6.0, -6.0, 3.0);
                sleep(1000);
                encoderDrive(DRIVE_SPEED * 0.4, -6.0, -6.0, 3.0);
            }
            sleep(500);
        }
        return false;
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
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);
            int counter1 = 0;
            int counter2 = 0;

            // Turn On RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftMotor.setPower(Math.abs(speed));
            rightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {
                counter1++;
                if (Math.abs(newLeftTarget - leftMotor.getCurrentPosition()) < (6.0 * COUNTS_PER_INCH)) {
                    leftMotor.setPower(Math.abs(speed * 0.3));
                    rightMotor.setPower(Math.abs(speed * 0.3));
                    counter2++;
                }
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        leftMotor.getCurrentPosition(),
                        rightMotor.getCurrentPosition());
                telemetry.addData("Distance", Math.abs(newLeftTarget - leftMotor.getCurrentPosition()));
                telemetry.addData("While counter", counter1);
                telemetry.addData("If counter", counter2);
                telemetry.update();
            }

            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void turnR90 () {
        sleep(250);
        encoderDrive(TURN_SPEED, -6, 6, 3.0);
        sleep(100);
    }

    public void turnL90 () {
        sleep(250);
        encoderDrive(TURN_SPEED, 6, -6, 3.0);
        sleep(100);

    }

    /*public int getColumn (){
        if(VuMark){
            return 1;
        }
        else if(){

        }
        else if(){

        }
        else{
            return 0;
        }
    }

    public void findColumn (int column){

        if (column == 1){

            telemetry.addData("Vumark", "Left visible");
            telemetry.update();

        }
        else if (column == 2){

            telemetry.addData("Vumark", "Center visible");
            telemetry.update();

        }
        else if (column == 3){

            telemetry.addData("Vumark", "Right visible");
            telemetry.update();

        }
        else{
            telemetry.addData("Vumark", "Not visible");
            telemetry.update();
        }

    }*/
}
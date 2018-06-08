package com.team2753;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import com.team2753.libs.subsystems.VuMark;
import com.team2753.subsystems.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static com.team2753.libs.subsystems.VuMark.SaveImage;
import static com.team2753.libs.subsystems.VuMark.blue;
import static com.team2753.libs.subsystems.VuMark.red;

/**
 * This class extends linearopmode and makes it
 * easier to make code for the robot and not copy
 * and pasting init code.
 *
 * See this for an example: http://bit.ly/2B8scLB
 * Created by joshua9889 on 12/10/2017.
 */

public abstract class Team2753Linear extends LinearOpMode {

    public Gamepad Ryan = gamepad1;
    public Gamepad Seth = gamepad2;

    // Robot class for all subsystems
    public Robot Robot = com.team2753.subsystems.Robot.getInstance();

    public static final String vuforiaKey = "AeUsQDb/////AAAAGXsDAQwNS0SWopXJpAHyRntcnTcoWD8Tns" +
            "R6PWGX9OwmlIhNxQgn8RX/1cH2RXXTsuSkHh6OjfMoCuHt35rhumaUsLnk8MZZJ7P9PEu+uSsUbH1hHcnnB" +
            "6GzJnX/FqlZJX5HWWfeQva5s4OHJEwSbPR2zxhkRxntAjeuIPGVSHeIseAikPB0NF0SqEiPZea+PWrxpryP" +
            "/bxKqy7VA77krKFtgDi6amam+vWvBCqyIo6tXxbo0w8q/HCXo4v/4UYyoFLRx1l1d2Wya5an5SwFfU3eKxy" +
            "0BYc3tnsaaDJww59RNJ6IK9D3PZM+oPDrmF9ukQrc/jw+u+6Zm4wQHieHt9urSwLR7dgz0V3aatDx1V7y";

    private static VuMark vumark = new VuMark(vuforiaKey);
    protected RelicRecoveryVuMark savedVumark = RelicRecoveryVuMark.UNKNOWN;

    private Bitmap bm = null;
    private int redVotes = 0;
    private int blueVotes = 0;

    // Jewel
    public enum Jewel_Color {
        Red, Blue
    }

    protected Jewel_Color jewel_Color = null;

    protected static ElapsedTime runtime = new ElapsedTime();
    private boolean isAuton = false; // Are we running auto

    private Telemetry.Item status;

    //Init Methods

    public void waitForStart(String OpModeName, boolean auton) {

//        CameraBlinker cameraBlinker = new CameraBlinker();
//        cameraBlinker.on();
        Ryan = gamepad1;
        Seth = gamepad2;

        telemetry.setAutoClear(true);
        status = telemetry.addData("Status", "Initializing...");
        Telemetry.Item currentOpMode = telemetry.addData("Running", OpModeName);
        telemetry.update();

        Robot.init(this, auton);

        if (auton) {
//            cameraBlinker.off();
            RobotLog.v("================ Start VuCam =============");
            vumark.setup(VuforiaLocalizer.CameraDirection.BACK, true);

            RobotLog.v("================ AutoTransitioner =============");
            //AutoTransitioner.transitionOnStop(this, "Teleop"); //Auto Transitioning

            RobotLog.v("================ VuCam Loop =============");
            while (!isStarted() && !isStopRequested()) {
                vumark.update();

                telemetry.clearAll();
                telemetry.addData("VuMark", vumark.getOuputVuMark());
                telemetry.update();

                savedVumark = vumark.getOuputVuMark();
                try {
                    bm = vumark.getBm(20);
                } catch (Exception e) {
                    bm = null;
                }

            }

            while (bm == null) {
                try {
                    bm = vumark.getBm(20);
                } catch (Exception e) {
                    bm = null;
                }
            }

            RobotLog.v("================ VuCam Loop Finished =============");

            RobotLog.v("================ Scan Bitmap =============");
            int redValue = 0;
            int blueValue = 0;

            // Scan area for red and blue pixels
            for (int x = (bm.getWidth() / 2) + (bm.getWidth() / 6);
                 x < ((bm.getWidth() / 2) + (18 * bm.getWidth() / 64)); x++) {
                for (int y = (2 * bm.getHeight() / 5) + (bm.getHeight() / 2); y < bm.getHeight(); y++) {
                    int pixel = bm.getPixel(x, y);
                    redValue = red(pixel);
                    blueValue = blue(pixel);

                    if (redValue > blueValue) {
                        redVotes++;
                        bm.setPixel(x, y, Color.RED);
                    } else if (blueValue > redValue) {
                        blueVotes++;
                        bm.setPixel(x, y, Color.BLUE);
                    }
                }
            }

            SaveImage(bm);

            if (redVotes > blueVotes)
                jewel_Color = Jewel_Color.Red;
            else if (redVotes < blueVotes)
                jewel_Color = Jewel_Color.Blue;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleep(1000);
                    vumark.disableVuforia();
                    Thread.yield();
                }
            }).start();

        } else {
            SetStatus("Initialized, Waiting for Start");
            waitForStart();
//            cameraBlinker.off();
        }

        runtime.reset();
        SetStatus("Running OpMode");
        RobotLog.v("================ Running OpMode =============");
    }

    public void SetStatus(String update) {
        status.setValue(update);
        telemetry.update();
    }


    public void resetRuntime() {
        runtime.reset();
    }

    //Telemetry
    public void updateTelemetry() {
        telemetry.addData("Gyro Position", Robot.getDrive().getGyroAngleDegrees());
//        telemetry.addData("Follower Wheel Position", Robot.getSlammer().followerWheel());

        if (isAuton) {
            telemetry.addData("Match Time", 30 - getRuntime());
        }
        if (!isAuton) {
            telemetry.addData("Match Time", 120 - runtime.seconds());
            if (runtime.seconds() > 90) {
                telemetry.addData("Phase", "End game");
            }
            if (runtime.seconds() > 120) {
                telemetry.addData("Phase", "Overtime");
            }
        }

        Robot.outputToTelemetry(telemetry);

        telemetry.update();
    }


    //Other

    public void finalAction() {
        Robot.stop();
        requestOpModeStop();
    }

    public void waitForTick(long periodMs) {
        long remaining = periodMs - (long) runtime.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        runtime.reset();
    }
}
package com.team2753.auto.SPLINE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team2753.auto.AutoModeBase;
import com.team2753.splines.FollowPath;
import com.team2753.splines.field.JoshuaField;
import com.team2753.subsystems.Slammer;

/**
 * Created by joshua9889 on 5/29/2018.
 */

@Autonomous
public class BLUE_CLOSE extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        PathStorage.calculateBlueClose(new JoshuaField());

        Robot.init(this, true);

        telemetry.addData("Here", "");
        telemetry.update();
        waitForStart();
        this.SuperHitJewel(Jewel_Color.Red, Jewel_Color.Blue);
        Robot.getIntake().releaseIntake();

        Thread intake = new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(200);
                while(!Thread.interrupted()){
                    Robot.getIntake().setLeftRightPower(1.0, 1.0);
                    sleep(1500);
                    Robot.getIntake().setLeftRightPower(-1.0, 1.0);
                    sleep(200);
                    Thread.yield();
                }
                sleep(400);
                Robot.getIntake().reverse();
            }
        });
        intake.start();

        PathStorage.pathFromBlueCloseToGlyphPit.goRight();
        new FollowPath(this, Robot.getDrive(), PathStorage.pathFromBlueCloseToGlyphPit, -1, -1);
        while(!Robot.getDrive().turnToAngle(-90) && !isStopRequested()) Thread.yield();

        new FollowPath(this, Robot.getDrive(),
                PathStorage.pathFromBlueCloseGlyphPitToLeftColumn, 1, 1);

        while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING) && !isStopRequested())
            Thread.yield();

        intake.interrupt();

        while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING) && !isStopRequested())
            Thread.yield();

        sleep(1000);

        while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING) && !isStopRequested())
            Thread.yield();
        //Robot.getDrive().encoderDrive(0.3, -5, -5, 2);
    }
}

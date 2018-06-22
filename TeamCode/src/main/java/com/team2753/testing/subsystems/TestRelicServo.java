package com.team2753.testing.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team2753.subsystems.Jewel;
import com.team2753.subsystems.Relic;
import com.team2753.subsystems.Slammer;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@TeleOp
@Disabled
public class TestRelicServo extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
//        Servo wristServo = hardwareMap.get(Servo.class, "yellow");
//        waitForStart();
//
//        double oneRotation = (1.0/8.0)*(16.0/24.0);
//        double oneDegree = (oneRotation/360.0);
//
//        wristServo.setPosition(180*oneDegree);
//        sleep(3000);
        Relic mRelic = new Relic();
        Slammer mSlammer = new Slammer();
        Jewel mJewel = new Jewel();

        mJewel.init(this, true);
        mSlammer.init(this, true);

        mRelic.init(this, false);
        mRelic.setWristPostion(0);
        waitForStart();
        while (opModeIsActive()){

            if(gamepad1.left_stick_y*90>=0){
                mRelic.setWristAngle(gamepad1.left_stick_y*90);
                telemetry.addData("Position", gamepad1.left_stick_y*90);
                telemetry.update();
            }

            Thread.yield();
        }
    }
}

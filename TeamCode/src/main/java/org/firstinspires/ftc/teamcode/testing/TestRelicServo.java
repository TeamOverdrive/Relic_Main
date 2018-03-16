package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Relic;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@TeleOp
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
        mRelic.init(this, false);
        waitForStart();
        mRelic.setWristPostion(34);// Deploy To Intake
        sleep(3000);
        mRelic.setWristPostion(50);//
    }
}

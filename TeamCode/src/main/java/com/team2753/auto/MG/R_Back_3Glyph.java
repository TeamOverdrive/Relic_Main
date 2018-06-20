package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Red Back", group = "3 Glyph")
public class R_Back_3Glyph extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RBack");

        DefaultHitJewel(this.jewel_Color, Jewel_Color.Red);

        switch (RelicRecoveryVuMark.RIGHT){
            case RIGHT:
                boolean rightFarther = false;
                Robot.getDrive().encoderDrive(0.6, 21, 21, 3);
                Robot.getDrive().encoderDrive(0.6, 0, (Constants.WHEEL_BASE*PI*(90))/180, 3);
                Robot.getDrive().encoderDrive(0.6, 2, 2, 3);
                Robot.getSlammer().stopperUp();
                sleep(400);

                Robot.getIntake().releaseIntake();
                Robot.getDrive().encoderDrive(0.7, -4, -4, 3);
                Robot.getSlammer().stopperDown();
                Robot.getIntake().intake();

                Robot.getDrive().encoderDrive(0.3, -20, -20, 3);
                Robot.getDrive().encoderDrive(0.2, -5, -5, 2);

                sleep(500);
                Robot.getIntake().reverse();
                sleep(1000);
                
                Robot.getIntake().setPower(0.5);

                
                Robot.getDrive().encoderDrive(0.4, (Constants.WHEEL_BASE*PI*(25))/180, 0, 3);
                Robot.getDrive().encoderDrive(0.4, 32, 32, 4);
                

                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(1, 4, 4, 2);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;

            case CENTER:
                boolean centerFarther = false;
                Robot.getDrive().encoderDrive(0.6, 22, 22, 4);
                Robot.getDrive().encoderDrive(0.6, 0, (Constants.WHEEL_BASE*PI*(57))/180, 3);
                Robot.getDrive().encoderDrive(0.6, 7, 7, 3);
                Robot.getSlammer().stopperUp();
                sleep(400);
                Robot.getIntake().releaseIntake();
                Robot.getDrive().encoderDrive(0.6, -(Constants.WHEEL_BASE*PI*(90-57))/180, 0, 3);
                Robot.getDrive().encoderDrive(0.6, -5, -5, 2);
                Robot.getSlammer().stopperDown();
                Robot.getIntake().intake();

                Robot.getDrive().encoderDrive(0.3, -20, -20, 3);
                Robot.getDrive().encoderDrive(0.2, -5, -5, 2);

                sleep(500);
                Robot.getIntake().reverse();
                sleep(1000);
            
                Robot.getIntake().intake();

                
                Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(-3))/180, 3);
                Robot.getDrive().encoderDrive(0.4, 35, 35, 4);
                

                

                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(1, 4, 4, 2);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;

            case LEFT:
                boolean leftFarther = false;
                Robot.getDrive().encoderDrive(0.6, 27, 27, 4);
                Robot.getDrive().encoderDrive(0.6, 0, (Constants.WHEEL_BASE*PI*(54))/180, 3);
                Robot.getDrive().encoderDrive(0.6, 7, 7, 3);
                Robot.getSlammer().stopperUp();
                sleep(400);
                Robot.getIntake().releaseIntake();
                Robot.getDrive().encoderDrive(0.6, -(Constants.WHEEL_BASE*PI*(90-54))/180, 0, 3);

                Robot.getDrive().encoderDrive(0.6, -5, -5, 2);
                Robot.getSlammer().stopperDown();
                Robot.getIntake().intake();

                Robot.getDrive().encoderDrive(0.3, -20, -20, 3);
                Robot.getDrive().encoderDrive(0.2, -5, -5, 2);

                
                sleep(500);
                Robot.getIntake().reverse();
                sleep(300);

                Robot.getIntake().setPower(0.5);

                Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(10))/180, 3);
                Robot.getDrive().encoderDrive(0.4, 35, 35, 4);

                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(1, 4, 4, 2);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;
        }
    }
}
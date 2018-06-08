package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Blue Back 3",group = "3 Glyph")
public class B_Back_3Glyph extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("BBack", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        hitJewel(this.jewel_Color, Jewel_Color.Blue);

        Robot.getDrive().encoderDrive(0.3, -36, -36, 4000);


        Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*(82))/180, 4000);


        Robot.getDrive().encoderDrive(0.3, 7, 7, 3000);

        Robot.getSlammer().stopperUp();
        sleep(600);
        Robot.getDrive().encoderDrive(0.3, -10, -10, 3000);
    }
}

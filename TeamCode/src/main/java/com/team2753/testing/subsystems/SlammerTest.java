package com.team2753.testing.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.subsystems.Slammer;

/**
 * Created by joshua9889 on 5/29/2018.
 */

@TeleOp
@Disabled
public class SlammerTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Slammer slammer = new Slammer();
        slammer.init(this, false);
        waitForStart();
        ElapsedTime dt = new ElapsedTime();
        double position = 0.5;
        while (opModeIsActive()){
            if (dt.milliseconds()<5){
                if (gamepad1.a)
                    position += 0.01;
                else if(gamepad1.b)
                    position -= 0.01;
            } else if(!(dt.milliseconds()<10)){
                dt.reset();
            }

            slammer.setSlammerPosition(position);
            telemetry.addData("Position", position);
            telemetry.update();
            // Down = 0.51
            // Level = 0.3
            // Scored = 0.0
        }
    }
}

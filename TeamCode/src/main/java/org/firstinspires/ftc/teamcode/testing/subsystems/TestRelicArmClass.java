package org.firstinspires.ftc.teamcode.testing.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.subsystems.Relic;

/**
 * Created by joshua9889 on 3/10/2018.
 */

@TeleOp
@Disabled
public class TestRelicArmClass extends Team2753Linear {
    enum RelicStates{
        Retracted, Deployed, Zone3
    }

    RelicStates currentState = RelicStates.Retracted;
    double modifier = 90;

    @Override
    public void runOpMode() throws InterruptedException {
        Relic mRelic = new Relic();
        mRelic.init(this, false);
        telemetry.addData("Waiting for Start","");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){

        }
    }
}

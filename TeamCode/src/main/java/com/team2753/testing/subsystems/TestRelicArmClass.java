package com.team2753.testing.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team2753.Team2753Linear;
import com.team2753.subsystems.Relic;

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

package org.firstinspires.ftc.teamcode.testing;

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
    @Override
    public void runOpMode() throws InterruptedException {
        Relic mRelic = new Relic();
        mRelic.init(this, false);

        waitForStart();

        while (opModeIsActive()){
            mRelic.turretAndElbow(1,1);

            sleep(5000);

            mRelic.turretAndElbow(0,0);

            sleep(5000);
        }
    }
}

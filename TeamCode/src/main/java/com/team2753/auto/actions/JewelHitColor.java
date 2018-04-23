package com.team2753.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.Team2753Linear;
import com.team2753.subsystems.Jewel;
import com.team2753.subsystems.Robot;

/**
 * Created by joshua9889 on 4/19/2018.
 */

public class JewelHitColor implements Action {

    private Jewel mJewel = Robot.getInstance().getJewel();
    private ElapsedTime t = new ElapsedTime();

    private Team2753Linear.Jewel_Color sJ, aC;

    public JewelHitColor(Team2753Linear.Jewel_Color scannedJewel,
                         Team2753Linear.Jewel_Color alliance_color){
        sJ = scannedJewel;
        aC = alliance_color;
    }

    @Override
    public void start() {
        t.reset();
    }

    @Override
    public void update() {
        if (t.milliseconds()<500)
            mJewel.deploy(true);
        else if(t.milliseconds()<700){
            if(sJ==aC){
                mJewel.rightHit();
            } else {
                mJewel.leftHit();
            }
        } else {
            mJewel.retract(false);
        }
    }

    @Override
    public boolean isFinished() {
        return t.milliseconds()>1000;
    }

    @Override
    public void done() {}
}

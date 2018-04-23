package com.team2753.auto;

import com.team2753.Team2753Linear;
import com.team2753.auto.actions.Action;

/**
 * Created by joshua9889 on 3/23/2018.
 */

public abstract class AutoModeBase extends Team2753Linear {

    /**
     * @param action All actions are defined in action folder
     */
    public void runAction(Action action){
        if(opModeIsActive() && !isStopRequested())
            action.start();

        while (opModeIsActive() && !isStopRequested() && action.isFinished())
            action.update();

        if(opModeIsActive() && !isStopRequested())
            action.done();
    }

    /**
     * @param action All actions are defined in action folder
     */
    public void ThreadAction(final Action action){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                runAction(action);
            }
        };

        if(opModeIsActive() && !isStopRequested())
            new Thread(runnable).start();
    }
}
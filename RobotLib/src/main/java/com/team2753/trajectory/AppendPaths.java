package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class AppendPaths {
    public static Path appendPath(Path original, Path to_append){
        original.getLeftWheelTrajectory().append(to_append.getLeftWheelTrajectory());
        original.getRightWheelTrajectory().append(to_append.getRightWheelTrajectory());

        return original;
    }

    public static Path appendSpecial(Path original, Path to_append){
        Trajectory left = original.getLeftWheelTrajectory();
        Trajectory after = to_append.getLeftWheelTrajectory();

        for (int i = 0; i < after.getNumSegments()-1; i++) {
            double pos = after.getSegment(i).pos + left.getSegment(left.getNumSegments()-1).pos;
            double vel = after.getSegment(i).vel + left.getSegment(left.getNumSegments()-1).vel;
            double acc = after.getSegment(i).acc + left.getSegment(left.getNumSegments()-1).acc;
            double heading = after.getSegment(i).heading + left.getSegment(left.getNumSegments()-1).heading;
            double x = after.getSegment(i).x + left.getSegment(left.getNumSegments()-1).x;
            double y = after.getSegment(i).y + left.getSegment(left.getNumSegments()-1).y;
            Trajectory.Segment update = new Trajectory.Segment(pos, vel, acc, heading, after.getSegment(i).jerk,
                    after.getSegment(i).dt, x, y);
            after.setSegment(i, update);
        }
        return new Path("", new Trajectory.Pair(after, original.getRightWheelTrajectory()));
    }
}

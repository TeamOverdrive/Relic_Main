package com.team2753.localTestCode;

/**
 * Created by joshua9889 on 4/30/2018.
 *
 * See http://team449.shoutwiki.com/wiki/Pose_Estimation for reasoning
 */

public class RobotStateEstimator {

    private double wheelbase_width;

    private double reallyBigNumber = 10e9;
    private double left_encoder_prev_distance_ = 0;
    private double right_encoder_prev_distance_ = 0;

    /**
     * @param wheelbase_width Wheelbase width in what ever the robot is measuring
     * @param initLeftPosition initial position of the left side
     * @param initRightPosition initial position of the right side
     */
    public RobotStateEstimator(double wheelbase_width, double initLeftPosition, double initRightPosition){
        this.wheelbase_width = wheelbase_width;
        left_encoder_prev_distance_ = initLeftPosition;
        right_encoder_prev_distance_ = initRightPosition;
    }

    public void update(double leftPosition, double rightPosition, double theda){
        double left_distance = leftPosition;
        double right_distance = rightPosition;

        double radiusOfTurn = (wheelbase_width/2) * ((right_distance+left_distance)/(right_distance-left_distance));



        left_encoder_prev_distance_ = left_distance;
        right_encoder_prev_distance_ = right_distance;
    }
}

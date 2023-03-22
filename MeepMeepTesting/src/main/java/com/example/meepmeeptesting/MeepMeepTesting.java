package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(10, 100, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(33, 62, Math.toRadians(-90)))
                                .forward(4)
                                .strafeRight(33)
                                .forward(26)
                                .forward(6)
                                .back(6)
                                .strafeLeft(42)
                                .build()


                        //drive.trajectorySequenceBuilder(new Pose2d(38, 62, Math.toRadians(-90)))
                                /*
                                .splineToConstantHeading(new Vector2d(35,60),Math.toRadians(-90))
                                .lineToLinearHeading(new Pose2d(10,58, Math.toRadians(-90)))
                                .lineToLinearHeading(new Pose2d(10,23, Math.toRadians(-180)))
                                .forward(3)
                                .back(3)
                                .back(3)
                                .lineToLinearHeading(new Pose2d(10,12, Math.toRadians(-0)))
                                .lineToLinearHeading(new Pose2d(60,12, Math.toRadians(0)))
                                .forward(3)
                                .back(3)
                                .lineToLinearHeading(new Pose2d(24,12, Math.toRadians(90)))
                .forward(3)
                                .forward(2)
                                .back(5)
                                //Park


                           */

                        // ---------------------Top Right two cones improved
                        /*

                        drive.trajectorySequenceBuilder(new Pose2d(-38, 62, Math.toRadians(-90)))
                                .splineToConstantHeading(new Vector2d(-35,58),Math.toRadians(-90))
                                .lineToLinearHeading(new Pose2d(-12,58, Math.toRadians(-90)))

                         */
                        // -------------------TOP-Left //
                        /*
                        drive.trajectorySequenceBuilder(new Pose2d(-34, 62, Math.toRadians(-90)))
                                .splineToConstantHeading(new Vector2d(-34,15),Math.toRadians(-90))
                                .splineToConstantHeading(new Vector2d(-24,8),Math.toRadians(-90))
                                .forward(2)
                                .back(2)
                                .splineToConstantHeading(new Vector2d(-35,11),Math.toRadians(-90))
                                .lineToLinearHeading(new Pose2d(-60,12, Math.toRadians(180)))

                        */

                        /*
                                    // ---------------------------Top-Right--------------------
                        drive.trajectorySequenceBuilder(new Pose2d(33, 65, Math.toRadians(-90)))
                                //To first junction
                                .splineToConstantHeading(new Vector2d(37,60),Math.toRadians(-90))
                                .splineToConstantHeading(new Vector2d(35,18),Math.toRadians(-90))
                                .splineToConstantHeading(new Vector2d(24,7),Math.toRadians(-90))
                                .forward(2)
                                .back(2)
                                //To cone stack
                                .splineToConstantHeading(new Vector2d(32,10),Math.toRadians(0))
                                .lineToLinearHeading(new Pose2d(60,12, Math.toRadians(0)))
                                .forward(2)
                                .back(2)
                                //Back to junction
                                .lineToLinearHeading(new Pose2d(32,12, Math.toRadians(-90)))
                                .splineToConstantHeading(new Vector2d(24,7),Math.toRadians(0))
                                .forward(3)
                                .back(3)
                                //Back to conestack
                                .splineToConstantHeading(new Vector2d(32,10),Math.toRadians(0))
                                .lineToLinearHeading(new Pose2d(60,12, Math.toRadians(0)))
                                .forward(2)
                                .back(2)
                                //Back to junction
                                .lineToLinearHeading(new Pose2d(32,12, Math.toRadians(-90)))
                                .splineToConstantHeading(new Vector2d(24,8),Math.toRadians(0))
                                .forward(3)
                                .back(3)
                                //Back to conestack
                                .splineToConstantHeading(new Vector2d(32,10),Math.toRadians(0))
                                .lineToLinearHeading(new Pose2d(60,12, Math.toRadians(0)))
                                .forward(2)
                                .back(2)
                                //Back to junction
                                .lineToLinearHeading(new Pose2d(32,12, Math.toRadians(-90)))
                                .splineToConstantHeading(new Vector2d(24,9),Math.toRadians(0))
                                .forward(3)
                                .back(3)
                                //Back to conestack
                                .splineToConstantHeading(new Vector2d(32,10),Math.toRadians(0))
                                .lineToLinearHeading(new Pose2d(60,12, Math.toRadians(0)))
                                .forward(2)
                                .back(2)
                                //Back to junction
                                .lineToLinearHeading(new Pose2d(32,12, Math.toRadians(-90)))
                                .splineToConstantHeading(new Vector2d(24,10),Math.toRadians(0))
                                .forward(3)
                                .back(3)

                                //Park
                                .back(3)
                                .strafeRight(12)



                                //.splineTo(new Vector2d(55,12),Math.toRadians(60))


                                /*
                                .forward(4)
                                .strafeLeft(43)
                                .forward(26)
                                .forward(6)
                                .back(6)
                                .strafeRight(42)
                                .turn(Math.toRadians(-90))
                                .strafeLeft(24)
                                .forward(26)
                                .back(37)
                                .turn(Math.toRadians(-90))
                                .forward(5)
                                .back(5)
                                .strafeRight(13)

                                 */


                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
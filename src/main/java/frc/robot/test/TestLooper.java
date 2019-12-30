/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.test;

import frc.robot.teleop.TeleopLooper;
import frc.robot.util.PixyCamera;

/**
 *  Add all the requried stuff here for testing
 */
public class TestLooper implements ITest {

    private TeleopLooper mTestTeleop;
    private static TestLooper instance;
    private PixyCamera mPixy;

    // target width
    private int targetWidth = 2;
    // focal lengh = (P x D) / W
    private int kFocalLen = 456;

    public static TestLooper getInstance() {
        if (instance == null) {
            instance = new TestLooper();
        }
        return instance;
    }

    @Override
    public void testInit() {
        mTestTeleop.init();

        mPixy = new PixyCamera();
    }

    @Override
    public void testLooper() {
        mTestTeleop.autoSequence();

        double width = mPixy.getWidth();
        double distance = (targetWidth *kFocalLen) / width;

        System.out.println("Distance: " + distance);
    }
}

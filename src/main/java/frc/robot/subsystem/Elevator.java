/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem{

    private double mCurrPos = 0.0;

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void zeroSensors() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public Boolean checkSystem() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateDashboard() {
        // TODO Auto-generated method stub

    }

    public void moveToPosition(double position) {
        getCurPosition();

        if (mCurrPos < position) {
            // move up
            // up elevator PID
        } else if (mCurrPos > position) {
            // move down
            // down elevator PID
        } else {
            // do nothing
            // hold positoin
        }
    }

    public double getCurPosition() {
        // update encoder value and return
        return mCurrPos;
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Talon;
import frc.robot.Constants;

public class Shooter extends Subsystem {
    Talon mShooterCim1 = new Talon(Constants.SHOOTER_CIM_1);

    private static Shooter instance = null;

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    public Shooter() {
        mShooterCim1.set(0);
    }

    public void shoot(double value) {
        this.mShooterCim1.set(value);
    }

    public void eject() {
        this.mShooterCim1.set(-0.5);
    }

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
        return true;
    }

    @Override
    public void updateDashboard() {
        // TODO Auto-generated method stub

    }
}

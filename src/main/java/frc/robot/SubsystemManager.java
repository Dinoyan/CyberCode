/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;
import java.util.List;

import frc.robot.subsystem.Subsystem;

/**
 *  
 */
public class SubsystemManager {

    private static SubsystemManager mInstance = null;

    private List<Subsystem> mAllSubsystem;

    public static SubsystemManager getInstance() {
        if (mInstance == null) {
            mInstance = new SubsystemManager();
        }
        return mInstance;
    }

    public void outPutDashboard() {

    }

    public void stop() {

    }

    public void zeroAll() {
        mAllSubsystem.forEach(Subsystem::zeroSensors);
    }

    public boolean checkAllSystem() {
        return true;
    }

    public void setSystem(Subsystem... subsystems) {
        this.mAllSubsystem = Arrays.asList(subsystems);
    }

    public List<Subsystem> getSubsystems() {
        return this.mAllSubsystem;
    }
}

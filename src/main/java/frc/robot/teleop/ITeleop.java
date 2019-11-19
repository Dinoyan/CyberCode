/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.teleop;

/**
 * Add your docs here.
 */
interface ITeleop {

    public void init();
    public void driveEnabledLoop();
    public void superstructureEnabledLoop();
}

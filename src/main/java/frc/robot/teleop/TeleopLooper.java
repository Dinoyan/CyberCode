/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.teleop;

import frc.robot.JoystickHandler;
import frc.robot.subsystem.Drivetrain;

/**
 * Add your docs here.
 */
public class TeleopLooper implements ITeleop {

    private JoystickHandler mJoystick;
    private Drivetrain mDrive;
 
    @Override
    public void init() {
        mJoystick = JoystickHandler.getInstance();
        mDrive = Drivetrain.getInstance();
        
    }

    @Override
    public void driveEnabledLoop() {
        double left = mJoystick.getDriveStick().getRawAxis(1);
        double right = mJoystick.getDriveStick().getRawAxis(5);
        mDrive.drive(left, right);

    }

    @Override
    public void superstructureEnabledLoop() {
        // upper mech control code with joysticks here
    }

 
}

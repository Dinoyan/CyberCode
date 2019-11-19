/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class JoystickHandler {

    private Joystick driveStick = new Joystick(Constants.DRIVE_STICK);
    private Joystick shootStick = new Joystick(Constants.SHOOT_STICK);

    private static JoystickHandler instance = null;

    public static JoystickHandler getInstance() {
        if (instance == null) {
            instance = new JoystickHandler();
        }
        return instance;
    }

    public Joystick getDriveStick() {
        return this.driveStick;
    }

    public Joystick getShootStick() {
        return this.shootStick;
    }
}

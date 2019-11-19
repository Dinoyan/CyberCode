/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class AutoSelector {
    
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";

    /* private enum autoModes{
        TEST, DEFAULT
    }
    */

    private final SendableChooser<String> m_chooser = new SendableChooser<>();

    private String m_autoSelected;
    private byte mode;

    private static AutoSelector mInstance = null;

    public static AutoSelector getInstance() {
        if (mInstance == null) {
            mInstance = new AutoSelector();
        }
        return mInstance;
    }
    
    public AutoSelector() {
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
    }

    public byte getAutoMode() {
        this.m_autoSelected = m_chooser.getSelected();
        
        switch (m_autoSelected) {
            case "TEST":
                mode = 1;
                break;
            
            case "DEFAULT":
                mode = 0;
                break;
        }
        return mode;
      
    }
}

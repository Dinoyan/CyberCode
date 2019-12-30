/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.auto.AutoHandler;
import frc.robot.auto.AutoSelector;
import frc.robot.subsystem.Drivetrain;
import frc.robot.subsystem.Shooter;
import frc.robot.util.CrashTracker;
import frc.robot.teleop.TeleopLooper;
import frc.robot.test.TestLooper;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  private Drivetrain mDrivetrain;
  private Shooter mShooter;
  private SubsystemManager mSubsystemManager;
  private TeleopLooper mTeleopLooper;
  private AutoHandler mAutoHanlder;
  private AutoSelector mAutoSelector;

  // test class
  private TestLooper mTest;

  private byte mAutoMode = 1;
  
  @Override
  public void robotInit() {
  
    this.mDrivetrain = Drivetrain.getInstance();
    this.mShooter = Shooter.getInstance();
    this.mSubsystemManager = SubsystemManager.getInstance();
    this.mTeleopLooper = TeleopLooper.getInstance();
    this.mAutoHanlder = AutoHandler.getInstance();
    this.mAutoSelector = AutoSelector.getInstance();

    // test instance
    this.mTest = TestLooper.getInstance();
 
    CrashTracker.logRobotInit();
  
    this.mSubsystemManager.setSystem(
      mDrivetrain,
      mShooter
    );

    mSubsystemManager.checkAllSystem();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    
    CrashTracker.logAutoInit();
    mAutoMode = mAutoSelector.getAutoMode();

    mSubsystemManager.zeroAll();
    mAutoHanlder.init(mAutoMode);
  }

  @Override
  public void autonomousPeriodic() {
   mAutoHanlder.autonomousPeriodHandler();
  }

  @Override
  public void teleopInit() {
    CrashTracker.logTeleopInit();
    mSubsystemManager.checkAllSystem();

    mTeleopLooper.init();
    
  }

  @Override
  public void teleopPeriodic() {
    mTeleopLooper.driveEnabledLoop();
    mTeleopLooper.superstructureEnabledLoop();
  }

  @Override
  public void disabledInit() {
    mSubsystemManager.stop();
    mSubsystemManager.zeroAll();
  }

  @Override
  public void testInit() {
   CrashTracker.logTestInit();
  
   mTest.testInit();
  }
  
  @Override
  public void testPeriodic() {
    CrashTracker.SubsystemsDisgonitics();
    
    mTest.testLooper();
  }

}

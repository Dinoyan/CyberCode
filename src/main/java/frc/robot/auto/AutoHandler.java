/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystem.Drivetrain;
import frc.robot.util.CyberPID;

public class AutoHandler {
    private Drivetrain drive;

    private static final byte TEST_STATE_MACHINE = 1;
    private static final byte TEST_STATE_MACHINE_V2 = 2;

    private static final byte DRIVE_FORWARD = 1;
    private static final byte DRIVE_BACKWARD = 2;
    private static final byte WAIT_STATE = 3;

    private byte currentState;
    private byte currentStateIndex;
    private byte[] nextStateArray;

    private Timer mTimer;
    private Boolean mStop = false;

    private static AutoHandler mInstance = null;

    public static AutoHandler getInstance() {
      if (mInstance == null) {
        mInstance = new AutoHandler();
      }
      return mInstance;
    }

    public void init(byte selection) {
      this.drive = Drivetrain.getInstance();
      currentStateIndex = 0;
      setCurrentState(WAIT_STATE);
      setNextStateArray(selection);

      mTimer.start();
    }
 
    public void setNextStateArray(byte mode) {
      byte stateCounter = 0;
      if (TEST_STATE_MACHINE == mode) {
        nextStateArray[stateCounter] = DRIVE_FORWARD;
        stateCounter++;
        nextStateArray[stateCounter] = WAIT_STATE;
      } else if (TEST_STATE_MACHINE_V2 == mode) {
        nextStateArray[stateCounter] = DRIVE_BACKWARD;
      }
    }

    public void setCurrentState(byte state) {
      this.currentState = state;
    }

    public void autonomousPeriodHandler() {
      if (currentState == DRIVE_FORWARD) {
        this.driveToDistance(3);
      } else if (currentState == WAIT_STATE) {
        // do nothing
      }
    }

    private boolean infLoopChecker() {
      if (mTimer.get() > 15) {
        this.mStop = true;
      }
      return this.mStop;
    }

    private void driveToDistance(double distance) {
      CyberPID pidController = new CyberPID();
      Boolean cond = infLoopChecker();
      
      while (!pidController.onTarget(this.drive.getRightEncoderDis()) && !cond) {
        cond = infLoopChecker();
        double value = pidController.getOutput(this.drive.getRightEncoderDis());
        this.drive.drive(value, value);
      }

      currentStateIndex++;
      setCurrentState(nextStateArray[currentStateIndex]);
    }
}

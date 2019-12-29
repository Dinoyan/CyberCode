/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * Custom PID Implementation
 * Dinoyan Ganeshalingam
 */
public class CyberPID {

    private double kP;
    private double kI;
    private double kD;
    private double mSetpoint;
    private double kTolerance = 5;
    private double kResetTime = 0.02;
    private int mFinishCount = 5;
    private int mCount = 0;
    boolean mTarget = false;

    private double prevError;

    private double mError;
    private double mIntegral;
    private double mDerivative;

    private double mOutput;


    public CyberPID() {
        this.kP = 0.01;
        this.kI = 0.01;
        this.kD = 0.01;
    } 

    public CyberPID(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public CyberPID(double kP, double kI, double kD, double setpoint) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.mSetpoint = setpoint;
    }

    public CyberPID(double kP, double kI, double kD, double setpoint, double tolerance) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.mSetpoint = setpoint;
        this.kTolerance = tolerance;
    }

    public double getP() {
        return this.kP;
    }

    public double getI() {
        return this.kI;
    }

    public double getD() {
        return this.kD;
    }

    public void setSetpoint(double setpoint) {
        this.mSetpoint = setpoint;
    }

    public double getSetpoint() {
        return this.mSetpoint;
    }

    public void reset() {
        this.mCount = 0;
        this.mTarget = false;
    }

    public boolean onTarget(double curr) {

        if (Math.abs(this.mSetpoint - curr) < kTolerance) {
            mCount++;
            mTarget = mCount > mFinishCount;
        } else {
            mTarget = false;
            mCount = 0;
        }
        return mTarget;
    }

    public synchronized double getOutput(double curr) {
        this.mError = this.mSetpoint - curr;
        
        this.mIntegral += (mError * kResetTime);
        
        if ((mError == 0) || (mError > mSetpoint)) {
            mIntegral = 0;
        }
        
        this.mDerivative = mError - prevError;
        prevError = mError;

        this.mOutput = this.kP * mError + this.kI * mIntegral +  this.kD *mDerivative;
        
        if (mOutput < -1) {
            mOutput = -1;
        } else if (mOutput > 1) {
            mOutput = 1;
        }
        return -this.mOutput;
    }
}

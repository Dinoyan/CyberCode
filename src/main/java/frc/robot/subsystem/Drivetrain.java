/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;


public class Drivetrain extends Subsystem {
    private static Drivetrain  instance = null;

    WPI_TalonSRX lDriveMaster = new WPI_TalonSRX(Constants.LEFT_DRIVE1);
    WPI_TalonSRX rDriveMaster = new WPI_TalonSRX(Constants.RIGHT_DRIVE1);
  
    WPI_TalonSRX lDriveSlave = new WPI_TalonSRX(Constants.LEFT_DRIVE2);
    WPI_TalonSRX rDriveSlave = new WPI_TalonSRX(Constants.RIGHT_DRIVE2);
    
    DifferentialDrive _drive = new DifferentialDrive(lDriveMaster, rDriveMaster);

    Encoder leftEncoder = new Encoder(Constants.LEFT_ENC_ONE, Constants.LEFT_ENC_TWO, true, Encoder.EncodingType.k4X);
    Encoder rightEncoder = new Encoder(Constants.RIGHT_ENC_ONE, Constants.RIGHT_ENC_TWO, true, Encoder.EncodingType.k4X);

    private boolean mReady = false;

    // AHRS ahrs = new AHRS(SerialPort.Port.kMXP);

    public static Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }
        return instance;
    }

    public double getLeftEncoderDis() {
        return this.leftEncoder.getDistance();
    }

    public double getRightEncoderDis() {
        return this.rightEncoder.getDistance();
    }

    public void drive(double left, double right) {
        _drive.tankDrive(left, right);

    }

    @Override
    public void init() {
        lDriveMaster.configFactoryDefault();
	rDriveMaster.configFactoryDefault();
	lDriveSlave.configFactoryDefault();
	rDriveSlave.configFactoryDefault();

	lDriveSlave.follow(lDriveMaster);
        rDriveSlave.follow(rDriveMaster);
        
        lDriveMaster.setInverted(false);
	rDriveMaster.setInverted(true); 
	lDriveSlave.setInverted(InvertType.FollowMaster);
        rDriveSlave.setInverted(InvertType.FollowMaster);
    
        lDriveMaster.setNeutralMode(NeutralMode.Coast);
        rDriveMaster.setNeutralMode(NeutralMode.Coast);

	_drive.setRightSideInverted(false);
    }

    public void switchModeCoast() {
        lDriveMaster.setNeutralMode(NeutralMode.Coast);
        rDriveMaster.setNeutralMode(NeutralMode.Coast);
    }

    public void switchModeBrake() {
        lDriveMaster.setNeutralMode(NeutralMode.Brake);
        rDriveMaster.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void zeroSensors() {
        this.rightEncoder.reset();
        this.leftEncoder.reset();
    }

    @Override
    public void stop() {
        drive(0, 0);
    }

    @Override
    public Boolean checkSystem() {
        mReady = lDriveMaster.isAlive();
        return mReady;
    }

    @Override
    public void updateDashboard() {
        SmartDashboard.putString("Enc Value Left", Double.toString(this.getLeftEncoderDis()));
        SmartDashboard.putString("Enc Value Right", Double.toString(this.getRightEncoderDis()));
    }
}

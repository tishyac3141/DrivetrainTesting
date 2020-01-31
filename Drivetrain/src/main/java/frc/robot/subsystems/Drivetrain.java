/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team6479.lib.subsystems.TankDrive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase implements TankDrive {
  /**
   * Creates a new Drivetrain.
   */
  private TalonSRX leftFront;
  private TalonSRX leftBack;
  private TalonSRX rightFront;
  private TalonSRX rightBack;

  public Drivetrain() {
    leftFront = new TalonSRX(DrivetrainConstants.DRIVETRAIN_LEFT_FRONT);
    leftBack = new TalonSRX(DrivetrainConstants.DRIVETRAIN_LEFT_BACK);
    rightFront = new TalonSRX(DrivetrainConstants.DRIVETRAIN_RIGHT_FRONT);
    rightBack = new TalonSRX(DrivetrainConstants.DRIVETRAIN_RIGHT_BACK);

    leftFront.configFactoryDefault();
    leftBack.configFactoryDefault();
    rightFront.configFactoryDefault();
    rightBack.configFactoryDefault();

    leftBack.follow(leftFront);
    rightBack.follow(rightFront);

    rightFront.setInverted(true);
    leftFront.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void stop() {
    rightFront.set(ControlMode.PercentOutput, 0.0);
    leftFront.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void arcadeDrive(double forward, double turn) {
    leftFront.set(ControlMode.PercentOutput, forward, DemandType.ArbitraryFeedForward, +turn);
    rightFront.set(ControlMode.PercentOutput, forward, DemandType.ArbitraryFeedForward, -turn);
  }

  @Override
  public void tankDrive(double leftSpeed, double rightSpeed) {
    leftFront.set(ControlMode.PercentOutput, leftSpeed);
    rightFront.set(ControlMode.PercentOutput, rightSpeed);
  }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5288.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	 public static double JOYDEADZONE = 0.15;

	public static final int liftMotor = 4;
	// Drivetrain
	//Drivetrain motors (PWM)
	public static final int LDriveMotor1 = 2;
	public static final int LDriveMotor2 = 3;
	public static final int RDriveMotor1 = 0;
	public static final int RDriveMotor2 = 1;
	// Drivetrain Sensors (D I/O)
	public static final int LDriveEncoder1 = 0;
	public static final int LDriveEncoder2 = 1;
	public static final int RDriveEncoder1 = 3;
	public static final int RDriveEncoder2 = 4;
		
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

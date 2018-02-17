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
	
// Drivetrain
	//Drivetrain motors (PWM)
	public static int LDriveMotor1 = 0;
	public static int LDriveMotor2 = 1;
	public static int RDriveMotor1 = 2;
	public static int RDriveMotor2 = 3;
	
	// Drivetrain Sensors (D I/O)
	public static int LDriveEncoder1 = 0;
	public static int LDriveEncoder2 = 1;
	public static int RDriveEncoder1 = 2;

	public static int RDriveEncoder2 = 3;
// INTAKE
	public static int leftIntakeMotor = 
// LIFT
	// LIFT motor (CAN)
	public static int LiftMotor = 0;
// RAMP
	//RAMP motors (PWM)
	// there are two ramp motors on each side, but a *splitter* is being used to output the same power to both at the same time using ONE PWM slot.
	public static int LRampMotors = 4;
	public static int RRampMotors = 5;
	public static int lRampServo = 6;
	public static int rRampServo = 7;

// JOYSTICK
	// Joystick dead/safe zone
	public static double JOYDEADZONE = 0.15;
	
	// DriveStraight PID values
	public static double P = 0;
	public static double I = 0;
	public static double D = 0;
	public static double FF = 0;
	
// LIMIT SWITCHES (D I/O)
	public static int LRampLimitSwitch = 4;
	public static int RRampLimitSwitch = 5;
	
	
	
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

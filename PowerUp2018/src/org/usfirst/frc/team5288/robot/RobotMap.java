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
	// Drivetrain motors (PWM)
	public static int LDriveMotor1 = 0;
	public static int LDriveMotor2 = 1;
	public static int RDriveMotor1 = 2;
	public static int RDriveMotor2 = 3;

	// a is white, b is brown, x is green, (right side), red is power!! black is ground!!
	// Drivetrain Sensors (D I/O)
	public static int LDriveEncoder1 = 0;
	public static int LDriveEncoder2 = 1;
	public static int RDriveEncoder1 = 2;
	public static int RDriveEncoder2 = 3;
	// INTAKE
	public static int lIntakeMotor = 8;
	public static int rIntakeMotor = 9;
	// LIFT
	// LIFT motor (CAN)
	public static int LiftMotor = 0;
	// RAMP
	// RAMP motors (PWM)
	// there are two ramp motors on each side, but a *splitter* is being used to
	// output the same power to both at the same time using ONE PWM slot.
	public static int LRampMotors = 4;
	public static int RRampMotors = 5;
	public static int lRampServo = 6;
	public static int rRampServo = 7;

	// LIMIT SWITCHES (D I/O)
	public static int LRampLimitSwitch = 4;
	public static int RRampLimitSwitch = 5;
	public static int TopLiftLimit = 6;
	public static int BottomLiftLimit = 7;
	public static int intakeLimitSwitch = 8;
	// JOYSTICK
	// Joystick dead/safe zone
	public static double JOYDEADZONE = 0.06;
	// PID VALUES
	// DriveStraight PID values
	//0.009, 0, 0.001, 1/720
	public static double StraightP = 0.009;
	public static double StraightI = 0;
	public static double StraightD = 0.001;
	public static double StraightFF = 1/720;
	// DriveDistance PID values
	//1/7,0.4,0.24,0
	public static double DistanceP = 1/7;
	public static double DistanceI = 0.4;
	public static double DistanceD = 0.24;
	public static double DistanceFF = 0;
	// SpotTurnDegrees PID values
	//(0.02, 0.0095, 0.09, 0)
	public static double TurnP = 0.02;
	public static double TurnI = 0.0095;
	public static double TurnD = 0.09;
	public static double TurnFF = 0;

	// LiftToHeight PID values
	public static double LiftP = 0;
	public static double LiftI = 0;
	public static double LiftD = 0;
	public static double LiftFF = 0;
	//

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

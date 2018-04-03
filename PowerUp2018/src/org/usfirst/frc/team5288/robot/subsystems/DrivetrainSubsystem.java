/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;
//import org.usfirst.frc.team5288.robot.subsystems.Drivetrain.drivestates;
import org.usfirst.frc.team5288.robot.commands.ManualDriveCommand;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DrivetrainSubsystem extends Subsystem {
	// **DRIVETRAIN CONSTANTS**
	public final double wheelRadiusm = 0.0508; // meters
	public final double wheelcirc = 6 * Math.PI;
	public final double topSpeed = 3.048; // meters per second
	// *******************MOTOR CONTROLLER OBJECTS**************
	// These Motor controller objects will always be synced in pairs of output.
	private VictorSP lmotor1 = new VictorSP(RobotMap.LDriveMotor1);// Left Gearbox Motor #
	private VictorSP lmotor2 = new VictorSP(RobotMap.LDriveMotor2);// Left Gearbox Motor #
	private VictorSP rmotor1 = new VictorSP(RobotMap.RDriveMotor1);// Right Gearbox Motor #1
	private VictorSP rmotor2 = new VictorSP(RobotMap.RDriveMotor2);// Right Gearbox Motor #2
	private boolean isBrakeMode = true;
	// **Drive Variables**
	private double throttle = 1;
	private double lPower = 0;// Raw Power percentage being output to the left gearbox.
	private double rPower = 0;// Raw Power percentage being output to the right gearbox.
	// GYRO VARIABLES
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();;
	private double gyroTotal = 0;
	public String PIDInput = "";
	public String PIDOutput = "";
	// **ULTRASONIC VARIABLES*
	// private AnalogInput ultrasonic = new AnalogInput(RobotMap.ultrasonicInput);
	// private double ultraSonicDistance = 0;
	// **ENCODER VARIABLES*
	private Encoder rEncoder;
	private Encoder lEncoder;
	// **SPEED CALCULATION BASED VARIABLES** //Encoder Tracking variables
	// Left
	private double lastSpeedL = 0;
	private double lastAccelL = 0;
	private double currentAccelL = 0;
	private double jerkL = 0;
	private double currentSpeedL = 0;
	private double encLastL = 0;
	private double encCurrentL = 0;
	private double encDiffL = 0;
	// Right
	private double lastAccelR = 0;
	private double currentAccelR = 0;
	private double jerkR = 0;
	private double lastSpeedR = 0;
	private double currentSpeedR = 0;
	private double encLastR = 0;
	private double encCurrentR = 0;
	private double encDiffR = 0;
	//Save max values reached.
	private double maxJerkR = 0;
	private double maxAccelR = 0;
	private double maxSpeedR = 0;

	private double maxJerkL = 0;
	private double maxAccelL = 0;
	private double maxSpeedL = 0;
	// Time
	private double timeLast = 0;
	private double timeCurrent = 0;
	private double timeDiff = 0;

	public DrivetrainSubsystem() {
		rEncoder = new Encoder(RobotMap.RDriveEncoder1, RobotMap.RDriveEncoder2, false, EncodingType.k4X);
		lEncoder = new Encoder(RobotMap.LDriveEncoder1, RobotMap.LDriveEncoder2, true, EncodingType.k4X);
		rEncoder.setMaxPeriod(5);
		lEncoder.setMaxPeriod(5);
		rEncoder.setMinRate(0);
		lEncoder.setMinRate(0);
		rEncoder.setSamplesToAverage(1);
		lEncoder.setSamplesToAverage(1);
		rEncoder.setDistancePerPulse(wheelcirc / 2048);
		lEncoder.setDistancePerPulse(wheelcirc / 2048);
		gyro.calibrate();
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new ManualDrive());
		setDefaultCommand(new ManualDriveCommand());
	}

	public double getGyroAngle() {
		return gyro.getAngle();
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void resetEncoders() {
		lEncoder.reset();
		rEncoder.reset();
	}

	public void setLPower(double power) {
		lPower = power;
		//lmotor1.set(-power);
		//lmotor2.set(-power);
	}

	public void setRPower(double power) {
		rPower = power;
		//rmotor1.set(power);
		//rmotor2.set(power);
	}

	private void outputToMotors(double pwrLeft, double pwrRight) {
		lmotor1.set(-pwrLeft);
		lmotor2.set(-pwrLeft);
		rmotor1.set(pwrRight);
		rmotor2.set(pwrRight);
	}

	public void setThrottle(double newThrottle) {
		throttle = newThrottle;
	}

	public double getThrottle() {
		return throttle;
	}

	public double getLeftDistanceInches() {
		return rEncoder.getDistance();//Intentional because the encoders are on the wrong side and this is quicker
	}

	public double getRightDistanceInches() {
		return lEncoder.getDistance();//Intentional because the encoders are on the wrong side and this is quicker, no need to edit
	}



	public void updateOutputs() {
		outputToMotors(lPower, rPower);
	}
	private void saveMaxValues() {
		if(currentSpeedL > maxSpeedL) {
			maxSpeedL = currentSpeedL;
		}
		if(currentAccelL > maxAccelL) {
			maxAccelL = currentAccelL;
		}
		if(jerkL > maxJerkL) {
			maxJerkL = jerkL;
		}
		if(currentSpeedR > maxSpeedR) {
			maxSpeedR = currentSpeedR;
		}
		if(currentAccelR > maxAccelR) {
			maxAccelR = currentAccelR;
		}
		if(jerkR > maxJerkR) {
			maxJerkR = jerkR;
		}
		 Robot.putDashboardNumber("Maximum Speed Left", maxSpeedL);
		 Robot.putDashboardNumber("Maximum Speed Right", maxSpeedR);
		 Robot.putDashboardNumber("Maximum Accel Left",maxAccelL);
		 Robot.putDashboardNumber("Maximum Accel Right",maxAccelR);
		 Robot.putDashboardNumber("Maximum Jerk Left",maxJerkL);
		 Robot.putDashboardNumber("Maximum Jerk Right",maxJerkR);
		 
	}
	public void updateSensors() {
		// Load last Values
		lastSpeedL = currentSpeedL;
		lastSpeedR = currentSpeedR;
		lastAccelL = currentAccelL;
		lastAccelR = currentAccelR;
		encLastR = encCurrentR;
		encLastL = encCurrentL;
		timeLast = timeCurrent;

		
		// Update Current Values
		timeCurrent = System.currentTimeMillis();
		encCurrentL = getLeftDistanceInches();
		encCurrentR = getRightDistanceInches();

		// ******Calculate New Values*******
		timeDiff = timeCurrent - timeLast;// Calculate time difference
		encDiffL = encCurrentL - encLastL;// Calculate encoder difference
		encDiffR = encCurrentR - encLastR;// Calculate encoder difference
		// Calculate The Current speed
		currentSpeedL = encDiffL / timeDiff;
		currentSpeedR = encDiffR / timeDiff;
		// ******Calculate Acceleration and difference in acceleration******
		currentAccelL = (lastSpeedL - currentSpeedL) / timeDiff;
		currentAccelR = (lastSpeedR - currentSpeedR) / timeDiff;
		jerkL = (currentAccelL - lastAccelL) / timeDiff;
		jerkR = (currentAccelR - lastAccelR) / timeDiff;
		// ***** ULTRASONIC VARIABLES
		// ultraSonicDistance = getUltraSonicVoltageData()*(( 4.88/5)/0.92)*39.283;
		saveMaxValues();
	}

	private void updateSmartDashboard() {
		/*
		 * SmartDashBoard.putNumber("leftTargetSpeed", targetSpeedL);
		 * SmartDashBoard.putNumber("rightTargetSpeed", targetSpeedR);
		 * SmartDashBoard.putNumber("leftDrivePower",lPower);
		 * SmartDashBoard.putNumber("rightDrivePower",rPower);
		 * SmartDashBoard.putNumber("leftDriveAccel",currentAccelL);
		 * SmartDashBoard.putNumber("rightDriveAccel",currentAccelR);
		 * SmartDashBoard.putNumber("leftDriveSpeed",currentSpeedL);
		 * SmartDashBoard.putNumber("rightDriveSpeed",currentSpeedL);
		 * SmartDashBoard.putNumber("leftDriveJerk",jerkL);
		 * SmartDashBoard.putNumber("rightDriveJerk",jerkR);
		 */
	}
}
/*
// Gearing Procedures

public void toggleHighGear(){ isHighGear = !isHighGear; } 
public boolean getGearing(){ return isHighGear; } //UltraSonic Procedures private double
getUltraSonicVoltageData(){ return ultrasonic.getAverageVoltage(); }
public double getUltraSonicDistanceInches() {
		return ultraSonicDistance;
	}
*/


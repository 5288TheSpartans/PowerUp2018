/*----------------------------------------------------------------------------*/
 /* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;
//import org.usfirst.frc.team5288.robot.subsystems.Drivetrain.drivestates;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DrivetrainSubsystem extends Subsystem {
	//**DRIVETRAIN CONSTANTS**
	public final double wheelRadiusm = 0.0508; //meters
	public final double wheelcirc = 4*Math.PI; 
	public final double topSpeed =  3.048; // meters per second
	//*******************MOTOR CONTROLLER OBJECTS**************
	//These Motor controller objects will always be synced in pairs of output.
	private VictorSP lmotor1 = new VictorSP(RobotMap.LDriveMotor1);//Left Gearbox Motor #
	private VictorSP lmotor2 = new VictorSP(RobotMap.LDriveMotor2);//Left Gearbox Motor #
	private VictorSP rmotor1 = new VictorSP(RobotMap.RDriveMotor1);//Right Gearbox Motor #1
	private VictorSP rmotor2 = new VictorSP(RobotMap.RDriveMotor2);//Right Gearbox Motor #2
	private boolean isBrakeMode = true;
	//**Drive Variables**
	private double throttle = 1;
	private double lPower = 0;//Raw Power percentage being output to the left gearbox.
	private double rPower = 0;//Raw Power percentage being output to the right gearbox.
	//GYRO VARIABLES
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro(); 
	private final double gyroCorrectionValue = -1.9923625000000003 +1.9959375000000001;
	private double gyroCurrent = 0;
	private double gyroDifference= 0;
	private double gyroTotal = 0;
	private double lastGyro = 0;
	//**ULTRASONIC VARIABLES*
	//private AnalogInput ultrasonic = new AnalogInput(RobotMap.ultrasonicInput);
	private double ultraSonicDistance = 0;
	//**ENCODER VARIABLES*
	private Encoder rEncoder;
	private Encoder lEncoder;
	//**SPEED CALCULATION BASED VARIABLES**	//Encoder Tracking variables
	//Left
	private double lastSpeedL = 0;
	private double lastAccelL = 0;
	private double currentAccelL = 0;
	private double jerkL = 0;
	private double currentSpeedL = 0;
	private double targetAccelL = 0;
	private double targetSpeedL = 0;
	private double encLastL = 0;
	private double encCurrentL = 0;
	private double encDiffL = 0;
	//Right
	private double lastAccelR = 0;
	private double targetAccelR = 0;
	private double currentAccelR = 0;
	private double jerkR = 0;
	private double lastSpeedR = 0;
	private double currentSpeedR = 0;
	private double targetSpeedR = 0;
	private double encLastR = 0;
	private double encCurrentR = 0;
	private double encDiffR = 0;
	public DrivetrainSubsystem() {
		rEncoder = new Encoder(RobotMap.RDriveEncoder1,RobotMap.RDriveEncoder2,true,EncodingType.k4X);	
		lEncoder = new Encoder(RobotMap.LDriveEncoder1,RobotMap.LDriveEncoder2,false,EncodingType.k4X);	
		rEncoder.setMaxPeriod(5);
		lEncoder.setMaxPeriod(5);
		rEncoder.setMinRate(0);
		lEncoder.setMinRate(0);
		rEncoder.setSamplesToAverage(1);
		lEncoder.setSamplesToAverage(1);		
		rEncoder.setDistancePerPulse(wheelcirc/360);
		lEncoder.setDistancePerPulse(wheelcirc/2048);
		gyro.calibrate();
		
	// PID OBJECTS/VARIABLES
		SpartanPID drivetrainPID = new SpartanPID(0.0,0.0,0.0,0.0);
		
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new ManualDrive());
	}
	public double getGyroAngle(){
		return gyroTotal;
	}
	public void resetGyro(){
		gyro.reset();
	}
	public void resetEncoders(){
		lEncoder.reset();
		rEncoder.reset();
	}
	public void setLPower(double power){
		lPower = power;
	}
	public void setRPower(double power){
		rPower = power;
	}
	private void outputToMotors(double pwrLeft, double pwrRight){
		lmotor1.set(-pwrLeft);
		lmotor2.set(-pwrLeft);
		rmotor1.set(pwrRight);
		rmotor2.set(pwrRight);
	}
	public void setThrottle(double newThrottle){
		throttle = newThrottle;
	}
	public double getThrottle(){
		return throttle;
	}
	
	public double getLeftDistanceInches(){
		return lEncoder.getDistance();
	}
	public double getRightDistanceInches(){
		return rEncoder.getDistance()*5.57;
	}
	//Gearing Procedures
	/*public void toggleHighGear(){
		isHighGear =  !isHighGear;
	}
	public boolean getGearing(){
		return isHighGear;
	}
	//UltraSonic Procedures
	private double getUltraSonicVoltageData(){
		return ultrasonic.getAverageVoltage();
	}
	*/
	public double getUltraSonicDistanceInches(){
		return ultraSonicDistance;
	}
	private void updateSmartDashboard(){
    	/*
		SmartDashBoard.putNumber("leftTargetSpeed", targetSpeedL);
    	SmartDashBoard.putNumber("rightTargetSpeed", targetSpeedR);
    	Robot.table.putNumber("leftDrivePower",lPower);
    	Robot.table.putNumber("rightDrivePower",rPower);
    	Robot.table.putNumber("leftDriveAccel",currentAccelL);
    	Robot.table.putNumber("rightDriveAccel",currentAccelR);
    	Robot.table.putNumber("leftDriveSpeed",currentSpeedL);
    	Robot.table.putNumber("rightDriveSpeed",currentSpeedL);
    	Robot.table.putNumber("leftDriveJerk",jerkL);
    	Robot.table.putNumber("rightDriveJerk",jerkR);
    	Robot.table.putNumber("LeftEncoderDistance",getLeftDistanceMeters() );
    	Robot.table.putNumber("RightEncoderDistance",getLeftDistanceMeters() );
		 */
	}
}

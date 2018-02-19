/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5288.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5288.robot.commands.ManualDriveCommand;
import org.usfirst.frc.team5288.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team5288.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team5288.robot.subsystems.LeftRampSubsystem;
// import org.usfirst.frc.team5288.robot.commands.ExampleCommand;
// import org.usfirst.frc.team5288.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team5288.robot.subsystems.Lift;
import org.usfirst.frc.team5288.robot.subsystems.RightRampSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	// public static final ExampleSubsystem kExampleSubsystem
	//		= new ExampleSubsystem();
	public static OI m_oi;
	public static boolean leftLimitCondition;
	public static boolean rightLimitCondition;
	public static final Lift lift = new Lift();
	public static final IntakeSubsystem intake = new IntakeSubsystem();
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final RightRampSubsystem rightRamp = new RightRampSubsystem();
	public static final LeftRampSubsystem leftRamp = new LeftRampSubsystem();
	public static String gameData = DriverStation.getInstance().getGameSpecificMessage();
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
	}
	public static String getDashboardValue(String key) {
		return SmartDashboard.getString(key, "null");
	}
	
	public void robotPeriodic() {
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {

		leftLimitCondition = Robot.leftRamp.isLimitChecked();
		rightLimitCondition = Robot.rightRamp.isLimitChecked();
	//	Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void updateGameData() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		
		  String autoSelected = SmartDashboard.getString("Auto Selector", "Default"); 
		  switch(autoSelected) { 
		  case "My Auto": autonomousCommand	  = new MyAutoCommand(); 
		  break; 
		  case "Default Auto": default: autonomousCommand = new ExampleCommand();
		  break; }
		 

		// schedule the autonomous command (example)
		
		updateGameData();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		updateSensors();
		updateSubsystems();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		updateSensors();
		updateSubsystems();
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		updateSensors();
		updateSubsystems();
		updateSmartDashboard();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		updateSensors();
		updateSubsystems();
		Scheduler.getInstance().run();
	}
	
	public void updateSensors(){

		leftLimitCondition = Robot.leftRamp.isLimitChecked();
		rightLimitCondition = Robot.rightRamp.isLimitChecked();
		leftRamp.updateSensors();
		rightRamp.updateSensors();
		intake.updateSensors();
		Robot.lift.updateSensors();
	}
	public void updateSubsystems() {
		lift.updateOutputs();
		intake.updateOutputs();
		//drivetrain.updateOutputs();
		rightRamp.updateOutputs();
		leftRamp.updateOutputs();	
	}
	public static double getDashboardNumber(String key) {
		return SmartDashboard.getNumber(key, 0);
	}
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Left distance (inches)", Robot.drivetrain.getLeftDistanceInches());
		SmartDashboard.putNumber("Right distance (inches)", Robot.drivetrain.getRightDistanceInches());
		SmartDashboard.putNumber("Lift encoder position", Robot.lift.getEncoderPosition());
		SmartDashboard.putBoolean("Right ramp limit switch", Robot.rightRamp.isLimitChecked());
		SmartDashboard.putBoolean("Left ramp limit switch", Robot.leftRamp.isLimitChecked());
	}
	
}

package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualDriveCommand extends Command {

	double speedMultiplier = 1.0; double leftSpeedOutput = 0; double rightSpeedOutput = 0;
	
    public ManualDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("INITIALIZING MANUALDRIVECOMMAND.");
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.m_oi.getLeftStickY() >= RobotMap.JOYDEADZONE || Robot.m_oi.getLeftStickY() <= -RobotMap.JOYDEADZONE ) {
    		leftSpeedOutput = speedMultiplier * Robot.m_oi.getLeftStickY();
    		Robot.drivetrain.setLPower(leftSpeedOutput);
    		System.out.println("MANUAL LEFT ENCODER: " + Robot.drivetrain.getLeftDistanceInches());
    	}
    	 else {// if the joystick isn't being moved outside of the joystick dead zone, the robot does not move
    		Robot.drivetrain.setLPower(0.0);
    	 }
    	
    	if(Robot.m_oi.getRightStickY() >= RobotMap.JOYDEADZONE || Robot.m_oi.getRightStickY() <= -RobotMap.JOYDEADZONE ) {
    		rightSpeedOutput = speedMultiplier * Robot.m_oi.getRightStickY();
    		Robot.drivetrain.setRPower(rightSpeedOutput);
    		System.out.println("MANUAL RIGHT ENCODER: " + Robot.drivetrain.getRightDistanceInches());
    	} 
    	else { // if the joystick isn't being moved outside of the joystick dead zone, the robot does not move
    		Robot.drivetrain.setRPower(0.0);
    	}
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

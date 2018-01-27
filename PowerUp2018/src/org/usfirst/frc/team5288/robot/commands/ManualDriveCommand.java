package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualDriveCommand extends Command {

    public ManualDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.m_oi.getLeftStickX() >= Robot.m_oi.joyDeadzone || Robot.m_oi.getLeftStickX() <= Robot.m_oi.joyDeadzone ) {
    		
    		drivetrain.outputToLeftDrive(speedMultiplier * Robot.m_oi.getLeftStickX());
  
    	}
    	else // if the joystick isn't being moved outside of the joystick dead zone, the robot does not move
    		Robot.drivetrain.outputToLeftDrive(0.0);
    		
    		
    	if(Robot.m_oi.getLeftStickX() >= Robot.m_oi.joyDeadzone || Robot.m_oi.getRightStickX() <= Robot.m_oi.joyDeadzone ) {
    		
    		drivetrain.outputToRightDrive(speedMultiplier * Robot.m_oi.getRightStickX());
  
    	}
    	else // if the joystick isn't being moved outside of the joystick dead zone, the robot does not move
    		Robot.drivetrain.outputToRightDrive(0.0);
    
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

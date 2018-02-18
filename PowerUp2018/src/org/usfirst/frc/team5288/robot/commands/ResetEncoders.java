package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetEncoders extends Command {
	boolean reset = false;
    public ResetEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.resetEncoders();
    	reset = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return reset;
    }

    // Called once after isFinished returns true
    protected void end() {
    	reset = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

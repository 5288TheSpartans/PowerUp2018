package org.usfirst.frc.team5288.robot.autocommands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class DoNothingTime extends Command {


    //Command's Parameters for time , the endTime will
    private double startingTime = 0;
    private double elapsedTime = 0;
    private double targetTime = 0;
    
    public DoNothingTime(int timeMillis) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	targetTime = timeMillis;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startingTime = System.currentTimeMillis();
    	Robot.drivetrain.PIDInput = "WAITING FOR DELAY TO END";
    	elapsedTime = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	elapsedTime =System.currentTimeMillis() - startingTime;
        if(elapsedTime  >= targetTime)
        {
        	return true;
        }
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

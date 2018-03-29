package org.usfirst.frc.team5288.robot.commands.ramps;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseRampsOverride extends Command {
	private double servoPlantedAngle = 90;
	private boolean finishedOperation;
    public ReleaseRampsOverride() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftRamp);
    	requires(Robot.rightRamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Initializing ReleaseRampsOverride.");
    	Robot.leftRamp.setOverride(true);
    	Robot.rightRamp.setOverride(true);
    	Robot.leftRamp.setServo(servoPlantedAngle);
    	Robot.rightRamp.setServo(servoPlantedAngle);
    	finishedOperation = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(finishedOperation)
        return true;
        else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftRamp.setOverride(false);
    	Robot.rightRamp.setOverride(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

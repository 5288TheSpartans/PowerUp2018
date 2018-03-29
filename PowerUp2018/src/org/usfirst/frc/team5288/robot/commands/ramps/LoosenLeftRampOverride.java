package org.usfirst.frc.team5288.robot.commands.ramps;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LoosenLeftRampOverride extends Command {

    public LoosenLeftRampOverride() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftRamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Initializing LoosenLeftRampOverride");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.leftRamp.setOverride(true);
    	Robot.leftRamp.outputOverride(-0.2);
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
    	Robot.leftRamp.outputOverride(0.0);
    	Robot.leftRamp.setOverride(false);
       	System.out.println("LoosenLeftRampOverride interrupted.");
    }
}

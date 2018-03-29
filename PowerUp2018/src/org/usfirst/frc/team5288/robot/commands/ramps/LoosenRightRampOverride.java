package org.usfirst.frc.team5288.robot.commands.ramps;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LoosenRightRampOverride extends Command {

    public LoosenRightRampOverride() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rightRamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Initializing LoosenRightRampOverride.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rightRamp.setOverride(true);
    	Robot.rightRamp.outputToRightRamp(-0.5);
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
    	Robot.rightRamp.outputToRightRamp(0.0);
    	Robot.rightRamp.setOverride(false);
    	System.out.println("LoosenRightRampOverride interrupted.");
    }
}

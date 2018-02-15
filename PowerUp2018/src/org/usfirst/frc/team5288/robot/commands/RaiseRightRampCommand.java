package org.usfirst.frc.team5288.robot.commands;



import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseRightRampCommand extends Command {

    public RaiseRightRampCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rightRamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("RaiseRightRampCommand initialized.");
    	Robot.rightRamp.outputToRightRamp(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.rightRamp.outputToRightRamp(0.65);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        	Robot.rightRamp.updateSensors();
        	if(Robot.rightLimitCondition) {
        		return false;
        	}
        	else {
        		System.out.println("Limit switch activated");
        		return true;
        	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rightRamp.outputToRightRamp(0.0);
    	System.out.println("Turning off motor due to limit switch trigger.");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    Robot.rightRamp.outputToRightRamp(0.0);
    System.out.println("RaiseRightRampCommand interrupted.");
    }
}

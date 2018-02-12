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
    	if(Robot.rightRamp.checkRLimitSwitch())
    		System.out.println("Right limit switch is triggered. Will not raise right ramp further.");
    	else Robot.rightRamp.outputToRightRamp(-0.65);
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
    System.out.println("RaiseRightRampCommand interrupted.");
    }
}

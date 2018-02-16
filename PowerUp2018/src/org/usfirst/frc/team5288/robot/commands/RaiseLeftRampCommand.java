package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RaiseLeftRampCommand extends Command {

    public RaiseLeftRampCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftRamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Initializing RaiseLeftRampCommmand.");
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.leftRamp.updateSensors();
    	if(Robot.leftLimitCondition) {	
    	Robot.leftRamp.outputToLeftRamp(-0.65);
    		}
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.leftRamp.updateSensors();
    	if(Robot.leftLimitCondition) {
    		return false;
    	} else return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftRamp.outputToLeftRamp(0.0);
    //	System.out.println("Turning off motor due to limit switch trigger.");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.leftRamp.outputToLeftRamp(0.0);
    	System.out.println("RaiseLeftRampCommand interrupted.");

}
}
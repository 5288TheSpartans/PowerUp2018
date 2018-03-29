package org.usfirst.frc.team5288.robot.commands.ramps;



import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.RightRampSubsystem.state;

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
    	System.out.println("Initializing RaiseRightRampCommand.");
 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rightRamp.setState(state.planted);
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
    	Robot.rightRamp.setState(state.neutral);
    	System.out.println("RaiseRightRampCommand interrupted.");
    }
}

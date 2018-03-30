package org.usfirst.frc.team5288.robot.commands.ramps;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.RightRampSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseRightRampCommand extends Command {
	double currentTime = 0;
	double startingTime = 0;
	boolean operationFinished = false;
    public ReleaseRightRampCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rightRamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rightRamp.setState(RightRampSubsystem.state.planted);
    //	currentTime = System.currentTimeMillis();
    //	startingTime = System.currentTimeMillis();
    	System.out.println("ReleaseRightRampCommand.");
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //	currentTime = System.currentTimeMillis();
    //	if(currentTime >= startingTime + 1000) operationFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    //	Robot.rightRamp.setState(RightRampSubsystem.state.neutral);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    //	Robot.rightRamp.setState(RightRampSubsystem.state.neutral);
    }
}

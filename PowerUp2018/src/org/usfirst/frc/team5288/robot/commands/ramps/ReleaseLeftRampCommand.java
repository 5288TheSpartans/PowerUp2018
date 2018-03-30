package org.usfirst.frc.team5288.robot.commands.ramps;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.LeftRampSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseLeftRampCommand extends Command {

    public ReleaseLeftRampCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftRamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
     	Robot.leftRamp.setState(LeftRampSubsystem.state.planted);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftRamp.setState(LeftRampSubsystem.state.neutral);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

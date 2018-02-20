package org.usfirst.frc.team5288.robot.commands.ramps;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.LeftRampSubsystem;
import org.usfirst.frc.team5288.robot.subsystems.RightRampSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseRamps extends Command {

    public ReleaseRamps() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.rightRamp);
        requires(Robot.leftRamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.leftRamp.setState(LeftRampSubsystem.state.planted);
    	Robot.rightRamp.setState(RightRampSubsystem.state.planted);
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
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.leftRamp.setState(LeftRampSubsystem.state.planted);
    	Robot.rightRamp.setState(RightRampSubsystem.state.planted);
    }
}

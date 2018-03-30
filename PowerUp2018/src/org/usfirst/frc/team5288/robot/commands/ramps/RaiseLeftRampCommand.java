package org.usfirst.frc.team5288.robot.commands.ramps;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.LeftRampSubsystem.state;

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
    	Robot.leftRamp.setState(state.planted);
    	Robot.leftRamp.outputToLeftRamp(-1.0);
    	
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
    	Robot.leftRamp.outputToLeftRamp(0.0);
    	System.out.println("RaiseLeftRampCommand interrupted.");
    }
}
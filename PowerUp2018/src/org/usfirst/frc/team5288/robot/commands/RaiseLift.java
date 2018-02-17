package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.Lift;
import org.usfirst.frc.team5288.robot.subsystems.Lift.liftState;
import org.usfirst.frc.team5288.robot.subsystems.LeftRampSubsystem.state;

import com.ctre.phoenix.motorcontrol.NeutralMode;

//import org.usfirst.frc.team5288.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseLift extends Command {
    public RaiseLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.setState(liftState.raising);
    	System.out.println("RaiseLift command initialized.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    	Robot.lift.setState(liftState.stopped);
    	System.out.println("RaiseLift command interrupted.");
    }
}

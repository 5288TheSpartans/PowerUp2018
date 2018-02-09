package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResistLiftWeight extends Command {
	
	
	SpartanPID liftResistPID;
    public ResistLiftWeight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	liftResistPID = new SpartanPID(0,0,0,0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Starting ResistLiftWeight.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.outputToLift(liftResistPID.getOutput());
    	
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
    	System.out.println("Lift weight resist interrupted.");
    }
}

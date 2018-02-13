package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ResistLiftWeight extends Command {
	
	
	SpartanPID liftResistPID;
	double initialHeight;
	double currentHeight;
	double deltaHeight;// current - initial
    public ResistLiftWeight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	liftResistPID = new SpartanPID(0.005,0,0.001,0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//
    	initialHeight = Robot.lift.getEncoderPosition();
    	currentHeight = initialHeight;
    	deltaHeight = 0;
    	liftResistPID.setTarget(0);
    	 
    	System.out.println("Starting ResistLiftWeight.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentHeight = Robot.lift.getEncoderPosition();
    	deltaHeight = currentHeight - initialHeight;
    	liftResistPID.update(deltaHeight);
    	Robot.lift.outputToLift(liftResistPID.getOutput());
    	//	Robot.lift.outputToLift(0);
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
    	//TODO: Try too not set numbers to 0
    	Robot.lift.outputToLift(0.0);
    }
}

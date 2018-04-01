package org.usfirst.frc.team5288.robot.commands.lift;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.Lift.liftState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftToHeightTime extends Command {
	private double initialTime;
	private double currentTime;
	private double timePassed = 0;
	private double [] possibleHeights = {0,25,100}; // SWITCH, PORTAL, SCALE heights
	private double [] timeToLift = {0,1000,4000}; // SWITCH,PORTAL,SCALE times
	private double correctTimeToLift = 0;
    private int wantedHeight = 0;
	public LiftToHeightTime(int height) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    	wantedHeight = height;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialTime = System.currentTimeMillis();
    	for(int i = 0; i < timeToLift.length; i++) {
    		if(wantedHeight == possibleHeights[i])
    			correctTimeToLift = timeToLift[i];
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentTime = System.currentTimeMillis();
    	timePassed = currentTime - initialTime;
    	Robot.lift.setState(liftState.raising);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timePassed >= correctTimeToLift) return true;
    	else return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.setState(liftState.stopped);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

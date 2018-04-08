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
	private double [] possibleHeights = {0,Robot.switchHei,Robot.scaleHei}; // BOTTOM, SWITCH, SCALE heights
	private double [] timeToLift = {0,1500,4000}; // BOTTOM, SWITCH, SCALE times
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
    	if(correctTimeToLift != 0)Robot.lift.setState(liftState.raising);
    	else if(correctTimeToLift == 0) Robot.lift.setState(liftState.lowering);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timePassed >= correctTimeToLift && correctTimeToLift != 0) {
    		System.out.println("LiftToHeightTime to " + wantedHeight +" has finished.");
    		System.out.println("Ran for " + correctTimeToLift + " milliseconds.");
    		return true;
    	}
    	else if(correctTimeToLift == 0 && Robot.lift.isAtBottom()) {
    		
    		System.out.println("Lift has reached bottom. Ending LiftToHeightTime.");
    		return true;
    	}
    	if(timePassed >= 12000) {
    		System.out.println("LiftToHeightTime has timed out. How ironic; something's wrong.");
    		return true;
    	}
        return false;
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

package org.usfirst.frc.team5288.robot.commands.lift;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.Lift.liftState;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftToHeight extends Command {
	
	
	SpartanPID liftResistPID;
	double initialHeight;
	//double deltaHeight;// current - initial
	double wantedHeight;
	double currentHeightInches = 0;
	double initialTime = 0;
	double currentTime = 0;
	double deltaTime = 0;
	int[] milliSecondsPerHeight = {1000,2000,3000}; // scale + switch to 0, 0 to scale, 0 to switch
	int[] possibleHeights = {0,40,80};
	double matchedTime = 0;
	public LiftToHeight(double height) {

    	requires(Robot.lift);
    	liftResistPID = new SpartanPID(0.20,0,0.02,0.1);
    	wantedHeight = height;
    	
    }

    protected void initialize() {
    	if (wantedHeight == 0){
    		matchedTime = milliSecondsPerHeight[0];
    	}
    	else if(wantedHeight >= 70) {
    		matchedTime = milliSecondsPerHeight[2];
    	}
    	else if(wantedHeight >= 40) {
    		matchedTime = milliSecondsPerHeight[1];
    		
    	}
    	initialTime = System.currentTimeMillis();
    	currentTime = initialTime;
    	deltaTime = currentTime;
    /*	//
    	initialHeight = Robot.lift.getLiftHeight();
    	currentHeightInches = initialHeight;
    	//deltaHeight = 0;
    	liftResistPID.setTarget(wantedHeight);
    	Robot.lift.setState(liftState.PID); 
    	System.out.println("Sending");
    */}

    
    /*
     * You have an array of x length containing height. 
     * you have a matching array of y = x +1 length containing matching times.
     * It given height z, choose the height directly larger than z such that
     */
    protected void execute() {
    	currentTime = System.currentTimeMillis();
    	currentHeightInches = Robot.lift.getLiftHeight();
    	deltaTime = currentTime - initialTime;
    /*	currentHeightInches = Robot.lift.getLiftHeight();
    	//deltaHeight = currentHeight - initialHeight;
    	liftResistPID.update(currentHeightInches);
    	System.out.println(liftResistPID.getOutput());
    	currentHeightInches = Robot.lift.getLiftHeight();
    	if(currentHeightInches > wantedHeight + 2) {
    		Robot.lift.outputToLift(-0.4);
    	}
    	else {
        	Robot.lift.outputToLift(liftResistPID.getOutput());
    	}
    */}

    protected boolean isFinished() {
    	if(deltaTime >= matchedTime) {
    		return true;
    	}
    	else return false;
    	/*if(currentHeightInches <= wantedHeight+1 && currentHeightInches >= wantedHeight-1) {
    		Robot.lift.setState(liftState.stopped);
    		return true;
    	}
    	else return false;
    */}

    protected void end() {
    	Robot.lift.setState(liftState.stopped);
    }

    protected void interrupted() {

    }
}

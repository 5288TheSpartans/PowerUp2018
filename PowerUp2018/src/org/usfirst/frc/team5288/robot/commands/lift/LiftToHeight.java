package org.usfirst.frc.team5288.robot.commands.lift;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;
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
	double deltaHeight = 0;
	public LiftToHeight(double height) {

    	requires(Robot.lift);
    	System.out.println("Starting LiftToHeight Command, lifting to: " + height);
    	liftResistPID = new SpartanPID(RobotMap.LiftP,RobotMap.LiftI,RobotMap.LiftD,RobotMap.LiftFF);
    	wantedHeight = height;
    	liftResistPID.setTarget(wantedHeight);
    	
    	
    }

    protected void initialize() {
/*    	if (wantedHeight == 0){
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
    */	//
    	initialTime = System.currentTimeMillis();
    	initialHeight = Robot.lift.getLiftHeight();
    	currentHeightInches = initialHeight;
    	//deltaHeight = 0;
    	liftResistPID.setTarget(wantedHeight);
    	Robot.lift.setState(liftState.PID); 
    
    }

    
    /*
     * You have an array of x length containing height. 
     * you have a matching array of y = x +1 length containing matching times.
     * It given height z, choose the height directly larger than z such that
     */
    protected void execute() {
    	currentTime = System.currentTimeMillis();
    	currentHeightInches = Robot.lift.getLiftHeight();
    	deltaTime = currentTime - initialTime;
    	deltaHeight = currentHeightInches - initialHeight;
    	liftResistPID.update(deltaHeight);
    	System.out.println(liftResistPID.getOutput());
    	Robot.lift.outputToLift(liftResistPID.getOutput());
    	
    }

    protected boolean isFinished() {
    /*	if(deltaTime >= matchedTime) {
    		return true;
    	}
    	else return false;
    	*/if(currentHeightInches <= wantedHeight+1 && currentHeightInches >= wantedHeight-1) {
    		Robot.lift.setState(liftState.stopped);
    		return true;
    	}
    	else if(deltaTime >= 4000) {
    		System.out.println("Ending LiftToHeight due to timeout.");
    		return true;
    	}
    	else return false;
    }

    protected void end() {
    	Robot.lift.setState(liftState.stopped);
    }

    protected void interrupted() {

    }
}

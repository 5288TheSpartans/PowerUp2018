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
	double currentHeight;
	double deltaHeight;// current - initial
	double wantedHeight;
    public LiftToHeight(double height) {

    	requires(Robot.lift);
    	liftResistPID = new SpartanPID(0.20,0,0.001,0);
    	wantedHeight = height;
    }

    protected void initialize() {
    	//
    	initialHeight = Robot.lift.getEncoderPosition();
    	currentHeight = initialHeight;
    	deltaHeight = 0;
    	liftResistPID.setTarget(wantedHeight);
    	Robot.lift.setState(liftState.PID); 
    	System.out.println("Sending");
    }

    protected void execute() {
    	currentHeight = Robot.lift.getEncoderPosition();
    	deltaHeight = currentHeight - initialHeight;
    	liftResistPID.update(deltaHeight);
    	Robot.lift.outputToLift(liftResistPID.getOutput());
    	
    }

    protected boolean isFinished() {
    	if(currentHeight <= wantedHeight+1 && currentHeight >= wantedHeight-1) {
    		Robot.lift.setState(liftState.stopped);
    		return true;
    	}
    	else return false;
    }

    protected void end() {
    }

    protected void interrupted() {

    }
}

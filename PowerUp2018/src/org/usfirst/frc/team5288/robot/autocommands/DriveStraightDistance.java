package org.usfirst.frc.team5288.robot.autocommands;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightDistance extends Command {

    double error;
	double speed;
    private double startingDistance = 0;
    private double inWantedDistance = 0;

    private SpartanPID PID = new SpartanPID(RobotMap.P, RobotMap.I, RobotMap.D, RobotMap.FF);
    private SpartanPID distancePID = new SpartanPID(1/7,0.4,0.24,0);
    
    public DriveStraightDistance(double distance) {
    	requires(Robot.drivetrain);
    	inWantedDistance = distance;//Set the distance being searched for.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	startingDistance = getCurrentDistance();
    	PID.setTarget(0);//PID in error
    	distancePID.setTarget(inWantedDistance);
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       	distancePID.setTarget(inWantedDistance);

    	Robot.drivetrain.PIDInput = "" + (getCurrentDistance() - startingDistance);
    	distancePID.update(getCurrentDistance() - startingDistance);
    	speed = distancePID.getOutput();
    	Robot.drivetrain.PIDOutput = "DistancePID : " +  distancePID.getOutput();
    	error = PID.getOutput();
    	if(inWantedDistance  -  getCurrentDistance()  >= 24)
    	{
    		speed = 0.6;
    	}
    	
    	System.out.println("PID INPUT(distance): " +  getCurrentDistance());
    	//Output using the distanceScalar*(Maxspeed + error), effectively scaling down both the speed and the error.
    	Robot.drivetrain.setLPower(-speed- error);
    	Robot.drivetrain.setRPower(-speed+ error);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean rv = false;
    	
    	if(inWantedDistance > 0)
    	{
    		rv = getCurrentDistance() - startingDistance >=  inWantedDistance - .25;
    	}
    	else{
    		rv = getCurrentDistance() - startingDistance <= inWantedDistance + .25;
    	}
    	if(rv)
    	{

        	Robot.drivetrain.PIDInput = "command done";
    		Robot.drivetrain.setLPower(0);
    		Robot.drivetrain.setRPower(0);
    	}return rv;
    }
    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setLPower(0);
    	Robot.drivetrain.setRPower(0);
    }
    private double getCurrentDistance() {
        return (Robot.drivetrain.getLeftDistanceInches());// + Robot.drivetrain.getRightDistanceInches()) / 2;
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}

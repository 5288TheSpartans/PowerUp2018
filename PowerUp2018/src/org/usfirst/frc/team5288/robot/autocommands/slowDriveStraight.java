package org.usfirst.frc.team5288.robot.autocommands;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class slowDriveStraight extends Command {
	Preferences prefs;
    double error;
	double speed;
    private double startingDistance = 0;
    private double inWantedDistance = 0;
    private double distancetravelled = 0;
    private long deltaTime = 0;
    private long startTime = 0;
    private long currentTime = 0;
    private SpartanPID PID = new SpartanPID(RobotMap.StraightP, RobotMap.StraightI, RobotMap.StraightD, RobotMap.StraightFF);
    private SpartanPID distancePID = new SpartanPID(RobotMap.DistanceP,RobotMap.DistanceI,RobotMap.DistanceD,RobotMap.DistanceFF);
    //private SpartanPID distancePID = new SpartanPID(1/7,0.4,0.24,0);
    
    public slowDriveStraight(double distance) {
    	requires(Robot.drivetrain);
    	inWantedDistance = distance;//Set the distance being searched for.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	startingDistance = getCurrentDistance();
    	PID.setTarget(0);//PID in error
    	System.out.println("SLOW Drive Straight Distance initialized: " + inWantedDistance);
    	distancePID.setTarget(inWantedDistance);
    	startTime = System.currentTimeMillis();
    	currentTime = startTime;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Robot has driven " + distancetravelled + "distance during command.");
    	currentTime = System.currentTimeMillis();
    	deltaTime = currentTime - startTime;
    	distancetravelled = getCurrentDistance() - startingDistance;
    	Robot.drivetrain.PIDInput = "" + (distancetravelled);
    	distancePID.update(distancetravelled);
    	PID.update(Robot.drivetrain.getLeftDistanceInches() - Robot.drivetrain.getRightDistanceInches());
    	error = PID.getOutput();
    	speed = distancePID.getOutput();
    	if(speed > 0.1) speed = 0.1;
    	System.out.println("Speed: " + speed + "\nError: " + error);
    	System.out.println("PID INPUT(distance): " +  getCurrentDistance());
    	//Output using the distanceScalar*(Maxspeed + error), effectively scaling down both the speed and the error.
    	Robot.drivetrain.setLPower(-speed- error);
    	Robot.drivetrain.setRPower(-speed+ error);
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean rv = false;
    	rv = distancetravelled <= inWantedDistance - 0.5 && distancetravelled >= inWantedDistance + 0.5;
    	
    	/*	
     * if(inWantedDistance > 0)
    	{
    		rv = distancetravelled >=  inWantedDistance - .25;
    	}
    	else{
    		rv = distancetravelled <= inWantedDistance + .25;

    	}*/
    	if( deltaTime >= 15000) {
    		System.out.println("SlowDriveStraight cancelled due to timeout.");
    		return true;
    	}
    	if(rv)
    	{
    		System.out.println("SlowDriveStraight finished.");
        	Robot.drivetrain.PIDInput = "SlowDriveStraight finished.";
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
        return ((Robot.drivetrain.getLeftDistanceInches()+ Robot.drivetrain.getRightDistanceInches()) / 2);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}

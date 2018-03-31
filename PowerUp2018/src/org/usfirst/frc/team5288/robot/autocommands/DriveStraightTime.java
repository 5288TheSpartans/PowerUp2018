package org.usfirst.frc.team5288.robot.autocommands;

import org.usfirst.frc.team5288.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightTime extends Command {

   	private double currentTime = 0;
	private double startingTime = 0;
	private double deltaTime = 0;
	private double driveTime = 0;
    public DriveStraightTime(double timeMillis) {
    	 // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    		requires(Robot.drivetrain);
    		driveTime = timeMillis;
    }
       
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentTime = System.currentTimeMillis();
    	startingTime = currentTime;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentTime = System.currentTimeMillis();
    	Robot.drivetrain.setLPower(-0.5);
    	Robot.drivetrain.setRPower(-0.5);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	if(currentTime - startingTime >= driveTime)
    	return true;
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    Robot.drivetrain.setLPower(0);
    Robot.drivetrain.setRPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    System.out.println("drivestraighttime interrupted.");
    }
}

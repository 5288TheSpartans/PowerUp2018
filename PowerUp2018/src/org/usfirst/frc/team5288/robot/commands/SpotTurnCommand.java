package org.usfirst.frc.team5288.robot.commands;

import org.usfirst.frc.team5288.robot.Robot;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpotTurnCommand extends Command {
	private double targetAngle, startTime, initialAngle, currentAngle;
	private double turnSpeed = 0.5;
	SpartanPID turnPID = new SpartanPID(0,0,0,0);
    public SpotTurnCommand(double turnDegree) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	targetAngle = turnDegree;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    System.out.println("INITIALZING SPOT TURN COMMAND. TURNING " + targetAngle + "DEGREES.");
    startTime = System.currentTimeMillis();
    Robot.drivetrain.resetGyro();
    initialAngle = Robot.drivetrain.getGyroAngle();
    System.out.println("INITIAL ANGLE: " + initialAngle + "DEGREES.");
    turnPID.setTarget(targetAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.drivetrain.getGyroAngle() - initialAngle;
    	turnPID.update(currentAngle);
    	
    	Robot.drivetrain.setLPower(-turnSpeed );
    	Robot.drivetrain.setRPower(turnSpeed );
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(currentAngle <= (targetAngle -1) || currentAngle >= (targetAngle + 1))	return false;
        		else return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setLPower(0.0);
    	Robot.drivetrain.setRPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

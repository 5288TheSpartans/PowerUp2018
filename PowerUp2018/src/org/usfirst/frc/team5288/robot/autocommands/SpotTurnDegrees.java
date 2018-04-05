package org.usfirst.frc.team5288.robot.autocommands;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpotTurnDegrees extends Command {
	public double speed = 0.25;
	public final double gain = 0;
	private double currentTurn = 0;
	private double initialangle = 0;
	private double targetAngle = 0;
	private double PIDOUTPUT = 0;
	private SpartanPID PID = new SpartanPID(RobotMap.TurnP,RobotMap.TurnI , RobotMap.TurnD, RobotMap.TurnFF);
	private double startTime = 0;
	public SpotTurnDegrees(double turn) {
		requires(Robot.drivetrain);
		targetAngle = turn;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetGyro();
		startTime = System.currentTimeMillis();
		initialangle = Robot.drivetrain.getGyroAngle();
		PID.setTarget(targetAngle);//PID in turn
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		currentTurn = Robot.drivetrain.getGyroAngle() - initialangle;
		PID.update(currentTurn);
		
		System.out.println("TURN PID INPUT : " + currentTurn);
		System.out.println("TURN PID Target : " + targetAngle);
		System.out.println("TURN PID OUTPUT: " + PIDOUTPUT);
		System.out.println("SPEED: " + speed);
		if(currentTurn > targetAngle - 1 && currentTurn < targetAngle + 1)
		{	System.out.println("RUN.");
			PIDOUTPUT = 0;
		}
		else
		{
			PIDOUTPUT = PID.getOutput();
		}
		Robot.drivetrain.setLPower(-speed*PIDOUTPUT);
		Robot.drivetrain.setRPower(speed*PIDOUTPUT);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(Math.abs(targetAngle)<= Math.abs(currentTurn) + 1)
		{	System.out.println("SpotTurn finished. Current Turn: " + currentTurn);
			return true;
		}
		if(System.currentTimeMillis()>= startTime + 7000)
		{
			System.err.println("Command did not achieve goal, ended through time.");
			return true;

		}
		else
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.setLPower(0);
		Robot.drivetrain.setRPower(0);
	}
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to runa
	protected void interrupted() {
	}

}

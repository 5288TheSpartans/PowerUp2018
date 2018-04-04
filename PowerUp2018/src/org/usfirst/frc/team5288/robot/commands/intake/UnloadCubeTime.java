package org.usfirst.frc.team5288.robot.commands.intake;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.subsystems.IntakeSubsystem.intakeState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UnloadCubeTime extends Command {
	double initialTime = 0;
	double currentTime = 0;
	double timePassed = 0;
	double timeOutConstant = 2000;
    public UnloadCubeTime() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Running UnloadCubeTime");
    	initialTime = System.currentTimeMillis();
    	currentTime = initialTime;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setOverride(false);
    	currentTime = System.currentTimeMillis();
    	timePassed = currentTime - initialTime;
    	Robot.intake.setIntakeState(intakeState.outtakeLow);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timePassed >= timeOutConstant) return true;
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.setIntakeState(intakeState.stopped);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

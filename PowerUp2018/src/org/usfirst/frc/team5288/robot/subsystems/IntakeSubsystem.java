package org.usfirst.frc.team5288.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5288.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP lIntake = new VictorSP(RobotMap.lIntakeMotor);
	private VictorSP rIntake = new VictorSP(RobotMap.rIntakeMotor);
	public enum state{};
	private state currentState;
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void outputToIntake(double power) {
    	lIntake.set(power);
    	rIntake.set(power);
    }
    
    public void setState(state a) {
    	currentState = a;
    }
}


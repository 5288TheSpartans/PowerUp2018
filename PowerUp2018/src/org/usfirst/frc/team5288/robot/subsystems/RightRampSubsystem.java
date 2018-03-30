package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RightRampSubsystem extends Subsystem {
	
	// Define right servo angles
	private final double servoInitialAngle = 0;
	private final double servoNeutralAngle = 120;
	private final double servoPlantedAngle = 120;
	// Define ramp & servo states
	public enum state {initial,neutral, planted};
	private state currentState;
	// Define motors
	private VictorSP rmotor1 = new VictorSP(RobotMap.RRampMotors);	//	Left ramp motors; PWM is split.
	private Servo rServo = new Servo(RobotMap.rRampServo);
	// Define limit switch
	private DigitalInput rLimitSwitch = new DigitalInput(RobotMap.RRampLimitSwitch);
	
	private boolean limitSwitchStatus;
	private boolean isOverride;
    private double rampMotorOutput = 1.0;
    private double rampLooseningOutput = -0.5;

	public	RightRampSubsystem() {
		currentState = state.initial;
	}
    public void initDefaultCommand() {
    }
    
    public void outputToRightRamp(double power) {
    	rampMotorOutput = power;
    }
    public void updateSensors() {
		limitSwitchStatus = rLimitSwitch.get();
	}
    public void updateOutputs() {
    	if(!isOverride) {
	    	if (currentState == state.planted){
	    		if(limitSwitchStatus){
			    	rmotor1.set(rampMotorOutput);
			    	rServo.setAngle(servoPlantedAngle);
		    		}
		    		else {
				    	rmotor1.set(0);
				    	rServo.setAngle(servoPlantedAngle);
		    		}
	    	}
	    	else if(currentState == state.neutral) {
	    		rmotor1.set(0);
		    	rServo.setAngle(servoNeutralAngle);
	    	}
	    	else if (currentState == state.initial) {
	    		rmotor1.set(0);
		    	rServo.setAngle(servoInitialAngle);
	    	}
    	}
    }
    public void setState(state newState) {
    	currentState = newState;
    }
    public boolean isLimitChecked() {
    	return limitSwitchStatus;
    }
    public void setOverride(boolean rightRampOverride) {
    	isOverride = rightRampOverride;
    }
    public boolean getOverride() {
    	return isOverride;
    }
    public void setServo(double angle) {
    	rServo.setAngle(angle);
    }
    public void outputOverride(double output) {
    	rmotor1.set(output);
    }
}


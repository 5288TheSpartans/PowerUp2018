package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;
import org.usfirst.frc.team5288.robot.commands.ramps.DoNothingRightRampCommand;

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
	private final double servoPlantedAngle = 100;//90 working
	// Define ramp & servo states
	public enum state {initial, planted};
	private state currentState = state.initial;
	// Define motors
	private VictorSP rmotor1 = new VictorSP(RobotMap.RRampMotors);	//	Left ramp motors; PWM is split.
	private Servo rServo = new Servo(RobotMap.rRampServo);
	// Define limit switch
	private DigitalInput rLimitSwitch = new DigitalInput(RobotMap.RRampLimitSwitch);
	
	private boolean limitSwitchStatus;
	private boolean isOverride;
    private double rampMotorOutput = 0;
    private double rampLooseningOutput = -0.5;


	public	RightRampSubsystem() {
		
	}
    public void initDefaultCommand() {
    	setDefaultCommand(new DoNothingRightRampCommand());
    	
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
		    	rServo.setAngle(servoPlantedAngle);

	    		if(limitSwitchStatus){
			    	rmotor1.set(rampMotorOutput);
		    		}
		    		else {
				    	rmotor1.set(0);
		    		}
	    	}
	    	else if (currentState == state.initial) {
	    		rmotor1.set(0);
	    		rampMotorOutput = 0;
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


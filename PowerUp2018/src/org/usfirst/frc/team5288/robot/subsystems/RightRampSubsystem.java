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
		private final double servoInitialAngle = 0;
		private final double servoNeutralAngle = 0;
		private final double servoPlantedAngle = 0;
		public enum state {initial,neutral, planted};
		
		private state currentState;
		// Define motors
		private VictorSP rmotor1 = new VictorSP(RobotMap.RRampMotors);	//	Left ramp motors; PWM is split.
		private Servo rServo = new Servo(RobotMap.rRampServo);
		// Define limit switch
		private DigitalInput rLimitSwitch = new DigitalInput(RobotMap.RRampLimitSwitch);
		private boolean limitSwitchStatus;
	    private double rampMotorOutput = 0;

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
	    	if (currentState == state.planted){
		    	rmotor1.set(rampMotorOutput);
		    	rServo.set(servoPlantedAngle);
	    	}
	    	else if(currentState == state.neutral) {
	    		rmotor1.set(0);
		    	rServo.set(servoPlantedAngle);
	    	}
	    	else if (currentState == state.initial) {
	    		rmotor1.set(0);
		    	rServo.set(servoInitialAngle);
	    	}
	    }
	    public void setState(state newState) {
	    	
	    }
	    public boolean isLimitChecked() {
	    	return limitSwitchStatus;
	    }
}


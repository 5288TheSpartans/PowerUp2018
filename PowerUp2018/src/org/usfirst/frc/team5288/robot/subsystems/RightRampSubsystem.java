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
	
		private state currentState;
		private enum state {initial, moving, planted, raising};
		// Define motors
		private VictorSP rmotor1 = new VictorSP(RobotMap.RRampMotors);	//	Left ramp motors; PWM is split.
		private Servo lRelease = new Servo(RobotMap.lRampServo);
		// Define limit switch
		private DigitalInput rLimitSwitch = new DigitalInput(RobotMap.RRampLimitSwitch);
		private boolean limitSwitchStatus;
	    // Put methods for controlling this subsystem
	    // here. Call these from Commands.
		
		public	RightRampSubsystem() {
			currentState = initial;
		}
		
	    public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	    	//setDefaultCommand(new MySpecialCommand());
	    
	    }
	    public void outputToRightRamp(double power) {
	    	rmotor1.set(power);
	    }
	    public void updateSensors() {
			limitSwitchStatus = rLimitSwitch.get();
		}
	    public boolean isLimitChecked() {
	    	return limitSwitchStatus;
	    }
}


package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LeftRampSubsystem extends Subsystem {

		private enum state {initial, moving, planted, raising} ;
		// Define motors
		private VictorSP lmotor1 = new VictorSP(RobotMap.LRampMotors);	//	Left ramp motors; PWM is split.
		private Servo lRelease = new Servo(RobotMap.LServo);
		// Define limit switch
		private DigitalInput Llimitswitch = new DigitalInput(RobotMap.LRampLimitSwitch);
	    boolean limitSwitchStatus;
		// Put methods for controlling this subsystem
	    // here. Call these from Commands.
		
		public LeftRampSubsystem() {
		}
		public void updateSensors() {
			limitSwitchStatus = Llimitswitch.get();
		}
		
	    public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	    	//setDefaultCommand(new MySpecialCommand());
	    
	    }
	    public void outputToLeftRamp(double power) {
	    	lmotor1.set(power);
	    }
	    public boolean isLimitChecked() {
	    	return limitSwitchStatus;
	    }
	    
    
}


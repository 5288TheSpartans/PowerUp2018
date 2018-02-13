package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RightRampSubsystem extends Subsystem {
	
		// Define motors
		private VictorSP rmotor1 = new VictorSP(RobotMap.RRampMotors);	//	Left ramp motors; PWM is split.
		
		// Define limit switch
		private DigitalInput rLimitSwitch = new DigitalInput(RobotMap.RRampLimitSwitch);
		
	    // Put methods for controlling this subsystem
	    // here. Call these from Commands.
		
		public	RightRampSubsystem() {
		}
		
	    public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	    	//setDefaultCommand(new MySpecialCommand());
	    
	    }
	    public void outputToRightRamp(double power) {
	    	rmotor1.set(power);
	    }
	    public boolean isLimitChecked() {
	    	return rLimitSwitch.get();
	    }
}


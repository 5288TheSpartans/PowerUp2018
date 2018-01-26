package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;
import edu.wpi.first.wpilibj.VictorSP;


import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private VictorSP liftmotor1 = new VictorSP(RobotMap.liftMotor);
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void outputToLift(double pwr) {
    	
    	liftmotor1.set(pwr);
    	
    }
}


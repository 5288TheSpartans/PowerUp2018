package org.usfirst.frc.team5288.robot.subsystems;

import org.usfirst.frc.team5288.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private VictorSP motor = new VictorSP(RobotMap.liftMotor);
	private Encoder encoder;
	private double power = 0;//Raw Power percentage being output to the lift
	private double lastSpeed = 0;
	private double lastAccel = 0;
	private double currentAccel = 0;
	private double jerk = 0;
	private double currentSpeed = 0;
	private double targetAccel = 0;
	private double targetSpeed = 0;
	private double encLast = 0;
	private double encCurrent = 0;
	private double encDiff = 0;
	//TODO get diameter
	private double wheelCirc = 0*Math.PI;
	
	public Lift() {
		//this has been changed assholes
		encoder = new Encoder(RobotMap.liftEncoderA, RobotMap.liftEncoderB, true, EncodingType.k4X);	
		encoder.setMaxPeriod(5);
		encoder.setMinRate(0);
		encoder.setSamplesToAverage(1);		
		encoder.setDistancePerPulse(wheelCirc/2048);
		//This means nothing whatsoever
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void resetEncoders(){
		encoder.reset();
	}
    
    public void setRPower(double liftPower){
		power = liftPower;
	}
    
    public void outputToLift(double pwr) {
    	motor.set(pwr);
    }
    
    public double getDistanceInches(){
		return encoder.getDistance();
	}
}


package org.usfirst.frc.team5288.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team5288.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.*;
/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Encoder liftEncoder = new Encoder();
	private TalonSRX LiftMotor = new TalonSRX(RobotMap.LiftMotor);
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
	//public static final ControlMode PercentOutput;
	
	public Lift() {
		
/*		encoder = new Encoder(RobotMap.liftEncoderA, RobotMap.liftEncoderB, true, EncodingType.k4X);	
		encoder.setMaxPeriod(5);
		encoder.setMinRate(0);
		encoder.setSamplesToAverage(1);		
		encoder.setDistancePerPulse(wheelCirc/2048);
*/		LiftMotor.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0,0);
		LiftMotor.setSensorPhase(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void resetEncoders(){
		encoder.reset();
	}
    
  
    public void outputToLift(double pwr) {
    	LiftMotor.set(ControlMode.PercentOutput,pwr);
    	SmartDashboard.putNumber("Lift encoder velocity:", LiftMotor.getSelectedSensorVelocity(0));
    }
    
    public double getDistanceInches(){
		return encoder.getDistance();
	}
}


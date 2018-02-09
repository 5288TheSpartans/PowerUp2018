package org.usfirst.frc.team5288.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team5288.robot.RobotMap;
import org.usfirst.frc.team5288.robot.commands.ResistLiftWeight;

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

	private TalonSRX LiftMotor = new TalonSRX(RobotMap.LiftMotor);
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
	// distance the shaft has spun is equal to the circumference divided by the total count (4096) times the current count.
	// distancePerEncoderTick = perimeter of the hex shaft/4096 
	// distancePassed = distancePerEncoderTick * encoderCount
	
	public Lift() {
		
/*		encoder = new Encoder(RobotMap.liftEncoderA, RobotMap.liftEncoderB, true, EncodingType.k4X);	
		encoder.setMaxPeriod(5);
		encoder.setMinRate(0);
		encoder.setSamplesToAverage(1);		
		encoder.setDistancePerPulse(wheelCirc/2048);
*/		LiftMotor.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0,0);
		LiftMotor.setSensorPhase(false);
		SmartDashboard.putNumber("Lift encoder velocity:", LiftMotor.getSelectedSensorVelocity(0));
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ResistLiftWeight());
    	
    }
    
    public void resetEncoders(){
		// encoder.reset(); does not work for TalonSRX encoders; they have their own methods
	}
    
  
    public void outputToLift(double pwr) {
    	LiftMotor.set(ControlMode.PercentOutput,pwr);
    	
    }
    public void setMode(boolean mode) {
    	if(mode) {
    		LiftMotor.setNeutralMode(NeutralMode.Brake);
    	}
    	else {
    		LiftMotor.setNeutralMode(NeutralMode.Coast);
    	}
    }
  //  public double getDistanceInches(){
		// return encoder.getDistance()}
}


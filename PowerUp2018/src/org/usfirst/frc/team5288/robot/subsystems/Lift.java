package org.usfirst.frc.team5288.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;
import org.usfirst.frc.team5288.robot.commands.lift.ResistLiftWeight;

import edu.wpi.first.wpilibj.DigitalInput;
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
    // here. Call these from Commands
	// declare talonSRX
	private TalonSRX LiftMotor = new TalonSRX(RobotMap.LiftMotor);
	
	// lift states
	public enum liftState {raising, PID, lowering, stopped};
	private liftState currentState;
	//
	private DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.LowerLiftLimit);
	private DigitalInput topLimitSwitch = new DigitalInput(RobotMap.UpperLiftLimit);
	private boolean isAtTop = false;
	private boolean isAtBottom = true;
	private int liftHeight = 0;
	// lift modes
	public enum liftMotorMode {brake, coast};
	private liftMotorMode currentMode;
	// lift outputs
	private double liftMotorRaisingOutput = 1.0;
	private double liftMotorLoweringOutput = -0.2; // needs to be 0.0 as this will let gravity make the lift fall
	private double liftMotorStoppedOutput = 0.0;
	private double liftPower;
	private double lastSpeed = 0;
	private double currentSpeed= 0;
	// Loop counting
	long lastTime;
	long currentTime; 
	
	public Lift() {
		LiftMotor.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Absolute, 0,0);
		LiftMotor.setSensorPhase(false);
		lastTime = System.nanoTime();
		currentTime = System.nanoTime();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ResistLiftWeight());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void resetEncoders(){
		// encoder.reset(); does not work for TalonSRX encoders; they have their own methods
	}
    
  
    public void outputToLift(double pwr) {
    	liftPower = pwr;
    	
    }
    public void updateOutputs() {
    	if(currentState == liftState.raising) {
    		setMode(liftMotorMode.coast);
    		if(!topLimitSwitch.get()) {
    		LiftMotor.set(ControlMode.PercentOutput, liftMotorRaisingOutput);
    		}
    		else
    		{
    			LiftMotor.set(ControlMode.PercentOutput, 0);
    		}
    	} 
    	else if(currentState == liftState.lowering) {
    		setMode(liftMotorMode.coast);
    		if(!bottomLimitSwitch.get()) {
        		LiftMotor.set(ControlMode.PercentOutput, liftMotorLoweringOutput);
        		}
        		else
        		{
        			LiftMotor.set(ControlMode.PercentOutput, 0);
        		}
    		// LiftMotor.set(ControlMode.PercentOutput, liftMotorLoweringOutput);
    	}
    	else if(currentState == liftState.stopped) {
    		setMode(liftMotorMode.brake);
    		LiftMotor.set(ControlMode.PercentOutput, liftMotorStoppedOutput);
    	}
    	else if(currentState == liftState.PID) {
    		
    		if(!bottomLimitSwitch.get()) {
    			setMode(liftMotorMode.coast);
    		LiftMotor.set(ControlMode.PercentOutput, liftPower);
    		}
    		else {
    		LiftMotor.set(ControlMode.PercentOutput,0.0);
    		}
    	}
    }    public void setState(liftState newState) {
    	currentState = newState;
    }
    public void setMode(liftMotorMode newMode) {
    	if(newMode == liftMotorMode.brake) {
    		LiftMotor.setNeutralMode(NeutralMode.Brake);
    	}
    	else if(newMode == liftMotorMode.coast){
    		LiftMotor.setNeutralMode(NeutralMode.Coast);
    	}
    }
    public void updateSensors() {
    	isAtTop = topLimitSwitch.get();
    	isAtBottom = bottomLimitSwitch.get();
    	if(isAtTop && isAtBottom) {
    		//IDK
    	}
    	else if (isAtBottom) {
    		liftHeight = 0;//Inches
    	}
    	else if(isAtTop) {
    		liftHeight = 84;

    	}
    	lastTime = currentTime;
    	currentTime = System.nanoTime();
    	lastSpeed = currentSpeed;
    	currentSpeed = LiftMotor.getSelectedSensorVelocity(0);
    	liftHeight += (currentTime - lastTime)*((lastSpeed + currentSpeed)/2);
    	System.out.println("LiftHeight recorded to be:"  + liftHeight);
    }
    
    public double getEncoderPosition(){
    	SmartDashboard.putNumber("Lift encoder velocity: ", LiftMotor.getSelectedSensorVelocity(0));
		System.out.println("Lift encoder Position: " + LiftMotor.getSelectedSensorPosition(0));
		return LiftMotor.getSelectedSensorPosition(0);
    }
}


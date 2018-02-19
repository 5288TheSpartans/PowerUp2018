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

	// lift modes
	public enum liftMotorMode {brake, coast};
	private liftMotorMode currentMode;
	// lift outputs
	private double liftMotorRaisingOutput = 1.0;
	private double liftMotorLoweringOutput = -0.2; // needs to be 0.0 as this will let gravity make the lift fall
	private double liftMotorStoppedOutput = 0.0;
	private double liftPower;
	
	
	
	public Lift() {
		LiftMotor.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Absolute, 0,0);
		LiftMotor.setSensorPhase(false);
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
    
    public double getEncoderPosition(){
    	SmartDashboard.putNumber("Lift encoder velocity:", LiftMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Lift encoder Position:", LiftMotor.getSelectedSensorPosition(0));
		return LiftMotor.getSelectedSensorPosition(0);
    }
  //  public double getDistanceInches(){
		// return encoder.getDistance()}
}


package org.usfirst.frc.team5288.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;
import org.usfirst.frc.team5288.robot.commands.lift.ReleaseLift;
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
	// Objects
	private TalonSRX LiftMotor = new TalonSRX(RobotMap.LiftMotor);
	private DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.BottomLiftLimit);
	private DigitalInput topLimitSwitch = new DigitalInput(RobotMap.TopLiftLimit);

	// Lift states
	public enum liftState {
		raising, PID, lowering, stopped, falling
	};

	// Falling: Disabling the fact that the gearbox locks and letting string unwind.
	// coast mode enabled.
	private liftState currentState;

	// LiftPosition variables
	private boolean isAtTop = false;
	private boolean isAtBottom = true;
	private boolean override = false;
	private double liftHeight = 0;
	private double maxLiftHeight = 18*10; // inches

	// lift modes
	public enum liftMotorMode {
		brake, coast
	};

	// lift outputs
	private final double liftMoveMultiplier = 0.8;
	private final double liftMotorRaisingOutput = 1.0;
	private final double liftMotorLoweringOutput = -1.0; 
	private final double liftMotorStoppedOutput = 0.01;
	private final double liftMotorFallingOutput = -0.01;
	// Stuff for finding Height
	private double liftPower = 0;
	private double lastSpeed = 0;
	private double currentSpeed = 0;
	private long lastTime;
	private long currentTime;
	private final double radius = 3 / 4; // (inches)
	private final double ratio = 1;
	private final double heightConstant = 0.007364;//ratio * 4096 / (6 * radius);
	// Used to convert from units to inches where 1u = 1/4096 =
	// avgPerimeter(in)/4096
	// => in = 1*u*4096/(avgPerimeter)

	public Lift() {
		LiftMotor.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0,
				0);
		LiftMotor.setSensorPhase(false);
		lastTime = System.nanoTime();
		currentTime = System.nanoTime();
	}

	public void initDefaultCommand() {
	//	setDefaultCommand(new ReleaseLift());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void resetEncoders() {
		LiftMotor.setSelectedSensorPosition(0, 0, 0);
		// encoder.reset(); does not work for TalonSRX encoders; they have their own
		// methods
	}

	public void outputToLift(double pwr) {
		liftPower = pwr;

	}

	public void updateOutputs() {
		if(!override) {
			if (currentState == liftState.raising) {
				setMode(liftMotorMode.coast);
				setLiftPower(liftMotorRaisingOutput);
				
				if (!isAtTop) {
					setLiftPower(liftMotorRaisingOutput);
				} else {
					setLiftPower(liftMotorStoppedOutput);
					setMode(liftMotorMode.brake);
				}
			} else if (currentState == liftState.lowering) {
				setMode(liftMotorMode.coast);
				if (!isAtBottom) {
					setLiftPower(liftMotorLoweringOutput);
				} else {
					setMode(liftMotorMode.brake);
					setLiftPower(liftMotorStoppedOutput);
				}
			} else if (currentState == liftState.stopped) {
				LiftMotor.set(ControlMode.PercentOutput, 0.0);
				setMode(liftMotorMode.brake);
		
			} else if (currentState == liftState.falling) {
				setMode(liftMotorMode.coast);
				LiftMotor.set(ControlMode.PercentOutput, liftMotorFallingOutput);
			} else if (currentState == liftState.PID) {
				if (isAtBottom && liftPower < 0) {
					setMode(liftMotorMode.brake);
					setLiftPower(liftMotorStoppedOutput);
				} else if(isAtTop && liftPower > 0){
				//	setMode(liftMotorMode.brake);
					setLiftPower(liftMotorStoppedOutput);
				}
				else {
					setMode(liftMotorMode.coast);
					setLiftPower(liftPower);
				}
			}
		}else if(override) {
			Robot.lift.setLiftPower(liftPower);
		}
	}

	public void setState(liftState newState) {
		currentState = newState;
	}

	public void setMode(liftMotorMode newMode) {
		if (newMode == liftMotorMode.brake) {
			LiftMotor.setNeutralMode(NeutralMode.Brake);
		} else if (newMode == liftMotorMode.coast) {
			LiftMotor.setNeutralMode(NeutralMode.Coast);
		}
	}

	public void updateSensors() {
		isAtTop = isAtTop(); // If top limit switch is remounted, use !topLimitSwitch.get()
		isAtBottom = !bottomLimitSwitch.get();
		if (isAtTop && isAtBottom) {
			// IDK what to say if they are both hit, we have an issue.
			System.out.println("ISSUE IN LIFT UPDATE SENSORS.");
		} 
		if (isAtBottom) {
			liftHeight = 0;// Inches
			LiftMotor.setSelectedSensorPosition(0, 0, 0);

	//		LiftMotor.setSelectedSensorPosition(0, 0, 0);
	//		System.out.println("Lift height reset to 0 because at bottom.");
		} else if (isAtTop) {
			liftHeight = maxLiftHeight;
			System.out.println("IS AT TOP.");


		}
		calculateLiftHeightClean();
		Robot.putDashboardNumber("Lift Height", liftHeight);
		Robot.putDashboardNumber("SketchyLiftHeight = ", liftHeight);
		SmartDashboard.putBoolean("Top Limit Switch", !isAtTop);
		SmartDashboard.putBoolean("Bottom Limit Switch", isAtBottom);

	}
	
	private void setLiftPower(double output) {
		LiftMotor.set(ControlMode.PercentOutput, liftMoveMultiplier*output);
	}

	private void calculateLiftHeightClean() {
		liftHeight = LiftMotor.getSelectedSensorPosition(0) * heightConstant;
	//	System.out.println("LiftHeight recorded to be:" + liftHeight);

	}

	public double getLiftHeight() {
		calculateLiftHeightClean();
		return liftHeight;
	}

	public double getEncoderPosition() {
		SmartDashboard.putNumber("Lift encoder velocity: ", LiftMotor.getSelectedSensorVelocity(0));
	//	System.out.println("Lift encoder Position: " + LiftMotor.getSelectedSensorPosition(0));
		return LiftMotor.getSelectedSensorPosition(0);
	}
	private boolean isAtTop() {
		return getLiftHeight() >= maxLiftHeight;
	}
	public boolean isAtBottom() {
		return isAtBottom;
	}
	public void setOverride(boolean liftOverride) {
		override = liftOverride;
	}
	public void outputOverride(double power) {
		if(override) {
			setLiftPower(power);
		}
	}
}

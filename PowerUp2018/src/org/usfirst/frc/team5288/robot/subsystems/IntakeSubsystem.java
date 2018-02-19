package org.usfirst.frc.team5288.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5288.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP lIntake = new VictorSP(RobotMap.lIntakeMotor);
	private VictorSP rIntake = new VictorSP(RobotMap.rIntakeMotor);
	public enum cubeState{hasCube,noCube};
	public enum intakeState{intake,outtake,stopped};
	private cubeState currentCubeState;
	private intakeState currentIntakeState;
	private boolean hasCube;
	private boolean override;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void outputToIntake(double power) {
    	lIntake.set(power);
    	rIntake.set(power);
    }
    
    public void setOverride(boolean intakeOverride) {
    	override = intakeOverride;
    }
    public void setCubeState(cubeState newState) {
    	currentCubeState = newState;
    }
    
    public void setIntakeState(intakeState newState) {
    	currentIntakeState = newState;
    }
    public void updateSensors() {
    // placeholder intake sensor; sense if cube is there or not
    }
    public void updateOutputs() {
    	updateCubeState();
    	if(!override) {
    		// if the commands are being overridden, then don't use updateSubsystems()
    		// at all, as the outputs will be manually set in override commands
    		if(currentIntakeState == intakeState.intake && hasCube ) {
    		outputToIntake(-1.0);
    		}
    		else if(currentIntakeState == intakeState.outtake && !hasCube) {
    		outputToIntake(1.0);
    		}
    		else if(currentIntakeState == intakeState.stopped) {
    		outputToIntake(0.0);
    		}
    	}
    }
    
}


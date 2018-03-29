/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5288.robot;

import org.usfirst.frc.team5288.robot.autocommands.DriveStraight;

import org.usfirst.frc.team5288.robot.autocommands.SpotTurnDegrees;
import org.usfirst.frc.team5288.robot.commands.ResetEncoders;
import org.usfirst.frc.team5288.robot.commands.lift.LiftToHeight;
import org.usfirst.frc.team5288.robot.commands.lift.LowerLift;
import org.usfirst.frc.team5288.robot.commands.lift.RaiseLift;
import org.usfirst.frc.team5288.robot.commands.lift.ResistLiftWeight;
import org.usfirst.frc.team5288.robot.commands.ramps.LoosenLeftRampOverride;
import org.usfirst.frc.team5288.robot.commands.ramps.LoosenRightRampOverride;
import org.usfirst.frc.team5288.robot.commands.ramps.RaiseLeftRampCommand;
import org.usfirst.frc.team5288.robot.commands.ramps.RaiseRightRampCommand;
import org.usfirst.frc.team5288.robot.commands.ramps.ReleaseRamps;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	 // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    Joystick joystickLeft = new Joystick(0);    //left joystick is in port 1
    Joystick joystickRight = new Joystick(1);    //right joystick is in port 0
    Joystick xbox = new Joystick(2);
    
 	private JoystickButton
 					btnL1 = new JoystickButton(joystickLeft,1),
 					btnL2 = new JoystickButton(joystickLeft,2),
					btnL3 = new JoystickButton(joystickLeft,3),
					btnL4 = new JoystickButton(joystickLeft,4),
					btnL5 = new JoystickButton(joystickLeft,5),
					btnL6 = new JoystickButton(joystickLeft,6),
 					btnL7 = new JoystickButton(joystickLeft,7),
 					btnL8 = new JoystickButton(joystickLeft,8),
 					btnL9 = new JoystickButton(joystickLeft,9),
 				btnR1 = new JoystickButton(joystickRight,1),
				btnR2 = new JoystickButton(joystickRight,2),
				btnR3 = new JoystickButton(joystickRight,3),
				btnR4 = new JoystickButton(joystickRight,4),
				btnR5 = new JoystickButton(joystickRight,5),
				btnR6 = new JoystickButton(joystickRight,6),
				btnR7 = new JoystickButton(joystickRight,7),
				btnR8 = new JoystickButton(joystickRight,8),
				btnR9 = new JoystickButton(joystickRight,9),
 				btnR10 = new JoystickButton(joystickRight,10),
 		 		btnR11 = new JoystickButton(joystickRight,11); 
 
 	private JoystickButton 
 		xboxA = new JoystickButton(xbox,1),//Coloured buttons
 		xboxB = new JoystickButton(xbox,2),//Coloured buttons
 		xboxY = new JoystickButton(xbox,4),//Coloured buttons
 		xboxX = new JoystickButton(xbox,3),//Coloured buttons
 		xboxLB = new JoystickButton(xbox,5),//Left Bumper
 		xboxRB = new JoystickButton(xbox,6),//Right Bumper
 		xboxStart = new JoystickButton(xbox,8),//XboxStart buttons
 		xboxBack = new JoystickButton(xbox,7),//XboxBack buttons
 		xboxLStickButton = new JoystickButton(xbox, 9),
 		xboxRStickButton = new JoystickButton(xbox, 10);

    public OI()
    {
    
    btnL4.whileHeld(new RaiseLift());
    btnL3.whileHeld(new LowerLift());
    btnL5.whileHeld(new DriveStraight(0.2));
    btnL6.whenPressed(new ReleaseRamps());
    btnL8.whileHeld(new LoosenLeftRampOverride());
    btnL9.whileHeld(new LoosenRightRampOverride());
    btnL2.whenPressed(new SpotTurnDegrees(10));
    //btnL3.whileHeld(new ResistLiftWeight());
    btnR6.whenPressed(new ResetEncoders());
    btnL2.whenPressed(new LiftToHeight(5));
    btnL7.whenPressed(new RaiseLeftRampCommand());
    btnR3.whileHeld(new RaiseRightRampCommand());
    
    
    
    xboxA.whenPressed(new LiftToHeight(3));
    xboxB.whenPressed(new LiftToHeight(20));
    xboxLB.whileHeld(new LowerLift());
    xboxRB.whileHeld(new RaiseLift());
    /*    btnL9.whenPressed(new DriveStraightTime(4000));
 * 
 * 
 * 
    xboxLStickButton.toggleWhenPressed(new OuttakeBalls());
    xboxRStickButton.toggleWhenPressed(new IntakeBalls());
    xboxY.whileHeld(new RunClimber());
    xboxA.whenPressed(new ParkAndShoot());
    btnR2.whileHeld(new ShootFromDistance(2));
    //Shooter Commands
    
    btnR5.whileHeld(new RunClimber());
    btnR3.whileHeld(new ParkAndShoot());
   // btnR.whileHeld(new ShootUsingVariable());
    btnR4.toggleWhenPressed(new IntakeBalls());
   // xboxX.whileHeld(new LightsToYellow());
  */ // btnR1.toggleWhenPressed(new DriveStraight());
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.

    	
   
   //-----------------------------Code for returning Joystick Axis--------------------------
    
    }
    public double getXLY()
    {
    	return (-xbox.getRawAxis(2));
    }
    public double getXLX()
    {
    	return (xbox.getRawAxis(1));              
    }
    public double getXRY()
    {
    	return (-xbox.getRawAxis(5));
    }
    public double getXRX()
    {
    	return (xbox.getRawAxis(4));
    }
    public double getLeftStickY()
    {
     
    	return(joystickLeft.getY());//Returns the chosen axis specified by code.
    }
    public double getLeftStickX()
    {
    	return(joystickLeft.getX());//Returns the chosen axis specified by code.
    }
    //Right Joystick
    public double getRightStickY()
    {
    	return(joystickRight.getY());//Returns the chosen axis specified by code.
    }
    public double getRightStickX()
    {
    	return(joystickRight.getX());
    }
    public double getRightStickThrottle()
    {
    	return(joystickRight.getThrottle());
    }
}	



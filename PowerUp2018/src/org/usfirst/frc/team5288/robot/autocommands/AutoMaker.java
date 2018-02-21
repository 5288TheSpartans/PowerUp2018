package org.usfirst.frc.team5288.robot.autocommands;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;
import org.usfirst.frc.team5288.robot.commands.intake.UnloadCube;
import org.usfirst.frc.team5288.robot.commands.lift.LowerLift;
import org.usfirst.frc.team5288.robot.commands.lift.RaiseLift;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;;


public class AutoMaker extends CommandGroup {
	public AutoMaker() {
		int goal, spawn, switch_cf, scale_cf; //the 4 parameters

		goal = 0; //choose spawn location, 0 = left, 1 = center, 2 = right
		
		spawn = 0; //choose goal, 0 = cross auto line, 1 = switch ownership, 2 = scale ownership
		
		char fms[] = new char[3]; //read in fms data
		
		for (int o=0; 0<3; o++) {
			fms[o] = Robot.gameData.charAt(o);
		}
		
		/*
		for (int n=0; n<2; n++) {  //setting cf variables
			
			char temp = fms[n];
			
			if (('L'==temp) & (n==0)) {
				switch_cf = 0; //switch side is left
			} else if (('R'==temp) & (n==0)) {
				switch_cf = 1; //switch side is right
			} if (('L'==temp) & (n==1)) {
				scale_cf = 0; //scale side is left	
			} else if (('R'==temp) & (n==1)) {
				scale_cf = 1; //scale side is right
			}
		
		}
		
		//auto selection
		if ((goal==0) & ((spawn==0) | (spawn==2))) {
			//left or right spawn, cross line goal (0.0 / 2.0)
			addSequential(new DriveStraightDistance(192));
			
		} else if (goal==1) {
			
			if ((spawn==0) & (switch_cf==0)) {
				//left spawn, left switch, to pile (0.1.0.0)
				addSequential(new DriveStraightDistance(174));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(48));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				addSequential(new LowerLift(30));
				//unique for vers=0
				addSequential(new SpotTurnDegrees(180));
				addSequential(new DriveStraightDistance(10));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(60));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(60));
			
			} else if ((spawn==0) & (switch_cf==0) & (vers==1)) {
				//left spawn, left switch, to portal (0.1.0.1)
				addSequential(new DriveStraightDistance(174));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(40));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				addSequential(new LowerLift(30));
				//unique for vers=1
				addSequential(new SpotTurnDegrees(180));
				addSequential(new DriveStraightDistance(72));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(468));
				
			} else if ((spawn==0) & (switch_cf==1)) {
				//left spawn, right switch, to pile (0.1.1.0)
				addSequential(new SpotTurnDegrees(45));
				addSequential(new DriveStraightDistance(216));
				addSequential(new SpotTurnDegrees(-135));
				addSequential(new DriveStraightDistance(40));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				addSequential(new LowerLift(30));
				//unique for vers=0
				addSequential(new SpotTurnDegrees(180));
				addSequential(new DriveStraightDistance(10));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(60));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(60));
				
			} else if ((spawn==0) & (switch_cf==1) & (vers==1)) {
				//left spawn, right switch, to portal (0.1.1.1)
				addSequential(new SpotTurnDegrees(45));
				addSequential(new DriveStraightDistance(216));
				addSequential(new SpotTurnDegrees(-135));
				addParallel(new DriveStraightDistance(40));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				addSequential(new LowerLift(30));
				//unique for vers=1
				addSequential(new SpotTurnDegrees(180));
				addSequential(new DriveStraightDistance(72));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(468));
				
			}
			
			
			if ((spawn==1) & (switch_cf==0)) {
				//center spawn, left switch, to pile (1.1.0.0)
				addSequential(new SpotTurnDegrees(-45));
				addSequential(new DriveStraightDistance(236));
				addSequential(new SpotTurnDegrees(135));
				addParallel(new DriveStraightDistance(100));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				//unique for vers=0
				addParallel(new SpotTurnDegrees(180));
				addParallel(new LowerLift(30));
				addSequential(new DriveStraight(12));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(60));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(60));
			
			} else if ((spawn==1) & (switch_cf==0) & (vers==1)) {
				//center spawn, left switch, to portal (1.1.0.1)
				addSequential(new SpotTurnDegrees(-45));
				addSequential(new DriveStraightDistance(236));
				addSequential(new SpotTurnDegrees(135));
				addParallel(new DriveStraightDistance(100));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				//unique for vers=1
				addParallel(new LowerLift(30));
				addParallel(new SpotTurnDegrees(180));
				addSequential(new DriveStraight(72));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(468));
				
			 else if ((spawn==1) & (switch_cf==1)) {
				//center spawn, right switch, to pile (1.1.1.0)
				addSequential(new SpotTurnDegrees(45));
				addSequential(new DriveStraightDistance(236));
				addSequential(new SpotTurnDegrees(-135));
				addParallel(new DriveStraightDistance(100));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				//unique for vers=0
				addParallel(new SpotTurnDegrees(180));
				addParallel(new LowerLift(30));
				addSequential(new DriveStraight(12));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(60));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(60));
				
			} else if ((spawn==1) & (switch_cf==1) & (vers==1)) {
				//center spawn, right switch, to portal (1.1.1.1)
				addSequential(new SpotTurnDegrees(45));
				addSequential(new DriveStraightDistance(236));
				addSequential(new SpotTurnDegrees(-135));
				addParallel(new DriveStraightDistance(100));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				//unique for vers=1
				addParallel(new LowerLift(30));
				addParallel(new SpotTurnDegrees(180));
				addSequential(new DriveStraight(72));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(468));
				
			}
			
			
			if ((spawn==2) & (switch_cf==0)) {
				//right spawn, left switch, to pile (2.1.0.0)
				addSequential(new SpotTurnDegrees(45));
				addSequential(new DriveStraightDistance(216));
				addSequential(new SpotTurnDegrees(-135));
				addSequential(new DriveStraightDistance(40));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				addSequential(new LowerLift(30));
				//unique for vers=0
				addSequential(new SpotTurnDegrees(180));
				addSequential(new DriveStraightDistance(10));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(60));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(60));
				
			} else if ((spawn==1) & (switch_cf==0) & (vers==1)) {
				//right spawn, left switch, to portal (2.1.0.1)
				addSequential(new SpotTurnDegrees(45));
				addSequential(new DriveStraightDistance(216));
				addSequential(new SpotTurnDegrees(-135));
				addParallel(new DriveStraightDistance(40));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				addSequential(new LowerLift(30));
				//unique for vers=1
				addSequential(new SpotTurnDegrees(180));
				addSequential(new DriveStraightDistance(72));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(468));
				
				
				
			} else if ((spawn==1) & (switch_cf==1) ) {
				//right spawn, right switch, to pile (2.1.1.0)
				addSequential(new DriveStraightDistance(174));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(48));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				addSequential(new LowerLift(30));
				//unique for vers=0
				addSequential(new SpotTurnDegrees(180));
				addSequential(new DriveStraightDistance(10));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(60));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(60));
				
			} else if ((spawn==1) & (switch_cf==1) & (vers==1)) {
				//right spawn, right switch, to portal (2.1.1.1)
				addSequential(new DriveStraightDistance(174));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(40));
				addParallel(new RaiseLift(30));
				addSequential(new UnloadCube());
				addSequential(new LowerLift(30));
				//unique for vers=1
				addSequential(new SpotTurnDegrees(180));
				addSequential(new DriveStraightDistance(72));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(468));
				
			}
			
		} else if (goal==2) {
			
			if ((spawn==0) & (scale_cf==0)) {
				//left spawn, left scale, to pile (0.2.0.0)
				addSequential(new DriveStraightDistance(324));
				addSequential(new SpotTurnDegrees(90));
				addParallel(new DriveStraightDistance(30));
				addParallel(new RaiseLift(72));
				addSequential(new UnloadCube());
				addParallel(new LowerLift(72));
				//unique for vers=0
				addParallel(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(174));
				addSequential(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(84));
				
			} else if ((spawn==0) & (scale_cf==0) & (vers==1)) {
				//left spawn, left scale, to portal (0.2.0.1)
				addSequential(new DriveStraightDistance(324));
				addSequential(new SpotTurnDegrees(90));
				addParallel(new DriveStraightDistance(30));
				addParallel(new RaiseLift(72));
				addSequential(new UnloadCube());
				addParallel(new LowerLift(72));
				//unique to vers=1
				addParallel(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(312));
				
			} else if ((spawn==2) & (scale_cf==1)) {
				//right spawn, right scale, to pile (2.2.1.0)
				addSequential(new DriveStraightDistance(324));
				addSequential(new SpotTurnDegrees(-90));
				addParallel(new DriveStraightDistance(30));
				addParallel(new RaiseLift(72));
				addSequential(new UnloadCube());
				addParallel(new LowerLift(72));
				//unique for vers=0
				addParallel(new SpotTurnDegrees(-90));
				addSequential(new DriveStraightDistance(174));
				addSequential(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(84));
				
			} else if ((spawn==2) & (scale_cf==1) & (vers==1)) {
				//right spawn, right scale, to portal (2.2.1.1)
				addSequential(new DriveStraightDistance(324));
				addSequential(new SpotTurnDegrees(-90));
				addParallel(new DriveStraightDistance(30));
				addParallel(new RaiseLift(72));
				addSequential(new UnloadCube());
				addParallel(new LowerLift(72));
				//unique for vers=1
				addParallel(new SpotTurnDegrees(90));
				addSequential(new DriveStraightDistance(312));
				
			} else {
				addSequential(new DriveStraightDistance(192));
			}
			
		} else {
			//what tf you trying to accomplish??
		}*/
	}
		
}
 
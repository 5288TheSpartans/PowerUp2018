package org.usfirst.frc.team5288.robot.autocommandGroups;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.autocommands.DoNothingTime;
import org.usfirst.frc.team5288.robot.autocommands.DriveStraightDistance;
import org.usfirst.frc.team5288.robot.autocommands.SpotTurnDegrees;
import org.usfirst.frc.team5288.robot.commands.intake.*;
import org.usfirst.frc.team5288.robot.commands.lift.LiftToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autoRightSidetoRightScale extends CommandGroup {

    public autoRightSidetoRightScale() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	// drive to scale, dump cube in scale
    	
    	addParallel(new ReleaseIntake());
    	addSequential(new DriveStraightDistance(252));
    	addSequential(new SpotTurnDegrees(-90));
    	addSequential(new DriveStraightDistance(39));
    	addSequential(new SpotTurnDegrees(90));
    	addSequential(new DriveStraightDistance(10));
    	addSequential(new LiftToHeight(Robot.scaleHei));
    	addSequential(new ShootCubeTime());
    	addParallel(new LiftToHeight(0));
    	addSequential(new SpotTurnDegrees(180));
    	addParallel(new DriveStraightDistance(60));
    	addSequential(new LoadCubeTime());
    	addSequential(new LoadCubeTime()); //Remove if one LoadCubeTime is enough to intake cube
    //Don't Know if this will work
    	addSequential(new DriveStraightDistance(-60));
    	addSequential(new SpotTurnDegrees(180));
    	addSequential(new LiftToHeight(Robot.scaleHei));
    	addSequential(new UnloadCubeTime());
    	addSequential(new LiftToHeight(0));
    	
    	
    	
    	
    	
    	
    	
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

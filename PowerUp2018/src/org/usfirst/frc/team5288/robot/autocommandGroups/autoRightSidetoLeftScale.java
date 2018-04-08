package org.usfirst.frc.team5288.robot.autocommandGroups;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.autocommands.*;
import org.usfirst.frc.team5288.robot.commands.intake.*;
import org.usfirst.frc.team5288.robot.commands.lift.LiftToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autoRightSidetoLeftScale extends CommandGroup {

    public autoRightSidetoLeftScale() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	
    	addParallel(new ReleaseIntake());
    	addSequential(new DriveStraightDistance(226));
    	addSequential(new SpotTurnDegrees(-90));
    	addSequential(new DriveStraightDistance(190));
    	addSequential(new SpotTurnDegrees(90));
    	addParallel(new LiftToHeight(Robot.scaleHei));
    	addSequential(new DriveStraightDistance(32));
    	addSequential(new ShootCubeTime());
    	addParallel(new LiftToHeight(0));
    	addSequential(new SpotTurnDegrees(180));
    	addParallel(new DriveStraightDistance(38));
    	addSequential(new LoadCubeTime());
    	addSequential(new LoadCubeTime()); // ReRuns code incase we do not inake cube
    //Do not know if this will work
    	addSequential(new DriveStraightDistance(-36));
    	addSequential(new SpotTurnDegrees(180));
    	addSequential(new LiftToHeight(Robot.scaleHei));
    	addSequential(new ShootCubeTime());
    	addSequential(new LiftToHeight(0));
    	
    	
    	
    	
    	

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

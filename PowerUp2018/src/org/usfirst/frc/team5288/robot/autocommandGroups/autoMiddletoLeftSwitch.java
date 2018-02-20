package org.usfirst.frc.team5288.robot.autocommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5288.robot.autocommands.*;
import org.usfirst.frc.team5288.robot.commands.lift.*;
import org.usfirst.frc.team5288.robot.commands.intake.*;

/**
 *
 */
public class autoMiddletoLeftSwitch extends CommandGroup {

    public autoMiddletoLeftSwitch() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new SpotTurnDegrees(-45));
    	addParallel(new LiftToHeight(25));
    	addSequential(new DriveStraightDistance(215));
    	addSequential(new SpotTurnDegrees(135));
    	addSequential(new DriveStraightDistance(65));
    	addSequential(new UnloadCube());
    	addSequential(new DriveStraightDistance(-30));
    	addSequential(new SpotTurnDegrees(-90));
    	addParallel(new LiftToHeight(0));
    	addSequential(new DriveStraightDistance(46));
    	addSequential(new SpotTurnDegrees(90));
    	addParallel(new LoadCube());
    	addSequential(new DriveStraightDistance(49));
    	
    		

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

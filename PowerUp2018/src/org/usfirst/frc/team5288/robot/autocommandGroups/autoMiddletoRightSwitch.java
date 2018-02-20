package org.usfirst.frc.team5288.robot.autocommandGroups;

import org.usfirst.frc.team5288.robot.autocommands.*;
import org.usfirst.frc.team5288.robot.commands.lift.*;
import org.usfirst.frc.team5288.robot.commands.intake.*;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autoMiddletoRightSwitch extends CommandGroup {

    public autoMiddletoRightSwitch() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	
    	addSequential(new SpotTurnDegrees(45));
    	addParallel(new LiftToHeight(25));
    	addSequential(new DriveStraightDistance(176));
    	addSequential(new SpotTurnDegrees(-45));
    	addSequential(new DriveStraightDistance(40));
    	addSequential(new SpotTurnDegrees(-90));
    	addSequential(new DriveStraightDistance(50));
    	addSequential(new UnloadCube());
    	addSequential(new DriveStraightDistance(-40));
    	addSequential(new SpotTurnDegrees(90));
    	addParallel(new LiftToHeight(0));
    	addSequential(new DriveStraightDistance(38));
    	addSequential(new SpotTurnDegrees(-90));
    	addParallel(new LoadCube());
    	addSequential(new DriveStraightDistance(41));

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

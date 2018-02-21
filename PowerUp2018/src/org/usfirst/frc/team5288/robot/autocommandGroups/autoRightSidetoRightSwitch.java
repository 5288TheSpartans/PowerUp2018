package org.usfirst.frc.team5288.robot.autocommandGroups;

import org.usfirst.frc.team5288.robot.autocommands.*;
import org.usfirst.frc.team5288.robot.commands.lift.*;
import org.usfirst.frc.team5288.robot.commands.intake.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autoRightSidetoRightSwitch extends CommandGroup {

    public autoRightSidetoRightSwitch() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addSequential(new DriveStraightDistance(180));
    	addSequential(new SpotTurnDegrees(-90));
    	addParallel(new LiftToHeight(25));
    	addSequential(new DriveStraightDistance(52));
    	addSequential(new UnloadCube());
    	addSequential(new DriveStraightDistance(-23));
    	addParallel(new LiftToHeight(0));
    	addSequential(new SpotTurnDegrees(90));
    	addSequential(new DriveStraightDistance(24));
    	addSequential(new SpotTurnDegrees(-90));
    	addParallel(new LoadCube());
    	addSequential(new DriveStraightDistance(40));
    	addSequential(new LoadCube());

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

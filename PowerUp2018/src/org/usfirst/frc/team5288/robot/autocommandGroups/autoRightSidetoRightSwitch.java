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
    	
    	addSequential(new DriveStraightDistance(200));
    	addSequential(new SpotTurnDegrees(-90));
    	addParallel(new LiftToHeight(30));
    	addSequential(new DriveStraightDistance(50));
    	addSequential(new UnloadCube());
    	addParallel(new LiftToHeight(0));
    	addSequential(new SpotTurnDegrees(90));
    	addSequential(new DriveStraightDistance(18));
    	addSequential(new SpotTurnDegrees(-90));
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

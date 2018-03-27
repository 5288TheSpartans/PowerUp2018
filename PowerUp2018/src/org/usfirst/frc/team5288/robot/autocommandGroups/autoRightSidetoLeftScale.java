package org.usfirst.frc.team5288.robot.autocommandGroups;

import org.usfirst.frc.team5288.robot.autocommands.*;
import org.usfirst.frc.team5288.robot.commands.intake.UnloadCube;
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
    	
    	addSequential(new DriveStraightDistance(236));
    	addSequential(new SpotTurnDegrees(-90));
    	addParallel(new LiftToHeight(100));
    	addSequential(new DriveStraightDistance(256));
    	addSequential(new SpotTurnDegrees(90));
    	addSequential(new DriveStraightDistance(77));
    	addSequential(new SpotTurnDegrees(90));
    	addSequential(new DriveStraightDistance(30));
    	addSequential(new UnloadCube());
    	addSequential(new LiftToHeight(0.0)); // min lift height
    	// now get back in position for switch cube
    	addParallel(new SpotTurnDegrees(90));
    	addSequential(new DriveStraightDistance(88));
    	addSequential(new SpotTurnDegrees(-90));
    	
    	
    	

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

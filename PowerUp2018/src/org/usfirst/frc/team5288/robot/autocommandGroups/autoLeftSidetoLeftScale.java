package org.usfirst.frc.team5288.robot.autocommandGroups;

import org.usfirst.frc.team5288.robot.autocommands.DriveStraightDistance;
import org.usfirst.frc.team5288.robot.autocommands.SpotTurnDegrees;
import org.usfirst.frc.team5288.robot.commands.intake.*;
import org.usfirst.frc.team5288.robot.commands.lift.LiftToHeight;
import org.usfirst.frc.team5288.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autoLeftSidetoLeftScale extends CommandGroup {
    public autoLeftSidetoLeftScale() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addParallel(new LiftToHeight(Robot.scaleHei)); // max lift height
    	addSequential(new DriveStraightDistance(320)); 
    	addSequential(new SpotTurnDegrees(90));
    	addSequential(new DriveStraightDistance(36));
    	addSequential(new UnloadCubeTime());
    	addSequential(new LiftToHeight(0.0)); // min lift height
    	// now get back in position for switch cube
    	addParallel(new SpotTurnDegrees(90));
    	addSequential(new DriveStraightDistance(88));
    	addSequential(new SpotTurnDegrees(-90));
    	
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

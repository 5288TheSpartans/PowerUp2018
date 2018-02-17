package org.usfirst.frc.team5288.robot.autocommands;

import org.usfirst.frc.team5288.robot.Robot;
import org.usfirst.frc.team5288.robot.RobotMap;

import accessories.SpartanPID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;;


public class AutoMaker extends CommandGroup {
	public AutoMaker() {
		int goal, spawn, switch_cf, scale_cf, vers; //the 5 parameters

		goal = 0; //choose spawn location, 0 = left, 1 = center, 2 = right
		
		spawn = 0; //choose goal, 0 = cross auto line, 1 = switch ownership, 2 = scale ownership
		
		vers = 0; //choose version, 0 = end at pile, 1 = end a portal
		
		char fms[] = new char[3]; //read in fms data
		//fms data code
		
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
			
			addSequential(new DriveStraightDistance(192));
			
		} else if (goal==1) {
			
			if ((spawn==0) & (switch_cf==0) & (vers==0)) {
				//left spawn, left switch, to pile
			} else if ((spawn==0) & (switch_cf==0) & (vers==1)) {
				//left spawn, left switch, to portal
			} else if ((spawn==0) & (switch_cf==1) & (vers==0)) {
				//left spawn, right switch, to pile
			} else if ((spawn==0) & (switch_cf==1) & (vers==1)) {
				//left spawn, right switch, to portal
			}
			
			
			if ((spawn==1) & (switch_cf==0) & (vers==0)) {
				//center spawn, left switch, to pile
			} else if ((spawn==1) & (switch_cf==0) & (vers==1)) {
				//center spawn, left switch, to portal
			} else if ((spawn==1) & (switch_cf==1) & (vers==0)) {
				//center spawn, right switch, to pile
			} else if ((spawn==1) & (switch_cf==1) & (vers==1)) {
				//center spawn, right switch, to portal
			}
			
			
			if ((spawn==2) & (switch_cf==0) & (vers==0)) {
				//right spawn, left switch, to pile
			} else if ((spawn==1) & (switch_cf==0) & (vers==1)) {
				//right spawn, left switch, to portal
			} else if ((spawn==1) & (switch_cf==1) & (vers==0)) {
				//right spawn, right switch, to pile
			} else if ((spawn==1) & (switch_cf==1) & (vers==1)) {
				//right spawn, right switch, to portal
			}
			
		} else if (goal==2) {
			
			if ((spawn==0) & (scale_cf==0) & (vers==0)) {
				//left spawn, left scale, to pile
			} else if ((spawn==0) & (scale_cf==0) & (vers==1)) {
				//left spawn, left scale, to portal
			} else if ((spawn==2) & (scale_cf==1) & (vers==0)) {
				//right spawn, right scale, to pile
			} else if ((spawn==2) & (scale_cf==1) & (vers==1)) {
				//right spawn, right scale, to portal
			}
			
		} else {
			//what tf you trying to accomplish??
		}
		
	}
	/*int goal, spawn, switch_cf, scale_cf, vers; //the 5 parameters

	goal = 0; //choose spawn location, 0 = left, 1 = center, 2 = right
	
	spawn = 0; //choose goal, 0 = cross auto line, 1 = switch ownership, 2 = scale ownership
	
	vers = 0; //choose version, 0 = end at pile, 1 = end a portal
	
	public 
	
	char fms[] = new char[2]; //read in fms data
	//fms data code
	
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
		
		DriveStraightDistance(192);
		
	} else if (goal==1) {
		
		if ((spawn==0) & (switch_cf==0) & (vers==0)) {
			//left spawn, left switch, to pile
		} else if ((spawn==0) & (switch_cf==0) & (vers==1)) {
			//left spawn, left switch, to portal
		} else if ((spawn==0) & (switch_cf==1) & (vers==0)) {
			//left spawn, right switch, to pile
		} else if ((spawn==0) & (switch_cf==1) & (vers==1)) {
			//left spawn, right switch, to portal
		}
		
		
		if ((spawn==1) & (switch_cf==0) & (vers==0)) {
			//center spawn, left switch, to pile
		} else if ((spawn==1) & (switch_cf==0) & (vers==1)) {
			//center spawn, left switch, to portal
		} else if ((spawn==1) & (switch_cf==1) & (vers==0)) {
			//center spawn, right switch, to pile
		} else if ((spawn==1) & (switch_cf==1) & (vers==1)) {
			//center spawn, right switch, to portal
		}
		
		
		if ((spawn==2) & (switch_cf==0) & (vers==0)) {
			//right spawn, left switch, to pile
		} else if ((spawn==1) & (switch_cf==0) & (vers==1)) {
			//right spawn, left switch, to portal
		} else if ((spawn==1) & (switch_cf==1) & (vers==0)) {
			//right spawn, right switch, to pile
		} else if ((spawn==1) & (switch_cf==1) & (vers==1)) {
			//right spawn, right switch, to portal
		}
		
	} else if (goal==2) {
		
		if ((spawn==0) & (scale_cf==0) & (vers==0)) {
			//left spawn, left scale, to pile
		} else if ((spawn==0) & (scale_cf==0) & (vers==1)) {
			//left spawn, left scale, to portal
		} else if ((spawn==2) & (scale_cf==1) & (vers==0)) {
			//right spawn, right scale, to pile
		} else if ((spawn==2) & (scale_cf==1) & (vers==1)) {
			//right spawn, right scale, to portal
		}
		
	} else {
		//what tf you trying to accomplish??
	}

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
	*/
	
	
}
 
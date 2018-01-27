package accessories;

public class SpartanPID {
	//Constants used in math
	private double P_GAIN = 0;
	private double I_GAIN = 0;
	private double D_GAIN = 0;
    private double F_GAIN = 0;
    //Maximums and minimums for calculation

    //Output  and math variables
	private double kP = 0;
	private double kI = 0;
	private double kD = 0;
	private double lastProportional = 0;
	private double lastTime = 0;
	private double currentTime = 0;
	private double timeDifference = 0;
	//CURRENT OUTPUT 
	private double target = 0;
	private double output = 0;
	public SpartanPID(double pGain, double iGain,double dGain,double fGain){
		P_GAIN = pGain;
				I_GAIN = iGain;
				D_GAIN = dGain;
				F_GAIN = fGain;
				target = 0;
	}
	public void setTarget(double newtarget){
		target = newtarget;
	}
	public void update(double input){
		lastTime = currentTime;
		currentTime = System.currentTimeMillis();
		timeDifference = currentTime - lastTime;
		lastProportional = kP;
		kP = P_GAIN*(target - input);
		kI += kP;
		kD = ((kP - lastProportional)/(timeDifference))*D_GAIN;
		output = kP + kI*I_GAIN - kD + F_GAIN*(input);

	}
	public double getOutput(){
		return output;
	}
}
package time;

public class MorningNightTimeOfUse extends VariableTimeOfUse {

	public MorningNightTimeOfUse(double[] clockTimesWhenIsUsed, double[] amountOfPowerOnHours) throws Exception {
		super(2, clockTimesWhenIsUsed, amountOfPowerOnHours);		
	}

	public double getMorningClockTimeWhenIsUsed() {
		return super.getFirstClockTimeWhenIsUsed();
	}
	
	public double getMorningAmountOfPowerOnHours() {
		return super.getFirstAmountOfPowerOnHours();
	}

	public double getNightClockTimeWhenIsUsed() {
		return super.getSecondClockTimeWhenIsUsed();
	}

	public double getNightAmountOfPowerOnHours() {
		return super.getSecondAmountOfPowerOnHours();
	}	
}
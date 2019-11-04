package time;

public class AllDayPeriodsTimeOfUse extends VariableTimeOfUse {

	public AllDayPeriodsTimeOfUse(double[] clockTimesWhenIsUsed, double[] amountOfPowerOnHours) throws Exception {
		super(clockTimesWhenIsUsed.length, clockTimesWhenIsUsed, amountOfPowerOnHours);
	}
	
	public double getMorningClockTimeWhenIsUsed() {
		return super.getFirstClockTimeWhenIsUsed();
	}
	
	public double getMorningAmountOfPowerOnHours() {
		return super.getFirstAmountOfPowerOnHours();
	}
	
	public double getNoonClockTimeWhenIsUsed() {
		return super.getSecondClockTimeWhenIsUsed();
	}
	
	public double getNoonAmountOfPowerOnHours() {
		return super.getSecondAmountOfPowerOnHours();
	}

	public double getNightClockTimeWhenIsUsed() {
		return super.getThirdClockTimeWhenIsUsed();
	}

	public double getNightAmountOfPowerOnHours() {
		return super.getThirdAmountOfPowerOnHours();
	}	
}
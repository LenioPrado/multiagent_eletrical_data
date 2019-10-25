package time;

public abstract class VariableTimeOfUse {

	private double[] _clockTimeWhenIsUsed;
	private double[] _amountOfPowerOnHours;
	
	public VariableTimeOfUse(int numberOfPeriods, double[] clockTimeWhenIsUsed, double[] amountOfPowerOnHours) throws Exception {
		_clockTimeWhenIsUsed = clockTimeWhenIsUsed;
		_amountOfPowerOnHours = amountOfPowerOnHours;
		
		String lenghtError = "Vector %s must have %d times usage values!!!";
		if (clockTimeWhenIsUsed == null || clockTimeWhenIsUsed.length != numberOfPeriods) {
			throw new Exception(String.format(lenghtError, "ClockTimeWhenIsUsed", numberOfPeriods));
		}
		
		if (amountOfPowerOnHours == null || amountOfPowerOnHours.length != numberOfPeriods) {
			throw new Exception(String.format(lenghtError, "AmountOfPowerOnHours", numberOfPeriods));
		}
		
		if (clockTimeWhenIsUsed.length != amountOfPowerOnHours.length) {
			throw new Exception("Vectors ClockTimeWhenIsUsed and AmountOfPowerOnHours must have the same sizes!!!");
		}
	}

	protected double getFirstClockTimeWhenIsUsed() {
		return _clockTimeWhenIsUsed[0];
	}
	
	protected double getFirstAmountOfPowerOnHours() {
		return _amountOfPowerOnHours[0];
	}

	protected double getSecondClockTimeWhenIsUsed() {
		return _clockTimeWhenIsUsed[1];
	}

	protected double getSecondAmountOfPowerOnHours() {
		return _amountOfPowerOnHours[1];
	}
	
	protected double getThirdClockTimeWhenIsUsed() {
		return _clockTimeWhenIsUsed[2];
	}
	
	protected double getThirdAmountOfPowerOnHours() {
		return _amountOfPowerOnHours[2];
	}	
}
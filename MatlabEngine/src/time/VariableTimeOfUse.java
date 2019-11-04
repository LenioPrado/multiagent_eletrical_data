package time;

public abstract class VariableTimeOfUse {

	private double[] _clockTimeWhenIsUsed;
	private double[] _amountOfPowerOnHours;
	
	public VariableTimeOfUse(int numberOfPeriods, double[] clockTimeWhenIsUsed, double[] amountOfPowerOnHours) throws Exception {
		_clockTimeWhenIsUsed = clockTimeWhenIsUsed;
		_amountOfPowerOnHours = amountOfPowerOnHours;
		
		if (numberOfPeriods <= 0) {
			throw new Exception("NumberOfPeriods must be non negative!!!");
		}
		
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
		return _clockTimeWhenIsUsed.length > 1 ? _clockTimeWhenIsUsed[1] : 1;
	}

	protected double getSecondAmountOfPowerOnHours() {
		return _amountOfPowerOnHours.length > 1 ? _amountOfPowerOnHours[1] : 0;
	}
	
	protected double getThirdClockTimeWhenIsUsed() {
		return _clockTimeWhenIsUsed.length > 2 ? _clockTimeWhenIsUsed[2] : 1;
	}
	
	protected double getThirdAmountOfPowerOnHours() {
		return _amountOfPowerOnHours.length > 2 ? _amountOfPowerOnHours[2] : 0;
	}	
}
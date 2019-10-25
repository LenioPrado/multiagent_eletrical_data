package time;

public class FixedTimeOfUse {

	private int _minutesOfUse;
	private double[] _clockTimesWhenIsUsed;
	
	public FixedTimeOfUse(int minutesOfUse, double[] clockTimesWhenIsUsed) throws Exception {
		_minutesOfUse = minutesOfUse;
		_clockTimesWhenIsUsed = clockTimesWhenIsUsed;
		
		if (clockTimesWhenIsUsed == null || clockTimesWhenIsUsed.length != 3) {
			throw new Exception("ClockTimesWhenIsUsed vector must have 3 times usage values");
		}
	}

	public int getMinutesOfUse() {
		return _minutesOfUse;
	}
	
	public double[] getClockTimesWhenIsUsed() {
		return _clockTimesWhenIsUsed;
	}

	public double getFirstTimeUsage() {
		return _clockTimesWhenIsUsed[0];
	}

	public double getSecondTimeUsage() {
		return _clockTimesWhenIsUsed[1];
	}

	public double getThirdTimeUsage() {
		return _clockTimesWhenIsUsed[2];
	}	
}